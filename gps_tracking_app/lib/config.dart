class Config {
  static const String apiBaseUrl = "http://10.0.2.2:8080"; // Android Emulator localhost
  static const String loginEndpoint = "$apiBaseUrl/login";
  static const String gpsEndpoint = "$apiBaseUrl/gps";
  
  static const Duration locationUpdateInterval = Duration(minutes: 30);
  static const String backgroundTaskName = "geoTrackingTask";
  static const String backgroundTaskUniqueName = "sendLocationTask";
} 