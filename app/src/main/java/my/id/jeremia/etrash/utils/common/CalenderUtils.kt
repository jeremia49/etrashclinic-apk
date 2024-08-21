package my.id.jeremia.etrash.utils.common

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone


object CalendarUtils {

    private const val TAG = "CalendarUtils"

    fun now(): Date = Calendar.getInstance().time

    fun getTodayFormattedDate(): String =
        try {
            "Today, " + SimpleDateFormat("EEEE dd", Locale.ENGLISH).format(Date())
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            "Today"
        }

    fun getFormattedCurrentTime(): String =
        try {
            SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date())
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            "Current Time"
        }

    fun getDateFromString(date:String):Date?{
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("UTC") // Handle UTC time
        try {
            val trimmedDate: String = date.substring(0, 19) // "2024-08-15T10:56:48"
            val javaDate = formatter.parse(trimmedDate)
            println("Converted Date: $javaDate")
            return javaDate
        } catch (e: ParseException) {
            e.printStackTrace()
            return null
        }
    }

    fun getFormattedDateTime(date:Date):String?{
        return "${getFormattedDate(date)} ${getFormattedTime(date)}"
    }

    fun getFormattedDate(date: Date): String? =
        try {
            SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(date)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            null
        }

    fun getFormattedTime(date: Date): String? =
        try {
            SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(date)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            null
        }

    fun getFormattedTimeSecond(date: Date): String? =
        try {
            SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH).format(date)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            null
        }

    fun getFormattedDateInDayAndMonth(date: Date): String =
        try {
            SimpleDateFormat("dd MMM", Locale.ENGLISH).format(date)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            ""
        }

    fun parseDayMonthYear(millis: Long): Triple<String, String, String>? =
        try {
            val calendar = Calendar.getInstance()
            calendar.time.time = millis

            val day = SimpleDateFormat("dd", Locale.ENGLISH).format(calendar.time)
            val month = SimpleDateFormat("MMMM", Locale.ENGLISH).format(calendar.time)
            val year = SimpleDateFormat("YYYY", Locale.ENGLISH).format(calendar.time)

            Triple(day, month, year)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            null
        }

    fun parseDay(date: Date): String? =
        try {
            SimpleDateFormat("dd", Locale.ENGLISH).format(date)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            null
        }

    fun parseMonth(date: Date): String? =
        try {
            SimpleDateFormat("MMMM", Locale.ENGLISH).format(date)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            null
        }

    fun parseYear(date: Date): String? =
        try {
            SimpleDateFormat("YYYY", Locale.ENGLISH).format(date)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            null
        }

    fun parseTime(date: Date): String? =
        try {
            SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(date)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            null
        }
}