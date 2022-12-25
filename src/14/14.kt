fun main(){
    val text = readInput(real = true)
    val blocked = mutableSetOf<Pii>()
    val vals = text.lines().forEach {
        val idk = it.split(" -> ").map { it.split(",").map{ it.toInt() } }
            .windowed(2,1).forEach {
                val (s,e) = it
                var (sx,sy)=s
                val (ex,ey)=e
                blocked.add(Pii(sx,sy))
                while(Pii(sx,sy)!=Pii(ex,ey)){
                    sx+=sign(ex-sx)
                    sy+=sign(ey-sy)
                    blocked.add(sx to sy)
                }
        }
    }
    val hi = blocked.maxOf { it.second }+2
    for(i in -2000..2000) blocked.add(i to hi)
    var steps = 0
    while(true){
        steps++
        var x = 500
        var y = 0
        if((x to y) in blocked) break
        for(idc in 1..10000){
            if(Pii(x,y+1) !in blocked){
                y+=1
            }else if(Pii(x-1,y+1) !in blocked){
                x-=1;y+=1
            }else if(Pii(x+1,y+1) !in blocked){
                x+=1;y+=1
            }else{
                blocked.add(x to y)
                break

            }
        }
    }
    println(steps)
}