package encryptdecrypt

fun main(args: Array<String>) {
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
//    val alpha = "abcdefghijklmnopqrstuvwxyz"
    val modeInd = args.indexOfFirst { it == "-mode" }
    val mode = if (modeInd != -1) args[modeInd + 1] else "enc"
    val keyInd = args.indexOfFirst { it == "-key"}
    val key = if (keyInd != -1) args[keyInd + 1].toInt() else 0
    val dataInd = args.indexOfFirst { it == "-data" }
    val data = if (keyInd != -1) args[dataInd + 1] else ""
//    val command = readln()
//    val phraseInput = readln()
//    val keyNum = readln().toInt()
    //val charKey = alpha[keyNum - 1]

    val cc = CaesarCipher(key)
    if (mode == "enc") {
        println(cc.encryptMessage(data))
    }
    if (mode == "dec") {
        println(cc.decryptMessage(data))
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