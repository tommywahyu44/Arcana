package com.example.arcana.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.arcana.R
import com.google.api.ContextRule

class NotificationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val bundle = intent.extras
        var title = bundle?.getString("title") ?: ""
        var message = bundle?.getString("message") ?: ""

        if(!title.isNullOrBlank()){
            showAlertDialog(title,message)
        }

    }

    private fun showAlertDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(
                applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(
                applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT
            ).show()

            dialog.dismiss()
        }

        builder.setNeutralButton("Maybe") { dialog, which ->
            Toast.makeText(
                applicationContext,
                "Maybe", Toast.LENGTH_SHORT
            ).show()

            dialog.dismiss()
        }
        builder.show()
    }

    companion object {
        fun startIntent(activity: Context, title: String, message: String) {
            val intent = Intent(activity, NotificationActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("message", message)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent)
        }
    }

}