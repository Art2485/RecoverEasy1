package com.recovereasy

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var tvStatus: TextView
    private lateinit var btnGrant: Button
    private lateinit var rvList: RecyclerView

    private val requestPerms = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { _ ->
        checkAndStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus = findViewById(R.id.tvStatus)
        btnGrant = findViewById(R.id.btnGrant)
        rvList = findViewById(R.id.rvList)

        btnGrant.setOnClickListener { askPermissions() }
        checkAndStart()
    }

    private fun checkAndStart() {
        if (hasMediaPerms()) {
            tvStatus.text = "ได้รับสิทธิ์แล้ว พร้อมสแกน"
            // TODO: เรียกฟังก์ชันสแกนของคุณ (ห่อด้วย try/catch เสมอ)
            try {
                // scanMedia()
            } catch (t: Throwable) {
                showCrash(t)
            }
        } else {
            tvStatus.text = "ยังไม่ได้สิทธิ์ กรุณากดปุ่มด้านล่าง"
        }
    }

    private fun askPermissions() {
        val perms = mutableListOf<String>()
        if (Build.VERSION.SDK_INT >= 33) {
            perms += listOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO
            )
        } else {
            @Suppress("DEPRECATION")
            perms += Manifest.permission.READ_EXTERNAL_STORAGE
        }
        requestPerms.launch(perms.toTypedArray())
    }

    private fun hasMediaPerms(): Boolean {
        return if (Build.VERSION.SDK_INT >= 33) {
            listOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO
            ).all { p -> ContextCompat.checkSelfPermission(this, p) == PackageManager.PERMISSION_GRANTED }
        } else {
            @Suppress("DEPRECATION")
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun showCrash(t: Throwable) {
        AlertDialog.Builder(this)
            .setTitle("พบข้อผิดพลาด")
            .setMessage(t.stackTraceToString())
            .setPositiveButton("ปิด") { d, _ -> d.dismiss() }
            .show()
    }
}
