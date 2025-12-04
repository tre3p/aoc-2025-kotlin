fun main() {
    println(part1(readInput("day4")))
}

private fun part1(input: List<String>): Int {
    val inputSplitted = input.map { it.toList() }

    return inputSplitted
        .mapIndexed { rowIdx, row ->
            row.foldIndexed(0) { colIdx, adjacentRollsCount, element ->
                if (element == '@') {
                    if (inputSplitted.getAmountOfAdjacentRolls(rowIdx, colIdx) < 4) {
                        adjacentRollsCount + 1
                    } else adjacentRollsCount
                } else adjacentRollsCount
            }
        }.sum()
}

private fun List<List<Char>>.getAmountOfAdjacentRolls(row: Int, col: Int): Int {
    var adjacentRolls = 0

    // Check left
    if (col != 0 && this[row][col.dec()] == '@') {
        adjacentRolls++
    }

    // Check right
    if (col != this[row].lastIndex && this[row][col.inc()] == '@') {
        adjacentRolls++
    }

    // Check up
    if (row != 0 && this[row.dec()][col] == '@') {
        adjacentRolls++
    }

    // Check down
    if (row != lastIndex && this[row.inc()][col] == '@') {
        adjacentRolls++
    }

    // Check top left
    if (row != 0 && col != 0 && this[row.dec()][col.dec()] == '@') {
        adjacentRolls++
    }

    // Check top right
    if (row != 0 && col != this[row].lastIndex && this[row.dec()][col.inc()] == '@') {
        adjacentRolls++
    }

    // Check bottom left
    if (row != lastIndex && col != 0 && this[row.inc()][col.dec()] == '@') {
        adjacentRolls++
    }

    // Check bottom right
    if (row != lastIndex && col != this[row].lastIndex && this[row.inc()][col.inc()] == '@') {
        adjacentRolls++
    }

    return adjacentRolls
}