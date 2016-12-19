package hr.altagama.localizationtesting

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.*

class LocaleApp : Application() {

    private var locale: Locale? = null

    /**
     * Sets up the locale on configuration change
     * type 1
     */
    @Suppress("DEPRECATION")
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (locale != null) {
            Locale.setDefault(locale)
            val config = Configuration(newConfig)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                config.locales = LocaleList(locale)
            else
                config.locale = locale
            baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        }
    }

    /**
     * Read from preferences and set the language
     * type 1
     */
    override fun onCreate() {
        super.onCreate()
        val pref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val lang = pref.getString("language", "")
        locale = Locale(lang)
        changeLang(lang)
    }


    /**
     * Change the language at runtime
     * type 1
     */
    @Suppress("DEPRECATION")
    fun changeLang(lang: String) {
        val config = baseContext.resources.configuration
        val currentLang = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) config.locales[0]
        else config.locale
        if ("" != lang && lang != currentLang.language) {
            val pref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
            pref.edit().putString("language", lang).commit()
            locale = Locale(lang)
            Locale.setDefault(locale)
            val conf = Configuration(config)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                conf.locales = LocaleList(locale)
            else
                conf.locale = locale
            baseContext.resources.updateConfiguration(conf, baseContext.resources.displayMetrics)
        }
    }

    /**
     * Change the language by attaching a custom ContextWrapper
     * Will not work at runtime - the app needs to be restarted (remember fo force close it to kill
     * remaining instances)
     * type 2 mod
     */
    override fun attachBaseContext(base: Context?) {
        val pref = base!!.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        super.attachBaseContext(wrap(base, pref.getString("language", "")))
    }
}