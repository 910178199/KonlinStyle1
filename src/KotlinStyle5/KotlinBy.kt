package KotlinStyle5

import java.sql.Ref
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 委托
 *  Kotlin 通过关键字 by 实现委托
 *
 */
fun main(args: Array<String>) {

    //调用类委托
    //在 Derived 声明中，by 子句表示，将 b 保存在 Derived 的对象实例内部，
    // 而且编译器将会生成继承自 Base 接口的所有方法, 并将调用转发给 b
    val baseImp = BaseImp(10)
    Derived(baseImp).print()

    //属性委托
    val e = Example()
    println(e.p)//访问该属性，调用getValue()

    e.p = "123"//调用setValue()函数
    println(e.p)

    //延时属性lazy
    println(lazyValue)//第一次执行，执行两次输出表达式
    println(lazyValue)//第二次执行，只输出返回值

    //观察者
    val user = User()
    user.name = "1"
    user.name = "2"

    //构造函数接受一个映射参数
    val site = Site(mapOf("name" to "N", "url" to "www"))
    //读取映射的值
    println(site.name)
    println(site.url)

    var map: MutableMap<String, Any?> = mutableMapOf(
            "name" to "mutable",
            "url" to "wwws"
    )
    val siteMutableMap = SiteMutableMap(map)
    println(siteMutableMap.name)
    println(siteMutableMap.url)

    println("--------")
    map.put("name", "baidu")
    map.put("url", "www.baidu.com")
    println(siteMutableMap.name)
    println(siteMutableMap.url)

    //notNull
    val foo = Foo()
    foo.notNullBar = "bar"
    println(foo.notNullBar)
}

/**
 * 类委托
 * 类的委托即一个类中定义的方法实际是调用另一个类的对象的方法来实现的。
 */

//创建接口
interface Base {
    fun print()
}

//实现接口
class BaseImp(val x: Int) : Base {
    override fun print() {
        println(x)
    }
}

//创建委托类
class Derived(b: Base) : Base by b

/**
 *  属性委托
 *  属性委托指的是一个类的某个属性值不是在类中直接进行定义，而是将其托付给一个代理类，从而实现对该类的属性统一管理
 *
 *  by 关键字之后的表达式就是委托, 属性的 get() 方法(以及set() 方法)将被委托给这个对象的 getValue() 和 setValue() 方法。
 *  属性委托不必实现任何接口, 但必须提供 getValue() 函数(对于 var属性,还需要 setValue() 函数)
 */

//定义一个被委托的类
//该类需要包含 getValue() 方法和 setValue() 方法，且参数 thisRef 为进行委托的类的对象，prop 为进行委托的属性的对象。

class Example {
    var p: String by Delegate()
}

//委托的类
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef,这里委托了${property.name}属性"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef,的${property.name}属性赋值为$value")
    }
}

/**
 * 标准委托
 * Kotlin 的标准库中已经内置了很多工厂方法来实现属性的委托。
 */

//延迟属性 Lazy
//lazy() 是一个函数, 接受一个 Lambda 表达式作为参数, 返回一个 Lazy <T> 实例的函数，
// 返回的实例可以作为实现延迟属性的委托： 第一次调用 get() 会执行已传递给 lazy() 的
// lamda 表达式并记录结果， 后续调用 get() 只是返回记录的结果。

val lazyValue: String by lazy {
    println("computed") //第一次调用输出，第二次调用不执行
    "Hello"
}

/**
 * 可观察属性Observable
 * 实现观察者模式
 * Delegates.observable() 函数接受两个参数: 第一个是初始化值, 第二个是属性值变化事件的响应器(handler)。
 * 在属性赋值后会执行事件的响应器(handler)，它有三个参数：被赋值的属性、旧值和新值
 */

class User {
    var name: String by Delegates.observable("初始化") { property, oldValue, newValue ->
        println("旧值：$oldValue -> 新值：$newValue")
    }
}

/**
 * 把属性储存在映射中
 * 一个常见的用例是在一个映射（map）里存储属性的值。 这经常出现在像解析 JSON 或者做其他"动态"事情的应用中。
 */

class Site(val map: Map<String, Any?>) {
    val name: String by map
    val url: String by map
}

//如果使用 var 属性，需要把 Map 换成 MutableMap：
class SiteMutableMap(val map: MutableMap<String, Any?>) {
    val name: String by map
    val url: String by map
}


/**
 * notNUll
 * notNull 适用于那些无法在初始化阶段就确定属性值的场合。
 */
class Foo {
    var notNullBar: String by Delegates.notNull<String>()
}

/**
 * 局部委托属性
 * 你可以将局部变量声明为委托属性
 * memoizedFoo 变量只会在第一次访问时计算
 */
fun example(computeFoo: () -> Foo) {
    val memoizedFoo by lazy(computeFoo)

    /*if (memoizedFoo.isValid()) {

    }*/
}

/**
 * 属性委托要求
 * 对于只读属性(也就是说val属性), 它的委托必须提供一个名为getValue()的函数。该函数接受以下参数：
 * thisRef —— 必须与属性所有者类型（对于扩展属性——指被扩展的类型）相同或者是它的超类型
 * property —— 必须是类型 KProperty<*> 或其超类型
 * 这个函数必须返回与属性相同的类型（或其子类型）。
 * 对于一个值可变(mutable)属性(也就是说,var 属性),除 getValue()函数之外,它的委托还必须 另外再提供一个名为setValue()的函数, 这个函数接受以下参数:
 * property —— 必须是类型 KProperty<*> 或其超类型
 * new value —— 必须和属性同类型或者是它的超类型。
 */








