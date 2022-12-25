import kotlin.math.max
import kotlin.math.min
import kotlin.system.measureTimeMillis

data class Vert(
    val name: String,
    val rate: Int,
    val children: List<String>,
    val reaches: MutableMap<String,Pair<Int,List<String>>> = mutableMapOf(),
    var nzi:Int=-1,
)

fun main(){
    val text = readInput(real = false)
    val lines = text.lines()
//    val groups = text.split("\n\n")
//        .map { it.lines() }
//    val vals = lines.map { it.toInt() }
    val timeInMillis = measureTimeMillis {
        val valves = mutableMapOf<String, Vert>()
        val vals =
            lines.map { Regex("""Valve (\w\w) has flow rate=(\d+); tunnels? leads? to valves? (.*)""").find(it)!!.destructured }
                .map { (s, f, t) ->
                    Vert(s, f.toInt(), t.split(", "))
                }

        for (v in vals) valves[v.name] = v
        var nz = 0
        for (v in vals) {
            val q = ArrayDeque<Pair<Pair<String, Int>, List<String>>>()
            q.addLast(v.name to 0 to listOf())
            while (q.isNotEmpty()) {
                val (info, path) = q.removeFirst()
                val (cur, d) = info
                for (con in valves[cur]!!.children) {
                    if (con in v.reaches || con == v.name) continue
                    v.reaches[con] = d + 1 to path
                    var newpath = path
                    if (valves[cur]!!.rate != 0) newpath += listOf(cur)
                    q.addLast(con to d + 1 to newpath)
                }

            }
            for (k in v.reaches.keys.toList()) {
                if (valves[k]!!.rate == 0) v.reaches.remove(k)
            }
            if (v.rate != 0) {
                v.nzi = nz
                nz += 1
            }
        }
        val dp = mutableMapOf<Pair<Pair<Int, Int>, Pair<Int, Pair<String, String>>>, Int>()
        fun solve(time1t: Int, time2t: Int, opened: Int, loc1: String, loc2: String): Int {
            var time1 = min(26, time1t)
            var time2 = min(26, time2t)
            if (time1 == 26 && time2 == 26) return 0
            val key = Pair(time1 to time2, Pair(opened, Pair(loc1, loc2)));
            if (key in dp) return dp[key]!!

            var ans = 0

            if (time1 <= time2) {
                loop@ for (b in valves[loc1]!!.reaches.entries) {
                    val shifted = (1 shl valves[b.key]!!.nzi)
                    if ((opened and shifted) != 0) continue
                    for (passed in b.value.second) {
                        val shifted = (1 shl valves[passed]!!.nzi)
                        if ((opened and shifted) == 0 && valves[passed]!!.rate >= valves[b.key]!!.rate)
                            continue@loop
                    }
                    val nt = time1 + b.value.first + 1
                    if (nt >= 26) continue
                    ans = max(ans, (26 - nt) * valves[b.key]!!.rate + solve(nt, time2, opened or shifted, b.key, loc2))
                }
            } else {
                loop@ for (b in valves[loc2]!!.reaches.entries) {
                    val shifted = (1 shl valves[b.key]!!.nzi)
                    if ((opened and shifted) != 0) continue
                    for (passed in b.value.second) {
                        val shifted = (1 shl valves[passed]!!.nzi)
                        if ((opened and shifted) == 0 && valves[passed]!!.rate >= valves[b.key]!!.rate)
                            continue@loop
                    }
                    val nt = time2 + b.value.first + 1
                    if (nt >= 26) continue
                    ans = max(ans, (26 - nt) * valves[b.key]!!.rate + solve(time1, nt, opened or shifted, loc1, b.key))
                }
            }
            if (dp.size % 1_000_000 == 0) println(dp.size)
            dp[key] = ans
            return ans
        }// 2169
        println(solve(0, 0, 0, "AA", "AA"))
    }
    println(timeInMillis)
}