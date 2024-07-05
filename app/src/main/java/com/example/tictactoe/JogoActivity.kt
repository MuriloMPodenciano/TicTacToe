package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.Constant.EXTRA_JOGO
import com.example.tictactoe.databinding.JogoBinding

class JogoActivity : AppCompatActivity(){
    private val ja: JogoBinding by lazy {
        JogoBinding.inflate(layoutInflater)
    }

    private lateinit var jogo: Jogo

    private val jogoController: JogoController by lazy {
        JogoController()
    }

    private lateinit var arl: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ja.root)

        ja.mainTb.apply {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        arl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val id = result.data?.getStringExtra(EXTRA_JOGO)!!
                jogo = jogoController.findJogoById(id)
            }
        }
    }
}