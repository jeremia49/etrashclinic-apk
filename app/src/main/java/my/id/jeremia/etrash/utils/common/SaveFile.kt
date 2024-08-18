package my.id.jeremia.etrash.utils.common

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import com.canhub.cropper.CropImage.CancelledResult.bitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

fun saveImageToStorage(context: Context, uri: Uri): String? {
    val contentResolver = context.contentResolver
    val inputStream: InputStream? = contentResolver.openInputStream(uri)
    val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val fileName = "saved_image_${System.currentTimeMillis()}.jpg"
    val file = File(directory, fileName)

    return try {
        val outputStream: FileOutputStream = FileOutputStream(file)
        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        file.absolutePath  // Return the file name if successful
    } catch (e: Exception) {
        e.printStackTrace()
        null  // Return null if there's an error
    }
}