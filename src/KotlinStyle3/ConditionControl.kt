package KotlinStyle3

import KotlinStyle1.c

/**
 * Konlin条件控制语句
 *
 *
 */

fun main(args: Array<String>) {

    //使用if条件语句
    useIf()
    useIfTest()

    //when表达式。when 类似其他语言的 switch 操作符，
    // 在 when 中，else 同 switch 的 default。
    useWhen()

}

fun useWhen() {

    val x: Int = 1
    when (x) {
        1 -> print("x == 1")
        2 -> print("y == 2")
    // 在 when 中，else 同 switch 的 default。
        else -> {
            print("其他数值")
        }
    }

    //很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔
    when (x) {
        0, 1 -> println("x==0 or 1")
        else -> {
            println("other")
        }
    }

    //监测一个值在（in）或者不在（!in）一个区间或一个集合
    when (x) {
        in 1..10 -> println("in 1..10")
        !in 10..20 -> println("!in 10..20")
        else -> {
            println("none of the abvoe")
        }
    }

    //is的使用
    val hasPrefix = hasPrefix("prefix")
    println(hasPrefix)

    //when 也可以用来取代 if-else if链。
    // 如果不提供参数，所有的分支条件都是简单的布尔表达式，而当一个分支的条件为真时则执行该分支
    val y: String = "string"
    when {
        y.startsWith("string") -> println("true string")
        y.startsWith("s") -> println("true s")
        else -> {
            println("false string")
        }
    }

    //when 中使用 in 运算符来判断集合内是否包含某实例：
    val items = setOf("a", "b", "c")
    when {
        "a" in items -> println("contian a")
        "b" in items -> println("contian b")
    }
}

//可能性是检测一个值是（is）或者不是（!is）一个特定类型的值。
// 注意： 由于智能转换，你可以访问该类型的方法和属性而无需 任何额外的检测
fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> false
}

fun useIfTest() {
    val x = 0
    if (x > 0) {
        println("x>0的值为$x")
    } else if (x < 0) {
        println("x<0的值为$x")
    } else {
        println("x的值为$x")
    }

    val a: Int = 1
    val b: Int = 2
    val c = if (a > b) a else b
    println("c的值为${c}")

    if (a in 1..10) {
        println("a的值在1-10之间")
    }

}

fun useIf() {

    val a: Int = 0
    val b: Int = 1
    //传统用法
    var max = a
    if (a < b) max = b

    //使用else
    val maxs: Int
    if (a < b) {
        maxs = b
    } else {
        maxs = a
    }

    //表达式
    val maxss = if (a < b) a else b

    //可以把if表达式的结果赋值给一个变量
    val valueMax = if (a < b) {
        print("choose a")
        a
    } else {
        print("choose b")
        b
    }
    println(valueMax)

    //不需要java中的三目运算符
    val condition: Boolean = true
    val c = if (condition) a else b
}
