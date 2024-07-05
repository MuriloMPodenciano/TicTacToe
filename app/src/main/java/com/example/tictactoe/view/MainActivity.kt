package com.example.tictactoe.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.model.Constant.EXTRA_JOGO
import com.example.tictactoe.controller.JogoController
import com.example.tictactoe.R
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val jogoController: JogoController by lazy {
        JogoController()
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
            val id = jogoController.createJogo()

            intent.putExtra(EXTRA_JOGO, id)
            setResult(RESULT_OK, intent)

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

                    intent.putExtra(EXTRA_JOGO, input.text.toString())
                    setResult(RESULT_OK, intent)

                    startActivity(intent)
                })
            builder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            builder.show()
        }
    }
}