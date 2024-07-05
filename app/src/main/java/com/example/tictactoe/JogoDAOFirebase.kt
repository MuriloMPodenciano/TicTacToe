package com.example.tictactoe

import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlin.random.Random

class JogoDAOFirebase : JogoDAO{
    companion object{
        private const val JOGO_ROOT_NODE = "jogo"
    }

    private val jogoFirebaseReference = Firebase.database.getReference(JOGO_ROOT_NODE)

    override fun createJogo(): String {
        val newJogo = Jogo(
            id = generateId()
        )
        jogoFirebaseReference.child(newJogo.id.toString()).setValue(newJogo)

        return newJogo.id
    }

    override fun findJogoById(id: Int) {

    }

    private fun generateId(): String{
        return Random(System.currentTimeMillis()).nextInt().toString()
    }
}