import knuth.linked.LinkedList
import knuth.Nil
fun main() {
    val list = LinkedList.of(1,2,3,4)
    val doubled = list.map { number -> number * 2}
    val even = list.filter { number -> number % 2 == 0}
    println(list)
    println(doubled)
    println(even)
    println(Nil)
    println(list.reduce(0) {reduced, item -> reduced + item}.head())
    println("Hello, Kotlin/Native!")
}