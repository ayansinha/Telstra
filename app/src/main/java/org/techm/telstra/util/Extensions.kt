package org.techm.telstra.util

import android.content.Context
import android.widget.Toast


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
