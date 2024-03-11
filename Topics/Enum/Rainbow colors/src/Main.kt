fun main() {
    // put your code here
    println(isInRainbow(readln()))
}

fun isInRainbow(color: String): Boolean {
    for (item in Rainbow.values()) {
        if (color.uppercase() == item.name) return true
    }
    return false
}

enum class Rainbow() {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}