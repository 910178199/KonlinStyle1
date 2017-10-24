package KotlinStyle5

/**
 * 枚举
 *  枚举类最基本的用法是实现一个类型安全的枚举。
 *  枚举常量用逗号分隔,每个枚举常量都是一个对象。
 */

val name: String = ""//获取枚举名称
val ordinal: Int = 0//获取枚举值在所有枚举数组中定义的顺序


fun main(args: Array<String>) {

    val color: Color = Color.BLACK
    println(Color.values()[0])
    println(Color.valueOf("RED"))
    println(color.name)
    println(color.ordinal)

    //遍历枚举
    printAllValue<Color>()
}

enum class Color {
    RED, BLACK, BLUE, GREEN, WHITE
}

//初始化枚举类
enum class Colors(var rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

//默认名称为枚举字符名，值从0开始。若需要指定值，则可以使用其构造函数
enum class Shape(value: Int) {
    ovel(100),
    rectangle(200)
}

//声明自己的匿名类及相应方法，以及覆盖基类的方法
//如果枚举类定义任何成员，要使用分号将成员定义中的枚举常量定义分隔开
enum class ProtocoState {
    WAITING {
        override fun signal() = TALKING
    },
    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocoState
}

/**
 * 使用枚举常量
 */
//EnumClass.valueOf(value: String): EnumClass  // 转换指定 name 为枚举值，若未匹配成功，
// 会抛出IllegalArgumentException
//EnumClass.values(): Array<EnumClass>        // 以数组的形式，返回枚举值

/**
 *  自 Kotlin 1.1 起，可以使用 enumValues<T>() 和 enumValueOf<T>() 函数以泛型的方式访问枚举类中的常量
 *  inline:内联函数，
 *  reified:reified修饰类型后，我们就能够在函数内部使用相关类型了。
 */
inline fun <reified T : Enum<T>> printAllValue() {
    print(enumValues<T>().joinToString { it.name })
}

