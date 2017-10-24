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

    //型变
    var a: Runoob<String> = Runoob("A")
    var b: Runoob<Any> = Runoob("B")
    b = a
    println(b.foo())

    var strDco = Runoobs("a")
    var anyDco = Runoobs<Any>("any")
    strDco = anyDco
    println(strDco.foo())

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
 * 默认的上界是 Any?
 */
fun <T : Comparable<T>> sort(list: List<T>) {
    for (l in list.indices) {
        println(list[l])
    }
}

/**
 * 对于多个上界约束条件，可以用 where 子句.
 */
//fun <T> cloneWhenGreater(list: List<T>, threshold: T): List<T>
//        where T : Comparable, Cloneable {
//    return list.filter(it > threshold).map(it.clone())
//}

/**
 *  型变
 *  1.声明处型变（declaration-site variance）
 *  2.类型投影（type projections）
 *
 *  声明处型变使用注解变量：in,out,消费者：in,生产者：out
 *
 */

//使用 out 使得一个类型参数协变，协变类型参数只能用作输出，可以作为返回值类型但是无法作为入参的类型
class Runoob<out A>(val a: A) {
    fun foo(): A {
        return a
    }
}

//in 使得一个类型参数逆变，逆变类型参数只能用作输入，可以作为入参的类型但是无法作为返回值的类型
class Runoobs<in A>(a: A) {
    fun foo() {

    }
}

/**
 * 星号投射
 *  如果一个泛型类型中存在多个类型参数, 那么每个类型参数都可以单独的投射.
 *  比如, 如果类型定义为interface Function<in T, out U> , 那么可以出现以下几种星号投射
 */

//Function<*, String> , 代表 Function<in Nothing, String> ;
//Function<Int, *> , 代表 Function<Int, out Any?> ;
//Function<, > , 代表 Function<in Nothing, out Any?> .