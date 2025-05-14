import 'package:flutter/material.dart';
import '../services/fingerprint_service.dart';
import '../services/api_service.dart';
import 'package:provider/provider.dart';
import '../providers/auth_provider.dart';

class AttendanceScreen extends StatefulWidget {
  const AttendanceScreen({super.key});

  @override
  State<AttendanceScreen> createState() => _AttendanceScreenState();
}

class _AttendanceScreenState extends State<AttendanceScreen> {
  bool _isProcessing = false;
  String _message = '';

  Future<void> _captureAndMarkAttendance() async {
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
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            if (_isProcessing)
              const CircularProgressIndicator()
            else
              ElevatedButton(
                onPressed: _captureAndMarkAttendance,
                child: const Text('Mark Attendance'),
              ),
            const SizedBox(height: 20),
            Text(_message),
          ],
        ),
      ),
    );
  }
} 