package hr.altagama.localizationtesting

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLocale()
        showDates()
        showNumbers()
    }

    fun showLocale() {
        add("Locale.getDefault",
                Locale.getDefault().displayName)
        add("resources.configuration.locale",
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    resources.configuration.locales[0].displayName
                else
                    resources.configuration.locale.displayName)
    }

    fun showDates() {
        add("android.text.format.DateFormat.getDateFormat()",
                DateFormat.getDateFormat(this).format(Date()))
        add("android.text.format.DateFormat.getMediumDateFormat()",
                DateFormat.getMediumDateFormat(this).format(Date()))
        add("android.text.format.DateFormat.getLongDateFormat()",
                DateFormat.getLongDateFormat(this).format(Date()))
    }

    fun showNumbers() {
        add("String.format",
                String.format("%f", 1000000.1555))
        add("DecimalFormat.getInstance().format",
                DecimalFormat.getInstance().format(1000000.1555))
        add("DecimalFormat.getCurrencyInstance().format",
                DecimalFormat.getCurrencyInstance().format(1000000.1555))
    }

    fun add(description: String, text: String) {
        mainList.addView(getView(description, text))
    }

    fun getView(description: String, text: String): ExampleView {
        val v = ExampleView(this)
        v.setDescription(description)
        v.setText(text)
        return v
    }
}
