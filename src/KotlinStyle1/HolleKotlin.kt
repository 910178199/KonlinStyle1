package KotlinStyle1

/**
 * Kotlin基础语法
 *
 *  一,函数定义
 *  1.可变长参数函数
 *  2.lambda(匿名函数)
 *
 *  二,定义常量与变量
 *     可变变量定义：var 关键字
 *     不可变变量定义：val 关键字，只能赋值一次的变量(类似Java中final修饰的变量)
 *     常量与变量都可以没有初始化值,但是在引用前必须初始化
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

//常量赋值
val a: Int = 1
val b = 1//自动推断为Int类型
val c: Int = 2

//变量赋值
var d: Int = 3
var e = 4

/**
 * NULL检查机制
 *
 */

//类型后面加？表示可为空
var age: String? = "123"
//抛出空指针异常
var ages = age!!.toInt()
//不做处理返回null
val ages1 = age?.toInt()
//age为空返回-1
val ages2 = age?.toInt() ?: -1


fun main(args: Array<String>) {
    println("Hello kotlin")

    println(sum(1, 1))
    println(sumAuto(1, 2))
    println(sumPublic(1, 3))

    vars(1, 2, 3, 4, 5, 6, 7, 8)

    /**
     * lambda(匿名函数)
     */
    val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
    println(sumLambda(1, 4))

    //字符串模板
    StrMode()

    println(age)

    //返回值为null
//    returnNull()

    //类型监测及自动监测
    println(getStringLength("1"))
    println(getStringLength2("22"))
    println(getStringLength3("333"))

    //区间
    section()
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


/**
 *  字符串模板
 *  $表示一个变量名或变量值
 *  $varName 表示变量值
 *  ${varName.fun()} 表示变量的方法返回值:
 */
fun StrMode() {
    var a = 1;
    //模板中的简单名称
    val s1 = "a is $a"

    a = 2
    //模板中的任意表达式
    val s2 = "${s1.replace("is", "是")}，but is $a"
    print(s2)
}


/**
 * 当str中的字符串内容不是一个整数时，返回null
 */
fun parseInt(str: String): Int? {
    return str.toInt()
}

//使用一个返回值可为 null 的函数:
fun returnNull(args: Array<String>) {
    if (args.size < 2) {
        print("Two integers expected")
        return
    }
    val x = parseInt(args[0])
    val y = parseInt(args[1])
    if (x != null && y != null) {
        println(x * y)
    }
}


/**
 * 类型监测及自动类型转换
 *  is 运算符检测一个表达式是否某类型的一个实例(类似于Java中的instanceof关键字)。
 *
 */
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // 做过类型判断以后，obj会被系统自动转换为String类型
        return obj.length
    }

    //在这里还有一种方法，与Java中instanceof不同，使用!is
    /*if (obj !is String){

    }*/

    return null
}

//或这样写
fun getStringLength2(obj: Any): Int? {

    if (obj !is String) {
        return null
    }
// 在这个分支中, `obj` 的类型会被自动转换为 `String`
    return obj.length
}

//甚至可以这样
fun getStringLength3(obj: Any): Int? {
    // 在&&运算符的右侧，obj将自动转换为String
    if (obj is String && obj.length > 0) {
        return obj.length
    }
    return null
}


/**
 * 区间
 *  区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
 */
fun section() {

    for (i in 1..4) print(i)//输出“1-100”

    for (i in 4..1) println(i)//什么都不输出

    var i: Int = 9
    if (i in 1..10) { // 等同于 1 <= i && i <= 10
        println(i)
    }

    //使用step指定步长
    for (i in 1..10 step 2) print(i)//13579

    println()

    for (i in 10 downTo 3 step 2) print(i)//downTo到哪一个数停止

    println()

    //使用until函数排除结束元素
    for (i in 1 until 10) {
        print(i)
    }
    
}




