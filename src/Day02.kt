fun main() {
    println(readInput("day2").first().split(",")
        .map { it.convertToRange() }
        .flatMap { filterInvalidIds(it) }
        .sumOf { it })
}

private fun String.convertToRange(): LongRange = this.split("-").let { LongRange(it[0].toLong(), it[1].toLong()) }

private fun filterInvalidIds(n: LongRange): List<Long> =
    n.filter { int ->
        val s = int.toString()
        if (s.length % 2 != 0) return@filter false
        val half = s.length / 2

        s.substring(0, half) == s.substring(half)
    }