package com.example.tictactoe

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jogo(
    var id: Int = -1,
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