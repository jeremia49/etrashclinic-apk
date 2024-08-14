package my.id.jeremia.etrash.utils.common

import androidx.core.util.PatternsCompat

fun String.isValidEmail() =
    this.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()