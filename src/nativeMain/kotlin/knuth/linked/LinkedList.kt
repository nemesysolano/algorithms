package knuth.linked
import knuth.Cons
import knuth.Nil
import knuth.NilType

class LinkedList<T>(private val item: T): Cons<T>() {
    private var first = Node<T>(item, NilType.of())
    private var last:Cons<T> = first
    override fun head(): T = first.head()
    override internal fun tail(): Cons<T> = first.tail()
    override fun forEach(action: (item: T) -> Unit):Unit = first.forEach(action)
    override fun <R> map(transformer: (item: T) -> R):Cons<R> = first.map(transformer)
    override fun <R> reduce(seed: R, reductor: (reduced:R, item:T) -> R): Cons<R> = first.reduce(seed, reductor)
    override fun compare(other: Cons<T>):Int = first.compare(other)
    override fun filter(predicate: (item: T) -> Boolean): Cons<T> {
        var head:Cons<T> = this.first
        var result:Node<T>? = null
        var tail:Node<T>? = null

        while(!NilType.isNil(head)) {
            val node = head as Node<T>
            if(predicate(node.item)) {
                if(result == null) {
                    result = Node(node.item, NilType.of())
                    tail = result
                } else {
                    val next = Node(node.item, NilType.of())
                    tail!!.next = next
                    tail = next
                }
            }
            head = node.next
        }

        return if(result == null) NilType.of() else filteredList(result,tail)
    }

    @Suppress("UNCHECKED_CAST")
    fun append(item: T) {
        if(first == last) {
            last = Node(item, NilType.of())
            first.next = last
        } else {
            val last = (this.last as Node<T>)
            last.next =  Node(item, NilType.of())
            this.last = last.next
        }
    }

    private fun filteredList(result:Node<T>?, tail:Node<T>?):LinkedList<T> {
        val head:Node<T> = result!!
        val last:Cons<T> = tail!!
        val list = LinkedList<T>(head.item)
        list.first = head
        list.last = last

        return list
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append('[')
        forEach { item-> builder.append(item.toString()); builder.append(','); }
        builder.set(builder.length-1, ']')
        return builder.toString()
    }

    companion object {
        inline fun <reified T> of(vararg input: T): LinkedList<T> {
            val result = LinkedList<T>(input[0])
            var index = 1
            while(index < input.size) {
                result.append(input[index]);
                index ++
            }

            return result
        }
    }
}


