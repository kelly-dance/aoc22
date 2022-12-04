import java.io.File

fun readInput(real: Boolean): String {
    val file = File(if(real)"./real.txt" else "./test.txt")
    return file.readText().replace("\r\n", "\n")
}

fun matchesToInts(matches: MatchResult): List<Int> {
    return matches.groupValues.drop(1).map { it.toInt() }
}

