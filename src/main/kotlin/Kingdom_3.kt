//Практикум "Королевство.Часть 3"

import kotlin.random.Random

open class Ruler3(private val rulerName3: String, private val intelect3: Int,
                  private val power3: Int) : Kingdom3() { // Объявление открытого класса Ruler3

    fun rullingKingdom3(): String {
        return "Имя короля: $rulerName3, интелект: $intelect3, сила: $power3"
    }
}

//Класс крестьянина
data class Peasant3(val occupation: Occupation3) //Объявление дата-класса (класса для хранения данных),
// внутри класса в скобках создается конструктор, который принимает объект Occupation3 в качестве
// параметра и сохраняет его в неизменяемую переменную occupation.

// Перечисление профессий
enum class Occupation3 { // Объявление enum (специальный класс для хранения коллекций,
    // констант или типов) класса Occupation, содержащий в себе
    WORKER, BUILDER, FARMER // неинициализированные строковые переменные (константы) WORKER, BUILDER, FARMER.
}

// Интерфейс для сборщиков налогов
interface CollectTaxes3 {
    fun collect() // Определение функции collect()
}

// Класс королевства
open class Kingdom3 { // Объявление класса Kingdom_3
    val heirs = mutableListOf<Heirs3>() // объявление списка наследников

    val archers = mutableListOf<Archer3>() // объявление списка лучников

    val warriors = mutableListOf<Warrior3>() // объявление списка воинов
    var treasury = 0 // Объявление изменяемой переменной treasury (казна) типа Int,
    // инициализированной нулем.
    val peasants = mutableListOf<Peasant3>() // объявление списка крестьян

}

// Абстрактный класс для сборщиков налогов, в скобках конструктор класса в сокращенной форме,
// двоеточие означает наследование от класса CollectTaxes
abstract class TaxCollector3(var kingdom: Kingdom3) : CollectTaxes3 {
    constructor() : this(Kingdom3()) //конструктор создан автоматически идеей, вписал только Kingdom_2
}

// Сборщик налогов для рабочих
val workerTaxCollector3 = object : TaxCollector3() { //Создается переменная с именем workerTaxCollector3
    //ей присваивается созданный анонимный объект object, который наследуется от класса TaxCollector3.
    override fun collect() { //Переопределяется метод collect() из класса TaxCollector.
        val taxGroup = kingdom.peasants.filter { it.occupation == Occupation3.WORKER }
        // Создается переменная taxGroup типа List<Peasant3>(см. класс Kingdom3).
        //Из свойства peasants королевства kingdom фильтруются крестьяне с профессией WORKER.
        //kingdom.peasants — список крестьян в королевстве.
        //filter { ... } — метод фильтрации списка.
        //it.occupation == Occupation3.WORKER — условие фильтрации: крестьянин должен иметь профессию WORKER.
        kingdom.treasury += taxGroup.size //В казначейство королевства kingdom добавляется количество
        // крестьян в группе taxGroup.
        // kingdom.treasury — свойство казначейства королевства.
        // += — оператор прибавления значения.
        // taxGroup.size — количество элементов в списке taxGroup.
    }
}

// Сборщик налогов для строителей
// (см. описание выше, как для worker, только кол-во элементов в списке еще умножаем на 2)
val builderTaxCollector3 = object : TaxCollector3() {
    override fun collect() {
        val taxGroup = kingdom.peasants.filter { it.occupation == Occupation3.BUILDER }
        kingdom.treasury += taxGroup.size * 2
    }
}

// Сборщик налогов для фермеров
// (см. описание выше, как для worker, только кол-во элементов в списке еще умножаем на 3)
val farmerTaxCollector3 = object : TaxCollector3() {
    override fun collect() {
        val taxGroup = kingdom.peasants.filter { it.occupation == Occupation3.FARMER }
        kingdom.treasury += taxGroup.size * 3
    }
}

private fun addPeasants3(myKingdom: Kingdom3) {  //функция addPeasants3 принимает параметр
    // myKingdom типа Kingdom3. Это значит, что функция addPeasants ожидает,
    // что ей будет передан объект типа Kingdom3.

    val peasants = mutableListOf<Peasant3>() // Создаем список для хранения крестьян

    // Создаем 100 крестьян с разными профессиями:
    for (i in 1..100) {
        // Используем `when` для выбора профессии:
        when {
            // Если i делится на 2, то крестьянин - строитель
            i % 2 == 0 -> {
                peasants.add(Peasant3(Occupation3.BUILDER))
            }
            // Если i делится на 3, то крестьянин - фермер
            i % 3 == 0 -> {
                peasants.add(Peasant3(Occupation3.FARMER))
            }
            // В остальных случаях крестьянин - рабочий
            else -> {
                peasants.add(Peasant3(Occupation3.WORKER))
            }
        }
    }
    //  Добавляем всех созданных крестьян в королевство
    myKingdom.peasants.addAll(peasants)
}

// Класс Heirs3 (наследницы), наследующий от Ruler3
class Heirs3(val name: String, val intellect: Int, val power: Int) : Ruler3(name, intellect, power) {
    // ... вариация класса Heir через список
}

// Класс Archer3 (лучник)
data class Archer3(val name: String, val bow: String, val dagger: String?) {
    //...
}

// Класс Warrior3 (воин)
data class Warrior3(val name: String, val weapon: String) {
    // ...
}

private fun addHeirs() {
    // Создаем список для хранения наследниц
    val heirs = mutableListOf<Heirs3>()
    // Добавляем наследниц в список
    heirs.add(Heirs3("Мария", 8, 6))
    heirs.add(Heirs3("Елизавета", 9, 8))
    heirs.add(Heirs3("Екатерина", 7, 8))
    // ... добавьте других наследниц

    // Выводим список наследниц
    println("Наследницы короля:")
    heirs.forEach { heir ->
        println("Имя: ${heir.name}, Интеллект: ${heir.intellect}, Сила: ${heir.power}")
    }
}

private fun addArchers() {
    // Создаем список для хранения лучников
    val archers = mutableListOf<Archer3>()

    // Создаем 5 лучников со случайным наличием кинжала
    for (i in 1..5) {
        val name = "Лучник $i"
        val bow = "Длинный лук $i"
        val dagger = if (Random.nextInt(2) == 0) "в наличии $i" else null
        archers.add(Archer3(name, bow, dagger))
    }
    // Выводим список лучников
    println("Лучники короля:")
    archers.forEach { archer ->
        println("${archer.name}: лук - ${archer.bow}, кинжал - ${archer.dagger ?: "без кинжала"}}")
    }
}

private fun addWarriors() {
    // Создаем список для хранения воинов
    val warriors = mutableListOf<Warrior3>()

    // Создаем 5 воинов со случайным выбором оружия
    for (x in 1..5) {
        val name = "Воин $x"
        val weapon = if (Random.nextInt(2) == 0) "Меч" else "Топор"
        warriors.add(Warrior3(name, weapon))
    }

    // Выводим список воинов
    println("Воины короля:")
    warriors.forEach { warrior ->
        println("${warrior.name}: оружие - ${warrior.weapon}")
    }
}
// Создание one line функции, которая будет считать, сколько каждый сборщик налогов собрал со своей группы:
fun taxCalculation(size: Int, multiplier: Int): Int = size * multiplier
// Разберем подробнее. Здесь у нас функция taxCalculation, где в скобках(size: Int, multiplier: Int): -
// это параметры функции. Оба параметра и size и multiplier являются Int, т.е. целочисленными.
// : Int: - сразу после скобок, указывает тип возвращаемого значения функции. У нас это целое число (Int).
// = size * multiplier - это тело функции. В нём происходит расчёт налога.
// Функция умножает size на multiplier и возвращает результат.

// Создание extension функции, которая будет добавлять «Ваше Высочество» к строкам и выводить это в консоль.
fun String.yourHighness() {
    println("Ваше Высочество, $this")
}
// Разберем подробнее. Здесь у нас функция yourHighness(): Это имя функции.
// Она не принимает никаких параметров. String: Указывает, что эта функция
// является расширяющей функцией для класса String. Это значит, что ее можно
// вызывать на объектах типа String. Println ("Ваше...) - вывод в консоль
// $this - специальный символ, который заменяется на значение объекта,
// на котором вызвана функция yourHighness. У нас в main это
// "Ваша популярность падает!" - значение объекта, .yourHighness() - вызов функции.

fun main() {
    // Создаем объект Ruler3 (правителя)
    val kingdom3 = Ruler3("Александр", 10, 10)

    //Чтобы использовать функцию addPeasants3, нужно создать объект Kingdom3 и передать его в addPeasants3:
    val myKingdom = Kingdom3() // Создаем объект Kingdom3

    addPeasants3(myKingdom) // Вызывая функцию addPeasants3 в main мы передаем ей переменную myKingdom,
    //которая представляет объект класса Kingdom3.

    println(kingdom3.rullingKingdom3()) // // Выводим в консоль информацию о правителе

    // Устанавливаем объект myKingdom для сборщика налогов
    //(связываем каждого сборщика налогов с королевством)
    workerTaxCollector3.kingdom = myKingdom
    builderTaxCollector3.kingdom = myKingdom
    farmerTaxCollector3.kingdom = myKingdom

    //Вызываем функцию collect() у каждой группы крестьян
    //(сбор налогов от каждой группы крестьян):
    workerTaxCollector3.collect()
    builderTaxCollector3.collect()
    farmerTaxCollector3.collect()

    // Выводим количество золота в королевской казне
    println("Золото короля: " + myKingdom.treasury)

    // Объявляем переменную workerTax с типом Int. Присваиваем ей (при помощи =)
    // результат вызова функции taxCalculation (функция, которая, рассчитывает налог).
    // workerTaxCollector3 - это объект (сборщик налогов для рабочих),
    // который содержит свойство .kingdom, которое содержит ссылку на объект Kingdom3.
    val workerTax = taxCalculation(workerTaxCollector3.kingdom.peasants.filter {
        it.occupation == Occupation3.WORKER }.size, 1)
    //.peasants - это свойство объекта Kingdom3 (список крестьян), к которому workerTaxCollector3.kingdom ссылается.
    // .filter { ... } - это метод, который фильтрует список крестьян.
    // { it.occupation == Occupation3.WORKER } - это лямбда-выражение, которое определяет условие фильтрации.
    // it: Это текущий элемент списка (крестьянин) во время итерации.
    //it.occupation: Это свойство крестьянина, которое определяет его профессию.
    //Occupation3.WORKER: Это константа, которая представляет профессию “рабочий”.
    //В результате выполнения .filter, мы получим новый список, который будет содержать только тех крестьян,
    // у которых профессия “рабочий”.
    // .size: Это свойство, которое возвращает количество элементов в списке
    // (в данном случае, количество рабочих). Для WORKER size = 1, для BUILDER и FARMER 2 и 3 соответственно.

    // Объявляем переменную builderTax с типом Int. Дальше смотри описание WORKER (выше)
    val builderTax = taxCalculation(builderTaxCollector3.kingdom.peasants.filter {
        it.occupation == Occupation3.BUILDER }.size, 2)

    // Объявляем переменную farmerTax с типом Int. Дальше смотри описание WORKER (выше)
    val farmerTax = taxCalculation(farmerTaxCollector3.kingdom.peasants.filter {
        it.occupation == Occupation3.FARMER }.size, 3)

    //Выводим в консоль количество денег для каждой группы крестьян
    println("Налоговая служба короля.")
    println("Рабочие: Собрано ${workerTax} золотых")
    println("Строители: Собрано ${builderTax} золотых")
    println("Фермеры: Собрано ${farmerTax} золотых")

    //Вызов функции .yourHighness()
    "Ваша популярность падает!".yourHighness()
    "На нас напали!".yourHighness()
    "Нужно больше золота!".yourHighness()

    //Вызываем наследниц короля
    addHeirs()

    println("Армия короля.")

    //Вызываем остальные методы addArchers addWarriors.
    addArchers()
    addWarriors()
}