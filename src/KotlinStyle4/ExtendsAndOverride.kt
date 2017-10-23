package KotlinStyle4

/**
 * 继承和重写
 *
 *
 */

fun main(args: Array<String>) {
    //继承
    val student = Student("lijian", 1, "1", 99)

    //重写
    val son = Son()
    son.study()

    //调用多个相同的方法
    val c = C()
    c.f()

    //属性重写
    val bar1 = Bar1()
    println(bar1.x)

    val bar2 = Bar2()
    println(bar2.count)
}

/**
 * Kotlin 中所有类都继承该 Any 类，它是所有类的超类，对于没有超类型声明的类是默认超类
 *
 */
class Example {//从隐式继承
    /**
     * Any 默认提供了三个函数：
     * equals()
     * hashCode()
     * toString()
     */


}

/**
 *  注意：Any 不是 java.lang.Object。
 *  如果一个类要被继承，可以使用 open 关键字进行修饰。
 */
open class Base(p: Int)

class Derived(p: Int) : Base(p)

/**
 *  实例：
 *
 */

open class Person(name: String) {
    /**
     * 次级构造函数
     */
    constructor(name: String, age: Int) : this(name) {
        //初始化
        println("父类次级构造函数")
    }
}

class Student : Person {

    constructor(name: String, age: Int, no: String, score: Int) : super(name, age) {
        println("-------继承类次级构造函数---------")
        println("学生名： ${name}")
        println("年龄： ${age}")
        println("学生号： ${no}")
        println("成绩： ${score}")
    }
}

/**
 * 在基类中，使用fun声明函数时，此函数默认为final修饰，不能被子类重写。
 * 如果允许子类重写该函数，那么就要手动添加 open 修饰它, 子类重写方法使用 override 关键词
 */

/**
 * 基类
 */
open class Bases {
    open fun study() {//open允许子类重写
        println("我毕业了")
    }
}

/**
 * 子类继承Bases类
 */
class Son : Bases() {
    override fun study() {//重写方法
        println("我还在上学")
    }
}

/**
 *如果有多个相同的方法（继承或者实现自其他类，如A、B类），
 * 则必须要重写该方法，使用super范型去选择性地调用父类的实现。
 */

open class A() {
    open fun f() {
        println("A")
    }

    fun a() {
        println("a")
    }
}

interface B {
    //接口默认的是open
    fun f() {
        println("B")
    }

    fun b() {
        println("b")
    }
}


class C() : A(), B {
    override fun f() {
        super<A>.f()//调用A.f
        super<B>.f()//调用B.f
        super<A>.a()
        super<B>.b()
    }
}


/**
 *  属性重写
 *  属性重写使用 override 关键字，属性必须具有兼容类型，
 *  每一个声明的属性都可以通过初始化程序或者getter方法被重写：
 */

open class Foo {
    open val x: Int get() = 2
}

class Bar1 : Foo() {
    override val x: Int
        get() = 3
}

/**
 *  你可以用一个var属性重写一个val属性，但是反过来不行。
 *  因为val属性本身定义了getter方法，重写为var属性会在衍生类中额外声明一个setter方法
 *  你可以在主构造函数中使用 override 关键字作为属性声明的一部分
 */

interface Fooo {
    val count: Int
}

class Bar(override val count: Int) : Fooo

class Bar2 : Fooo {
    override var count: Int = 1
}