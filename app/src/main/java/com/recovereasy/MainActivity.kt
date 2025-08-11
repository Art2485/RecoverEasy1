package com.recovereasy

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var tvStatus: TextView
    private lateinit var btnGrant: Button
    private lateinit var rvList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus = findViewById(R.id.tvStatus)
        btnGrant = findViewById(R.id.btnGrant)
        rvList = findViewById(R.id.rvList)

        tvStatus.text = "Ready"
        btnGrant.setOnClickListener {
            tvStatus.text = "Clicked"
        }
    }
}
