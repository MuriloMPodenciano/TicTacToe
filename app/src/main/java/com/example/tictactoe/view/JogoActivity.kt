package com.example.tictactoe.view

import android.os.Bundle
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
    private lateinit var listPosicoes: MutableList<Posicao>
    private lateinit var listImageViews: MutableList<ImageView>
    private lateinit var jogo: Jogo
    private var jogador: Int = -1


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

        val receivedJogador = intent.getIntExtra(EXTRA_JOGADOR, -1)
        val receivedId = intent.getStringExtra(EXTRA_JOGO)

        receivedId?.let {
            jogoController.findJogoById(receivedId)?.let{
                jogo = it
                jb.idjogoTv.text = "ID: ${jogo.id}"
            } ?:run {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("ID não existe!")
            }
        }

        receivedJogador?.let {
            jogador = it
            jb.jogadorTv.text = "você é o jogador ${jogador}"
        }
        listPosicoes = mutableListOf(jogo.a1, jogo.a2, jogo.a3, jogo.b1, jogo.b2, jogo.b3, jogo.c1, jogo.c2, jogo.c3)
        listImageViews = mutableListOf(jb.a1, jb.a2, jb.a3, jb.b1, jb.b2, jb.b3, jb.c1, jb.c2, jb.c3)

        for(i in listPosicoes.indices){
            listImageViews[i].setOnClickListener {
                if(jogo.jogador == jogador){
                    if(jogador == 1){
                        listPosicoes[i] = Posicao.CIRCULO
                        jogo.jogador = 2

                    }else if(jogador == 2){
                        listPosicoes[i] = Posicao.CRUZ
                        jogo.jogador = 1
                    }
                }else{
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                    builder.setTitle("Não é sua vez!")
                }
                setTable()
            }
        }
        setTable()
    }
    private fun setTable(){
        for(i in listPosicoes.indices){
            if( listPosicoes[i] == Posicao.CIRCULO){
                listImageViews[i].setImageResource(R.drawable.circulo)
                listImageViews[i].setOnClickListener(null)
            }
            else if( listPosicoes[i] == Posicao.CRUZ){
                listImageViews[i].setImageResource(R.drawable.cross)
                listImageViews[i].setOnClickListener(null)
            }
        }
    }


}