package KotlinStyle4

/**
 *  扩展
 *  扩展是一种静态行为，对被扩展的类代码本身不会造成任何影响。
 */

//扩展属性
val <T> List<T>.lastIndex: Int
    get() = size - 1

/**
 * 扩展属性允许定义在类或者kotlin文件中，不允许定义在函数中。初始化属性因为属性没有后端字段（backing field），
 * 所以不允许被初始化，只能由显式提供的 getter/setter 定义。
 *
 * 扩展属性只能被声明为 val
 */

//val Foo.bar = 1 //错误：扩展属性不能有初始化器


fun main(args: Array<String>) {

    //扩展函数
    val user = User("hh")
    user.Print()

    //位置互换
    val mutableListOf = mutableListOf(1, 2, 3, 4)
    //位置0,3的值做了互换
    mutableListOf.swap(0, 3)
    println(mutableListOf.toString())

    //扩展函数是静态解析的
    printFoo(Y())

    //扩展一个空对象
    var t = null
    println(t.toString())

    //伴生对象的扩展
    MyClass.foo()
    println("no ${MyClass.no}")

    //扩展声明为成员
    val d = D()
    val e = E()
    e.caller(d)


}

/**
 * 扩展函数
 * 扩展函数可以在已有类中添加新的方法，不会对原类做修改，扩展函数定义形式
 */

class User(var name: String)

fun User.Print() {
    println("用户名$name")
}

//扩展函数swap,调换不同位置的值
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] //this对应列表
    this[index1] = this[index2]
    this[index2] = tmp
}

/**
 *  扩展函数是静态解析的
 *  若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数。
 */
open class X

class Y : X()

fun X.foo() = "x"//扩展函数

fun Y.foo() = "y"//扩展函数

fun printFoo(x: X) {
    println(x.foo())
}


/**
 *  扩展一个空对象
 *  在扩展函数内， 可以通过 this 来判断接收者是否为 NULL,这样，即使接收者为 NULL,也可以调用扩展函数。
 */
fun Any?.toString(): String {
    if (this == null) {
        //空监测之后，"this"会自动转换为非空类型，
        //所以下面的toString(),解析为Any类的成员函数
        return "null"
    }
    return toString()
}


/**
 * 伴生对象的扩展
 * 如果一个类定义有一个伴生对象 ，你也可以为伴生对象定义扩展函数和属性
 * 伴生对象通过"类名."形式调用伴生对象，伴生对象声明的扩展函数，通过用类名限定符来调用
 */
class MyClass {
    //伴生对象
    companion object {

    }
}

fun MyClass.Companion.foo() {
    println("伴生对象的扩展函数")
}

val MyClass.Companion.no: Int
    get() = 10

/**
 * 扩展声明为成员
 *在这个扩展中，有个多个隐含的接受者，其中扩展方法定义所在类的实例称为分发接受者，
 * 而扩展方法的目标类型的实例称为扩展接受者。
 *
 * 在一个类内部你可以为另一个类声明扩展。
 */

class D {
    fun bar() {
        println("D bar")
    }
}

class E {
    fun bar() {
        println("E baz")
    }

    fun D.foo() {//D的扩展,扩展接收者优先
        bar()//D.bar
        this@E.bar()//E.baz
    }

    fun caller(d: D) {
        d.foo()//调用扩展函数
    }
}

