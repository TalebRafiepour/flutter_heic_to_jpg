import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_heic_to_jpg_platform_interface.dart';

/// An implementation of [FlutterHeicToJpgPlatform] that uses method channels.
class MethodChannelFlutterHeicToJpg extends FlutterHeicToJpgPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_heic_to_jpg');

  @override
  Future<String?> convert(String heicPath, {String? jpgPath}) async {
    final String? jpg = await methodChannel
        .invokeMethod('convert', {"heicPath": heicPath, "jpgPath": jpgPath});
    return jpg;
  }
}
