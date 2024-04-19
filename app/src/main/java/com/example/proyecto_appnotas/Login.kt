package com.example.proyecto_appnotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

        val Username = findViewById<EditText>(R.id.Username)
        val Password = findViewById<EditText>(R.id.Password)
        val Login = findViewById<Button>(R.id.Login)

        Login.setOnClickListener {
            val username = Username.text.toString()
            val password = Password.text.toString()

            // Iniciar sesión con Firebase Authentication
            Autenticacion.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Inicio de sesión exitoso, obtener el UID del usuario actual
                        val user = Autenticacion.currentUser
                        val uid = user?.uid

                        // Obtener una referencia a la colección de usuarios en la base de datos
                        val usersRef = Basedatos.reference.child("users")

                        // Verificar si el UID del usuario ya existe en la base de datos
                        usersRef.child(uid.toString()).get().addOnSuccessListener { dataSnapshot ->
                            if (dataSnapshot.exists()) {
                                // El usuario ya está registrado en la base de datos
                                val intent = Intent(this, GestorNotas::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                // El usuario no está registrado en la base de datos
                                // Puedes realizar alguna acción, como registrar al usuario automáticamente
                                // Aquí puedes guardar información adicional del usuario en la base de datos
                            }
                        }.addOnFailureListener {
                            // Error al obtener datos de la base de datos
                        }
                    } else {
                        // Si la autenticación falla, mostrar un mensaje de error
                        Toast.makeText(baseContext, "Inicio de sesión fallido.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        val Registrarse = findViewById<TextView>(R.id.Registro)
        Registrarse.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
        val Recuperacion = findViewById<TextView>(R.id.RecuperarPassword)
        Recuperacion.setOnClickListener {
            val intent = Intent(this, Recuperacion::class.java)
            startActivity(intent)
        }
    }
}
