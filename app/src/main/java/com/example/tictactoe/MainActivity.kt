package com.example.tictactoe

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
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
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Insira um ID existente")

            val input = EditText(this)

            input.inputType =
                InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, which ->
                    val intent = Intent(this, JogoActivity::class.java)

                    val resultIntent = Intent()
                    resultIntent.putExtra(EXTRA_JOGO, input.text.toString())
                    setResult(RESULT_OK, resultIntent)

                    startActivity(intent)
                })
            builder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            builder.show()
        }
    }
}