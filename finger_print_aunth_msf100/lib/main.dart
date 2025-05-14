import 'package:finger_print_aunth_msf100/screens/login_screen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../services/fingerprint_service.dart';
import '../providers/auth_provider.dart';
import '../services/api_service.dart';

class AttendanceScreen extends StatefulWidget {
  const AttendanceScreen({super.key});

  @override
  State<AttendanceScreen> createState() => _AttendanceScreenState();
}

class _AttendanceScreenState extends State<AttendanceScreen> {
  bool _isProcessing = false;
  String _message = '';

  Future<void> _markAttendance() async {
    setState(() {
      _isProcessing = true;
      _message = 'Place your finger on the sensor...';
    });

    try {
      final fingerprintTemplate = await FingerprintService.captureFingerprint();
      final token = Provider.of<AuthProvider>(context, listen: false).token;
      
      await ApiService.markAttendance(token!, fingerprintTemplate);
      
      setState(() {
        _message = 'Attendance marked successfully!';
      });
    } catch (e) {
      setState(() {
        _message = 'Error: ${e.toString()}';
      });
    } finally {
      setState(() {
        _isProcessing = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Mark Attendance'),
        actions: [
          IconButton(
            icon: const Icon(Icons.logout),
            onPressed: () {
              Provider.of<AuthProvider>(context, listen: false).logout();
              Navigator.of(context).pushReplacement(
                MaterialPageRoute(builder: (_) => const LoginScreen()),
              );
            },
          ),
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            if (_isProcessing)
              const CircularProgressIndicator()
            else
              ElevatedButton.icon(
                onPressed: _markAttendance,
                icon: const Icon(Icons.fingerprint),
                label: const Text('Mark Attendance'),
              ),
            const SizedBox(height: 20),
            Text(_message),
          ],
        ),
      ),
    );
  }
}