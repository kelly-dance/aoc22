
fun solve(inp: String, gap: Int): Int = gap + inp.windowed(gap)
    .indexOfFirst { it.toCharArray().distinct().size == gap }

fun main(){
    val inp = readInput(true)
    println(solve(inp, 4))
    println(solve(inp, 14))
}