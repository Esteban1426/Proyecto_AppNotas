package com.example.proyecto_appnotas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val nombre = findViewById<EditText>(R.id.nombre)
        val correo = findViewById<EditText>(R.id.correo)
        val telefono = findViewById<EditText>(R.id.telefono)
        val carrera = findViewById<EditText>(R.id.facultad)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {

            val datoname = nombre.text.toString().trim()
            val datomail = correo.text.toString().trim()
            val datotelefono = telefono.text.toString().trim()
            val datocarrera = carrera.text.toString().trim()

            if (datoname.isEmpty() || datomail.isEmpty() || datotelefono.isEmpty() || datocarrera.isEmpty()) {
                mostraralerta("Error", "Por favor completa todos los campos")
                return@setOnClickListener
            }

            val basedatos = FirebaseFirestore.getInstance()
            val usuario = hashMapOf(
                "Nombre" to datoname,
                "Correo" to datomail,
                "Telefono" to datotelefono,
                "Carrera" to datocarrera
            )
        }
    }

    private fun mostraralerta(titulo: String, mensaje: String) {
        val Alerta = AlertDialog.Builder(this)
        Alerta.setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                startActivity(Intent(this, GestorNotas::class.java))
                finish()
            }
        val contenido = Alerta.create()
        contenido.show()
    }
}

