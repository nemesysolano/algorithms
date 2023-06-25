package knuth.linked
import knuth.Cons
import knuth.NilType

data class Node<T>(val item: T, internal var next: Cons<T>): Cons<T>() {
    override fun head(): T = item
    override internal fun tail(): Cons<T> = next
     @Suppress("UNCHECKED_CAST")
    override fun forEach(action: (item: T) -> Unit){
        var first:Node<T> = this
        var next = first.next

        action(first.item)
        println(first.item)
        while(!NilType.isNil(next)) {
            first = (next as Node<T>)
            action(first.item)
            println(first.item)
            next = next.next
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <R> map(transformer: (item: T) -> R): Cons<R> {
        var first:Node<T> = this
        val result = LinkedList<R>(transformer(first.item))
        var next = first.next

        while(!NilType.isNil(next)) {
            first = (next as Node<T>)
            result.append(transformer(first.item))
            next = next.next
        }
        return result
    }

    override fun <R> reduce(seed: R, reductor: (reduced:R, item:T) -> R): Cons<R> {
        var reduced = seed
        forEach { item-> reduced = reductor(reduced, item) }
        return Node(reduced, NilType.of())
    }

    @Suppress("UNCHECKED_CAST")
    override fun filter(predicate: (item: T) -> Boolean): Cons<T> {
        var head:Cons<T> = this
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

        return if(result == null) NilType.of() else result
    }

    override fun compare(other: Cons<T>):Int {
        if(other == this) {
            return 0
        }
        var self:Cons<T> = this
        var ego = other

        while(!(NilType.isNil(self) || NilType.isNil(ego))) {
            if(!(exhaustiveEquals(self.head(), ego.head()))) {
                return -1
            }

            self = self.tail()
            ego = ego.tail()
        }

        return if(NilType.isNil(self) && NilType.isNil(ego) ) 0 else -1
    }

    fun exhaustiveEquals(value: T, that: Any?): Boolean {
        if(value == null && that == null) {
            return true
        } else if (that != null) {
            return that.equals(value)
        } else {
            return value!!.equals(that)
        }

    }
}