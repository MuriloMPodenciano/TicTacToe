package com.example.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.JogoBinding

class JogoActivity : AppCompatActivity(){
    private val ja: JogoBinding by lazy {
        JogoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ja.root)

        ja.mainTb.apply {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
    }
}