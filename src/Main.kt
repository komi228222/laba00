import kotlin.math.sqrt

// 1
fun task1() {
    print("Введите число: ")
    val s = readln()

    // способ 1 - через деление
    val n = s.toInt()
    val last = n % 10
    var first = n
    while (first >= 10) {
        first /= 10
    }
    println("Сумма (способ 1): " + (first + last))

    // способ 2 - через first и last
    val f = s.first().toString().toInt()
    val l = s.last().toString().toInt()
    println("Сумма (способ 2): " + (f + l))
}

// 2
fun task2() {
    var count = 0
    var sum = 0
    var n = -1
    println("Вводите числа, 0 - конец:")
    while (n != 0) {
        n = readln().toInt()
        if (n != 0) {
            sum += n
            count += 1
        }
    }
    println("Кол-во: $count")
    println("Сумма: $sum")
    println("Среднее: " + sum.toDouble() / count)
}

// 3
fun task3() {
    val a = (0..10).random()
    var b = -1
    while (b != a) {
        print("Введите число: ")
        b = readln().toInt()
        if (b > a) {
            println("Много")
        } else if (b < a) {
            println("Мало")
        }
    }
    println("Угадал!")
}

// 4
fun task4() {
    print("n = ")
    val n = readln().toInt()

    var count = 0
    var i = 2
    while (count < n) {
        var prost = true
        var d = 2
        while (d < i) {
            if (i % d == 0) {
                prost = false
                break
            }
            d += 1
        }
        if (prost) {
            count += 1
            println("$count-ое число: $i")
        }
        i += 1
    }
}

// 5
fun task5() {
    val arr = intArrayOf(1, 3, 2, 5, 4, 8, 7, 10, 9, 6)
    for (i in 1..arr.size - 2) {
        if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
            println(arr[i])
        }
    }
}

// 6
fun task6() {
    val arr = intArrayOf(2, 3, 4, 5, 6)

    // for
    var p1 = 1
    var mn1 = arr[0]
    var mx1 = arr[0]
    for (i in arr) {
        p1 *= i
        if (i < mn1) mn1 = i
        if (i > mx1) mx1 = i
    }
    println("for: произведение = $p1, min = $mn1, max = $mx1")

    // while
    var p2 = 1
    var mn2 = arr[0]
    var mx2 = arr[0]
    var k = 0
    while (k < arr.size) {
        p2 *= arr[k]
        if (arr[k] < mn2) mn2 = arr[k]
        if (arr[k] > mx2) mx2 = arr[k]
        k += 1
    }
    println("while: произведение = $p2, min = $mn2, max = $mx2")

    // forEach
    var p3 = 1
    arr.forEach { p3 *= it }
    println("forEach: произведение = $p3")

    // reduce
    val p4 = arr.reduce { acc, i -> acc * i }
    println("reduce: произведение = $p4")

    // min max
    println("min = " + arr.min() + ", max = " + arr.max())
}

// 7
fun sqr(n: Double): Double {
    return n * n
}

fun discriminant(a: Double, b: Double, c: Double): Double {
    return sqr(b) - 4 * a * c
}

fun rootsNumber(a: Double, b: Double, c: Double): Int {
    val d = discriminant(a, b, c)
    return when {
        d > 0 -> 2
        d == 0.0 -> 1
        else -> 0
    }
}

fun quadraticRoot(a: Double, b: Double, c: Double) {
    val d = discriminant(a, b, c)
    val kol = rootsNumber(a, b, c)
    when (kol) {
        2 -> {
            val x1 = (-b + sqrt(d)) / (2 * a)
            val x2 = (-b - sqrt(d)) / (2 * a)
            println("x1 = $x1, x2 = $x2")
        }

        1 -> {
            val x = -b / (2 * a)
            println("x = $x")
        }

        else -> {
            println("Корней нет")
        }
    }
}

fun task7() {
    print("a = ")
    val a = readln().toDouble()
    print("b = ")
    val b = readln().toDouble()
    print("c = ")
    val c = readln().toDouble()
    quadraticRoot(a, b, c)
}

// 8
class ArrayProcessor(private val arr: IntArray) {

    fun sumPositive(): Int {
        var s = 0
        for (i in arr) {
            if (i > 0) s += i
        }
        return s
    }
    
    fun product(): Int {
        var p = 1
        for (i in arr) {
            p *= i
        }
        return p
    }

    fun average(): Double {
        var s = 0
        for (i in arr) {
            s += i
        }
        return s.toDouble() / arr.size
    }
}

fun task8() {
    val arr = intArrayOf(-2, 3, 5, -1, 4, 0, 7)
    val m = ArrayProcessor(arr)
    println("Сумма положительных: " + m.sumPositive())
    println("Произведение: " + m.product())
    println("Среднее: " + m.average())
}

// 9
class Vector(val x: Double, val y: Double, val z: Double) {

    fun dlina(): Double {
        return sqrt(x * x + y * y + z * z)
    }

    infix fun dot(v: Vector): Double {
        return x * v.x + y * v.y + z * v.z
    }

    operator fun times(v: Vector): Double {
        return this dot v
    }
}

fun scalar(v1: Vector, v2: Vector): Double {
    return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z
}

fun task9() {
    val v1 = Vector(1.0, 2.0, 3.0)
    val v2 = Vector(3.0, 2.0, 1.0)

    println("Длина v1 = " + v1.dlina())
    println("Скаляр (метод): " + v1.dot(v2))
    println("Скаляр (infix): " + (v1 dot v2))
    println("Скаляр (*): " + (v1 * v2))
    println("Скаляр (внешняя): " + scalar(v1, v2))
}

// 10
open class Vehicle {
    open val name = "ТС"
    open val speed = 0

    open fun start() {
        println("$name начал движение со скоростью $speed км/ч")
    }

    open fun stop() {
        println("$name остановился")
    }
}

class Car : Vehicle() {
    override val name = "Автомобиль"
    override val speed = 90
}

class Motorcycle : Vehicle() {
    override val name = "Мотоцикл"
    override val speed = 120
}

class Bicycle : Vehicle() {
    override val name = "Велосипед"
    override val speed = 25
}

fun task10() {
    val c = Car()
    val m = Motorcycle()
    val b = Bicycle()

    c.start(); c.stop()
    m.start(); m.stop()
    b.start(); b.stop()
}

// меню
fun main() {
    while (true) {
        println()
        println("1 - задание 1")
        println("2 - задание 2")
        println("3 - задание 3")
        println("4 - задание 4")
        println("5 - задание 5")
        println("6 - задание 6")
        println("7 - задание 7")
        println("8 - задание 8")
        println("9 - задание 9")
        println("10 - задание 10")
        println("0 - выход")
        print("Выбор: ")

        val v = readln().toInt()
        when (v) {
            1 -> task1()
            2 -> task2()
            3 -> task3()
            4 -> task4()
            5 -> task5()
            6 -> task6()
            7 -> task7()
            8 -> task8()
            9 -> task9()
            10 -> task10()
            0 -> return
            else -> println("Нет такого пункта")
        }
    }
}