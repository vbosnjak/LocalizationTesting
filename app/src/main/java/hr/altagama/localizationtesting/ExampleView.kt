package hr.altagama.localizationtesting

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.example_view.view.*

class ExampleView : LinearLayout {

    constructor(context: Context) : super(context) {
        if (!isInEditMode)
            init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        if (!isInEditMode)
            init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        if (!isInEditMode)
            init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        if (!isInEditMode)
            init()
    }

    private fun init() {
        View.inflate(context, R.layout.example_view, this)
        isSaveEnabled = true
    }

    fun setDescription(desc: String) {
        description.text = desc
    }

    fun setText(tex: String) {
        text.text = tex
    }
}
