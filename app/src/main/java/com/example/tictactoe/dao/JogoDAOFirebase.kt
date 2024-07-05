package com.example.tictactoe.dao

import com.example.tictactoe.Jogo
import com.google.firebase.Firebase
import com.google.firebase.database.database

class JogoDAOFirebase : JogoDAO {
    companion object{
        private const val JOGO_ROOT_NODE = "jogo"
    }

    private val jogoFirebaseReference = Firebase.database.getReference(JOGO_ROOT_NODE)

    override fun createJogo(jogo: Jogo) {
        jogoFirebaseReference.child(jogo.id).setValue(jogo)
    }

    override fun findJogoById(id: String): Jogo? {
        val jogoReference = jogoFirebaseReference.child(id)
        var retrievedJogo: Jogo? = null

        jogoReference.get().addOnSuccessListener { dataSnapshot ->
            retrievedJogo = dataSnapshot.getValue(Jogo::class.java)
        }
        return retrievedJogo
    }
}