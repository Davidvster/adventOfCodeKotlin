package day17

import java.security.MessageDigest

fun main(args: Array<String>) {
    val startPosition = Pair(0,0)
    val puzzle = "pxxbnzuo"
    val shortestPath = findPath(startPosition, puzzle, "", false)
    val longestPast = findPath(startPosition, puzzle, "", true)

    println("part1: $shortestPath")
    println("part2: ${longestPast?.length}")
}

fun findPath(currentPosition: Pair<Int, Int>, puzzle: String, path: String, part2: Boolean): String? {
    var shortestPath: String? = null
    val md5 = hashString(puzzle+path)
    if (currentPosition == Pair(3,3)) {
        return path
    }
    //UP
    if (md5[0].toInt() in 'b'.toInt()..'f'.toInt() && currentPosition.second > 0) {
        val tmpPath = findPath(Pair(currentPosition.first, currentPosition.second - 1), puzzle, path+"U", part2)
        if (tmpPath != null) {
            if (tmpPath.length < shortestPath?.length?:Int.MAX_VALUE && part2.not()) shortestPath = tmpPath
            if (tmpPath.length > shortestPath?.length?:Int.MIN_VALUE && part2) shortestPath = tmpPath
        }
    }
    //DOWN
    if (md5[1].toInt() in 'b'.toInt()..'f'.toInt() && currentPosition.second < 3) {
        val tmpPath = findPath(Pair(currentPosition.first, currentPosition.second + 1), puzzle, path+"D", part2)
        if (tmpPath != null) {
            if (tmpPath.length < shortestPath?.length?:Int.MAX_VALUE && part2.not()) shortestPath = tmpPath
            if (tmpPath.length > shortestPath?.length?:Int.MIN_VALUE && part2) shortestPath = tmpPath
        }
    }
    //LEFT
    if (md5[2].toInt() in 'b'.toInt()..'f'.toInt() && currentPosition.first > 0) {
        val tmpPath = findPath(Pair(currentPosition.first-1, currentPosition.second), puzzle, path+"L", part2)
        if (tmpPath != null) {
            if (tmpPath.length < shortestPath?.length?:Int.MAX_VALUE && part2.not()) shortestPath = tmpPath
            if (tmpPath.length > shortestPath?.length?:Int.MIN_VALUE && part2) shortestPath = tmpPath
        }
    }
    //RIGHT
    if (md5[3].toInt() in 'b'.toInt()..'f'.toInt() && currentPosition.first < 3) {
        val tmpPath = findPath(Pair(currentPosition.first+1, currentPosition.second), puzzle, path+"R", part2)
        if (tmpPath != null) {
            if (tmpPath.length < shortestPath?.length?:Int.MAX_VALUE && part2.not()) shortestPath = tmpPath
            if (tmpPath.length > shortestPath?.length?:Int.MIN_VALUE && part2) shortestPath = tmpPath
        }
    }

    return shortestPath
}

fun hashString( input: String): String {
    val HEX_CHARS = "0123456789abcdef"
    val bytes = MessageDigest
            .getInstance("MD5")
            .digest(input.toByteArray())
    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()
}
