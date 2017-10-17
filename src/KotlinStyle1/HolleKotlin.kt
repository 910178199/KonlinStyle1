package KotlinStyle1

/**
 * Kotlin基础语法
 *
 *  一,函数定义
 *  1.可变长参数函数
 *  2.lambda(匿名函数)
 *
 *  二,定义常量与变量
 *
 *  三,字符串模板
 *
 *  四,NULL检查机制
 *
 *  五,类型监测及自动类型转换
 *
 *  六,区间
 *
 */

fun main(args: Array<String>) {
    println("Hello kotlin")

    println(sum(1, 1))
    println(sumAuto(1, 2))
    println(sumPublic(1, 3))

    vars(1, 2, 3, 4, 5, 6, 7, 8)


}

fun sum(a: Int, b: Int): Int {//int参数，返回int
    return a + b
}

fun sumAuto(a: Int, b: Int) = a + b;//自动推断返回类型

public fun sumPublic(a: Int, b: Int): Int = a + b//public方法则必须写出返回类型

/**
 * 无返回值的函数Unit类似void
 */
fun printSum(a: Int, b: Int): Unit {
    print(a + b)
}

/**
 * 如果是返回Unit类型，可以省略
 * 对于public方法也是这样
 */
public fun printSum(a: Int) {
    print(a)
}


/**
 * 可变长参数函数
 */
fun vars(vararg v: Int) {
    for (vt in v) {
        print(vt)
    }
}







