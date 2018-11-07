package day16

fun main(args: Array<String>) {
    val puzzle = "11100010111110100"
    val length1 = 272
    val length2 = 27211
//    val length2 = 35651584

    val correctChecksum1 = getChecksum(puzzle, length1)
    println("part1: $correctChecksum1")

    val correctChecksum2 = getChecksum(puzzle, length2)
    println("part2: $correctChecksum2")

}

fun getChecksum(puzzle: String, length: Int): String {
    var puzzleResult1 = puzzle
    while (puzzleResult1.length < length) {
        val a = puzzleResult1
        var b = puzzleResult1.reversed()
        b = b.replace("1", "2").replace("0", "1").replace("2", "0")
        puzzleResult1 = a+"0$b"
    }

    puzzleResult1 = puzzleResult1.substring(0, length)

    var evenChecksum = true
    var correctChecksum = puzzleResult1
    while (evenChecksum) {
        var checksum = ""
        (0 until correctChecksum.length/2).forEach { i ->
            val index = i*2
            checksum += when (correctChecksum[index] == correctChecksum[index+1]) {
                true -> "1"
                false -> "0"
            }
        }
        if (checksum.length %2 != 0) {
            evenChecksum = false
        }
        correctChecksum = checksum
    }

    return correctChecksum
}
