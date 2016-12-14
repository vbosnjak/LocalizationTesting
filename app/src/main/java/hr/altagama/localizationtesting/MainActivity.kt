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
        add("Headline1",
                "Locale.getDefault",
                Locale.getDefault().displayName)
        add("Headline2",
                "resources.configuration.locale",
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    resources.configuration.locales[0].displayName
                else
                    resources.configuration.locale.displayName)
    }

    fun showDates() {
        add("Headline3",
                "android.text.format.DateFormat.getDateFormat()",
                DateFormat.getDateFormat(this).format(Date()))
        add("Headline4",
                "android.text.format.DateFormat.getMediumDateFormat()",
                DateFormat.getMediumDateFormat(this).format(Date()))
        add("Headline5",
                "android.text.format.DateFormat.getLongDateFormat()",
                DateFormat.getLongDateFormat(this).format(Date()))
    }

    fun showNumbers() {
        add("Headline6",
                "String.format",
                String.format("%f", 1000000.1555))
        add("Headline7",
                "DecimalFormat.getInstance().format",
                DecimalFormat.getInstance().format(1000000.1555))
        add("Headline8",
                "DecimalFormat.getCurrencyInstance().format",
                DecimalFormat.getCurrencyInstance().format(1000000.1555))
    }

    fun add(headline: String, description: String, text: String) {
        mainList.addView(getHeadline(headline))
        mainList.addView(getView(description, text))
    }

    fun getView(description: String, text: String): ExampleView {
        val v = ExampleView(this)
        v.setDescription(description)
        v.setText(text)
        return v
    }

    fun getHeadline(headln: String): HeadlineView {
        val v = HeadlineView(this)
        v.setHeadline(headln)
        return v
    }
}
