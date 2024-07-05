package com.example.tictactoe.dao

import com.example.tictactoe.Jogo

interface JogoDAO {
    fun createJogo(jogo: Jogo)

    fun findJogoById(id: String) : Jogo?

}