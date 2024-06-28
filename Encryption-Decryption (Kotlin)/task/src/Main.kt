package encryptdecrypt

fun main() {
//    val input = "we found a treasure"
//    val alpha = "abcdefghijklmnopqrstuvwxyz"
//    val reversedAlpha = alpha.reversed()
//    val sb = StringBuilder()
//    for (c in input) {
//        if (c != ' '){
//            sb.append(reversedAlpha[alpha.indexOf(c)])
//        } else sb.append(" ")
//    }
//    println("${sb.toString()}!")
    val alpha = "abcdefghijklmnopqrstuvwxyz"
    val command = readln()
    val phraseInput = readln()
    val keyNum = readln().toInt()
    //val charKey = alpha[keyNum - 1]
    val cc = CaesarCipher(keyNum)
    if (command == "enc") {
        println(cc.encryptMessage(phraseInput))
    }
    if (command == "dec") {
        println(cc.decryptMessage(phraseInput))
    }
}

class CaesarCipher(val key: Int) {
//    val alpha = "abcdefghijklmnopqrstuvwxyz"
//    var cipherAlpha : String? = null
//    init {
//        cipherAlpha = alpha.substringAfter(key) + alpha.substringBefore(key)
//    }

    fun encryptMessage(msg: String): String {
        val sb = StringBuilder()
        for (c in msg) {
            sb.append(encryptChar(c))
        }
        return sb.toString()
    }

    fun encryptChar(ch: Char): Char {
        return (ch.code + key).toChar()
    }

    fun decryptMessage(msg: String): String {
        val sb = StringBuilder()
        for (ch in msg) {
            sb.append(decryptChar(ch))
        }
        return sb.toString()
    }
    fun decryptChar(ch: Char): Char {
        return (ch.code - key).toChar()
    }
}