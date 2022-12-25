val ds = listOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)
val sectorNeighbors = listOf(
    listOf(3 to 2,2 to 1,1 to 0,5 to 0),
    listOf(0 to 0,2 to 0,4 to 2,5 to 1),
    listOf(0 to 3,3 to 0,4 to 3,1 to 0),
    listOf(0 to 2,5 to 1,4 to 0,2 to 0),
    listOf(3 to 0,5 to 0,1 to 2,2 to 1),
    listOf(3 to 3,0 to 0,1 to 3,4 to 0),
)
val sectorLocs = listOf(0 to 2,0 to 1, 1 to 1,2 to 1, 2 to 0, 3 to 0)

fun main(){
    val text = readInput(real = true)
    val (mapTextRaw, moveText) = text.split("\n\n")
    val mapText = mapTextRaw.lines()
    val longest = mapText.maxOf { it.length }
    val map = mapText.map { it + " ".repeat(longest-it.length) }
    val moves = moveText.indices.toList().splitWhen(true) {
        (moveText[it] in setOf('L','R')) || (it > 0 && moveText[it-1] in setOf('L','R'))
    }.map { it.joinToString("") { moveText[it].toString() } }
        .mapIndexed { i,e -> if(i%2==0) "move" to e.toInt() else "turn" to (if(e=="L") -1 else 1) }
    fun getSector(loc:Pii): Int {
        val (x,y) = loc
        if(x<0 || y <0)return -1
        val (a, b) = (x/50) to (y/50)
        if (a to b in sectorLocs) return sectorLocs.indexOf(a to b)
        return -1
    }
    fun rotate(loc:Pii,time:Int):Pii {
        if(time==0) return loc
        val (x,y)=loc
        return rotate(y to 49-x,time-1)
    }
    fun move(loc: Pii, dirr: Int): Pair<Pii,Int>? {
        var dir = dirr
        val (x,y) = loc
        val sector = getSector(loc)
        val (dx,dy) = ds[dir%4]
        val (ox,oy) = (x+dx) to (y+dy)
        if(sector == getSector(ox to oy)) return if(map[ox][oy]=='.') ox to oy to dir else null
        val (fsector, perm) = sectorNeighbors[sector][dir]
        val (nlx,nly) = rotate((ox+50)%50 to (oy+50)%50, perm)
        val (sx,sy)=sectorLocs[fsector]
        val (fx, fy) = (sx*50+nlx) to (sy*50+nly)
        dir = (dir + perm) % 4
        return if(map[fx][fy]=='.') fx to fy to dir else null
    }
    var loc = 0 to map[0].indexOf('.')
    var dir = 0
    for((op,param) in moves){
        if(op == "move") repeat(param) {
            val (nl,nd) = move(loc, dir) ?: return@repeat
            loc = nl
            dir = nd
        } else dir = (4 + dir + param) % 4
    }
    val (x,y) = loc + (1 to 1)
    println(x*1000+y*4+dir)
}