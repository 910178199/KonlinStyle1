package KotlinStyle4

/**
 * 接口
 *
 */

fun main(args: Array<String>) {

    //接口
    val child = Child()
    child.bar()
    child.foo()
    println(child.name)

    //函数重写
    val ds = Ds()
    ds.foo()
    ds.bar()

}

interface MyInterface {
    var name: String  //name属性，抽象
    fun bar()//未实现
    fun foo() {//实现
        //可选的方法体
        println("foo")
    }
}

class Child : MyInterface {
    override var name: String = "重载属性"//重载属性

    override fun bar() {
        //方法体
        println("bar")
    }
}

/**
 * 函数重写
 */
interface As {
    fun bar()
    fun foo() {
        println("A")
    }
}

interface Bs {
    fun bar() {
        println("bar")
    }

    fun foo() {
        println("B")
    }
}

class Cs : As {
    override fun bar() {
        println("bar")
    }
}

class Ds : As, Bs {
    override fun bar() {
        super<Bs>.bar()
    }

    override fun foo() {
        super<As>.foo()
        super<Bs>.foo()
    }
}



