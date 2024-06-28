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
    val phraseInput = readln()
    val keyNum = readln().toInt()
    val charKey = alpha[keyNum - 1]
    val cc = CaesarCipher(charKey)
    println(cc.encryptMessage(phraseInput))
}

class CaesarCipher(val key: Char) {
    val alpha = "abcdefghijklmnopqrstuvwxyz"
    var cipherAlpha : String? = null
    init {
        cipherAlpha = alpha.substringAfter(key) + alpha.substringBefore(key)
    }

    fun encryptMessage(msg: String): String {
        val sb = StringBuilder()
        for (c in msg) {
            sb.append(encryptChar(c))
        }
        return sb.toString()
    }

    fun encryptChar(ch: Char) : Char {
        if (ch.isLetter()) {
            val indAlpha = alpha.indexOf(ch)
            return cipherAlpha!![indAlpha]
        } else {
            return ch
        }
    }
}