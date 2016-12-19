package hr.altagama.localizationtesting

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * Base class for managing languages in the activity
 * type 2
 */
open class BaseActivity : AppCompatActivity() {

    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        pref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    /**
     * Attach a modified ContextWrapper to the activity so it can provide custom language resources
     * type 2
     */
    override fun attachBaseContext(newBase: Context?) {
        pref = newBase!!.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        super.attachBaseContext(wrap(newBase, getLang()))
    }

    fun getLang(): String {
        return pref.getString("language", "")
    }

    fun setLang(lang: String) {
        pref.edit().putString("language", lang).apply()
    }
}