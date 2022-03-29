package br.com.sicredi.techchallenge.global.tools

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object KeyboardTools {
    fun hide(activity: Activity) {
        val view = activity.currentFocus ?: return
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager ?: return
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}