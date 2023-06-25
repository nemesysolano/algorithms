package knuth.linked
import knuth.linked.LinkedList
import knuth.Cons
import kotlin.test.Test
import kotlin.test.assertEquals

class LinkedListTest {

    @Test
    fun testLinkedListReduce() {
        val numbers:Cons<Int> = LinkedList.of(1,2,3,4)
        val size = numbers.reduce(0) {sum, _ -> sum + 1}.head()
        assertEquals(4,size)
    }

    @Test
    fun testLinkedListFilter() {
        val numbers:Cons<Int> = LinkedList.of(1,2,3,4)
        val even = numbers.filter {number -> number % 2 == 0}
        val size = even.reduce(0) {sum, _ -> sum + 1}.head()
        val sum = even.reduce(0) {sum, number -> sum + number}.head()
        assertEquals(6, sum)
        assertEquals(size, 2)
    }

    @Test
    fun testLinkedListMap() {
        val numbers:Cons<Int> = LinkedList.of(1,2,3)
        val doubled:Cons<Int> = numbers.map {number -> number * 2}
        val sum = doubled.reduce(0) {sum, number -> sum + number}.head()
        assertEquals(12, sum)
    }

    @Test
    fun testLinkedListForEach() {
        val numbers:Cons<Int> = LinkedList.of(1,2,3)
        var sum = 0
        numbers.forEach {
            number -> sum += number
        }

        assertEquals(6, sum)
    }
}