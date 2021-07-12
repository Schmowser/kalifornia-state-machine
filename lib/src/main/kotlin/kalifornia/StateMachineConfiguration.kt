package kalifornia

/**
 * State Machine configuration
 *
 * @author Victor Warno
 *
 * @param <T> type of stateful object extending StateAdapter
 * @param <S> type of state
 * @param <E> type of event
 */
class StateMachineConfiguration<T: StateAdapter<S>, S, E> {

    var states = mutableListOf<S>()
    val events = mutableListOf<E>()
    val transitions = mutableListOf<(E, T) -> Unit>()

    override fun toString(): String {
        return "states: $states; events: $events; transitions: $transitions"
    }
}