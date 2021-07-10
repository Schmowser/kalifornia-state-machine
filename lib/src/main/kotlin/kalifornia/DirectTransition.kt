package kalifornia

/**
 * Simple one-directional transition that sets the state of an object to a target state given it is in the provided
 * source state
 *
 * @author Victor Warno
 * @param <T> type of stateful object extending StateAdapter
 * @param <S> type of state
 */
class DirectTransition<T: StateAdapter<S>, S> (private val source: S, private val target: S): Transition<T, S> {

    /**
     * Executes transition
     *
     * @return the source state
     */
    override fun transit(item: T) {
        if (item.state == source) {
            item.state = target
        }
    }

}