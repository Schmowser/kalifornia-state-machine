package kalifornia

import kalifornia.util.TestClass
import kotlin.test.Test
import kotlin.test.assertEquals

class StateMachineTest {
    @Test fun `that sending an event transits the test object from source state to target state`() {
        val testInstance = TestClass()
        testInstance.state = "S1"
        val testEventHandler = EventHandler<TestClass, String, String>()
        val s1ToS2 = DirectTransition<TestClass, String>("S1", "S2")
        testEventHandler.addEvent("E1", s1ToS2)

        val classUnderTest = StateMachine(testEventHandler)

        classUnderTest.start<TestClass>()
        classUnderTest.sendEvent(testInstance, "E1")
        assertEquals("S2", testInstance.state, "Test object's state did not change correctly.")
    }

    @Test fun `that sending an event does not transits the test object to a different state`() {
        val testInstance = TestClass()
        testInstance.state = "S2"
        val testEventHandler = EventHandler<TestClass, String, String>()
        val s1ToS2 = DirectTransition<TestClass, String>("S1", "S2")
        testEventHandler.addEvent("E1", s1ToS2)

        val classUnderTest = StateMachine(testEventHandler)

        classUnderTest.start<TestClass>()
        classUnderTest.sendEvent(testInstance, "E1")
        assertEquals("S2", testInstance.state, "Test object's state changed.")
    }
}
