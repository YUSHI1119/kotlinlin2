package com.example.memo

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.memo.api.SampleHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button2.setOnClickListener {
            val intent = Intent(this@MenuActivity,EditActivity::class.java).apply {
//          putExtra("")
        }
            startActivity(intent)

        }
        button3.setOnClickListener {
            val intent = Intent(this@MenuActivity,CheckActivity::class.java).apply {
                //          putExtra("")
            }
           startActivity(intent)

        }

    }
}