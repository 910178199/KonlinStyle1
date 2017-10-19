package KotlinStyle3

/**
 * Kotlin 循环控制
 * for
 * while do..while
 * rerutn barek continue
 *
 */
fun main(args: Array<String>) {
    //使用for循环
    useFor()

    useWhile()

    useRuturnOrContinue()

}

fun useRuturnOrContinue() {

    for (i in 1..10) {
        if (i == 3) continue//跳过
        println(i)
        if (i > 5) break//退出循环
    }

    //在 Kotlin 中任何表达式都可以用标签（label）来标记。
    // 标签的格式为标识符后跟 @ 符号，例如：abc@、fooBar@都是有效的标签。
    // 要为一个表达式加标签，我们只要在其前加标签即可。
    loop@ for (i in 1..10) {
        print(i)
    }

    //跳出外层循环
    loop@ for (i in 1..100) {
        for (j in 1..10) {
            if (i == 2) {
                break@loop
            }
        }
        print(i)
    }

    var ints = arrayListOf("3", 0, 2, 3)
    println()
    foo(ints)

    println()
    var intList = arrayListOf<Int>(1, 2, 0, 3)
    foo2(intList)
}

/**
 * 匿名函数内部的 return 语句将从该匿名函数自身返回
 */
fun foo2(ints: ArrayList<Int>) {

    ints.forEach(fun(value: Int) {
        if (value == 0) return
        print(value)
    })

}

/**
 * 这个 return 表达式从最直接包围它的函数即 foo 中返回。
 * （注意，这种非局部的返回只支持传给内联函数的 lambda 表达式。）
 * 如果我们需要从 lambda 表达式中返回，我们必须给它加标签并用以限制 return。
 */
fun foo(ints: ArrayList<Any>) {
    ints.forEach lit@ {
        if (it == 0) return@lit
        print(it)
    }
}

fun useWhile() {
    //do…while 循环和 while 循环相似，不同的是，do…while 循环至少会执行一次。
    println("while的使用")
    var x = 5
    while (x > 0) {
        println(x--)
    }

    println("do..while使用")
    var y = 5
    do {
        println(y--)
    } while (y < 0)

}

fun useFor() {

    val itemsSet = setOf(1, 2, 3, 4)
    //基础用法
    for (item in itemsSet) print(item)

    for (item: Int in itemsSet) {
        print(item)
    }

    println()

    //通过索引遍历一个数组或者一个 list,获得索引indices
    for (i in itemsSet.indices) {
        print(itemsSet.toList()[i])
    }

    println()

    //用库函数 withIndex
    for ((index, value) in itemsSet.toList().withIndex()) {
        println("this is $index - $value")
    }

}



