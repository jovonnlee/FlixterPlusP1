package com.example.flixterplusp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.res.Configuration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        val fragmentTransacton = supportFragmentManager.beginTransaction()
        fragmentTransacton.replace(R.id.content, MovieFragment(), null).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        setContentView(R.layout.activity_main)
    }

}