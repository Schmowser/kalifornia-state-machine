package kalifornia

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

    fun sendEvent(instance: T, event: E) {
        configuration.transitions.forEach {
            it.invoke(event, instance)
        }
    }
}
