package kalifornia

/**
 * Event Handler storing and execution transitions based on events
 *
 * @author Victor Warno
 *
 * @param <T> type of stateful object extending StateAdapter
 * @param <S> type of state
 * @param <E> type of event
 *
 * @property transitions Mutable Map holding list of transitions triggered by a specific event
 */
class EventHandler<T: StateAdapter<S>, S, E> {

    private val transitions: MutableMap<E, MutableList<DirectTransition<T, S>>> = mutableMapOf()

    fun addEvent(event: E, transition: DirectTransition<T, S>) {
        val eventTransitions = transitions[event] ?: mutableListOf()
        eventTransitions.add(transition)
        transitions[event] = eventTransitions
    }

    fun executeEvent(instance: T, event: E) {
        transitions[event]?.forEach { it.transit(instance) }
    }

}
