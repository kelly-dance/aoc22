fun main(){
    val input = readInput(true)
    val lines = input.lines()
    val vals = lines.map { Regex("""(\d+)-(\d+),(\d+)-(\d+)""").find(it)!! }
        .map(::matchesToInts)
    val p1 = vals.count { (a,b,c,d) -> (c >= a && d <= b) || (a >= c && b <= d) }
    val p2 = vals.count { (a,b,c,d) -> (a..b).intersect((c..d)).isNotEmpty() }
    println(p1)
    println(p2)
}