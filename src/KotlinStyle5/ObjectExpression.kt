package KotlinStyle5

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JComponent

/**
 * Kotlin 对象表达式和对象声明
 * Kotlin 用对象表达式和对象声明来实现创建一个对某个类做了轻微改动的类的对象，且不需要去声明一个新的子类
 *
 * 对象表达式和对象声明之间的语义差异:
 * 1.对象表达式是在使用他们的地方立即执行的
 * 2.对象声明是在第一次被访问到时延迟初始化的
 * 3.伴生对象的初始化是在相应的类被加载（解析）时，与 Java 静态初始化器的语义相匹配
 */

fun main(args: Array<String>) {

    print(ab.y)

    //通过对象表达式可以越过类的定义直接得到一个对象
    val site = object {
        val name: String = "hh"
        val url: String = "www..."
    }
    println(site.name)
    println(site.url)

    //对象声明,可以用来获取一个单例
    var s1 = Sitess
    var s2 = Sitess
    s1.url = "www.123.com"
    println(s1.url)
    println(s2.url)

    val sites = Sites()
//    sites.DeskTop.age //错误，不能通过外部类的实例访问到该对象
    println(Sites.DeskTop.age)

    //伴生对象
    val instance = MyClass.create()
    //省略对象名
    val create = MyClass2.Companion
    //伴生对象实现对象
    val create1 = MyClass3.create()
}

//对象声明
object Sitess {
    var url: String = ""
    var name: String = "123"
}

//对象声明
class Sites {
    var url: String = ""
    var name: String = "123"

    //与对象表达式不同，当对象声明在另一个类的内部时，这个对象并不能通过外部类的实例访问到该对象，
// 而只能通过类名来访问，同样该对象也不能直接访问到外部类的方法和变量。
    object DeskTop {
        var age: Int = 2
        fun showAge() {
            println("age is $name")//错误，无法访问到外部的方法和变量
        }
    }
}

/**
 * 对象表达式
 */
//对象可以继承某个基类，或者实现其他接口
//如果超类型有一个构造函数，则必须传递参数给它。多个超类型和接口可以用逗号分隔

open class A(x: Int) {
    public open val y: Int = x
}

interface B {
}

val ab: A = object : A(1), B {
    override val y: Int = 2
}

/**
 * 请注意，匿名对象可以用作只在本地和私有作用域中声明的类型。
 * 如果你使用匿名对象作为公有函数的 返回类型或者用作公有属性的类型，那么该函数或属性的实际类型
 * 会是匿名对象声明的超类型，如果你没有声明任何超类型，就会是 Any。在匿名对象 中添加的成员将无法访问
 */
class C {

    //私有函数，所以返回的对象都是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    //公有函数，所以返回的类型是Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x
//        val x2 = publicFoo().x //错误：未能解析引用’x‘
    }
}

//在对象表达中可以方便的访问到作用域中的其他变量
fun countClicks(window: JComponent) {
    var clickCOunt = 0
    var enterCount = 0

    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            clickCOunt++
        }

        override fun mouseEntered(e: MouseEvent?) {
            enterCount++
        }
    })
    println(clickCOunt)
    println(enterCount)
}

/**
 *  对象声明
 *  Kotlin 使用 object 关键字来声明一个对象。
 *  Kotlin 中我们可以方便的通过对象声明来获得一个单例。
 */

object DataProvidManager {

    fun registerDataProvidProvider(provider: DataProvidManager) {
        println("registerDataProvidProvider")
    }

    val allDataProvid: Collection<DataProvidManager>
        get() = DataProvidManager.allDataProvid
}

/**
 * 伴生对象
 * 类内部的对象声明可以用 companion 关键字标记，
 * 这样它就与外部类关联在一起，我们就可以直接通过外部类访问到对象的内部元素。
 *
 * 1.省略掉该对象的对象名，然后使用 Companion 替代需要声明的对象名
 * 2.一个类里面只能声明一个内部关联对象，即关键字 companion 只能使用一次
 */
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}


class MyClass2 {
    companion object {

    }
}

//伴生对象的成员看起来像其他语言的静态成员，但在运行时他们仍然是真实对象的实例成员
interface Factory<T> {
    fun create(): T
}

//伴生对象实现接口
class MyClass3 {
    companion object : Factory<MyClass3> {
        override fun create(): MyClass3 = MyClass3()
    }
}