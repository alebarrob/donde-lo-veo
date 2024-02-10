package barrera.alejandro.dondeloveo.presentation.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(text: UiText) = Toast.makeText(this, text.asString(this), Toast.LENGTH_LONG).show()