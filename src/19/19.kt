import kotlin.math.max

private operator fun <E> List<E>.component6(): E {
    return this[5]
}

typealias State = List<Int>

fun main(){
    val text = readInput(real = true)
    val lines = text.lines()
//    val groups = text.split("\n\n")
//        .map { it.lines() }
//    val vals = lines.map { it.toInt() }
    val vals = lines.map { Regex("""Blueprint \d+: Each ore robot costs (\d+) ore. Each clay robot costs (\d+) ore. Each obsidian robot costs (\d+) ore and (\d+) clay. Each geode robot costs (\d+) ore and (\d+) obsidian.""").find(it)!!.ints() }
    var ans = 1
    val goalt=32
    for(i in vals.indices){
        val (a,b,c,d,e,f) = vals[i]
        val dp = mutableMapOf<State, Int>()
        fun solve(state:State): Int {
            if(state[8]==goalt) return 0
            if(state in dp) return dp[state]!!
//            if(dp.size%1_000_000==0) {
//                println(dp.size)
//                println(state)
//            }
            var best = 0
            if(state[0]>=a && state[4] < max(max(a,b),max(c,e)) && state[8] <= 24){
                best= max(best, solve(listOf(state[0]-a+state[4],state[1]+state[5],state[2]+state[6],0,state[4]+1,state[5],state[6],0,state[8]+1) as State))
            }
            if(state[0]>=b && state[5] < 11 && state[8] <= 24){
                best= max(best, solve(listOf(state[0]-b+state[4],state[5]+state[1],state[6]+state[2],0,state[4],state[5]+1,state[6],0,state[8]+1) as State))
            }
            if(state[0]>=c && state[1] >= d && state[6] < f){
                best= max(best, solve(listOf(state[4]+state[0]-c,state[5]+state[1]-d,state[6]+state[2],0,state[4],state[5],state[6]+1,0,state[8]+1) as State))
            }
            if(state[0]>=e && state[2]>=f){
                best= max(best, (goalt-state[8]-1)+solve(listOf(state[4]+state[0]-e,state[5]+state[1],state[6]+state[2]-f,0,state[4],state[5],state[6],0,state[8]+1) as State))
            }
            best= max(best, solve(listOf(state[0]+state[4],state[1]+state[5],state[2]+state[6],0,state[4],state[5],state[6],0,state[8]+1)))

            dp[state]=best
            return best
        }
        val score =solve(listOf(0,0,0,0,1,0,0,0,0,0))
        println(score)
        ans*=score
    }
    println(ans)
} // 22 29 46 29348


