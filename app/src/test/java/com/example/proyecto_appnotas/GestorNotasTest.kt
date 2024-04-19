package com.example.proyecto_appnotas

import org.junit.Test
import org.junit.Assert.assertEquals

class GestorNotasTest {

    @Test
    fun calcularPromedio() {
        // Caso de prueba 1: Notas válidas
        val gestorNotas = TestPromedio()

        val resultadoEsperado = 4.5
        val resultadoCalculado = gestorNotas.calcularPromedio(4.0, 5.0)
        assertEquals(resultadoEsperado, resultadoCalculado, 0.001)
        println("PRUEBA #1: $resultadoEsperado, Resultado calculado: $resultadoCalculado")

        // Caso de prueba 2: Nota1 válida, Nota2 nula
        val resultadoEsperado2 = 2.0
        val resultadoCalculado2 = gestorNotas.calcularPromedio(4.0, 0.0)
        assertEquals(resultadoEsperado2, resultadoCalculado2, 0.001)
        println("PRUEBA #2: $resultadoEsperado2, Resultado calculado: $resultadoCalculado2")

        // Caso de prueba 3: Nota1 nula, Nota2 válida
        val resultadoEsperado3 = 2.5
        val resultadoCalculado3 = gestorNotas.calcularPromedio(0.0, 5.0)
        assertEquals(resultadoEsperado3, resultadoCalculado3, 0.001)
        println("PRUEBA #3: $resultadoEsperado3, Resultado calculado: $resultadoCalculado3")

        // Caso de prueba 4: Notas nulas
        val resultadoEsperado4 = 1.0
        val resultadoCalculado4 = gestorNotas.calcularPromedio(0.0, 0.0)
        assertEquals(resultadoEsperado4, resultadoCalculado4, 0.001)
        println("RPRUEBA #4: $resultadoEsperado4, Resultado calculado: $resultadoCalculado4")
    }
}

