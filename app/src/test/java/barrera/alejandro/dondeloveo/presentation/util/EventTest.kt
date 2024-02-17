package barrera.alejandro.dondeloveo.presentation.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class EventTest {
    @Test
    fun `getContentIfNotHandled should return the content the first time it's invoked`() {
        val eventContent = "EventContent"
        val event = Event(eventContent)

        assertEquals(eventContent, event.getContentIfNotHandled())
    }

    @Test
    fun `getContentIfNotHandled should return null after the first time it's invoked`() {
        val eventContent = "EventContent"
        val event = Event(eventContent)

        event.getContentIfNotHandled()

        assertNull(event.getContentIfNotHandled())
    }

    @Test
    fun `peekContent should always return the content`() {
        val eventContent = "EventContent"
        val event = Event(eventContent)

        assertEquals(eventContent, event.peekContent())
        assertEquals(eventContent, event.peekContent())
    }
}