package com.example.proyecto_appnotas

class TestPromedio {
   fun calcularPromedio(nota1: Double, nota2: Double, nota3: Double, nota4: Double, nota5: Double): Double {
       val nota1Valida = nota1 ?: 0.0
       val nota2Valida = nota2 ?: 0.0
       val nota3Valida = nota3 ?: 0.0
       val nota4Valida = nota4 ?: 0.0
       val nota5Valida = nota5 ?: 0.0
       return (nota1Valida + nota2Valida + nota3Valida + nota4Valida + nota5Valida) / 5
   }
}