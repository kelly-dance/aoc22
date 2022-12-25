typealias Loc = List<Int>

val dirs = listOf(
    listOf(1,0,0),
    listOf(-1,0,0),
    listOf(0,1,0),
    listOf(0,-1,0),
    listOf(0,0,1),
    listOf(0,0,-1),
)

fun main(){
    val text = readInput(real = true)
    val inp = text.lines().map { it.split(',').map { it.toInt() } }
    val obj=inp.toSet()
    val contrib = DefaultMap<List<Int>, Int> { 0 }
    val sent: Loc = listOf(-100,-100,-100)
    val parent = DefaultMap<Loc, Loc> { sent }
    fun find(a:Loc):Loc{
        if(parent[a]==sent)return a
        parent[a]=find(parent[a])
        return parent[a]
    }
    fun join(a:Loc,b:Loc){
        val aa=find(a)
        val bb=find(b)
        if(aa==bb)return
        parent[aa]=bb
        contrib[bb]+=contrib[aa]
    }
    for(i in -1..22){
        for(j in -1..22){
            for(k in -1..22) {
                val loc = listOf(i,j,k)
                if(loc !in obj) {
                    for((dx,dy,dz) in dirs){
                        if(listOf(i+dx,j+dy,k+dz) in obj) contrib[loc]+=1
                    }
                }

            }
        }
    }
    for(i in -1..22){
        for(j in -1..22){
            for(k in -1..22) {
                val loc = listOf(i,j,k)
                for((dx,dy,dz) in dirs){
                    val otr= listOf(i+dx,j+dy,k+dz)
                    if((otr in obj) == (loc in obj)) join(loc, otr)
                }

            }
        }
    }
    println(contrib[find(listOf(0,0,0))])
}