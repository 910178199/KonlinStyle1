package KotlinStyle4

import com.sun.org.apache.xpath.internal.operations.Bool

/**
 *  泛型
 */

fun main(args: Array<String>) {

    val box: Box<Int> = Box<Int>(1)
    //编译器会进行类型推断，1 类型 Int，所以编译器知道我们说的是 Box<Int>。
    val box1 = Box(1)

    val boxString = Box<String>("str")
    println(box.value)
    println(boxString.value)

    //判断类型
    val value1: Int = 1
    val value2: String = "str"
    val value3: Boolean = true
    doPrintIn(value1)
    doPrintIn(value2)
    doPrintIn(value3)

    //泛型约束
    sort(listOf(1, 2, 3)) // OK。Int 是 Comparable<Int> 的子类型
//    sort(listOf(HashMap<Int, String>())) // 错误：HashMap<Int, String> 不是 Comparable<HashMap<Int, String>> 的子类型

    
}

class Box<T>(t: T) {
    var value = t
}

fun <T> doPrintIn(content: T) {
    when (content) {
        is Int -> println("Int 类型$content")
        is String -> println("String 类型$content")
        is Boolean -> println("Boolean 类型$content")
    }
}

/**
 *泛型约束
 *对泛型的的类型上限进行约束。
 */
fun <T : Comparable<T>> sort(list: List<T>) {
    for (l in list.indices) {
        println(list[l])
    }
}