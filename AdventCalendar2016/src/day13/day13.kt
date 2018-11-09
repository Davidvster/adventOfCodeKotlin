package day13

import java.util.*
private const val puzzle = 1364
fun main(args: Array<String>) {
    val startPosition = Pair(1,1)
    val endPosition = Pair(31,39)

    val path1 = breadthFirstSearch(startPosition, endPosition)
    println("part1: $path1")
}

fun isOpenSpace(x: Int, y: Int, designerNumber: Int = puzzle): Boolean {
    var number = x * x + 3 * x + 2 * x * y + y + y * y
    number += designerNumber
    val converted = Integer.toBinaryString(number)
    println("x: $x   y: $y  converted $converted ${converted.toString().count { it == '1' }}")
    return Integer.toBinaryString(number).count { it == '1' } %2 == 0
}

fun breadthFirstSearch(startPosition: Pair<Int, Int>, endPosition: Pair<Int, Int>): Int? {


    val openSet = ArrayDeque<Pair<Int, Int>>()

    val closedSet = mutableSetOf<Pair<Int, Int>>()

    val meta = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
    meta[startPosition] = Pair(-1, -1)
    openSet.add(startPosition)
    while (openSet.isNotEmpty()) {


        val subtree_root = openSet.remove()

        if (subtree_root == endPosition) {
            return constructPath(subtree_root, meta)
        }

        var successors = listOf(
                Pair(subtree_root.first+1, subtree_root.second),
                Pair(subtree_root.first, subtree_root.second-1),
                Pair(subtree_root.first-1, subtree_root.second),
                Pair(subtree_root.first, subtree_root.second+1))

        successors = successors.filter { it ->
            isOpenSpace(it.first, it.second)
        }

        for (child in successors) {

            if (child in closedSet) continue

            if ((child in openSet).not()) {
                meta[child] = subtree_root //# create metadata for these nodes
                openSet.add(child)              //# enqueue these nodes
            }
        }

        closedSet.add(subtree_root)
    }
    return null
}

fun constructPath(state: Pair<Int, Int>, meta: MutableMap<Pair<Int, Int>, Pair<Int, Int>>): Int {
    var state = state
    val actionList = mutableListOf<Pair<Int, Int>>()

    while (meta[state]?.first != -1 && meta[state]?.second != -1 ) {
        state = meta[state]!!
        val action = meta[state]
        actionList.add(action!!)
    }

    return actionList.size

}
