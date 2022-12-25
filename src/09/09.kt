import kotlin.math.abs

fun move(loc: Pii, head: Pii): Pii {
    if(abs(loc.first-head.first)<=1 && abs(loc.second-head.second)<=1) return head
    return head + when {
        loc.first == head.first -> 0 to sign(loc.second-head.second)
        loc.second == head.second -> sign(loc.first-head.first) to 0
        else -> sign(loc.first-head.first) to sign(loc.second-head.second)
    }
}

infix fun Int.from(otr: Int): Pii {
    return Pii(otr, this)
}

fun main(){
    val text = readInput(true)
    val lines = text.lines()
    val dirs = mutableMapOf("R" to (0 to 1), "L" to (0 to -1), "D" to (-1 to 0), "U" to (1 to 0))
    val vals = lines.map { Regex("""(\w) (\d+)""").find(it)!!.destructured }.map { (a,b) -> Pair(a, b.toInt()) }
    val chain = (0..9).map { Pii(0,0) }.toMutableList()
    val visited = mutableSetOf<Pii>()
    visited.add(chain[0])
    for((dir, amt) in vals){
        repeat(amt) {
            chain[0] = Pair(chain[0].first + dirs[dir]!!.first, chain[0].second + dirs[dir]!!.second)
            for(i in 1..9){
                chain[i] = move(chain[i-1],chain[i])
            }
            visited.add(chain[9])
        }
    }
    println(visited.size)
}
