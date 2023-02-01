package com.thurainx.bitmapassistant.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.graphics.scale
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


private fun Bitmap.scaleToRatio(ratio: Double): Bitmap {
    return this.scale((this.width * ratio).toInt(), (this.height * ratio).toInt())
}

private fun Uri.loadBitmapFromUri(context: Context): Bitmap {
    return Glide.with(context)
        .asBitmap()
        .load(this)
        .submit()
        .get()
}

@SuppressLint("CheckResult")
fun Uri.directConvertToBitmap(
    context: Context,
    qualityScale: Double = 0.25,
    onSuccess: (Bitmap) -> Unit,
    onFailed: (String) -> Unit
) {
    Observable.just(this)
        .map { it.loadBitmapFromUri(context) }
        .map { it.scaleToRatio(qualityScale) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                Timber.tag("BitmapAssistant").d("Generate bitmap success")
                onSuccess(it)
            },
            {
                Timber.tag("BitmapAssistant").e(it.localizedMessage ?: "Convert to bitmap failed.")
                onFailed(it.localizedMessage ?: "Convert to bitmap failed.")
            }
        )
}

fun Bitmap.directConvertToCacheFile(context: Context, fileName: String = ""): File? {
    val name: String = fileName.ifEmpty { "temp" + System.currentTimeMillis().toString() }
    val filesDir: File = context.cacheDir
    val imageFile = File(filesDir, "$name.jpg")
    val os: OutputStream
    try {
        os = FileOutputStream(imageFile)
        this.compress(Bitmap.CompressFormat.JPEG, 100, os)
        os.flush()
        os.close()
        Timber.tag("BitmapAssistant").d("Generate file success")
        return imageFile
    } catch (e: Exception) {
        Timber.tag("BitmapAssistant").e(e.localizedMessage ?: "Convert to file failed.")
        return null
    }
}

fun Uri.directConvertToCacheFile(
    context: Context,
    fileName: String = "",
    qualityScale: Double = 0.25,
    onSuccess: (File) -> Unit,
    onFailed: (String) -> Unit
){
    this.directConvertToBitmap(context, qualityScale, onSuccess = {
          val cachedFile = it.directConvertToCacheFile(context, fileName)
          if(cachedFile != null){
              Timber.tag("BitmapAssistant").d("Generate file success")
              onSuccess(cachedFile)
          }else{
              Timber.tag("BitmapAssistant").d("Cannot generate cache file.")
              onFailed("Cannot generate cache file.")
          }
    }, onFailed)
}