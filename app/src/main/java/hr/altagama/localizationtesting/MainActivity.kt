package hr.altagama.localizationtesting

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLocale()
        showDates()
        showNumbers()
        showSeparator()
    }

    fun showLocale() {
        addHeadline("Localizations")
        addLine("Locale.getDefault",
                Locale.getDefault().displayName)
        addLine("resources.configuration.locale",
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    resources.configuration.locales[0].displayName
                else
                    resources.configuration.locale.displayName)
    }

    fun showDates() {
        addHeadline("Dates")
        addLine("android.text.format.DateFormat.getDateFormat()",
                DateFormat.getDateFormat(this).format(Date()))
        addLine("android.text.format.DateFormat.getMediumDateFormat()",
                DateFormat.getMediumDateFormat(this).format(Date()))
        addLine("android.text.format.DateFormat.getLongDateFormat()",
                DateFormat.getLongDateFormat(this).format(Date()))
        addLine("android.text.format.DateFormat.getTimeFormat()",
                DateFormat.getTimeFormat(this).format(Date()))
    }

    fun showNumbers() {
        addHeadline("Numbers")
        addLine("String.format",
                String.format("%f", 1000000.1555))
        addLine("DecimalFormat.getInstance().format",
                DecimalFormat.getInstance().format(1000000.1555))
        addLine("DecimalFormat.getCurrencyInstance().format",
                DecimalFormat.getCurrencyInstance().format(1000000.1555))
    }

    fun showSeparator() {
        addHeadline("Separators")
        addLine("DecimalFormatSymbols.decimalSeparator",
                DecimalFormatSymbols().decimalSeparator.toString())
        addLine("DecimalFormatSymbol ThousandSeparator",
                DecimalFormatSymbols().groupingSeparator.toString())
        addLine("DecimalFormatSymbol.Currency",
                DecimalFormatSymbols().currencySymbol)
    }


    fun addLine(description: String, text: String) {
        mainList.addView(getView(description, text))
    }

    fun getView(description: String, text: String): ExampleView {
        val v = ExampleView(this)
        v.setDescription(description)
        v.setText(text)
        return v
    }

    fun addHeadline(headln: String) {
        mainList.addView(getHeadline(headln))
    }

    fun getHeadline(headln: String): HeadlineView {
        val v = HeadlineView(this)
        v.setHeadline(headln)
        return v
    }
}
