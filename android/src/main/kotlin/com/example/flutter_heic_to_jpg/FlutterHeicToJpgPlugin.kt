package com.example.flutter_heic_to_jpg

import android.content.Context
import android.os.Handler
import android.os.Looper
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** FlutterHeicToJpgPlugin */
class FlutterHeicToJpgPlugin: FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel : MethodChannel
    private lateinit var applicationContext: Context
    private lateinit var heicToJpgHelper: HeicToJpgHelper

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        applicationContext = flutterPluginBinding.applicationContext
        heicToJpgHelper = HeicToJpgHelper()
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_heic_to_jpg")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "convert") {
            if (call.hasArgument("heicPath") && !call.argument<String>("heicPath").isNullOrEmpty()) {
                val handler = Handler(Looper.getMainLooper())
                Thread {
                    var jpgPath = call.argument<String>("jpgPath")
                    if(jpgPath.isNullOrEmpty()){
                        jpgPath = "${applicationContext.cacheDir}/${System.currentTimeMillis()}.jpg"
                    }
                    val output = heicToJpgHelper.convertHeicToJpeg(call.argument<String>("heicPath")!!, jpgPath)
                    handler.post {
                        if (output != null) {
                            result.success(output)
                        } else {
                            result.error("error", "output path is null", null)
                        }
                    }
                }.start()
                return
            }
            result.error("illegalArgument", "heicPath is null or Empty.", null)
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
