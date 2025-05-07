import 'dart:ffi' as ffi;

// MSF100 function types
typedef InitializeNative = ffi.Int32 Function();
typedef InitializeDart = int Function();
typedef CaptureNative = ffi.Int32 Function();
typedef CaptureDart = int Function();
typedef GetTemplateNative = ffi.Pointer<ffi.Uint8> Function();
typedef GetTemplateDart = ffi.Pointer<ffi.Uint8> Function();

class FingerprintService {
  static late ffi.DynamicLibrary _lib;
  static bool _isInitialized = false;

  static Future<void> initialize() async {
    if (_isInitialized) return;

    try {
      _lib = ffi.DynamicLibrary.open('msf100.dll');
      
      final initialize = _lib
          .lookupFunction<InitializeNative, InitializeDart>('MSF100Initialize');
      
      final result = initialize();
      if (result != 0) {
        throw Exception('Failed to initialize MSF100 sensor');
      }
      
      _isInitialized = true;
    } catch (e) {
      throw Exception('Error initializing fingerprint sensor: $e');
    }
  }

  static Future<List<int>> captureFingerprint() async {
    if (!_isInitialized) {
      await initialize();
    }

    final capture = _lib
        .lookupFunction<CaptureNative, CaptureDart>('MSF100Capture');
    final getTemplate = _lib
        .lookupFunction<GetTemplateNative, GetTemplateDart>('MSF100GetTemplate');

    final captureResult = capture();
    if (captureResult != 0) {
      throw Exception('Failed to capture fingerprint');
    }

    final templatePtr = getTemplate();
    // Convert pointer to bytes - assuming 512 bytes template size for MSF100
    final bytes = templatePtr.asTypedList(512);
    return bytes.toList();
  }
} 