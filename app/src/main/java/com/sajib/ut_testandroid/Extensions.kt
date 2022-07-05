package com.sajib.ut_testandroid

import android.widget.EditText


fun EditText.getStringText(): String {
    return this.text.toString()
}

fun EditText.getInr(): Int {
    return this.text.toString().toInt()
}
