package org.techtown.medexhealing

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

class ModifyDialog(context:Context) {
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun showDialog()
    {
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val edit_pw = dialog.findViewById<EditText>(R.id.pw_edit)

        dialog.findViewById<Button>(R.id.finish_button).setOnClickListener {
            onClickListener.onClicked(edit_pw.text.toString())
            dialog.dismiss()
        }
    }

    interface OnDialogClickListener
    {
        fun onClicked(modifypw: String)
    }
}