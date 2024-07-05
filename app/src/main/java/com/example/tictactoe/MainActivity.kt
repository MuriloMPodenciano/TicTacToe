package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.Constant.EXTRA_JOGO
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val jogoDao: JogoDAO by lazy {
        JogoDAOFirebase()
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
            val id = jogoDao.createJogo()

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_JOGO, id)
            setResult(RESULT_OK, resultIntent)

            startActivity(intent)
        }

        amb.entrarBt.setOnClickListener {
            val intent = Intent(this, JogoActivity::class.java)

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Insira um ID existente")

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_JOGO, id)
            setResult(RESULT_OK, resultIntent)

            startActivity(intent)
        }
    }
}