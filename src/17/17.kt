import kotlin.math.max

fun main(){
    val text = readInput(real = true)
//    val groups = text.split("\n\n")
//        .map { it.lines() }
//    val vals = lines.map { it.toInt() }
//    val vals = lines.map { Regex("""()""").find(it)!!.ints() }
    val l = text.length
    val patterns = listOf(
        listOf(listOf(true,true,true,true)),
        listOf(listOf(false,true,false),listOf(true,true,true),listOf(false,true,false)),
        listOf(listOf(true,true,true),listOf(false,false,true),listOf(false,false,true)),
        listOf(listOf(true),listOf(true),listOf(true), listOf(true)),
        listOf(listOf(true,true),listOf(true,true)),
    )
    val filled = DefaultMap<Pii,Boolean> { (x,y) -> (y<=0 || x.abs >3) }
    var base = 1
    var pi = 0
    var da= mutableListOf<Int>()
    val req=l*10
    repeat(l*10){ i ->
        val pat = patterns[i%5]
        val h= pat.size
        val w = pat[0].size
        var x = -1
        var y = base+4

        fun push(dir: Char): Boolean {
            for(i in 0 until h){
                for(j in 0 until w){
                    if(!pat[i][j])continue
                    if(dir=='<'){
                        if(filled[(x+j-1 to y+i)]) return false
                    }else{ // >
                        if(filled[(x+j+1 to y+i)]) return false
                    }
                }
            }
            return true
        }
        fun down(): Boolean {
            for(i in 0 until h){
                for(j in 0 until w){
                    if(!pat[i][j])continue
                    if(filled[((x+j) to (y+i-1))]){
//                        println("no down ${i} ${j}")
                        return false
                    }
                }
            }
            return true
        }
//        println("$i $x $y")
        while(down()){
            y-=1
            val dir=text[pi%text.length]
            pi+=1
            if(push(dir)) x+=if(dir=='<')-1 else 1
//            println("$i $x $y")
        }
        for(i in 0 until h){
            for(j in 0 until w){
                if(!pat[i][j])continue
                filled[(x+j to y+i)]=true
            }
        }
        val old = base
        base= max(base,y+h)
        da.add(base-old)
        println("$i base: $base")
        if(false){
//            if(da.size>req){
            for(j in 0 until (da.size-req-2)){
                var fl = true
                for(k in 0 until req){
                    if(da[j+k]!=da[da.size-req+k]){
                        fl=false
                        break
                    }
                }
                if(fl){
                    println(da.subList(da.size-req, da.size))
                    println(da.subList(j, j+req))
                    println("$i $j")
                    break
                }
            }
        }
    }
    println(da)
}