package knuth.linked
import knuth.Cons
import knuth.NilType
import kotlinx.cinterop.pin

class LinkedList<T>(): Cons<T>() {
    private var first:Cons<T> = NilType.of()
    private var last:Cons<T> =  NilType.of()

    constructor(item: T): this() {
        this.first = Node(item, NilType.of(), NilType.of())
        this.last = first
    }

    override fun head(): T = if(isEmpty()) throw IllegalStateException("Empty lists are headless") else first.head()
    override fun tail(): Cons<T> = first.tail()
    override fun forEach(action: (item: T) -> Unit):Unit = first.forEach(action)
    override fun <R> map(transformer: (item: T) -> R):Cons<R> = first.map(transformer)
    override fun <R> reduce(seed: R, reductor: (reduced:R, item:T) -> R): Cons<R> = first.reduce(seed, reductor)
    override fun compare(other: Cons<T>):Int = first.compare(other)
    override fun filter(predicate: (item: T) -> Boolean): Cons<T> = first.filter(predicate)
    fun isEmpty(): Boolean = NilType.isNil(first)
    fun append(item: T): LinkedList<T> {
        val last = (this.last as Node<T>)
        last.next = Node(item, last, NilType.of())
        this.last = (last.next as Node<T>)
        return this
    }
    fun push(item: T): LinkedList<T> {
        val first = (this.first as Node<T>)
        this.first = Node(item, NilType.of(), first)
        first.prev = this.first
        return this
    }

    fun pop(): Cons<T> = if(NilType.isNil(this.first)) NilType.of() else removeFirst()
    fun drop(): Cons<T> = if(NilType.isNil(this.last)) NilType.of() else removeLast()

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append('[')
        forEach { item-> builder.append(item.toString()); builder.append(','); }
        builder[builder.length-1] = ']'
        return builder.toString()
    }

    private fun removeFirst(): Cons<T> {
        val first = (this.first as Node<T>)
        this.first = first.next
        first.next = NilType.of()
        first.prev = NilType.of()

        if(NilType.isNil(this.first)) {
            this.last = this.first
        }
        return first
    }

    private fun removeLast(): Cons<T> {
        val last = (this.last as Node<T>)
        this.last = last.prev
        last.next = NilType.of()
        last.prev = NilType.of()

        if(NilType.isNil(this.last)) {
            this.first = this.last
        }
        return last
    }

    companion object {
        inline fun <reified T> of(vararg input: T): LinkedList<T> {
            val result = LinkedList(input[0])
            var index = 1
            while(index < input.size) {
                result.append(input[index])
                index ++
            }

            return result
        }

        inline fun <reified T> ofReversed(vararg input: T): LinkedList<T> {
            val result = LinkedList(input[0])
            var index = 1
            while(index < input.size) {
                result.push(input[index])
                index ++
            }

            return result
        }
    }
}


