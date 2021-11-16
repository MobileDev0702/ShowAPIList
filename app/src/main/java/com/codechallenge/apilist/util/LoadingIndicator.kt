package com.codechallenge.apilist.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.codechallenge.apilist.R

class LoadingIndicator {

    var loadingIndicator: LoadingIndicator? = null
    lateinit var mDialog: Dialog

    fun getInstance(): LoadingIndicator? {
        if (loadingIndicator == null) {
            loadingIndicator = LoadingIndicator()
        }
        return loadingIndicator
    }

    fun showProgress(context: Context) {
        mDialog = Dialog(context)
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog.setContentView(R.layout.loadingindicator)
        mDialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        mDialog.window!!.setGravity(Gravity.CENTER)
        mDialog.setCancelable(false)
        mDialog.show()
    }

    fun hideProgress() {
        mDialog.dismiss()
    }
}