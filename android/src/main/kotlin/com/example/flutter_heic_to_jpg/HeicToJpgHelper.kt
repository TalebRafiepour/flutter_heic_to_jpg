package com.example.flutter_heic_to_jpg

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class HeicToJpgHelper {

    fun convertHeicToJpeg(heic: String, outputPath: String): String? {
        try {
            val bitmap = BitmapFactory.decodeFile(heic)
            val file = File(outputPath)
            file.createNewFile()
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,FileOutputStream(file))
            return file.path
        }catch (e: Exception){
            Log.e("Error", "convertHeicToJpeg: $e", e)
        }
        return null
    }
}