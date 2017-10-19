package KotlinStyle2

/**
 * Kotlin基本数据类型
 *  Kotlin 的基本数值类型包括 Byte、Short、Int、Long、Float、Double 等。
 *  不同于Java的是，字符不属于数值类型，是一个独立的数据类型。
 *
 *
 *
 */

fun main(args: Array<String>) {


    //比较两个数字
    compareValue()


}

/**
 * Kotlin 中没有基础数据类型，只有封装的数字类型，你每定义的一个变量，
 * 其实 Kotlin 帮你封装了一个对象，这样可以保证不会出现空指针
 *
 * 三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小。
 */
fun compareValue() {
    val a: Int = 10000
    println(a === a) //true,值相等，对象地址相等

    //经历装箱，创建了两个不同的对象
    val boxedA: Int? = a
    val auotherBoxedA: Int? = a

    println(boxedA === auotherBoxedA)//false，对象地址不一样
    println(boxedA == auotherBoxedA)//true,值相等

    /**
     * 字符
     * 和 Java 不一样，Kotlin 中的 Char 不能直接和数字操作，Char 必需是单引号''包含起来的。
     * 支持这几个转义序列：\t、 \b、\n、\r、\'、\"、\\ 和 \$
     */
    fun check(c: Char) {
        /*if (c == 1) { 错误

        }*/
        if (c == '1') {

        }
    }

    //把字符串转换为数字
    println(decimalDiagitValue('9'))

    /**
     * 逻辑运算符
     * || – 短路逻辑或
     *  && – 短路逻辑与
     *  ! - 逻辑非
     */

    /**
     * 数组：用Array实现，size,get,set
     * 创建的两种方式：
     * 1.arrayOf()
     * 2.使用工厂函数
     *
     * ByteArray, ShortArray, IntArray...，用来表示各个类型的数组，
     * 省去了装箱操作，因此效率更高，其用法同Array一样
     */
    useArray()

    /**
     * 字符串
     *
     */
    useString()

}

/**
 * 字符串操作
 */
fun useString() {
    val str: String = "string"


    for (c in str) {
        println(c)
    }

    println(str[5])

    //Kotlin 支持三个引号 """ 扩起来的字符串，支持多行字符串
    val text = """
       哈哈
        哈哈
        哈哈
    """
    println(text)

    //String 可以通过 trimMargin() 方法来删除多余的空白。
    val text2 = """
        1
        2
        3
    """.trimIndent()
    println(text2)

    //使用字符串模板
    useStrMode()
}

fun useStrMode() {

    val s = "reboot"
    val str = "$s.length is ${s.length}"
    println(str)

    // 如果你需要在原生字符串中表示字面值 $ 字符（它不支持反斜杠转义）
    val price = """
    ${'$'}9.99
    """
    println(price)
}

/**
 * 数组
 */
fun useArray() {
    //[1,2,3]
    val a = arrayOf(1, 2, 3)
    //[0,2,4]
    //使用工厂函数
    val b = Array(3, { i -> (i * 2) })

    println(a[0])
    println(a[1])
    println(a[2])

    println(b[0])
    println(b[1])
    println(b[2])

    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]
    println(x[0])
}


/**
 * 类型转换
 *
 * 由于不同的表示方式，较小类型并不是较大类型的子类型，较小的类型不能隐式转换为较大的类型。
 * 这意味着在不进行显式转换的情况下我们不能把 Byte 型值赋给一个 Int 变量。
 *
 *
 */
fun typeTransformation() {
    val b: Byte = 1
//    val i: Int = b 报错
    val i: Int = b.toInt()

    //有些情况下也是可以使用自动类型转化的，
    // 前提是可以根据上下文环境推断出正确的数据类型而且数学操作符会做相应的重载
    val l = 1L + 2 //Long+Int = Long
}


/**
 * 对于Int和Long类型，还有一系列的位操作符可以使用
 *  shl(bits) – 左移位 (Java’s <<)
 *  shr(bits) – 右移位 (Java’s >>)
 *  ushr(bits) – 无符号右移位 (Java’s >>>)
 *  and(bits) – 与
 *  or(bits) – 或
 *  xor(bits) – 异或
 *  inv() – 反向
 */
fun bitsOperation() {
    var a: Int = 1
    var b: Int = 2;

    a shl b
    a shr b
    a ushr b
    a and b
    a or b
    a xor b
}


fun decimalDiagitValue(c: Char): Int {
    //当需要可空引用时，像数字、字符会被装箱。装箱操作不会保留同一性。
    if (c !in '0'..'9')
        throw IllegalArgumentException("Out of range")
    return c.toInt() - '0'.toInt()
}