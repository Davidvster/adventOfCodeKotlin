package day7

import java.io.File

var pairs = mutableListOf<Pair<Char, Char>>()

fun main(args: Array<String>) {
    var validIps1 = 0
    var validIps2 = 0

    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day7/day7src.txt").forEachLine { line ->
        val l = line.split(Regex("""(\[|\])+"""))
        val mustHave = l.filterIndexed { index, s -> index %2 == 0  }
        val mustNotHave = l.filterIndexed { index, s -> index %2 == 1  }
        var hasOutside1 = false
        var hasOutside2 = false
        mustHave.forEach {
            if (checkTLS(it)) {
                hasOutside1 = true
            }

            if (checkSSL(it, true)) {
                hasOutside2 = true
            }
        }
        var hasInside1 = false
        var hasInside2 = false
        mustNotHave.forEach {
            if (checkTLS(it)) {
                hasInside1 = true
            }

            if (checkSSL(it, false)) {
                hasInside2 = true
            }
        }
        if (hasOutside1 && !hasInside1) {
            validIps1 ++
        }
        if (hasOutside2 && hasInside2) {
            validIps2 ++
        }
        pairs = mutableListOf()

    }
    println("part1: $validIps1")
    println("part2: $validIps2")
}

fun checkTLS(s: String): Boolean {
    (0..s.length-4).forEach { i ->
        if (s[i] == s[i+3] &&  s[i+1] == s[i+2] && s[i] != s[i+1]) {
            return true
        }
    }
    return false
}

fun checkSSL(s: String, outside: Boolean): Boolean {
    (0..s.length-3).forEach { i ->
        if (s[i] == s[i + 2] && s[i] != s[i + 1]) {
            if (outside) {
                pairs.add(Pair(s[i], s[i+1]))
            } else if (pairs.contains(Pair(s[i+1], s[i]))) {
                return true
            }
        }
    }
    if (outside) {
        return pairs.isNotEmpty()
    }
    return false
}