package hr.altagama.localizationtesting

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.*

/**
 * Create a ContextWrapper with the specified language
 * Use it in Application or Activity attachBaseContext
 */
@Suppress("DEPRECATION")
fun wrap(context: Context, language: String): ContextWrapper {
    var ctx = context
    val config = ctx.resources.configuration
    val sysLocale =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) config.locales.get(0)
            else config.locale

    if (language != "" && language != sysLocale.language) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val conf = Configuration(config)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            conf.locales = LocaleList(locale)
        else
            conf.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            ctx = ctx.createConfigurationContext(conf)
        else
            ctx.resources.updateConfiguration(conf, ctx.resources.displayMetrics)
    }
    return ContextWrapper(ctx)
}
