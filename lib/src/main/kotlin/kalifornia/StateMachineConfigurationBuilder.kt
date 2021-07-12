package kalifornia

/**
 * Builder that creates a StateMachineConfiguration
 *
 * @author Victor Warno
 *
 * @param <T> type of stateful object extending StateAdapter
 * @param <S> type of state
 * @param <E> type of event
 */
class StateMachineConfigurationBuilder<T: StateAdapter<S>, S, E> {

    private val configuration = StateMachineConfiguration<T, S, E>()

    fun build(): StateMachineConfiguration<T, S, E> {
        return configuration
    }

    fun states(vararg states: S) {
        if (states.isEmpty()) {
            throw IllegalArgumentException("At least one state should be defined")
        }
        this.configuration.states.addAll(states)
    }

    fun events(vararg events: E) {
        if (events.isEmpty()) {
            throw IllegalArgumentException("At least one state should be defined")
        }
        this.configuration.events.addAll(events)
    }

    fun transitions(vararg transitions: (E, T) -> Unit) {
        if (transitions.isEmpty()) {
            throw IllegalArgumentException("At least one state should be defined")
        }
        this.configuration.transitions.addAll(transitions)
    }

    infix fun <T: StateAdapter<S>, S> S.transitsTo(otherState: S): DirectTransition<T, S> {
        return DirectTransition(this, otherState)
    }
}

fun <T: StateAdapter<S>, S, E> config(
    init: StateMachineConfigurationBuilder<T, S, E>.() -> Unit
): StateMachineConfigurationBuilder<T, S, E> {
    return StateMachineConfigurationBuilder<T, S, E>().apply(init)
}

fun <T: StateAdapter<S>, S, E> onEvent(
    event: E, transition: DirectTransition<T, S>
): (E, T) -> Unit {
    return { e, t -> if (e == event) transition.transit(t) }
}