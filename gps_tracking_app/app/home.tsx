import React, { useEffect, useState } from 'react';
import { View, Text, ActivityIndicator, StyleSheet, Alert } from 'react-native';
import axiosInstance from '../lib/axiosInstance';
import AsyncStorage from '@react-native-async-storage/async-storage';
import * as Location from 'expo-location';

export default function HomeScreen() {
  const [user, setUser] = useState<any>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const id = await AsyncStorage.getItem('id');
        if (!id) throw new Error('User ID not found');

        const response = await axiosInstance.get(`api/doctors/${id}`);
        setUser(response.data);
      } catch (error) {
        console.error('Error fetching user data:', error);
      } finally {
        setLoading(false);
      }
    };

    const startLocationTimer = async () => {
      const { status } = await Location.requestForegroundPermissionsAsync();
      if (status !== 'granted') {
        Alert.alert('Permission denied', 'Location access is required.');
        return;
      }

      const sendLocation = async () => {
        try {
          const location = await Location.getCurrentPositionAsync({});
          const id = await AsyncStorage.getItem('id');
          if (!id) return;

          const gpsResponseStatus = await axiosInstance.post('/gps/validate', {
            latitude: location.coords.latitude,
            longitude: location.coords.longitude
          });
            
            console.log(gpsResponseStatus.data)
        } catch (err) {
          console.error('Failed to send GPS:', err);
        }
      };

      // Run immediately
      sendLocation();

      // Repeat every 30 minutes (1800000 ms)
      const interval = setInterval(sendLocation, 30 * 60 * 1000);

      return () => clearInterval(interval); // cleanup
    };

    fetchUserData();
    const cleanup = startLocationTimer();

    return () => {
      if (cleanup instanceof Function) cleanup();
    };
  }, []);

  if (loading) {
    return (
      <View style={styles.container}>
        <ActivityIndicator size="large" />
        <Text>Loading user data...</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Welcome, {user?.name || 'User'}!</Text>
      <Text>Email: {user?.email}</Text>
      <Text>Role: {user?.roles?.[0]?.name || 'N/A'}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  title: { fontSize: 24, marginBottom: 20 },
});
