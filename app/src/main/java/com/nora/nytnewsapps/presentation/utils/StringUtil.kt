package com.nora.nytnewsapps.presentation.utils

fun CharSequence.isBlankOrNotBlank(): Boolean {
    return !isBlank() || isBlank()
}
