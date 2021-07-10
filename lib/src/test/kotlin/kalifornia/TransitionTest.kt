package kalifornia

import kalifornia.util.TestClass
import kotlin.test.Test
import kotlin.test.assertEquals

class TransitionTest {
    @Test fun testSomeLibraryMethod() {
        val testInstance = TestClass()
        val classUnderTest = DirectTransition<TestClass, String>("S1", "S2")

        classUnderTest.transit(testInstance)

        assertEquals("S2", testInstance.state, "Test object's state did not change correctly.")
    }
}
