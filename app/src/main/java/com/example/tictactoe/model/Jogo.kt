package com.example.tictactoe.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jogo(
    var id: String = "",
    var table: MutableList<Posicao> = mutableListOf(Posicao.VAZIO,Posicao.VAZIO,Posicao.VAZIO,Posicao.VAZIO,Posicao.VAZIO,Posicao.VAZIO,Posicao.VAZIO,Posicao.VAZIO,Posicao.VAZIO),
    var jogador: Int = 1
    ): Parcelable