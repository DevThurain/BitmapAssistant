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
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


fun Bitmap.scaleToRatio(ratio: Double): Bitmap {
    return this.scale((this.width * ratio).toInt(), (this.height * ratio).toInt())
}

fun Uri.loadBitmapFromUri(context: Context): Bitmap {
    return Glide.with(context)
        .asBitmap()
        .load(this)
        .submit()
        .get()
}

@SuppressLint("CheckResult")
fun Uri.directConvertToBitmap(context: Context, onSuccess: (Bitmap) -> Unit) {
    Observable.just(this)
        .map { it.loadBitmapFromUri(context) }
        .map { it.scaleToRatio(0.25) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            onSuccess(it)
        }
}

fun cacheImage(bitmap: Bitmap, context: Context) : File?{
    val fileName: String = "nrc"+System.currentTimeMillis().toString()
    val filesDir: File = context.cacheDir
    val imageFile = File(filesDir, "$fileName.jpg")
    val os: OutputStream
    try {
        os = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
        os.flush()
        os.close()
        return imageFile
    } catch (e: Exception) {
        return null
    }
}