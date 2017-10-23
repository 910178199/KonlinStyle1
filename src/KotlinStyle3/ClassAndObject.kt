package KotlinStyle3

/**
 * 类和对象
 * Kotlin 类可以包含：构造函数和初始化代码块、函数、属性、内部类、对象声明。
 *
 */

fun main(args: Array<String>) {

    //使用普通函数那样使用构造函数创建类实例
    //kotlin中没有new关键字
    val site = Runoob("hhhhhh")
    println(site.city)
    println(site.url)
    println(site.name)
    println(site.printTest())

    //Kotlin对象使用
    var man: Man = Man()
    man.lastName = "zs"
    println("lastName is ${man.lastName}")

    man.no = 9527
    println("no is ${man.no}")


    println()

    //传入的是默认值
    var lazy: LazyProperty = LazyProperty { 0 }
    println("lazy is ${lazy.lazy}")


    val person = Person("sss", 100)

    //调用嵌套类
    val foo = Outer.Nested().foo()
    println(foo)

    //内部类
    val foo1 = Outers().Inner().foo()
    println(foo1)

    val foo2 = Outers().Inner().innerTest()
    println(foo2)

    //匿名内部类
    val test = Test()
    test.setInterFace(object : TestInterface {
        override fun test() {
            println("对象表达式创建匿名内部类的实例")
        }
    })


}

/**
 * Koltin 中的类可以有一个 主构造器，以及一个或多个次构造器，
 * 主构造器是类头部的一部分，位于类名称之后
 */
class Runoob constructor(string: String) {

    var name: String = "张三"
    var url: String = string
    var city: String = "bj"

    //    var allByDefault: Int? // 错误: 需要一个初始化语句, 默认实现了 getter 和 setter 方法
    var initialized = 1    // 类型为 Int, 默认实现了 getter 和 setter
    //    val simple: Int?       // 类型为 Int ，默认实现 getter ，但必须在构造函数中初始化
    val inferredType = 1   // 类型为 Int 类型,默认实现 getter

    init {
        println("初始化传入的值：$string")
    }

    fun printTest() {
        println("类中函数...")
    }
}

/**
 * 如果主构造器没有任何注解，也没有任何可见度修饰符，那么constructor关键字可以省略
 */
class Person(str: String) {

    /**
     * 如果类有主构造函数，每个次构造函数都要，
     * 直接或间接通过另一个次构造函数代理主构造函数。
     * 在同一个类中代理另一个构造函数使用 this 关键字
     */
    class Person {
        constructor(parent: Person) {

        }
    }

    /**
     * 次构造函数
     */
    constructor(str: String, age: Int) : this(str) {
        println("constructor string $str age is $age")
    }

    /**
     * 如果一个非抽象类没有声明构造函数(主构造函数或次构造函数)，
     * 它会产生一个没有参数的构造函数。构造函数是 public 。
     * 如果你不想你的类有公共的构造函数，你就得声明一个空的主构造函数
     */
    class DonrCreateMe private constructor() {

    }


    //初始化代码块init
    init {
        System.out.print("str is $str")
    }


}

/**
 * 可以通过主构造器来定义属性并初始化属性值（可以是var或val）
 */
class People(val firstName: String = "null", val lastName: String) {


}


/**
 * 包含两个可变变量 lastName 和 no，lastName 修改了 getter 方法，no 修改了 setter 方法。
 *
 * Kotlin 中类不能有字段。
 * 提供了 Backing Fields(后端变量) 机制,备用字段使用field关键字声明,field 关键词只能用于属性的访问器
 */
class Man() {

    var lastName: String = "li"
        get() = field.toUpperCase()//将变量赋值后转换为大写
        set

    var no: Int = 100
        get() = field//后端变量
        set(value) {
            if (value < 10) {
                field = value
            } else {
                field = -1
            }
        }

    var hitht: Float = 145.5f
        private set
}


/**
 * 非空属性必须在定义的时候初始化,
 * kotlin提供了一种可以延迟初始化的方案,使用 lateinit 关键字描述属性
 *
 *Kotlin lateinit 和 by lazy 的区别
 *  1.by lazy 只能使用在val类型，lateinit只能使用在var类型
 *  2.lateinit不能使用在可空的属性上和java基础类型上
 *  3.lateinit可以在任何位置初始化并且初始化多次，而lazy在第一次调用就被初始化，想改变只能重新定义
 *  4.lateinit有支持（反向）域（backing fields）
 */
class LazyProperty(val initializer: () -> Int) {

    val name: String by lazy { "lazy..." }//ba lazy
    lateinit var lazys: LazyProperty //laterinit

    var value: Int? = null
    val lazy: Int
        get() {
            if (value == null) {
                value = initializer()
            }
            return value!! //如果为空就报空指针
        }
}

/**
 * 注意：在 JVM 虚拟机中，如果主构造函数的所有参数都有默认值，编译器会生成一个附加的无参的构造函数，
 * 这个构造函数会直接使用默认值。这使得 Kotlin 可以更简单的使用像 Jackson 或者 JPA
 * 这样使用无参构造函数来创建类实例的库。
 */

class Customer(val customer: String = "") {

}

/**
 * 抽象类
 * 注意：无需对抽象类或抽象成员标注open注解。
 *
 * 对于可以重写的函数，都需要显示的指明，使用的是open关键字。
 * 如果没有，在子类中声明跟父类相同的方法是非法的。
 *
 * 抽象是面向对象编程的特征之一，类本身，或类中的部分成员，
 * 都可以声明为abstract的。抽象成员在类中不存在具体的实现
 */
open class Base {
    open fun f() {}
}

abstract class Derived : Base() {
    override abstract fun f()
}


/**
 * 嵌套类
 *
 */
class Outer {
    //外部类
    private val bar: Int = 1

    class Nested {
        //嵌套类
        fun foo() = 2
    }
}

/**
 * 内部类
 *  内部类使用 inner 关键字来表示。
 *  内部类会带有一个对外部类的对象的引用，所以内部类可以访问外部类成员属性和成员函数。
 *
 *  为了消除歧义，要访问来自外部作用域的 this，我们使用this@label，
 *  其中 @label 是一个 代指 this 来源的标签。
 */
class Outers {
    private val bar: Int = 1
    var v = "成员属性"

    /**
     * 嵌套内部类
     */
    inner class Inner {
        fun foo() = bar //访问外部类成员
        fun innerTest() {
            var o = this@Outers//获取外部类的成员变量
            println(o.v)
        }
    }
}


/**
 * 定义接口
 */
interface TestInterface {
    fun test()
}

/**
 * 匿名内部类
 */

class Test {
    var v = "成员变量"
    fun setInterFace(test: TestInterface) {
        test.test()
    }
}

/**
 * 类修饰符
 *
 * 类的修饰符包括 classModifier 和_accessModifier_:
 *classModifier: 类属性修饰符，标示类本身特性。
 *abstract    // 抽象类
 *final       // 类不可继承，默认属性
 *enum        // 枚举类
 *open        // 类可继承，类默认是final的
 *annotation  // 注解类

 *accessModifier: 访问权限修饰符
 *private    // 仅在同一个文件中可见
 *protected  // 同一个文件中或子类可见
 *public     // 所有调用的地方都可见
 *internal   // 同一个模块中可见
 */
