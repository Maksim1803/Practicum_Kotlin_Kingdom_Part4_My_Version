//Практикум "Королевство. Часть 2"

open class Ruler2  { // Объявление открытого класса Ruler2

    fun rullingKingdom_2(): String { // Определение функции rullingKingdom_2()
        return "" // Возвращаем пустую строку (можно было бы вписать:
    }           // "Его Высокое Благородие Король в здании", не создавая companion object)

    companion object { // Объявление объекта-компаньона (условие задания:
        //"создайте у короля Companion object с функцией,
        // в которой будет оглашаться его присутствие,
        // например:"Его Высокое Благородие Король в здании"."
        fun geroldGreetings() { // Определение функции geroldGreetings()
            println("Его Высокое Благородие Король в здании") // Вывод в консоль...
        }
    }
}

//Класс крестьянина
data class Peasant(val occupation: Occupation) //Объявление дата-класса (класса для хранения данных),
// внутри класса в скобках создается конструктор, который принимает объект Occupation в качестве
// параметра и сохраняет его в неизменяемую переменную occupation.

// Перечисление профессий
enum class Occupation { // Объявление enum (специальный класс для хранения коллекций,
    // констант или типов) класса Occupation, содержащий в себе
    WORKER, BUILDER, FARMER // неинициализированные строковые переменные (константы) WORKER, BUILDER, FARMER.
}

// Интерфейс для сборщиков налогов
interface CollectTaxes {
    fun collect() // Определение функции collect()
}

// Класс королевства
class Kingdom_2 { // Объявление класса Kingdom_2
    var treasury = 0 // Объявление изменяемой переменной treasury (казна) типа Int,
    // инициализированной нулем.
    val peasants = mutableListOf<Peasant>() // Объявление неизменяемой переменной
    // peasants типа List<Peasant>, инициализированной изменяемым списком Peasant.
}

// Абстрактный класс для сборщиков налогов, в скобках конструктор класса в сокращенной форме,
// двоеточие означает наследование от класса CollectTaxes
abstract class TaxCollector(var kingdom: Kingdom_2) : CollectTaxes {
    constructor() : this(Kingdom_2()) //конструктор создан автоматически идеей, вписал только Kingdom_2
}

// Сборщик налогов для рабочих
val workerTaxCollector = object : TaxCollector() { //Создается переменная с именем workerTaxCollector
    //ей присваивается созданный анонимный объект object, который наследуется от класса TaxCollector.
    override fun collect() { //Переопределяется метод collect() из класса TaxCollector.
        val taxGroup = kingdom.peasants.filter { it.occupation == Occupation.WORKER }
        // Создается переменная taxGroup типа List<Peasant>(см. класс Kingdom_2).
        //Из свойства peasants королевства kingdom фильтруются крестьяне с профессией WORKER.
        //kingdom.peasants — список крестьян в королевстве.
        //filter { ... } — метод фильтрации списка.
        //it.occupation == Occupation.WORKER — условие фильтрации: крестьянин должен иметь профессию WORKER.
        kingdom.treasury += taxGroup.size //В казначейство королевства kingdom добавляется количество
        // крестьян в группе taxGroup.
        // kingdom.treasury — свойство казначейства королевства.
        // += — оператор прибавления значения.
        // taxGroup.size — количество элементов в списке taxGroup.
    }
}

// Сборщик налогов для строителей
// (см. описание выше, как для worker, только кол-во элементов в списке еще умножаем на 2)
val builderTaxCollector = object : TaxCollector() {
    override fun collect() {
        val taxGroup = kingdom.peasants.filter { it.occupation == Occupation.BUILDER }
        kingdom.treasury += taxGroup.size * 2
    }
}

// Сборщик налогов для фермеров
// (см. описание выше, как для worker, только кол-во элементов в списке еще умножаем на 3)
val farmerTaxCollector = object : TaxCollector() {
    override fun collect() {
        val taxGroup = kingdom.peasants.filter { it.occupation == Occupation.FARMER }
        kingdom.treasury += taxGroup.size * 3
    }
}

fun main() {
    val gerold = Ruler2() // Создаем объект Ruler_2
    val myKingdom = Kingdom_2() // Создаем объект Kingdom_2
    val peasants = mutableListOf<Peasant>() // Создаем список для хранения крестьян

    // Создаем 100 крестьян с разными профессиями:
    for (i in 1..100) {
        // Используем `when` для выбора профессии:
        when {
            // Если i делится на 2, то крестьянин - строитель
            i % 2 == 0 -> {
                peasants.add(Peasant(Occupation.BUILDER))
            }
            // Если i делится на 3, то крестьянин - фермер
            i % 3 == 0 -> {
                peasants.add(Peasant(Occupation.FARMER))
            }
            // В остальных случаях крестьянин - рабочий
            else -> {
                peasants.add(Peasant(Occupation.WORKER))
            }
        }
    }
    //Добавляем всех созданных крестьян в королевство
    myKingdom.peasants.addAll(peasants)

    Ruler2.geroldGreetings() // Вызов статического метода для приветствия короля
    println(gerold.rullingKingdom_2())

    // Устанавливаем объект myKingdom для сборщика налогов
    //(связываем каждого сборщика налогов с королевством)
    workerTaxCollector.kingdom = myKingdom
    builderTaxCollector.kingdom = myKingdom
    farmerTaxCollector.kingdom = myKingdom

    //Вызываем функцию collect() у каждой группы крестьян
    //(сбор налогов от каждой группы крестьян):
    workerTaxCollector.collect()
    builderTaxCollector.collect()
    farmerTaxCollector.collect()

    //Выводим количество золота в королевской казне
    println("Gold: " + myKingdom.treasury)
}