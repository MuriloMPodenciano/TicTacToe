package com.example.tictactoe

interface JogoDAO {
    fun createJogo() : Int

    fun findJogoById(id: Int)
}