package com.mbmdevelop.raulcome

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit
fun isToday(date: Date): Boolean {
    val calHoy = Calendar.getInstance() // Calendar con la fecha de hoy
    val calFecha = Calendar.getInstance() // Calendar con la fecha de tu variable

    calFecha.time = date // Establecer la fecha de tu variable en el Calendar

    val esHoy = calHoy.get(Calendar.YEAR) == calFecha.get(Calendar.YEAR) &&
            calHoy.get(Calendar.MONTH) == calFecha.get(Calendar.MONTH) &&
            calHoy.get(Calendar.DAY_OF_MONTH) == calFecha.get(Calendar.DAY_OF_MONTH)

    return esHoy;
}

fun getDayLabel(date: Date): CharSequence? {
    val patternDay = "MM/dd"
    val formatToday = SimpleDateFormat(patternDay)
    return formatToday.format(date)
}

fun calculateMinutes(fechaInicial:Date, fechaFinal: Date): Int{
    val diferenciaMillis = fechaFinal.time - fechaInicial.time
    val diferenciaMinutos = TimeUnit.MILLISECONDS.toMinutes(diferenciaMillis)

    return diferenciaMinutos.toInt()
}

fun getDateWithMinutesSUM(minutes: Int): Date{
    val fecha = Calendar.getInstance()
    fecha.time = Date()
    fecha.add(Calendar.MINUTE, minutes)

    return fecha.time
}

fun calcularTiempoTranscurridoDuration(fechaInicial: Date, fechaFinal: Date): Long {
    val diferenciaMillis = fechaFinal.time - fechaInicial.time
    return TimeUnit.MILLISECONDS.toSeconds(diferenciaMillis)
}

fun convertirTiempoALegible(tiempoEnSegundos: Long): String {
    val horas: Long = tiempoEnSegundos / 3600
    val minutos: Long = (tiempoEnSegundos % 3600) / 60

    return String.format("%d horas y %d minutos", horas, minutos)
}
fun calcularTiempoTranscurrido(fechaInicial: Date, fechaFinal: Date): String {
    val fechaInicialLdt = fechaInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    val fechaFinalLdt = fechaFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

    val duracion = Duration.between(fechaInicialLdt, fechaFinalLdt)

    val dias = duracion.toDays()
    val horas = duracion.toHours() % 24
    val minutos = duracion.toMinutes() % 60

    val textoDias = if (dias == 1L) "1 día" else "$dias días"
    val textoHoras = if (horas == 1L) "1 hora" else "$horas horas"
    val textoMinutos = if (minutos == 1L) "1 minuto" else "$minutos minutos"

    val partes = mutableListOf<String>()
    if (dias > 0) partes.add(textoDias)
    if (horas > 0) partes.add(textoHoras)
    if (minutos > 0 && dias == 0L) partes.add(textoMinutos)

    return when {
        partes.isEmpty() -> "0 minutos"
        partes.size == 1 -> partes.first()
        partes.size == 2 -> "${partes[0]} y ${partes[1]}"
        else -> "${partes[0]}, ${partes[1]} y ${partes[2]}"
    }
}