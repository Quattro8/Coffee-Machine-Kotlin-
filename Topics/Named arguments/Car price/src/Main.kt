// complete this function, add default values
fun carPrice(old: Int = 5, kilometers: Int = 100_000, maximumSpeed: Int = 120, automatic: Boolean = false) {
    // The initial price of a new car with the default equipment is 20000$.
    var initialPrice = 20_000

    // Every year the price of the car decreases by 2000$
    initialPrice = initialPrice - (old * 2_000)

    /*The price goes down by 100$ for every kilometer per hour less than 120 km/h and
    increases by 100$ for every kilometer per hour greater than 120 km/h.)*/
    when {
        maximumSpeed > 120 -> initialPrice += (maximumSpeed - 120) * 100
        maximumSpeed < 120 -> initialPrice -= (120 - maximumSpeed) * 100
    }

    /*The price lowers by 200$ for every 10000 kilometers that the car passed.
    For example, for 19999 km, the price decreases by 200 dollars, but for 20000 km the price lowers by 400 dollars.*/
    initialPrice -= (kilometers / 10_000) * 200

    // If the car has automatic transmission, the price of the car goes up by 1500$, otherwise, it remains the same.
    if (automatic) initialPrice += 1_500

    println(initialPrice)
}

//fun main() {
//    carPrice()
//}