package day19

fun main(args: Array<String>) {
    val numElves = 3004953
//    val numElves = 11
    val elves1 = MutableList(numElves){1}
//part1
    var end1 = -1
    while(end1 == -1) {
        for (i in 0 until elves1.size) {
            if (elves1[i]!= 0) {
                if (elves1[i] == numElves) {
                    end1 = i
                }
                if (i == elves1.size-1) {
                    var j = 0
                    while (elves1[j] == 0 || j == i) {
                        j ++
                    }
                    elves1[i] += elves1[j]
                    elves1[j] = 0
                } else {
                    var j = 0
                    var s = i+j
                    var over = false
                    while (elves1[s] == 0 || s == i) {
                        j ++
                        if (i + j == elves1.size && !over) {
                            j = 0
                            over = true
                        }
                        s = when (!over) {
                            true -> i+j
                            false -> j
                        }
                    }
                    elves1[i] += elves1[s]
                    elves1[s] = 0
                }
            }
        }
        if (elves1.count { it > 0 } == 1) {
            end1 = elves1.indexOfFirst { it > 0} + 1
        }
    }

    println("part1: $end1")

    //part2
    val elves2 = MutableList(numElves){0}

    elves2.forEachIndexed { index, _ ->
        elves2[index] = index + 1
    }
    var i = 0
    while (elves2.size > 1) {
        if ((elves2.size)/2+i >= elves2.size) {
            elves2.removeAt(elves2.size/2+i - elves2.size)
            if (elves2.size/2+i - elves2.size < i) {
                i--
            }
        } else {
            elves2.removeAt(elves2.size/2+i)
            if (elves2.size/2+i < i) {
                i--
            }
        }
        i++
        if (i >= elves2.size) {
            i = 0
        }
        if (elves2.size %1000 == 0){
            println(elves2.size)
        }

    }

    println("part2: ${elves2[0]}")

}