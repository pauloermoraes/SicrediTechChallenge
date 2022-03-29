package br.com.sicredi.techchallenge.global.tools

import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import android.view.ContextThemeWrapper
import android.view.Window
import br.com.sicredi.techchallenge.R

object AlertDialogTools {

    fun alertDialog(
        activity: Activity,
        title: String = "",
        message: String,
        cancelable: Boolean = true,
        callback: (() -> Unit)? = null
    ) {
        activity.runOnUiThread {
            try {
                val dialog: AlertDialog = initDialog(
                    activity,
                    title,
                    message
                )
                dialog.setButton(
                    AlertDialog.BUTTON_POSITIVE,
                    activity.getString(R.string.ok_label)
                ) { _, _ ->
                    callback?.invoke()
                }
                if (title.isEmpty()) {
                    val window = dialog.window
                    window?.requestFeature(Window.FEATURE_NO_TITLE)
                }
                dialog.setCancelable(cancelable)
                dialog.show()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    private fun initDialog(
        activity: Activity,
        title: String?,
        message: String?,
    ): AlertDialog {
        var dialog: AlertDialog
        var builder: AlertDialog.Builder
        try {
            if (Build.VERSION.SDK_INT >= 22) {
                builder = AlertDialog.Builder(
                    ContextThemeWrapper(
                        activity,
                        androidx.appcompat.R.style.Theme_AppCompat_Dialog
                    )
                )
                if (title != null && title.isNotEmpty()) {
                    builder = builder.setTitle(title)
                }
                if (message != null && message.isNotEmpty()) {
                    builder = builder.setMessage(message)
                }
                dialog = builder.create()
            } else {
                builder = AlertDialog.Builder(
                    ContextThemeWrapper(
                        activity,
                        androidx.appcompat.R.style.Theme_AppCompat_Dialog
                    )
                )
                if (title != null && title.isNotEmpty()) {
                    builder = builder.setTitle(title)
                }
                if (message != null && message.isNotEmpty()) {
                    builder = builder.setMessage(message)
                }
                dialog = builder.create()
            }
        } catch (e: Exception) {
            builder = AlertDialog.Builder(
                ContextThemeWrapper(
                    activity,
                    androidx.appcompat.R.style.Theme_AppCompat_Dialog
                )
            )
            if (title != null && title.isNotEmpty()) {
                builder = builder.setTitle(title)
            }
            if (message != null && message.isNotEmpty()) {
                builder = builder.setMessage(message)
            }
            dialog = builder.create()
        }
        return dialog
    }

}