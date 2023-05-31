package com.lwsoftware.abletotrack.extensions

import java.util.Base64

fun Boolean.toInt() = if (this) 1 else 0
fun ByteArray.toBase64(): String = String(Base64.getEncoder().encode(this))
fun Int.toBoolean() = this == 1