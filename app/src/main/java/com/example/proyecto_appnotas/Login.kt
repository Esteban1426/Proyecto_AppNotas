package com.example.proyecto_appnotas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {

    lateinit var Autenticacion: FirebaseAuth
    lateinit var Basedatos: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar Firebase Authentication y Firebase Realtime Database
        Autenticacion = FirebaseAuth.getInstance()
        Basedatos = FirebaseDatabase.getInstance()

        val Email = findViewById<EditText>(R.id.Email)
        val Password = findViewById<EditText>(R.id.Password)
        val Login = findViewById<Button>(R.id.Login)

        Login.setOnClickListener {
            val email = Email.text.toString()
            val password = Password.text.toString()

            Autenticacion.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        mostrarHome(task.result?.user?.email?: "", ProviderType.BASIC)
                    } else {
                        Toast.makeText(baseContext, "Inicio de sesión fallido.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        val Registrarse = findViewById<TextView>(R.id.Registro)
        Registrarse.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
    fun mostrarHome(email: String, provider: ProviderType){
        val intent = Intent(this, GestorNotas::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(intent)
    }
}
