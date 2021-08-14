package com.example.swipepeople.view.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.swipepeople.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job
    private lateinit var progressDialog: ProgressDialog

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = Job()
    }

    protected fun showProgress() {
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading")
        progressDialog.setCancelable(true)
        progressDialog.show()
    }

    protected fun dismissProgress() {
        try {
            progressDialog.dismiss()
        } catch (e: Exception) {
            Log.e("ProgressDialog", e.localizedMessage!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}