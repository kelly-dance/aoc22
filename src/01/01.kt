fun main(){
    val text = readInput(true)
    val groups = text.split("\n\n")
        .map { it.lines().sumOf { it.toInt() } }
        .sortedDescending()
    val p1 = groups.first()
    val p2 = groups.take(3).sum()
    println(p1)
    println(p2)
}