import 'package:flutter/foundation.dart';
import '../services/api_service.dart';

class AuthProvider with ChangeNotifier {
  String? _token;
  String? get token => _token;

  Future<void> login(String username, String password) async {
    try {
      final response = await ApiService.login(username, password);
      _token = response['token'];
      notifyListeners();
    } catch (e) {
      _token = null;
      throw Exception('Login failed: ${e.toString()}');
    }
  }

  void logout() {
    _token = null;
    notifyListeners();
  }
}