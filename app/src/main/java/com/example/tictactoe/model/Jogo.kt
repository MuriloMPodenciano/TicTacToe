package com.example.tictactoe.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jogo(
    var id: String = "",
    var a1: Posicao = Posicao.VAZIO,
    var a2: Posicao = Posicao.VAZIO,
    var a3: Posicao = Posicao.VAZIO,
    var b1: Posicao = Posicao.VAZIO,
    var b2: Posicao = Posicao.VAZIO,
    var b3: Posicao = Posicao.VAZIO,
    var c1: Posicao = Posicao.VAZIO,
    var c2: Posicao = Posicao.VAZIO,
    var c3: Posicao = Posicao.VAZIO,
    ): Parcelable