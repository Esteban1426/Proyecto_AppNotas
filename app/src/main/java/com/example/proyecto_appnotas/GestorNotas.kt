package com.example.proyecto_appnotas
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

enum class  ProviderType{
    BASIC
}
class GestorNotas : AppCompatActivity() {

    private lateinit var Basedatos: FirebaseFirestore

    lateinit var campoNota1: EditText
    lateinit var campoNota2: EditText
    lateinit var campoNota3: EditText
    lateinit var campoNota4: EditText
    lateinit var campoNota5: EditText
    lateinit var tvNotas: TextView
    lateinit var tvResultado: TextView
    lateinit var botonCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestor_notas)

        Basedatos = FirebaseFirestore.getInstance()

        campoNota1 = findViewById(R.id.campoNota)
        campoNota2 = findViewById(R.id.campoNota2)
        campoNota3 = findViewById(R.id.campoNota3)
        campoNota4 = findViewById(R.id.campoNota4)
        campoNota5 = findViewById(R.id.campoNota5)

        tvNotas = findViewById(R.id.Notas)
        tvResultado = findViewById(R.id.tvResultado)
        botonCalcular = findViewById(R.id.Calcular)

        botonCalcular.setOnClickListener {

            val textoNota1 = campoNota1.text.toString()
            val textoNota2 = campoNota2.text.toString()
            val textoNota3 = campoNota3.text.toString()
            val textoNota4 = campoNota4.text.toString()
            val textoNota5 = campoNota5.text.toString()

            var cantidadNotas = 0
            val notas = mutableListOf<Double>()
            if (textoNota1.isNotBlank()) {
                notas.add(textoNota1.toDouble())
                cantidadNotas++
            }
            if (textoNota2.isNotBlank()) {
                notas.add(textoNota2.toDouble())
                cantidadNotas++
            }
            if (textoNota3.isNotBlank()) {
                notas.add(textoNota3.toDouble())
                cantidadNotas++
            }
            if (textoNota4.isNotBlank()) {
                notas.add(textoNota4.toDouble())
                cantidadNotas++
            }
            if (textoNota5.isNotBlank()) {
                notas.add(textoNota5.toDouble())
                cantidadNotas++
            }

            if (cantidadNotas < 2) {
                Toast.makeText(this, "Se necesitan mínimo 2 notas para realizar el cálculo", Toast.LENGTH_SHORT).show()
            } else {
                val promedio = notas.sum() / cantidadNotas

                tvNotas.text = notas.joinToString(" + ")
                tvResultado.text = "Resultado: $promedio"

                val notaData = hashMapOf(
                    "nota1" to textoNota1,
                    "nota2" to textoNota2,
                    "nota3" to textoNota3,
                    "nota4" to textoNota4,
                    "nota5" to textoNota5,
                    "Promedio" to promedio
                )

                Basedatos.collection("Datos")
                    .add(notaData)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this, "Notas registradas en Firestore correctamente", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error al registrar las notas en Firestore: $e", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}