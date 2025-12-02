fun main() {
    println(readInput("day2").first().split(",")
        .map { it.convertToRange() }
        .flatMap { filterInvalidIds(it) }
        .sumOf { it })
}

private fun String.convertToRange(): LongRange = this.split("-").let { LongRange(it[0].toLong(), it[1].toLong()) }

private fun filterInvalidIds(range: LongRange): List<Long> =
    range.filter { n ->
        val s = n.toString()
        val len = s.length

        for (blockSize in 1..(len / 2)) {
            if (len % blockSize != 0) continue

            val block = s.substring(0, blockSize)
            val repeats = len / blockSize

            if (repeats >= 2 && block.repeat(repeats) == s) {
                return@filter true
            }
        }

        false
    }