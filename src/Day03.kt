fun main() {
    println(part1(readInput("day3")))
}

private fun part1(input: List<String>): Int = input
    .sumOf { bank ->
        var first = 0
        var second = 0

        bank.split("")
            .filter { it.isNotEmpty() }
            .map { it.toInt() }
            .let { bankElements ->
                bankElements.forEachIndexed { idx, bankElement ->
                    if (bankElement > first && idx != bankElements.lastIndex) {
                        first = bankElement
                        second = 0
                    } else if (bankElement > second) {
                        second = bankElement
                    }
                }
            }

        "$first$second".toInt()
    }