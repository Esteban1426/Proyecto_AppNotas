package com.example.proyecto_appnotas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.FirebaseDatabase

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val basedatos = FirebaseDatabase.getInstance()
        val Autenticacion = FirebaseAuth.getInstance()

        val nombre = findViewById<EditText>(R.id.nombre)
        val correo = findViewById<EditText>(R.id.correo)
        val telefono = findViewById<EditText>(R.id.telefono)
        val carrera = findViewById<EditText>(R.id.facultad)
        val Password = findViewById<EditText>(R.id.Contrasena)
        val Repassword = findViewById<EditText>(R.id.RepetirContrasena)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {

            val datoname = nombre.text.toString().trim()
            val datomail = correo.text.toString().trim()
            val datotelefono = telefono.text.toString().trim()
            val datoContra = Password.text.toString().trim()
            val datoReContra = Repassword.text.toString().trim()
            val datocarrera = carrera.text.toString().trim()

            if(datoname.isEmpty() || datomail.isEmpty() || datotelefono.isEmpty() || datocarrera.isEmpty() || datoContra.isEmpty()|| datoReContra.isEmpty()) {
                mostrarToast("Error, Por favor completa todos los campos")
                return@setOnClickListener
            }

            if (datoContra == datoReContra) {
                Autenticacion.createUserWithEmailAndPassword(datomail, datoContra)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val user = Autenticacion.currentUser
                            val uid = user?.uid

                            val usersRef = basedatos.reference.child("users")
                            val userMap = HashMap<String, Any>()
                            userMap["Nombre"] = datoname
                            userMap["Email"] = datomail
                            userMap["Telefono"] = datotelefono
                            userMap["Carrera"] = datocarrera

                            if (uid != null) {
                                usersRef.child(uid).setValue(userMap)
                                    .addOnSuccessListener {
                                        // Registro de datos de usuario exitoso
                                        mostraralerta("Registro Exitoso", "Usuario ingresado correctamente")
                                    }
                                    .addOnFailureListener {
                                        // Error al registrar datos de usuario
                                        Toast.makeText(this, "Error al registrar datos de usuario.", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                    }
            }else{
                mostrarToast("Error, las contraseñas no coinciden")
                return@setOnClickListener
            }
        }
    }
    private fun mostraralerta(titulo: String, mensaje: String) {
        val Alerta = AlertDialog.Builder(this)
        Alerta.setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                startActivity(Intent(this, Login::class.java))
                finish()
            }
        val contenido = Alerta.create()
        contenido.show()
    }
    private fun mostrarToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}

