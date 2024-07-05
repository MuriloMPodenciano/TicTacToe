package com.example.tictactoe.dao

import com.example.tictactoe.model.Jogo
import com.google.firebase.Firebase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

class JogoDAOFirebase : JogoDAO {
    companion object{
        private const val JOGO_ROOT_NODE = "jogo"
        private val jogos: MutableList<Jogo> = mutableListOf()
    }

    private val jogoFirebaseReference = Firebase.database.getReference(JOGO_ROOT_NODE)

    init {
        jogoFirebaseReference.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val jogo: Jogo? = snapshot.getValue<Jogo>()

                jogo?.also {newJogo ->
                    if (!jogos.any(){it.id == newJogo.id}) {
                        jogos.add(newJogo)
                    }
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val jogo: Jogo? = snapshot.getValue<Jogo>()

                jogo?.also { editedJogo ->
                    jogos.apply {
                        this[indexOfFirst{editedJogo.id == it.id}] = editedJogo
                    }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                //NSA
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                //NSA
            }

            override fun onCancelled(error: DatabaseError) {
                //NSA
            }
        })

        jogoFirebaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val jogosMap = snapshot.getValue<Map<String, Jogo>>()

                jogos.clear()
                jogosMap?.values?.also {
                    jogos.addAll(it)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                //NSA
            }
        })
    }

    override fun createJogo(jogo: Jogo) {
        jogoFirebaseReference.child(jogo.id).setValue(jogo)
    }

    override fun findJogoById(id: String): Jogo? {
        if (jogos.indexOfFirst { it.id ==id } == -1)
            return null
        return jogos[jogos.indexOfFirst { it.id ==id }]
    }

    override fun updateJogo(jogo: Jogo) {
        jogoFirebaseReference.child(jogo.id).setValue(jogo)
    }
}