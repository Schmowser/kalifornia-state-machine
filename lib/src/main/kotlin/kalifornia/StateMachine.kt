package kalifornia

import java.lang.IllegalArgumentException

/**
 * State Machine that accepts events for state handling
 *
 * @author Victor Warno
 *
 * @param <T> type of stateful object extending StateAdapter
 * @param <S> type of state
 * @param <E> type of event
 */
class StateMachine<T: StateAdapter<S>, S, E> (private val configuration: StateMachineConfiguration<T, S, E>) {

    inline fun <reified T> start() {
        println("Start State Machine for " + T::class.java)
    }

    /**
     * Will execute transitions and actions for an instance of stateful object based on the provided event
     *
     * @param instance the type of a member in this group.
     * @param event the type of a member in this group.
     *
     * @throws IllegalArgumentException If the given event has not been registered in the state machine configuration,
     * this method will throw an exception
     */
    fun sendEvent(instance: T, event: E) {
        if (!configuration.events.contains(event)) {
            throw IllegalArgumentException("Event has to be configured for this State Machine")
        }
        configuration.transitions.forEach {
            it.invoke(event, instance)
        }
    }
}
