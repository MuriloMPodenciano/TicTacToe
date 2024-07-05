package com.example.tictactoe

import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlin.random.Random

class JogoDAOFirebase : JogoDAO{
    companion object{
        private const val JOGO_ROOT_NODE = "jogo"
    }

    private val jogoFirebaseReference = Firebase.database.getReference(JOGO_ROOT_NODE)

    override fun createJogo(jogo: Jogo) {
        jogoFirebaseReference.child(jogo.id).setValue(jogo)
    }

    override fun findJogoById(id: String): Jogo {
        return
    }


}