fun main(){
    val input = readInput(true)
    val lines = input.lines()

    val p1 = lines.map { it.substring(0, it.length / 2).toSet().intersect(it.substring(it.length/2).toSet()).first() }
        .sumOf { if(it.isLowerCase())(it.code-'a'.code+1)else(it.code-'A'.code+27) }

    val p2 = lines.windowed(3,3)
        .map {
            it[0].toSet()
                .intersect(it[1].toSet())
                .intersect(it[2].toSet()).first() }
        .sumOf { if(it.isLowerCase())(it.code-'a'.code+1)else(it.code-'A'.code+27) }

    println(p1)
    println(p2)
}