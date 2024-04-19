package com.example.proyecto_appnotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BotonComenzar = findViewById<Button>(R.id.Comenzar)
        BotonComenzar.setOnClickListener {
            // Navegar a la siguiente pantalla
            startActivity(Intent(this, Registro::class.java))
        }
    }
}
