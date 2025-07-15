import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_heic_to_jpg_method_channel.dart';

abstract class FlutterHeicToJpgPlatform extends PlatformInterface {
  /// Constructs a FlutterHeicToJpgPlatform.
  FlutterHeicToJpgPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterHeicToJpgPlatform _instance = MethodChannelFlutterHeicToJpg();

  /// The default instance of [FlutterHeicToJpgPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterHeicToJpg].
  static FlutterHeicToJpgPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterHeicToJpgPlatform] when
  /// they register themselves.
  static set instance(FlutterHeicToJpgPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> convert(String heicPath, {String? jpgPath}) async {
    throw UnimplementedError('convert() has not been implemented.');
  }
}
