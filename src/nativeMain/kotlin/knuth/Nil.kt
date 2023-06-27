package knuth
class NilType(): Cons<Any?>() {
    override fun head(): Any = Nil
    override fun tail(): Cons<Any?> = Nil
    override fun forEach(action: (item: Any?) -> Unit) {}
    override fun compare(other: Cons<Any?>):Int {
        return  if(other == this) 0 else -1
    }
    @Suppress("UNCHECKED_CAST")
    override fun <R> map(transformer: (item: Any?) -> R):Cons<R> = (Nil as Cons<R>)

    @Suppress("UNCHECKED_CAST")
    override fun filter(predicate: (item: Any?) -> Boolean): Cons<Any?> = Nil

    @Suppress("UNCHECKED_CAST")
    override fun <R> reduce(seed: R, reductor: (reduced: R, item: Any?) -> R): Cons<R> = (Nil as Cons<R>)

    override fun toString(): String = "<NIL>"
    companion object {
        @Suppress("UNCHECKED_CAST")
        inline fun <T> of() = (Nil as Cons<T>)

        @Suppress("UNCHECKED_CAST")
        inline fun <T> isNil(nil: Cons<T>) = when(nil) {
            Nil -> true
            else -> false
        }
    }

}

val Nil = NilType()