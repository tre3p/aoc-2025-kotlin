fun main() {
    println(part1(readInput("day5")))
}

private fun part1(input: List<String>): Int {
    val (ranges, ids) = input
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .partition { it.contains("-") }

    val actualRanges = ranges
        .map { it.split("-") }
        .map { LongRange(it.first().toLong(), it.last().toLong()) }

    return ids
        .count { id -> actualRanges.any { it.contains(id.toLong()) } }
}
