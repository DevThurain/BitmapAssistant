package com.thurainx.bitmapassistant

import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thurainx.bitmapassistant.utils.directConvertToBitmap
import com.thurainx.bitmapassistant.utils.directConvertToCacheFile
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        getUri().directConvertToBitmap(
//            context = this,
//            qualityScale = 0.25,
//            onSuccess = { bitmap ->
//                // further operation
//            },
//            onFailed = { message ->
//               // error message
//            })
//
//        val file : File? = getBitmap().directConvertToCacheFile(context = this, fileName = "myFile")
//
//        getUri().directConvertToCacheFile(
//            context = this,
//            fileName = "myFile",
//            qualityScale = 0.25,
//
//        )

    }

//    private fun getUri(): Uri? {
//        return null
//    }
//
//    private fun getBitmap(): Bitmap? {
//        return null
//    }
}