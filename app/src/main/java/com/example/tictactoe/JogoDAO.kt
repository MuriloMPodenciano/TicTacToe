package com.example.tictactoe

interface JogoDAO {
    fun createJogo() : String

    fun findJogoById(id: Int)
}