package com.example.proyecto_appnotas

import org.junit.Assert
import org.junit.Test

class GestorNotasTest {

    @Test
    fun `calcular promedio con dos notas`() {
        val prueba = TestPromedio()
        val notas = listOf(3.5, 4.0)
        val promedio = prueba.calcularPromedio(notas)
        Assert.assertEquals(3.75, promedio, 0.01)
        println("Prueba 1: Resultado esperado: 3.75, Resultado obtenido: $promedio")
    }
    @Test
    fun `calcular promedio con tres notas`() {
        val prueba = TestPromedio()
        val notas = listOf(3.5, 4.0, 5.0)
        val promedio = prueba.calcularPromedio(notas)
        Assert.assertEquals(3.75, promedio, 0.01)
        println("Prueba 1: Resultado esperado: 3.5, Resultado obtenido: $promedio")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `lanzar excepcion cuando menos de dos notas`() {
        val prueba = TestPromedio()
        val notas = listOf(3.5)
        prueba.calcularPromedio(notas)
        println("Prueba 2: Excepción esperada lanzada correctamente")
    }
}