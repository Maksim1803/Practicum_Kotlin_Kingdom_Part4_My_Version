//Практикум "Королевство. Часть 1"

import kotlin.random.Random


// Интерфейс Kingdom, описывающий функциональность управления королевством
interface Kingdom {
    fun rullingKingdom(): String
}

// Базовый класс Ruler (правитель), реализующий интерфейс Kingdom
open class Ruler(private val rulerName: String, private val intelect: Int,
                 private val power: Int) : Kingdom {
    // Переопределяем метод rullingKingdom для вывода информации о правителе
    override fun rullingKingdom(): String {
        return "Имя короля: $rulerName, интелект: $intelect, сила: $power"
    }
}

// Класс Heir1 (наследник 1), наследующий от Ruler
class Heir1(private val heir1Name: String, private val intelect: Int,
            private val power: Int) : Ruler(heir1Name, intelect, power) {
    // Переопределяем метод rullingKingdom для вывода информации о наследнике 1
    override fun rullingKingdom(): String {
        return "Имя наследника 1: $heir1Name, интелект: $intelect, сила: $power"
    }
}

// Класс Heir2 (наследник 2), наследующий от Ruler
class Heir2(private val heir2Name: String, private val intelect: Int,
            private val power: Int) : Ruler(heir2Name, intelect, power) {
    // Переопределяем метод rullingKingdom для вывода информации о наследнике 2
    override fun rullingKingdom(): String {
        return "Имя наследника 2: $heir2Name, интелект: $intelect, сила: $power"
    }
}

// Класс Heir (наследник), наследующий от Ruler
class Heir(val name: String, val intellect: Int, val power: Int) : Ruler(name, intellect, power) {
    // ... вариация класса Heir через список
}

// Класс Archer (лучник)
data class Archer(val name: String, val bow: String, val dagger: String?) {
    //...
}

// Класс Warrior (воин)
data class Warrior(val name: String, val weapon: String) {
    // ...
}

// Главная функция
fun main() {
    // Создаем объект Ruler (правителя)
    val kingdom = Ruler("Павел", 10, 10)
    // Создаем объект Heir1 (наследника 1)
    val heir1 = Heir1("Иван", 8, 7)
    // Создаем объект Heir2 (наследника 2)
    val heir2 = Heir2("Павел", 9, 9)
    // Выводим информацию о правителе и наследниках
    println(kingdom.rullingKingdom())
    println(heir1.rullingKingdom())
    println(heir2.rullingKingdom())

    // Создаем список для хранения наследниц
    val heirs = mutableListOf<Heir>()
    // Добавляем наследниц в список
    heirs.add(Heir("Мария", 8, 6))
    heirs.add(Heir("Елизавета", 9, 8))
    heirs.add(Heir("Екатерина", 7, 8))
    // ... добавьте других наследниц

    // Выводим список наследниц
    println("Список наследниц:")
    heirs.forEach { heir ->
        println("Имя: ${heir.name}, Интеллект: ${heir.intellect}, Сила: ${heir.power}")
    }

    // Создаем список для хранения лучников
    val archers = mutableListOf<Archer>()

    // Создаем 20 лучников со случайным наличием кинжала
    for (i in 1..20) {
        val name = "Archer $i"
        val bow = "LongBow $i"
        val dagger = if (Random.nextInt(2) == 0) "Dagger $i" else null
        archers.add(Archer(name, bow, dagger))
    }

    // Выводим список лучников
    println("Список лучников:")
    archers.forEach { archer ->
        println("${archer.name}: лук - ${archer.bow}, кинжал - ${archer.dagger ?: "без кинжала"}}")
    }

    // Создаем список для хранения воинов
    val warriors = mutableListOf<Warrior>()

    // Создаем 30 воинов со случайным выбором оружия
    for (x in 1..30) {
        val name = "Warrior $x"
        val weapon = if (Random.nextInt(2) == 0) "Sword" else "Axe"
        warriors.add(Warrior(name, weapon))
    }

    // Выводим список воинов
    println("Список воинов:")
    warriors.forEach { warrior ->
        println("${warrior.name}: оружие - ${warrior.weapon}")
    }
}