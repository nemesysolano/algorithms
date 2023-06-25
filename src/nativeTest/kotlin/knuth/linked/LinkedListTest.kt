package knuth.linked
import knuth.linked.LinkedList
import knuth.Cons
import knuth.NilType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
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
        val size:Int = even.reduce(0) {sum, _ -> sum + 1}.head()
        val sum:Int = even.reduce(0) {sum, number -> sum + number}.head()
        assertEquals(6, sum)
        assertEquals(size, 2)
    }

    @Test
    fun testLinkedListMap() {
        val numbers:Cons<Int> = LinkedList.of(1,2,3)
        val doubled:Cons<Int> = numbers.map {number -> number * 2}
        val sum:Int = doubled.reduce(0) {sum, number -> sum + number}.head()
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

    @Test
    fun testEqualsForNotNilAndNilList() {
        val numbers:Cons<Int> = LinkedList.of(1,2,3)
        val nil:Cons<Int> = NilType.of()
        assertNotEquals(0, numbers.compareTo(nil))
        assertNotEquals(0, nil.compareTo(numbers))
    }

    @Test
    fun testEqualsForIdenticalNonEmptyLists() {
        val numbers1:Cons<Int> = LinkedList.of(1,2,3)
        val numbers2:Cons<Int> = LinkedList.of(1,2,3)
        assertEquals(0, numbers1.compareTo( numbers2))
        assertEquals(0, numbers2.compareTo( numbers1))
    }

    @Test
    fun testEqualsForNonEmptyListWithDifferentSize() {
        val numbers0:Cons<Int> = LinkedList.of(1)
        val numbers1:Cons<Int> = LinkedList.of(1,3)
        val numbers2:Cons<Int> = LinkedList.of(1,2,3)

        assertNotEquals(0, numbers1.compareTo( numbers0))
        assertNotEquals(0, numbers2.compareTo( numbers0))

        assertNotEquals(0, numbers0.compareTo( numbers1))
        assertNotEquals(0, numbers0.compareTo( numbers2))

        assertNotEquals(0, numbers1.compareTo( numbers2))
        assertNotEquals(0, numbers2.compareTo( numbers1))
    }

}