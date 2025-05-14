import { useRouter } from 'expo-router';
import { View, Text, TextInput, Button, StyleSheet, Alert } from 'react-native';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AsyncStorage from '@react-native-async-storage/async-storage';
import Constants from 'expo-constants';
import { useAuth } from '../context/AuthContext';

export default function LoginScreen() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const router = useRouter();
  const { login } = useAuth();
  const apiUrl =  "http://192.168.209.174:8080";
  useEffect(() => {
    Alert.alert('Testing', 'Alert is working');
  }, []);

const handleLogin = async () => {
  try {
    console.log(`${apiUrl}/login`);
    const response = await axios.post(`${apiUrl}/login`, {
      email,
      password,
    });
      console.log(response);
    // Success case
    if(response.status === 200){
    const { token, id } = response.data;

    await AsyncStorage.setItem('token', token);
    await AsyncStorage.setItem('id', id);
   
    login({ token, id });

    Alert.alert('Login Successful', `ID: ${id}`);
    setTimeout(() => {
      router.replace('/home');
    }, 500);
  }
  } catch (error: any) {
    // Axios automatically goes here if status is not 2xx
    const message =
      error.response?.data?.message || 'Invalid credentials or server error';
    Alert.alert('Login Failed', message);
  }
};


  return (
    <View style={styles.container}>
      <Text style={styles.title}>Login</Text>
      <TextInput
        placeholder="Email"
        style={styles.input}
        value={email}
        onChangeText={setEmail}
      />
      <TextInput
        placeholder="Password"
        style={styles.input}
        secureTextEntry
        value={password}
        onChangeText={setPassword}
      />
      <Button title="Login" onPress={handleLogin} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', padding: 20 },
  title: { fontSize: 24, marginBottom: 20, textAlign: 'center' },
  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 12,
    marginBottom: 12,
    borderRadius: 6,
  },
});

export const metadata = {
  title: 'Login',
};
