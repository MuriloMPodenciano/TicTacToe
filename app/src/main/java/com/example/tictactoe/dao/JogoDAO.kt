package com.example.tictactoe.dao

import com.example.tictactoe.model.Jogo

interface JogoDAO {
    fun createJogo(jogo: Jogo)

    fun findJogoById(id: String) : Jogo?

}