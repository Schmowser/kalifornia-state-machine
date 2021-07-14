package kalifornia

import kalifornia.util.TestClass
import java.lang.IllegalArgumentException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class StateMachineTest {
    @Test fun `that sending an event transits the test object from source state to target state`() {
        val testInstance = TestClass()
        val testConfig = config<TestClass, String, String> {
            states("S1", "S2")
            events("E1")
            transitions(
                onEvent("E1", "S1" transitsTo "S2")
            )
        }.build()

        val classUnderTest = StateMachine(testConfig)

        classUnderTest.start<TestClass>()
        classUnderTest.sendEvent(testInstance, "E1")

        assertEquals("S2", testInstance.state, "Test object's state did not change correctly.")
    }

    @Test fun `that sending an event does not transits the test object to a different state`() {
        val testInstance = TestClass()
        testInstance.state = "S2"
        val testConfig = config<TestClass, String, String> {
            states("S1", "S2")
            events("E1")
            transitions(
                onEvent("E1", "S1" transitsTo "S2")
            )
        }.build()

        val classUnderTest = StateMachine(testConfig)

        classUnderTest.start<TestClass>()
        classUnderTest.sendEvent(testInstance, "E1")

        assertEquals("S2", testInstance.state, "Test object's state changed.")
    }

    @Test fun `that sending an unregistered event throws an exception`() {
        val testInstance = TestClass()
        testInstance.state = "S2"
        val testConfig = config<TestClass, String, String> {
            states("S1", "S2")
            events("E1")
            transitions(
                onEvent("E1", "S1" transitsTo "S2")
            )
        }.build()

        val classUnderTest = StateMachine(testConfig)

        classUnderTest.start<TestClass>()

        assertFailsWith<IllegalArgumentException> { classUnderTest.sendEvent(testInstance, "E2") }
    }
}
