package hr.altagama.localizationtesting

import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setLocaleSpinner()
        showLocale()
        showFromResources()
        showDates()
        showNumbers()
        showSeparator()
    }

    fun showLocale() {
        addHeadline("Localizations")
        addLine("Locale.getDefault",
                Locale.getDefault().displayName)
        @Suppress("DEPRECATION")
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

    fun showFromResources() {
        addHeadline("Resources")
        addLine("R.string.localization_name",
                getString(R.string.localization_name))
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

    /**
     * Add one line od description and text
     */
    fun addLine(description: String, text: String) {
        mainList.addView(getExampleView(description, text))
    }

    /**
     * Construct a new ExampleView
     */
    fun getExampleView(description: String, text: String): ExampleView {
        val v = ExampleView(this)
        v.setDescription(description)
        v.setText(text)
        return v
    }

    /**
     * Add a title for the section
     */
    fun addHeadline(headln: String) {
        mainList.addView(getHeadline(headln))
    }

    /**
     * Construct a HeadlineView
     */
    fun getHeadline(headln: String): HeadlineView {
        val v = HeadlineView(this)
        v.setHeadline(headln)
        return v
    }

    /**
     * Spinner for choosing the app locale
     * It sets up the listener so the sreen will be redrawn on selection of a new language
     * Language changes are stored in the shared preferences
     */
    fun setLocaleSpinner() {
        val locales = Locale.getAvailableLocales()
        val languages = locales.map { s -> s.language }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sLocalization.adapter = adapter
        if (getLang() != "") {
            val pos = languages.indexOf(getLang())
            sLocalization.setSelection(pos, false)
        } else {
            val pos = languages.indexOf(Locale.getDefault().language)
            sLocalization.setSelection(pos, false)
        }

        sLocalization.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val language = parent!!.getItemAtPosition(pos).toString()
                // type 1 change
                setLang(language)
                val app = applicationContext as LocaleApp
                app.changeLang(language)

                mainList.removeAllViews()
                showLocale()
                showFromResources()
                showDates()
                showNumbers()
                showSeparator()
            }
        }
    }
}
