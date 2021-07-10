package kalifornia

/**
 * Transition interface that requires the implementation of a transit method
 *
 * @author Victor Warno
 *
 * @param <R> type of stateful object extending StateAdapter
 * @param <S> type of state
 * @param <T> type of event
 */
interface Transition<R: StateAdapter<S>, S> {

    /**
     * Executes transition
     *
     * @return the source state
     */
    fun transit(item: R)

}