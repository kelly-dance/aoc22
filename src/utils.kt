import java.io.File

fun readInput(real: Boolean): String {
    val file = File(if(real)"./real.txt" else "./test.txt")
    return file.readText().replace("\r\n", "\n")
}

fun MatchResult.ints(): List<Int> {
    return this.groupValues.drop(1).map { it.toInt() }
}

val matchesToInts = MatchResult::ints
