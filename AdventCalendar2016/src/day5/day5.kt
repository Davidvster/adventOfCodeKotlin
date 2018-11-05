package day5

import java.security.MessageDigest

fun main(args: Array<String>) {
    val puzzle = "ffykfhsq"
//    val puzzle = "abc"
    var number = 0L
    var password2 = "________"
    var password1 = ""
    var count = 0
    while(password2.contains("_")) {
        var foundHash = true
        while (foundHash) {
            val md5 = hashString("$puzzle$number")
            if (md5.substring(0, 5) == "00000") {
                if (count < 8) {
                    password1 += md5[5]
                    count ++
                }
                if (md5[5].isDigit() && md5[5].toString().toInt() in 0..7 && password2[md5[5].toString().toInt()] == '_') {
                    password2 = password2.replaceRange(md5[5].toString().toInt(), md5[5].toString().toInt()+1, md5[6].toString())
                    println("$password2")
                }
                foundHash = false
            }
            number++
        }
    }

    println("part1: $password1")
    println("part2: $password2")
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
