fun main() {
    println(part1(readInput("day1")))
    println(part2(readInput("day1")))
}

private fun part1(input: List<String>): Int {
    val acc = CircularInt()

    return input.map { rotation ->
        val rotationsCount = rotation.drop(1).toInt()

        when (rotation[0]) {
            'R' -> acc.apply(rotationsCount)
            'L' -> acc.apply(-rotationsCount)
            else -> error("Unexpected operation '${rotation[0]}'")
        }
    }.count { it == 0 }
}

private fun part2(input: List<String>): Int {
    val acc = CircularInt()

    input.forEach { rotation ->
        val rotationsCount = rotation.takeLastWhile { Character.isDigit(it) }.toInt()

        when (rotation[0]) {
            'R' -> acc.apply(rotationsCount)
            'L' -> acc.apply(-rotationsCount)
            else -> error("Unexpected operation '${rotation[0]}'")
        }
    }

    return acc.wraps
}

class CircularInt(start: Int = 50, max: Int = 99) {
    private val range = max.inc()

    var value: Int = start
        private set
    var wraps: Int = 0
        private set

    fun apply(delta: Int): Int {
        val before = value
        val after = ((before + delta) % range + range) % range

        wraps += if (delta > 0) {
            (before + 1..before + delta).count { it % range == 0 }
        } else if (delta < 0) {
            (before + delta until before).count { (it % range + range) % range == 0 }
        } else 0

        value = after
        return after
    }
}
