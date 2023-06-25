package knuth
abstract  class Cons<T> () {
    abstract fun head(): T
    abstract internal fun tail(): Cons<T>
    abstract fun forEach(action: (item: T) -> Unit)
    abstract fun <R> map(transformer: (item: T) -> R): Cons<R>
    abstract fun filter(predicate: (item: T) -> Boolean): Cons<T>
    abstract fun <R> reduce(seed: R, reductor: (reduced:R, item:T) -> R): Cons<R>
    abstract fun compare(other: Cons<T>):Int
    operator fun compareTo(other: Cons<T>):Int = compare(other)

}