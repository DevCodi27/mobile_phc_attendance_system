import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(FingerprintAttendanceApp());

class FingerprintAttendanceApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Fingerprint Attendance',
      home: FingerprintAuth(),
      debugShowCheckedModeBanner: false,
    );
  }
}

class FingerprintAuth extends StatefulWidget {
  @override
  _FingerprintAuthState createState() => _FingerprintAuthState();
}

class _FingerprintAuthState extends State<FingerprintAuth> {
  static const platform = MethodChannel('mfs100_scanner');
  String _authResult = 'Not Authenticated';

  Future<void> authenticate() async {
    try {
      final String result = await platform.invokeMethod('captureFingerprint');
      setState(() {
        _authResult = result;
      });
    } on PlatformException catch (e) {
      setState(() {
        _authResult = "Error: '${e.message}'";
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color.fromARGB(255, 18, 18, 81),
        title: Text('Fingerprint Attendance',style: TextStyle(color: Colors.white),),
        centerTitle: true,),
      body: Container(
        decoration: BoxDecoration(
        image: DecorationImage(
          image: AssetImage("assets/images/bg.jpeg"),
          fit: BoxFit.fitHeight)
            ),
        child: Center(
          child: Container(
             
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('Status: $_authResult'),
                SizedBox(height: 20),
                ElevatedButton(
                  onPressed: authenticate,
                  child: Text('Capture Fingerprint'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
