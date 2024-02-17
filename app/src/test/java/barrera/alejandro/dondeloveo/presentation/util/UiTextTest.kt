package barrera.alejandro.dondeloveo.presentation.util

import android.content.Context
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class UiTextTest {
    private val context = mock(Context::class.java)

    @Test
    fun `UiText asString returns correct String`() {
        val dynamicStringText = "Text"
        val stringResourceText = "String Resource"
        val dynamicString = UiText.DynamicString(dynamicStringText)
        val stringResource = UiText.StringResource(resId = 1)

        `when`(context.getString(anyInt())).thenReturn(stringResourceText)

        assertEquals(dynamicStringText, dynamicString.asString(context))
        assertEquals(stringResourceText, stringResource.asString(context))
    }
}