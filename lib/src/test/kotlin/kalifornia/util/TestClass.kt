package kalifornia.util

import kalifornia.EnableStateMachine
import kalifornia.StateAdapter

@EnableStateMachine
class TestClass : StateAdapter<String>("S1") {
    fun someLibraryMethod(): Boolean {
        return true
    }
}
