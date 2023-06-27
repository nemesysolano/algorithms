import knuth.linked.LinkedList
import kotlin.test.assertEquals

fun main() {
    val numbers:LinkedList<Int> = LinkedList.of(1)
    numbers.push(2)
    println( numbers.toString())
    println("Hello, Kotlin/Native!")
}