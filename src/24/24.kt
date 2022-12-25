fun main(){
    val real = true
    val text = readInput(real)
    val grid = text.lines().drop(1).dropLast(1).map { it.drop(1).dropLast(1) }
    println(grid)
    val verts=grid[0].map { mutableSetOf<Int>() }
    val horz=grid.map { mutableSetOf<Int>() }
    grid.forEachIndexed { i, s -> s.forEachIndexed { j, c ->
        when(c){
            '<' -> horz[i].add(-j-1)
            '>' -> horz[i].add(j)
            '^' -> verts[j].add(-i-1)
            'v' -> verts[j].add(i)
        }
    } }
    println(horz)
    println(verts)
    val w = grid.size
    val h = grid[0].length
    val start = -1 to 0
    val goal = grid.size to grid[0].length-1
    val dirs = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1,0 to 0)
    fun isClear(loc: Pii,time:Int):Boolean{
        if(loc == start) return true
        if(loc == goal) return true
        val (x,y) = loc
        if(x<0 || x >= w || y< 0||y>=h)return false
        if(mod(x-time,w) in verts[y]) return false
        if((-mod(x+time,w)-1) in verts[y]) return false
        if(mod(y-time,h) in horz[x]) return false
        if((-mod(y+time,h)-1) in horz[x]) return false
        return true
    }
    val queue = ArrayDeque<Pair<Pii, Pii>>()
    val vis = mutableSetOf<Pair<Pii, Pii>>()
    vis.add(start to (0 to 0))
    queue.add(start to (0 to 0))
    var best=0
    while(queue.isNotEmpty()){
        val (loc, meta) = queue.removeFirst()
        val (time, stage) = meta
        if(stage<best) continue
        best=stage
        var nexstage=stage
        if(loc == goal && stage==2){
            println(time)
            return
        }
        if(loc == goal && stage==0) nexstage=1
        if(loc == start && stage==1) nexstage=2
        for(delt in dirs){
            val nl = delt+loc
            val nex = nl to (time+1 to nexstage)
            if(vis.containsAdd(nex)) continue
            if(!isClear(nl,time+1)) continue
            queue.addLast(nex)
        }
    }
}