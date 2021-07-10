package kalifornia

/**
 * Event Handler storing and execution transitions based on events
 * Objects that want to use a State Machine need to extend this class
 *
 * @author Victor Warno
 *
 * @param <S> type of state
 *
 * @param initialState initial state an instance of this object is in
 */
open class StateAdapter<S>(initialState: S) {
    internal var state = initialState
}
