# flutter_heic_to_jpg

A Flutter plugin for converting HEIC images to JPG format on Android and iOS devices. Easily integrate HEIC to JPG conversion into your Flutter apps with a simple API.

## Installation
Add the Package
```yaml
dependencies:
  flutter_heic_to_jpg: ^1.0.0
```

## How to use

Import the package in your dart file

```dart
import 'package:flutter_heic_to_jpg/flutter_heic_to_jpg.dart';
```

And call convert method with local HEIC/HEIF image file path.
```dart
String jpegPath = await FlutterHeicToJpg.convert(heicPath);
```

