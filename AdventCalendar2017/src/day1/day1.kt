package day1

import java.io.File

fun main(args: Array<String>) {
    var sum1 = 0
    var sum2 = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2017/src/day1/day1src.txt").forEachLine { captcha ->
        //part1
        captcha.forEachIndexed { index, num ->
            if( index + 1 == captcha.length) {
               if (num == captcha[0]) sum1 += num.toString().toInt()
            } else {
                if (num == captcha[index+1]) sum1 += num.toString().toInt()
            }
        }
        //part2
        captcha.forEachIndexed { index, num ->
            if (index + captcha.length/2 >= captcha.length) {
                if (num == captcha[index + captcha.length/2 - captcha.length]) sum2 += num.toString().toInt()
            } else {
                if (num == captcha[index+captcha.length/2]) sum2 += num.toString().toInt()
            }
        }
    }
    println("part1: $sum1")
    println("part2: $sum2")
}