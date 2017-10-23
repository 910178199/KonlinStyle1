package KotlinStyle4

import KotlinStyle1.age

/**
 *  Kotlin 数据类与密封类
 *  标准库提供了 Pair 和 Triple在大多数情形中，
 *  命名数据类是更好的设计选择，因为这样代码可读性更强而且提供了有意义的名字和属性。
 */

fun main(args: Array<String>) {

    //data数据类
    val users = Users(name = "Jack", age = 1)
    val oldAge = users.copy(age = 2)
    println(users)
    println(oldAge)

    //数据类以及解构声明
    val jane = Users(name = "jane", age = 33)
    val (name, age) = jane
    println("$name $age years of age")

    //密封类
    val eval = eval(Const(33.0))
    println(eval)
}

/**
 *  Kotlin 可以创建一个只包含数据的类，关键字为 data
 *  equals() / hashCode()
 *  toString() 格式如 "User(name=John, age=42)"
 *  componentN() functions 对应于属性，按声明顺序排列
 *  copy() 函数
 *
 *  主构造函数至少包含一个参数。
 *  所有的主构造函数的参数必须标识为val 或者 var ;
 *  数据类不可以声明为 abstract, open, sealed 或者 inner;
 *  数据类不能继承其他类 (但是可以实现接口)。
 */

data class Users(val name: String, val age: Int)


/**
 * 密封类用来表示受限的类继承结构：当一个值为有限集中的类型, 而不能有任何其他类型时。
 * 在某种意义上，他们是枚举类的扩展：枚举类型的值集合 也是受限的，但每个枚举常量只存在一个实例，
 * 而密封类的一个子类可以有可包含状态的多个实例。
 * 声明一个密封类，使用 sealed 修饰类，密封类可以有子类，但是所有的子类都必须要内嵌在密封类中。
 * sealed 不能修饰 interface ,abstract class(会报 warning,但是不会出现编译错误)
 *
 * 使用密封类的关键好处在于使用 when 表达式 的时候，
 * 如果能够 验证语句覆盖了所有情况，就不需要为该语句再添加一个 else 子句了
 */

sealed class Expr

data class Const(val number: Double) : Expr()

data class Sum(val e1: Expr, val e2: Expr) : Expr()

object NotANumber : Expr()

fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}

