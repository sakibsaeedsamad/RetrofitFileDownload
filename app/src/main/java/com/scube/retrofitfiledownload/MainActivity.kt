package com.scube.retrofitfiledownload

import android.Manifest
import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import android.content.pm.PackageManager
import android.net.Uri

import androidx.core.app.ActivityCompat

import androidx.core.content.ContextCompat

import android.view.View
import android.widget.Button

import androidx.lifecycle.ViewModelProviders
import java.io.*
import java.net.URL
import java.net.URLConnection


class MainActivity : AppCompatActivity() {
    var btnDownloadFile: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_main)
        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 101)
        btnDownloadFile = findViewById(R.id.button)




        btnDownloadFile?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val url = ""
                val request = DownloadManager.Request(Uri.parse(url))
                    .setTitle("File")
                    .setDescription("Downloading...")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setAllowedOverMetered(true)

                val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager

                dm.enqueue(request)



            }
        })
    }




    private fun askForPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    permission
                )
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(permission),
                    requestCode
                )
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(permission),
                    requestCode
                )
            }
        } else if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            Toast.makeText(applicationContext, "Permission was denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ActivityCompat.checkSelfPermission(
                this,
                permissions[0]
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (requestCode == 101) Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}