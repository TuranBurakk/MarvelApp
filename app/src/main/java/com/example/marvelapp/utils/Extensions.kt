package com.example.marvelapp.utils

import android.app.AlertDialog
import android.content.Context
import android.view.View

fun View.gone() {
    visibility = View.GONE
}
fun View.show() {
    visibility = View.VISIBLE
}
fun showDialog(
    context: Context,
    title: String? = "Uyarı",
    message: String?,
    cancellable: Boolean = true,
    action: () -> Unit = {}
){
    AlertDialog.Builder(context).apply {
        setTitle(title)
        setMessage(message)
        setCancelable(cancellable)
        setPositiveButton("Tamam"){_,_ -> action.invoke()}
    }.show()
}
fun convert(string: String): String {
    var newString = string
    if (string.startsWith("http:")) {
        newString = string.replace("http", "https")
    }
    return newString
}