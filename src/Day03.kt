fun main() {
    println(part1(readInput("day3")))
    println(part2(readInput("day3")))
}

private fun part1(input: List<String>): Int = input
    .sumOf { bank ->
        var first = 0
        var second = 0

        bank.toIntSequence()
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

private fun part2(input: List<String>) =
    input.sumOf { bank ->
        bank.toIntSequence()
            .let { bankElements ->
                var safeToDrop = bankElements.size - 12
                val resultStack = ArrayDeque<Int>()

                bankElements.forEach { el ->
                    while (resultStack.isNotEmpty() && el > resultStack.last() && safeToDrop != 0) {
                        resultStack.removeLast()
                        safeToDrop--
                    }
                    resultStack.add(el)
                }

                resultStack.take(12).joinToString("").toLong()
            }
    }

private fun String.toIntSequence() = this.split("").filter { it.isNotEmpty() }.map { it.toInt() }