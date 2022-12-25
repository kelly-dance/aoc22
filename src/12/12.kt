fun main(){
    val text = readInput(real = true)
    val q = ArrayDeque<Pair<Pii,Int>>()
    var end = Pii(0,0)
    val inp = text.lines().mapIndexed{ i,l -> l.mapIndexed { j,c ->
        when(c){
            'S' -> {
                q.addLast(Pair(Pii(i,j),0))
                0
            }
            'E' -> {
                end=Pii(i,j)
                25
            }
            else -> {
                if(c=='a')q.addLast(Pair(Pii(i,j),0))
                c.code-'a'.code
            }
        }
    } }
    val seen = mutableSetOf<Pii>()
    while(q.isNotEmpty()){
        val (loc,d) = q.removeFirst()
        if(loc == end){
            println(d)
            return;
        }
        for((dx,dy) in listOf(Pii(1,0),Pii(0,1),Pii(-1,0),Pii(0,-1))){
            val nl=Pii(loc.first+dx,loc.second+dy)
            if(nl.first<0 || nl.first >= inp.size || nl.second < 0 || nl.second >= inp[0].size) continue
            if(inp[nl.first][nl.second]-inp[loc.first][loc.second]>1)continue
            if(nl in seen) continue
            seen.add(nl)
            q.addLast(Pair(nl,d+1))
        }
    }
}