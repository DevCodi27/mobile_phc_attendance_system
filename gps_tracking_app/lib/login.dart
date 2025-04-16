import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:gps_tracking_app/main.dart';

import 'package:http/http.dart' as http;
import 'package:workmanager/workmanager.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  Future<void> login() async {
    final url = Uri.parse("http://192.168.1.5:8080/login"); // Your backend
    final response = await http.post(
      url,
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({
        "email": emailController.text,
        "password": passwordController.text,
      }),
    );

    if (response.statusCode == 200) {
      final data = jsonDecode(response.body);
      final jwtToken = data["token"];
      final doctorId = data["doctor_id"];

      // Schedule background task every 30 mins
      await Workmanager().registerPeriodicTask(
        "geoTrackingTask",
        taskName,
        frequency: Duration(minutes: 30),
        inputData: {
          "jwt_token": jwtToken,
          "doctor_id": doctorId.toString(),
        },
      );

      ScaffoldMessenger.of(context).showSnackBar(SnackBar(
        content: Text("Logged in. Background location updates started."),
      ));
    } else {
      ScaffoldMessenger.of(context).showSnackBar(SnackBar(
        content: Text("Login failed"),
      ));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Login")),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
                controller: emailController, decoration: InputDecoration(labelText: "Email")),
            TextField(
                controller: passwordController,
                decoration: InputDecoration(labelText: "Password"),
                obscureText: true),
            SizedBox(height: 20),
            ElevatedButton(onPressed: login, child: Text("Login")),
          ],
        ),
      ),
    );
  }
}
