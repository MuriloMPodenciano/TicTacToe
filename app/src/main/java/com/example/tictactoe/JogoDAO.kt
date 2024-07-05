package com.example.tictactoe

interface JogoDAO {
    fun createJogo(jogo: Jogo)

    fun findJogoById(id: String) : Jogo

}