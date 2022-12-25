fun main(){
    val text = readInput(real = true)
    var locs = mutableSetOf<Pii>()
    val dirs = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
    text.lines().forEachIndexed { i,r -> r.forEachIndexed { j,e -> if(e=='#') locs.add(i to j) } }
    var turn = 0
    while(true){
//    repeat(10){
        var moves = locs.map { loc ->
            var fl = false
            for(i in -1..1){
                for(j in -1..1){
                    if(i==0 && j==0)continue
                    if((i to j)+loc in locs) fl=true
                }
            }
            if(!fl) return@map loc to loc
            for(i in 0 until 4){
                val dir = dirs[(turn+i)%4]
                val perp = dirs[(turn+i+2)%4]
                if((-1..1).all { (loc+perp*it+dir) !in locs }){
                    return@map loc to (loc+dir)
                }
            }
            return@map loc to loc
        }.toMap()
        val cts = DefaultMap<Pii, Int> {0}
        moves.values.forEach { cts[it] += 1 }
        val nexLocs = moves.map { (src,dest) ->
            if(cts[dest] == 1) dest else src
        }.toMutableSet()
        if(nexLocs == locs) break
        locs = nexLocs
        turn++
//        println("Round ${it+1}")
//        for(i in -4..10){
//            for(j in -4..10){
//                print(if((i to j) in locs) '#' else '.')
//            }
//            println()
//        }
    }
    println(turn+1)
    val minX = locs.minOf { it.first }
    val maxX = locs.maxOf { it.first }
    val minY = locs.minOf { it.second }
    val maxY = locs.maxOf { it.second }
    println((maxX-minX+1)*(maxY-minY+1)-locs.size)
}