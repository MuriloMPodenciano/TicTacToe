package com.example.tictactoe.controller

import com.example.tictactoe.model.Jogo
import com.example.tictactoe.dao.JogoDAO
import com.example.tictactoe.dao.JogoDAOFirebase
import kotlin.random.Random

class JogoController {

    private val jogoDao: JogoDAO by lazy {
        JogoDAOFirebase()
    }
    private fun generateId(): String{
        return Random(System.currentTimeMillis()).nextInt().toString()
    }

    fun createJogo(): String{
        val newJogo = Jogo(
            id = generateId()
        )
        jogoDao.createJogo(newJogo)
        return newJogo.id
    }
    fun findJogoById(id: String): Jogo?{
        return jogoDao.findJogoById(id)
    }
}