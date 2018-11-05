package day14

import java.security.MessageDigest

private const val puzzle = "ngcjuoqr"
//private const val puzzle = "abc"

fun main(args: Array<String>) {

    val number1 = findNumber(0)
    println("part1: $number1")
    val number2 = findNumber(2016)
    println("part2: $number2")
}

fun findNumber(stretched: Int): Long {
    var number = 0L
    var keysFound = 0
    while(keysFound < 64) {
        var foundKey = false
        while (!foundKey) {
            var md5 = hashString("$puzzle$number", stretched)
            val triplet = findTriplet(md5)
            if (triplet != null && isValid(triplet, number, stretched)) {
                keysFound ++
//                println("key: $keysFound, number $number")
                foundKey = true
            }
            if (keysFound < 64) {
                number++
            }
        }
    }
    return number
}

fun hashString( input: String, stretched: Int): String {
    val HEX_CHARS = "0123456789abcdef"
    val bytes = MessageDigest
            .getInstance("MD5")
    bytes.update(input.toByteArray())

    if (stretched > 0) {
        for (i in 1..stretched) {
            bytes.update(bytes.digest().toHex().toByteArray())
        }
    }
    val md5 = bytes.digest()

    return md5.toHex()
}

fun isValid(t: Char, number: Long, stretched: Int): Boolean {

    (number+1 .. number+999).forEach {
        val md5 = hashString("$puzzle$it", stretched)
        if (md5.contains("$t$t$t$t$t")) {

            return true
        }
    }
    return false
}

fun findTriplet(hash:String): Char? {
    return (0..hash.length - 3).firstOrNull { i -> hash[i] == hash[i+1] && hash[i] == hash[i+2] }?.let { c-> hash[c] }
}

fun ByteArray.toHex(): String{
    val retval = StringBuilder(size*2)

    val HEX_CHARS = "0123456789abcdef".toCharArray()

    forEach {
        val octet = it.toInt()
        retval.append(HEX_CHARS[octet ushr 4 and 0x0F])
        retval.append(HEX_CHARS[octet and 0x0F])
    }

    return retval.toString()
}
