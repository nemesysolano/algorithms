import knuth.linked.LinkedList
import knuth.Nil
import knuth.Cons

fun main() {
    val numbers1:Cons<Int> = LinkedList.of(1,2,3)
    val numbers2:Cons<Int> = LinkedList.of(1,2,3)
    println(numbers1.compareTo( numbers2))
    println("Hello, Kotlin/Native!")
}