package com.example.proyecto_appnotas
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
enum class  ProviderType{
    BASIC
}
class GestorNotas : AppCompatActivity() {

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

        campoNota1 = findViewById(R.id.campoNota)
        campoNota2 = findViewById(R.id.campoNota2)
        campoNota3 = findViewById(R.id.campoNota3)
        campoNota4 = findViewById(R.id.campoNota4)
        campoNota5 = findViewById(R.id.campoNota5)

        tvNotas = findViewById(R.id.Notas)
        tvResultado = findViewById(R.id.tvResultado)
        botonCalcular = findViewById(R.id.Calcular)

        botonCalcular.setOnClickListener {
            val nota1 = campoNota1.text.toString().toDoubleOrNull() ?: 0.0
            val nota2 = campoNota2.text.toString().toDoubleOrNull() ?: 0.0
            val nota3 = campoNota3.text.toString().toDoubleOrNull() ?: 0.0
            val nota4 = campoNota4.text.toString().toDoubleOrNull() ?: 0.0
            val nota5 = campoNota5.text.toString().toDoubleOrNull() ?: 0.0

            val promedio = calcularPromedio(nota1, nota2, nota3, nota4, nota5)

            tvNotas.text = "$nota1 + $nota2 + $nota3 + $nota4 + $nota5"

            tvResultado.text = "Resultado: $promedio"
        }
    }

    fun calcularPromedio(nota1: Double, nota2: Double, nota3: Double, nota4: Double, nota5: Double): Double {
        return (nota1 + nota2 + nota3 + nota4 + nota5) / 5
    }
}