package com.thurainx.bitmapassistant

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thurainx.bitmapassistant.utils.directConvertToBitmap
import com.thurainx.bitmapassistant.utils.directConvertToFile

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}