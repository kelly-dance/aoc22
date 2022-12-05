fun main(){
    val input = readInput(true)
    val lines = input.lines()
    val vals = lines.map { Regex("""(\d+)-(\d+),(\d+)-(\d+)""").find(it)!! }
        .map(::matchesToInts)
        .map { (a,b,c,d) -> Pair((a..b).toSet(),(c..d).toSet()) }
    val p1 = vals.count { (a,b) -> a.containsAll(b) || b.containsAll(a) }
    val p2 = vals.count { (a,b) -> a.intersect(b).isNotEmpty() }
    println(p1)
    println(p2)
}