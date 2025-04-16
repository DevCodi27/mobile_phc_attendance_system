import 'dart:async';
import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:geolocator/geolocator.dart';
import 'package:flutter_email_sender/flutter_email_sender.dart';

class MapScreen extends StatefulWidget {
  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  Completer<GoogleMapController> _controller = Completer();
  LatLng _currentPosition = LatLng(0.0, 0.0); // Default
  LatLng _regionCenter = LatLng(12.9716, 77.5946); // Example: Bangalore
  double _regionRadius = 1000; // 1000 meters (1 km)

  @override
  void initState() {
    super.initState();
    _getCurrentLocation();
  }

  // Get current location
  Future<void> _getCurrentLocation() async {
    bool serviceEnabled;
    LocationPermission permission;

    serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      return;
    }

    permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.deniedForever) {
        return;
      }
    }

    Position position = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high);

    setState(() {
      _currentPosition = LatLng(position.latitude, position.longitude);
    });

    _checkIfInsideRegion();
  }

  // Check if user is inside the defined region
  void _checkIfInsideRegion() {
    double distance = Geolocator.distanceBetween(
      _currentPosition.latitude,
      _currentPosition.longitude,
      _regionCenter.latitude,
      _regionCenter.longitude,
    );

    if (distance > _regionRadius) {
      _sendEmailAlert();
    }
  }

  // Send email alert if outside the region
  Future<void> _sendEmailAlert() async {
    final Email email = Email(
      body: 'The user has moved out of the specified region!',
      subject: 'Location Alert!',
      recipients: ['your-email@example.com'],
      isHTML: false,
    );

    try {
      await FlutterEmailSender.send(email);
    } catch (error) {
      print('Failed to send email: $error');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Google Maps Location Tracker')),
      body: GoogleMap(
        initialCameraPosition: CameraPosition(
          target: _currentPosition,
          zoom: 14,
        ),
        myLocationEnabled: true,
        markers: {
          Marker(markerId: MarkerId('userLocation'), position: _currentPosition),
          Marker(markerId: MarkerId('regionCenter'), position: _regionCenter),
        },
        circles: {
          Circle(
            circleId: CircleId('regionBoundary'),
            center: _regionCenter,
            radius: _regionRadius,
            fillColor: Colors.blue.withOpacity(0.3),
            strokeColor: Colors.blue,
            strokeWidth: 2,
          ),
        },
        onMapCreated: (GoogleMapController controller) {
          _controller.complete(controller);
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _getCurrentLocation,
        child: Icon(Icons.my_location),
      ),
    );
  }
}
