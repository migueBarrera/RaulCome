package com.mbmdevelop.raulcome

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class DataHelperUnitTest {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Test
    fun calcularTiempoTranscurrido_Diferencia7diasDosHorasTreintaMinutos_ReturnsResultadoEsperado() {
        val fechaInicial = formatter.parse("2023-05-15 10:00:00")
        val fechaFinal = formatter.parse("2023-05-22 12:30:00")
        val resultado = calcularTiempoTranscurrido(fechaInicial!!, fechaFinal!!)
        assertEquals("7 días y 2 horas", resultado)
    }

    @Test
    fun calcularTiempoTranscurrido_DiferenciaUnDiaDosHorasTreintaMinutos_ReturnsResultadoEsperado() {
        val fechaInicial = formatter.parse("2023-05-21 10:00:00")
        val fechaFinal = formatter.parse("2023-05-22 12:30:00")
        val resultado = calcularTiempoTranscurrido(fechaInicial!!, fechaFinal!!)
        assertEquals("1 día y 2 horas", resultado)
    }

    @Test
    fun calcularTiempoTranscurrido_DiferenciaCeroDiasCincoHorasCuarentaCincoMinutos_ReturnsResultadoEsperado() {
        val fechaInicial = formatter.parse("2023-05-22 15:00:00")
        val fechaFinal = formatter.parse("2023-05-22 20:45:00")
        val resultado = calcularTiempoTranscurrido(fechaInicial!!, fechaFinal!!)
        assertEquals("5 horas y 45 minutos", resultado)
    }

    @Test
    fun calcularTiempoTranscurrido_DiferenciaCeroDiasCeroHorasQuinceMinutos_ReturnsResultadoEsperado() {
        val fechaInicial = formatter.parse("2023-05-22 23:45:00")
        val fechaFinal = formatter.parse("2023-05-23 00:00:00")
        val resultado = calcularTiempoTranscurrido(fechaInicial!!, fechaFinal!!)
        assertEquals("15 minutos", resultado)
    }

    @Test
    fun calcularTiempoTranscurrido_DiferenciaCeroDiasCeroHorasCeroMinutos_ReturnsResultadoEsperado() {
        val fechaInicial = formatter.parse("2023-05-23 12:00:00")
        val fechaFinal = formatter.parse("2023-05-23 12:00:00")
        val resultado = calcularTiempoTranscurrido(fechaInicial!!, fechaFinal!!)
        assertEquals("0 minutos", resultado)
    }
}