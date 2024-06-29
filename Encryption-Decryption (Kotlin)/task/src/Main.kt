package encryptdecrypt

import java.io.File
import java.io.IOException

fun main(args: Array<String>) {

//    val alpha = "abcdefghijklmnopqrstuvwxyz"
    val algInd = args.indexOfFirst { it == "-alg" }
    val alg = if (algInd != -1) args[algInd + 1] else "shift"
    val modeInd = args.indexOfFirst { it == "-mode" }
    val mode = if (modeInd != -1) args[modeInd + 1] else "enc"
    val keyInd = args.indexOfFirst { it == "-key"}
    val key = if (keyInd != -1) args[keyInd + 1].toInt() else 0
    val dataInd = args.indexOfFirst { it == "-data" }
    val data = if (keyInd != -1) args[dataInd + 1] else ""
    val fileInInd = args.indexOfFirst { it == "-in" }
    val fileIn = if (fileInInd != -1) args[fileInInd + 1] else ""
    val workingDirectory = System.getProperty ("user.dir")
    val separator = File.separator
    val inputFromFile = if (fileIn.isNotEmpty()) try {
        val file = File(fileIn)
        file.readText()
    } catch (e: IOException) {
        println("Error: ${e.message}")
    } else {
        ""
    }

    val fileOutInd = args.indexOfFirst { it == "-out" }
    var outFile : File?
    if (fileOutInd != -1) {

        outFile = File("${workingDirectory}${separator}${args[fileOutInd + 1]}")
    } else {
        outFile = null
    }

    //FileWriter()
//    val command = readln()
//    val phraseInput = readln()
//    val keyNum = readln().toInt()
    //val charKey = alpha[keyNum - 1]

    val cc = CaesarCipher(key, alg)
    if (mode == "enc") {
        val output : String?
        if (dataInd != -1) {
            output = cc.encryptMessage(data)
        } else if (fileInInd != -1) {
            output = cc.encryptMessage(inputFromFile.toString())
        } else {
            output = ""
        }
        if (outFile != null) {
            outFile.writeText(output)
        } else {
            println(output)
        }
    }
    if (mode == "dec") {
        val output : String?
        if (dataInd != -1) {
            output = cc.decryptMessage(data)
        } else if (fileInInd != -1) {
            output = cc.decryptMessage(inputFromFile.toString())
        } else {
            output = ""
        }
        if (outFile != null) {
            outFile.writeText(output)
        } else {
            println(output)
        }
        //println(cc.decryptMessage(data))
    }
}

class CaesarCipher(val key: Int, val alg: String) {

    val alpha = "abcdefghijklmnopqrstuvwxyz"
    val upperAlpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var cipherAlpha : String? = null
    var cipherUpperAlpha: String? = null
    init {
        if (key == 0) {
            cipherAlpha = alpha
            cipherUpperAlpha = upperAlpha
        } else {
            cipherAlpha = alpha.substringAfter(alpha[key - 1]) + alpha.substringBefore(alpha[key - 1])
            cipherUpperAlpha = upperAlpha.substringAfter(alpha[key - 1].uppercase()) + upperAlpha.substringBefore(alpha[key - 1].uppercase())

        }

    }

    fun encryptMessage(msg: String): String {
        val sb = StringBuilder()
        for (c in msg) {
            sb.append(encryptChar(c))
        }
        return sb.toString()
    }

    fun encryptChar(ch: Char): Char {
        if (alg == "unicode") {
            return (ch.code + key).toChar()
        } else {
            if (!ch.isWhitespace() && ch.isLetter()) {
                if (ch.isUpperCase()){
                    return cipherUpperAlpha!![upperAlpha.indexOf(ch)]
                } else {
                    return cipherAlpha!![alpha.indexOf(ch)]
                }
            }
        }
        return ch
    }

    fun decryptMessage(msg: String): String {
        val sb = StringBuilder()
        for (ch in msg) {
            sb.append(decryptChar(ch))
        }
        return sb.toString()
    }
    fun decryptChar(ch: Char): Char {
        if (alg == "unicode") {
            return (ch.code - key).toChar()
        } else {
            if (!ch.isWhitespace() && ch.isLetter()) {
                if (ch.isUpperCase()){
                    return upperAlpha[cipherUpperAlpha!!.indexOf(ch)]
                } else {
                    return alpha[cipherAlpha!!.indexOf(ch)]
                }
            }

        }
        return ch

    }
}