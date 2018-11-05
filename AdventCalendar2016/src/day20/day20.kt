package day20

import java.io.File

fun main(args: Array<String>) {
    val size = 4294967295L
//    val size = 9L
    val validIps = MutableList(1){Pair(0L, size)}
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day20/day20src.txt").forEachLine { line ->
        val l = line.split("-").map { it.toLong() }
        val toRemove = mutableListOf<Pair<Long, Long>>()
        val toAdd = mutableListOf<Pair<Long, Long>>()
        validIps.sortBy { it.first }
        validIps.forEachIndexed { index, it ->
            if (l[0] < it.second) {
                if (l[0] >= it.first) {
                    if (l[1] < it.second) {
                        validIps[index] = Pair(l[1] + 1, it.second)
                        if (l[0] > it.first) {
                            toAdd.add(Pair(it.first, l[0]-1))
                        }
                    } else {
                        if (l[0] > it.first) {
                            validIps[index] = Pair(it.first, l[0]-1)
                        } else {
                            toRemove.add(it)
                        }
                    }
                } else if (l[1] <= it.second && l[1] > it.first ) {
                    if (l[1] < it.second) {
                        validIps[index] = Pair(l[1]+1, it.second)
                    } else {
                        toRemove.add(it)
                    }
                }

                if (it.first < l[0] && it.second > l[0]) {
                    toAdd.add(Pair(it.first, l[0]-1))
                }
                if (it.first < l[1] && it.second > l[1]){
                    toAdd.add(Pair(l[1]+1, it.second))
                }
            } else if (l[0] == it.second) {
                validIps[index] = Pair(validIps[index].first, l[0]-1)
            } else if(l[1] == it.first) {
                validIps[index] = Pair(validIps[index].first, l[1]-1)
            }
        }
        validIps.removeAll(toRemove)
        toAdd.forEach {
            if (validIps.contains(it).not()) {
                validIps.add(it)
            }
        }
    }
    validIps.sortBy { it.first }
    println("part1: $validIps")


    var invalidIps = mutableListOf<Pair<Long, Long>>()
    val lines = File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day20/day20src.txt")
            .reader()
            .readLines()
            .map { it.split('-').map { it.toLong() } }
            .map { it[0] to it[1] }
            .sortedBy { it.first }

    lines.forEach {
        if (invalidIps.isEmpty() || invalidIps.last().second < (it.first-1)) {
            invalidIps.add(it)
        } else {
            invalidIps =(invalidIps.dropLast(1) +  Pair(invalidIps.last().first, Math.max(invalidIps.last().second, it.second))).toMutableList()
        }
    }

    println("part2: ${size - invalidIps.map { it.second - it.first + 1 }.sum() +1}")


}