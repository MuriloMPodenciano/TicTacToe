package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.Constant.EXTRA_JOGO
import com.example.tictactoe.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.mainTb.apply {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        amb.criarBt.setOnClickListener {
            val intent = Intent(this, JogoActivity::class.java)

            val msg = Jogo(
                id = generateId()
            )

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_JOGO, msg)
            setResult(RESULT_OK, resultIntent)

            startActivity(intent)
        }

        amb.entrarBt.setOnClickListener {
            val intent = Intent(this, JogoActivity::class.java)
            startActivity(intent)
        }
    }
    private fun generateId(): Int{
        return Random(System.currentTimeMillis()).nextInt()
    }
}