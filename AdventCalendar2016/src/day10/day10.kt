package day10

import java.io.File

data class Bot(val id: Int,
               var values: MutableList<Int>)

fun main(args: Array<String>) {
    val bots = mutableListOf<Bot>()
    val orders = mutableListOf<MutableList<String>>()
    val toCompare = listOf(17, 61)
//    val toCompare = listOf(2, 5)
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day10/day10src.txt").forEachLine { line ->
        val l = line.split(" ")
        if (l.contains("value")) {
            if (bots.find { it.id == l[5].toInt() } != null) {
                bots.find { it.id == l[5].toInt() }!!.values.add(l[1].toInt())
            } else {
                bots.add(Bot(l[5].toInt(), mutableListOf(l[1].toInt())))
            }
        } else {
            orders.add(l.toMutableList())
        }
    }

    val output = mutableMapOf<Int, Int>()
    var responsibleBot = -1
    while (orders.isNotEmpty()) {
        val currentBot = bots.find { it.values.size == 2 }
        if (currentBot != null) {
            currentBot.values.sort()
            if (currentBot.values == toCompare) {
                responsibleBot = currentBot.id
            }
            val order = orders.find { it[1].toInt() == currentBot.id }

            if (order?.getOrNull(5) == "output") {
                output[order[6].toInt()] = currentBot.values[0]
            } else if (bots.find { order?.getOrNull(6)?.toInt() == it.id } != null) {
                bots.find { order?.getOrNull(6)?.toInt() == it.id }!!.values.add(currentBot.values[0])
            } else {
                bots.add(Bot(order?.getOrNull(6)!!.toInt(), mutableListOf(currentBot.values[0])))
            }

            if (order?.getOrNull(10) == "output") {
                output[order[11].toInt()] = currentBot.values[1]
            } else if (bots.find { order?.getOrNull(11)?.toInt() == it.id } != null){
                bots.find { order?.getOrNull(11)?.toInt() == it.id }?.values!!.add(currentBot.values[1])
            } else {
                bots.add(Bot(order?.getOrNull(11)!!.toInt(), mutableListOf(currentBot.values[1])))
            }

            currentBot.values = mutableListOf()
            orders.remove(order)
        }
    }

    println("part1: $responsibleBot")

    val multiplied = output.getOrDefault(0, 1) * output.getOrDefault(1, 1) * output.getOrDefault(2, 1)
    println("part2: $multiplied")
}