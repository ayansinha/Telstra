package org.techm.telstra.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


/**
 * extension function for toast short
 */
fun Context.toastShort(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this , message , duration).show()
}

/**
 * extension function for toast long
 */
fun Context.toastLong(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this , message , duration).show()
}


/**
 * extension function for snack-bar
 */
fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this , message , duration).show()
}


