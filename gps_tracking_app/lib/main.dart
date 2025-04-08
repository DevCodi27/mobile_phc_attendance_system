import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:http/http.dart' as http;
import 'package:workmanager/workmanager.dart';

import 'login.dart';

const taskName = "sendLocationTask";

void callbackDispatcher() {
  Workmanager().executeTask((task, inputData) async {
    try {
      Position position = await Geolocator.getCurrentPosition(
          desiredAccuracy: LocationAccuracy.high);

      final response = await http.post(
        Uri.parse("http://192.168.1.5:8080/update-location"), // Use your IP
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer ${inputData?['jwt_token']}"
        },
        body: jsonEncode({
          "doctor_id": inputData?['doctor_id'],
          "latitude": position.latitude,
          "longitude": position.longitude,
        }),
      );

      print("Location sent: ${response.statusCode}");
    } catch (e) {
      print("Error: $e");
    }

    return Future.value(true);
  });
}

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Workmanager().initialize(callbackDispatcher, isInDebugMode: true);
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: LoginPage(),
      debugShowCheckedModeBanner: false,
    );
  }
}
