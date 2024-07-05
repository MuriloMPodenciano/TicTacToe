package com.example.tictactoe.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.model.Constant.EXTRA_JOGO
import com.example.tictactoe.model.Jogo
import com.example.tictactoe.controller.JogoController
import com.example.tictactoe.R
import com.example.tictactoe.databinding.JogoBinding

class JogoActivity : AppCompatActivity(){
    private val jb: JogoBinding by lazy {
        JogoBinding.inflate(layoutInflater)
    }

    private lateinit var jogo: Jogo

    private val jogoController: JogoController by lazy {
        JogoController()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(jb.root)

        jb.mainTb.apply {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }


        val receivedId = intent.getStringExtra(EXTRA_JOGO)
        receivedId?.let {
            jogoController.findJogoById(receivedId)?.let{
                jogo = it
                jb.idjogoTv.text = "ID: ${jogo.id}"
            } ?:run {
                jb.idjogoTv.text = "ID: erro viu $receivedId"
            }
        }
    }
}