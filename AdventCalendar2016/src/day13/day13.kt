package day13

import java.util.*

private const val puzzle = 1364

fun main(args: Array<String>) {
    val startPosition = Pair(1,1)
    val endPosition = Pair(31,39)

    val path1 = breadthFirstSearch(startPosition, endPosition)
    println("part1: ${path1?.size}")

    val path2 = breadthFirstSearch2(startPosition)
    println("part2: ${path2.size}")
}

fun isOpenSpace(x: Int, y: Int, designerNumber: Int = puzzle): Boolean {
    var number = x * x + 3 * x + 2 * x * y + y + y * y
    number += designerNumber
    return Integer.toBinaryString(number).count { it == '1' } %2 == 0
}

fun breadthFirstSearch(startPosition: Pair<Int, Int>, endPosition: Pair<Int, Int>): MutableList<Pair<Int, Int>>? {
    val openSet = ArrayDeque<Pair<Int, Int>>()

    val closedSet = mutableSetOf<Pair<Int, Int>>()

    val meta = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
    meta[startPosition] = Pair(-1, -1)
    openSet.add(startPosition)
    while (openSet.isNotEmpty()) {
        val subtreeRoot = openSet.remove()

        if (subtreeRoot == endPosition) {
            return constructPath(subtreeRoot, meta)
        }

        var successors = listOf(
                Pair(subtreeRoot.first+1, subtreeRoot.second),
                Pair(subtreeRoot.first, subtreeRoot.second-1),
                Pair(subtreeRoot.first-1, subtreeRoot.second),
                Pair(subtreeRoot.first, subtreeRoot.second+1))

        successors = successors
                .filter { it.first >= 0 && it.second >= 0 }
                .filter { isOpenSpace(it.first, it.second) }

        for (child in successors) {
            if (child in closedSet) continue
            if ((child in openSet).not()) {
                meta[child] = subtreeRoot //# create metadata for these nodes
                openSet.add(child)              //# enqueue these nodes
            }
        }

        closedSet.add(subtreeRoot)
    }
    return null
}

fun constructPath(state: Pair<Int, Int>, meta: MutableMap<Pair<Int, Int>, Pair<Int, Int>>): MutableList<Pair<Int, Int>> {
    var state = state
    val actionList = mutableListOf<Pair<Int, Int>>()

    while (meta[state]?.first != -1 && meta[state]?.second != -1 ) {
        state = meta[state]!!
        val action = meta[state]
        actionList.add(action!!)
    }

    return actionList
}

fun breadthFirstSearch2(startPosition: Pair<Int, Int>): MutableSet<Pair<Int, Int>> {
    val openSet = ArrayDeque<Pair<Pair<Int, Int>, Int>>()

    val visited = mutableSetOf<Pair<Int, Int>>()

    openSet.add(Pair(startPosition, 0))
    while (openSet.isNotEmpty()) {
        val subtreeRoot = openSet.remove()

        var successors = listOf(
                Pair(Pair(subtreeRoot.first.first+1, subtreeRoot.first.second), subtreeRoot.second+1),
                Pair(Pair(subtreeRoot.first.first, subtreeRoot.first.second-1), subtreeRoot.second+1),
                Pair(Pair(subtreeRoot.first.first-1, subtreeRoot.first.second), subtreeRoot.second+1),
                Pair(Pair(subtreeRoot.first.first, subtreeRoot.first.second+1), subtreeRoot.second+1))

        successors = successors
                .filter { it.first.first >= 0 && it.first.second >= 0 }
                .filter { isOpenSpace(it.first.first, it.first.second) }

        for (child in successors) {
            if (child.second <= 50 && child.first !in visited) {
                openSet.add(child)
                visited.add(child.first)
            }
        }
    }
    return visited
}