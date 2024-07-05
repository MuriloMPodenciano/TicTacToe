package com.example.tictactoe.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.model.Constant.EXTRA_JOGO
import com.example.tictactoe.model.Jogo
import com.example.tictactoe.controller.JogoController
import com.example.tictactoe.R
import com.example.tictactoe.databinding.JogoBinding
import com.example.tictactoe.model.Constant.EXTRA_JOGADOR
import com.example.tictactoe.model.Posicao

class JogoActivity : AppCompatActivity(){
    private val jb: JogoBinding by lazy {
        JogoBinding.inflate(layoutInflater)
    }

    companion object {
        const val GET_JOGO = 1
        const val GET_JOGO_INTERVAL = 2000L
    }

    private lateinit var listImageViews: MutableList<ImageView>
    private lateinit var jogo: Jogo
    private var jogador: Int = -1
    private var winFlag = false

    private val jogoController: JogoController by lazy {
        JogoController()
    }

    val updateJogoHandler = object: Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if (msg.what == GET_JOGO) {
                jogoController.findJogoById(jogo.id)?.let {
                    jogo = it
                }

                sendMessageDelayed(
                    obtainMessage().apply { what = GET_JOGO },
                    GET_JOGO_INTERVAL
                )
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(jb.root)

        jb.mainTb.apply {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        val receivedJogador = intent.getIntExtra(EXTRA_JOGADOR, -1)
        val receivedId = intent.getStringExtra(EXTRA_JOGO)

        receivedId?.let {
            jogoController.findJogoById(receivedId)?.let{
                jogo = it
                jb.idjogoTv.text = "ID: ${jogo.id}"

            } ?:run {
                finish()
                return
            }
        }

        receivedJogador.let {
            jogador = it
            jb.jogadorTv.text = "você é o jogador ${jogador}"
        }

        listImageViews = mutableListOf(jb.a1, jb.a2, jb.a3, jb.b1, jb.b2, jb.b3, jb.c1, jb.c2, jb.c3)

        for(i in listImageViews.indices){
            listImageViews[i].setOnClickListener {
                if(jogo.jogador == jogador){
                    if(jogo.jogador == 1){
                        jogo.table[i] = Posicao.CRUZ
                        jogo.jogador = 2
                        jogoController.updateJogo(jogo)
                    }else if(jogo.jogador == 2){
                        jogo.table[i] = Posicao.CIRCULO
                        jogo.jogador = 1
                        jogoController.updateJogo(jogo)
                    }
                }else{
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                    builder.setTitle("Não é sua vez!")
                    builder.setNegativeButton("Cancel"
                    ) { dialog, which -> dialog.cancel() }

                    builder.show()
                }
                setTable()
            }
        }

        updateJogoHandler.apply {
            sendMessage(
                obtainMessage().apply { what = GET_JOGO }
            )
        }
        setTable()
    }
    private fun setTable(){
        for(i in listImageViews.indices){
            if( jogo.table[i] == Posicao.CIRCULO){
                listImageViews[i].setImageResource(R.drawable.circulo)
                listImageViews[i].setOnClickListener(null)
            }
            else if( jogo.table[i] == Posicao.CRUZ){
                listImageViews[i].setImageResource(R.drawable.cross)
                listImageViews[i].setOnClickListener(null)
            }
        }
        jb.jogadorAtualTv.text = "vez do jogador ${jogo.jogador}"

        if (!winFlag){
            validateJogo()
        }
    }
    private fun validateJogo(){
        var winner = Posicao.VAZIO
        if((jogo.table[0] == jogo.table[1]) and (jogo.table[0] ==jogo.table[2])){
            winner = jogo.table[0]
        }else if((jogo.table[3] == jogo.table[4]) and (jogo.table[3] ==jogo.table[5])){
            winner = jogo.table[3]
        }else if((jogo.table[6] == jogo.table[7]) and (jogo.table[6] ==jogo.table[8])){
            winner = jogo.table[6]
        }else if((jogo.table[0] == jogo.table[3]) and (jogo.table[0] ==jogo.table[6])){
            winner = jogo.table[0]
        }else if((jogo.table[1] == jogo.table[4]) and (jogo.table[1] ==jogo.table[7])){
            winner = jogo.table[1]
        }else if((jogo.table[2] == jogo.table[5]) and (jogo.table[2] ==jogo.table[8])){
            winner = jogo.table[2]
        }else if((jogo.table[0] == jogo.table[4]) and (jogo.table[0] ==jogo.table[8])){
            winner = jogo.table[0]
        }else if((jogo.table[2] == jogo.table[4]) and (jogo.table[2] ==jogo.table[6])){
            winner = jogo.table[2]
        }
        if(winner == Posicao.CRUZ){
            winFlag = true

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Jogador 1 ganhou!!!!!!!!!!!!!!!!")
            builder.setNegativeButton("Ok!"
            ) { dialog, which -> dialog.cancel() }
            builder.show()

            for(i in listImageViews.indices)
                listImageViews[i].setOnClickListener(null)

        }else if(winner == Posicao.CIRCULO){
            winFlag = true

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Jogador 2 ganhou!!!!!!!!!!!!!!!!")
            builder.setNegativeButton("Ok!"
            ) { dialog, which -> dialog.cancel() }
            builder.show()

            for(i in listImageViews.indices)
                listImageViews[i].setOnClickListener(null)

        }else if(jogo.table.filter { it== Posicao.VAZIO}.isEmpty()){
            winFlag = true

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Empate.")
            builder.setNegativeButton("ok.."
            ) { dialog, which -> dialog.cancel() }
            builder.show()
        }
    }
}