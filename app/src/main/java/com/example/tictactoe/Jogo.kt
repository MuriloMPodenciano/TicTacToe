package com.example.tictactoe

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jogo(
    var id: Int = -1,
): Parcelable