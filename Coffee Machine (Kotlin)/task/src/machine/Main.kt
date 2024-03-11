package machine

fun main() {

    val espresso = Cup(250, 0, 16, 4)
    val latte = Cup(350, 75, 20, 7)
    val cappuccino = Cup(200, 100, 12, 6)

    val coffeeMachine = CoffeeMachine()

    menu@ while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        val action = readln()
        when (action) {
            "fill" -> {
                println("Write how many ml of water you want to add:")
                val addWater = readln().toInt()
                println("Write how many ml of milk you want to add:")
                val addMilk = readln().toInt()
                println("Write how many grams of coffee beans you want to add:")
                val addCoffeeBeans = readln().toInt()
                println("Write how many disposable cups you want to add:")
                val disposableCups = readln().toInt()
                coffeeMachine.fill(addWater, addMilk, addCoffeeBeans, disposableCups )
            }
            "buy" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - back:")
                when (readln()) {
                    "1" -> coffeeMachine.buy(espresso)
                    "2" -> coffeeMachine.buy(latte)
                    "3" -> coffeeMachine.buy(cappuccino)
                    "back" -> continue@menu
                    else -> {
                        println("Unsupported type of coffee")
                        break@menu
                    }
                }
            }
            "remaining" -> coffeeMachine.coffeeMachineInfo()
            "take" -> coffeeMachine.take()
            "exit" -> return
            else -> {
                println("Unsupported action")
                break@menu
            }
        }
    }


//    println("Write how many cups of coffee you will need:")
//    coffeeMachine.checkIfCanMakeThisAmountOfCups(readln().toInt())
}

data class Cup(
    val water: Int,
    val milk: Int,
    val coffeeBeans: Int,
    val price: Int
)

private class CoffeeMachine(
    var waterAvailable: Int = 400,
    var milkAvailable: Int = 540,
    var coffeeBeansAvailable: Int = 120,
    var disposableCups: Int = 9,
    var money: Int = 550
) {
    var maxCups: Int

    init {
        val waterForCups = waterAvailable / 200
        val milkForCups = milkAvailable / 50
        val coffeeBeansForCups = coffeeBeansAvailable / 15

        maxCups = minOf(waterForCups, milkForCups, coffeeBeansForCups)
    }

    fun checkIfCanMakeCoffee(cup: Cup): Boolean {
        when {
            waterAvailable < cup.water -> {
                println("Sorry, not enough water!")
                return false
            }
            milkAvailable < cup.milk -> {
                println("Sorry, not enough milk!")
                return false
            }
            coffeeBeansAvailable < cup.coffeeBeans -> {
                println("Sorry, not enough coffee beans!")
                return false
            }
            disposableCups == 0 -> {
                println("Sorry, not enough cups!")
                return false
            }
            else -> {
                println("I have enough resources, making you a coffee!")
                return true
            }
        }
    }

    fun fill(
        addWater: Int,
        addMilk: Int,
        addCoffeeBeans: Int,
        addDisposableCups: Int
    ) {
        waterAvailable += addWater
        milkAvailable += addMilk
        coffeeBeansAvailable += addCoffeeBeans
        disposableCups += addDisposableCups
    }

    fun coffeeMachineInfo() {
        println("""
            
            The coffee machine has:
            $waterAvailable ml of water
            $milkAvailable ml of milk
            $coffeeBeansAvailable g of coffee beans
            $disposableCups disposable cups
            ${'$'}$money of money
        """.trimIndent())
    }

    fun buy(typeOfCoffee: Cup) {
        if (!checkIfCanMakeCoffee(typeOfCoffee)) return
        waterAvailable -= typeOfCoffee.water
        milkAvailable -= typeOfCoffee.milk
        coffeeBeansAvailable -= typeOfCoffee.coffeeBeans
        disposableCups -= 1
        money += typeOfCoffee.price
    }

    fun take() {
        println("I gave you ${'$'}$money")
        money = 0
    }
}