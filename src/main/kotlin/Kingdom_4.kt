// Практикум "Королевство. Часть 4"

import kotlin.random.Random

// Класс Archer4 (лучник):
data class Archer4(val name: String, var bow: String, val dagger: String?) {
    //...
}

// Класс Warrior4 (воин):
data class Warrior4(val name: String, var weapon: String) {
    // ...
}

//Класс крестьянина:
data class Peasant4(val occupation: Occupation4)//Объявление дата-класса (класса для хранения данных),
// внутри класса в скобках создается конструктор, который принимает объект Occupation4 в качестве
// параметра и сохраняет его в неизменяемую переменную occupation.

// Перечисление профессий
enum class Occupation4 { // Объявление enum (специальный класс для хранения коллекций,
    // констант или типов) класса Occupation4, содержащий в себе
    WORKER, BUILDER, FARMER // неинициализированные строковые переменные WORKER, BUILDER, FARMER.
}

// Объявления неизменяемых переменных worker, builder и farmer, которые хранят значения
// Occupation4.WORKER, Occupation4.BUILDER и Occupation4.FARMER соответственно,
// т.е. хранят значения констант WORKER, BUILDER, FARMER из класса Occupation4:
    val worker = Occupation4.WORKER
    val builder = Occupation4.BUILDER
    val farmer = Occupation4.FARMER

// Улучшения для лучников. Объявляем функцию (ключевое слово fun) с именем upgradeYourArchers.
// В скобках после имени записываем параметры функции. Первый параметр это "list: List<Archer4>",
// который является списком объектов типа Archer4. Второй параметр это "operation: (List<Archer4>) -> Unit)",
// который является лямбда-функцией, и передается как аргумент. Эта функция принимает список объектов
// типа Archer4 и не возвращает значение Unit. В скобках { ... } у нас находится тело функции.
fun upgradeYourArchers(list: List<Archer4>, operation: (List<Archer4>) -> Unit) {
// Далее у нас идет "list.forEach" - метод для перебора всех элементов в списке list.
// "it" - переменная, которая в каждой итерации цикла приобретает значение следующего элемента списка.
// "it.bow = "Составной лук"" означает, что в каждой итерации цикла метод forEach изменяет свойство bow
// текущего элемента списка it на "Составной лук":
    list.forEach {
        it.bow = "Составной лук"
    }
    // Вызов лямбда-функции, переданной в качестве параметра,
    // и передача списка list в качестве аргумента лямбда-функции:
       operation(list)
}

// Улучшения для воинов. Аналогично, как и для лучников. Коротко, эти лямбда-функции
// проходят по всем воинам и лучникам в списках list и устанавливают новые свойства
// ("Булатный клинок" и “Составной лук”), заменяя текущие значения ("меч", "Длинный лук")
fun upgradeYourWarriors(list: List<Warrior4>, operation: (List<Warrior4>) -> Unit) {
    list.forEach {
        it.weapon = "Булатный клинок"
    }
    operation(list)
}
// Сравнение крестьян. Объявляем функцию с именем givegiveFunToPesants.
// В скобках после имени записываем параметры функции. Здесь один параметр это "list: List<Peasant4>",
// который является списком объектов типа Peasant4. В скобках { ... } у нас находится тело функции.
fun givegiveFunToPesants(list: List<Peasant4>) {
// Далее у нас идет "list.forEach" - метод для перебора всех элементов в списке list.
// "peasant ->" - лямбда выражение, где peasant - переменная, которая в каждой итерации
// цикла приобретает значение следующего элемента списка. В скобках { ... } у нас находится тело метода.
    list.forEach {peasant ->
// Внутри нашего метода есть вложенный цикл "list.forEach { if ...}", который выполняется внутри каждой
// итерации внешнего цикла (метода) "list.forEach {peasant -> ... }".
// if(peasant.occupation == it.occupation) println("Привет, коллега!")` - в этой строке проверяется,
// совпадает ли профессия крестьянина peasant с профессией крестьянина it. Если да, то в консоль
// выводится строка "Привет, коллега!", иначе: "Вижу мы ... разным"
        list.forEach {
            if(peasant.occupation == it.occupation) println("Привет, коллега!")
            else println("Вижу мы с вами занимаемся разным")
        }
    }
}

fun main() {
    // Создаем список для хранения лучников
    val archers = mutableListOf<Archer4>()

    // Создаем 5 лучников со случайным наличием кинжала
    for (i in 1..5) {
        val name = "Лучник $i"
        val bow = "Длинный лук $i" // Здесь должны быть "Длинные луки"
        val dagger = if (Random.nextInt(2) == 0) "в наличии" else null
        archers.add(Archer4(name, bow, dagger))
    }

    upgradeYourArchers(archers) { list ->
        println("Лучники короля:")
        list.forEach { archer ->
            println("${archer.name}: лук - ${archer.bow}, кинжал - ${archer.dagger ?: "без кинжала"}}")
        }
    }
    // Создаем список для хранения воинов
    val warriors = mutableListOf<Warrior4>()

    // Создаем 5 воинов со случайным выбором оружия
    for (x in 1..5) {
        val name = "Воин $x"
        val weapon = if (Random.nextInt(2) == 0) "Меч" else "Топор"
        warriors.add(Warrior4(name, weapon))
    }

    // Выводим список воинов
    upgradeYourWarriors(warriors) { list ->
        println("Воины короля:")
        warriors.forEach { warrior ->
            println("${warrior.name}: оружие - ${warrior.weapon}")
        }
    }

    // Создаем список для хранения крестьян
    val peasants4 = mutableListOf<Peasant4>()

    for (i in 1..4) {
        when {
            i % 2 == 0 -> {
                peasants4.add(Peasant4(builder)) // Используем builder вместо Occupation5.BUILDER
            }
            i % 3 == 0 -> {
                peasants4.add(Peasant4(farmer)) // Используем farmer вместо Occupation5.FARMER
            }
            else -> {
                peasants4.add(Peasant4(worker)) // Используем worker вместо Occupation5.WORKER
            }
        }
    }
    givegiveFunToPesants(peasants4) // вызываем функцию givegiveFunToPesants
}