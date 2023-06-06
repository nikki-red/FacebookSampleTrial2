package com.example.facebooksampletrial2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    /*
    This block declares a companion object,
    which contains a constant STATUS used as a key to pass the status value between activities.
    The intent() function is a convenience method that
    creates an Intent with the provided status value.
    */
    companion object {
        private const val STATUS = "status"
        fun intent(status: Status) = Intent().apply { putExtra(STATUS, status.name) }
    }
    /*
    This block defines an enumeration Status,
    which represents different status values (OK, WARNING, and ERROR).
    */
    enum class Status {
        OK,
        WARNING,
        ERROR
    }
    /*
    This is the onCreate() method, which is called when the activity is created.
    It sets the layout for the activity (activity_main.xml) using setContentView().
    It also sets up the toolbar.
    It retrieves the TextView from the layout and gets the status value from the intent
    using the intent.string() extension function.
    If no status value is provided, it defaults to Status.OK.name.
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val textView = findViewById<TextView>(R.id.text_view)
        val status = Status.valueOf(intent.string(STATUS, Status.OK.name))
        /*
        This when block checks the status value and updates the textView accordingly.
        It sets the text color and text content based on the selected status.
        */
        when (status) {
            Status.OK ->
                textView.run {
                    setTextColor(context.color(R.color.ok))
                    text = "Status is OK"
                }
            Status.WARNING ->
                textView.run {
                    setTextColor(context.color(R.color.warning))
                    text = "Status is WARNING"
                }
            Status.ERROR ->
                textView.run {
                    setTextColor(context.color(R.color.error))
                    text = "Status is ERROR"
                }
        }
        /*
        This sets a click listener on the FloatingActionButton (R.id.fab)
        and shows a Snackbar when clicked.
        */
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "This is a snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }
    /*
    This method is responsible for inflating the options menu (menu_main.xml) for the activity.
    */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    /*
    private fun Intent.string(name: String, defValue: String): String {
        if (hasExtra(name)) {
            return getStringExtra(name)
        }
        return defValue
    }*/
    /*
    This is a private extension function for the Intent class
    that helps retrieve a String extra value from the intent.
    It checks if the intent has the specified extra (name).
    If it does, it returns the value using getStringExtra().
    If the extra is not present, it returns the provided default value (defValue).
    */
    private fun Intent.string(name: String, defValue: String): String {
        return getStringExtra(name) ?: defValue
    }
    /*
    This is a private extension function for the Context class
    that helps retrieve a color resource (@ColorRes)
    and returns the corresponding color value using ContextCompat.getColor().
    */
    private fun Context.color(@ColorRes color: Int): Int {
        return ContextCompat.getColor(this, color)
    }
}