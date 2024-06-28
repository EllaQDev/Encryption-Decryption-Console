package encryptdecrypt

fun main() {
    val input = "we found a treasure"
    val alpha = "abcdefghijklmnopqrstuvwxyz"
    val reversedAlpha = alpha.reversed()
    val sb = StringBuilder()
    for (c in input) {
        if (c != ' '){
            sb.append(reversedAlpha[alpha.indexOf(c)])
        } else sb.append(" ")
    }
    println("${sb.toString()}!")
}