import java.util.Stack

fun solve(part: Int) {
    val real = true
    val text = readInput(real)
    val n = if(real) 9 else 3
    val m = if(real) 8 else 3
    val lines = text.lines()
    val stacks = mutableListOf<Stack<Char>>()
    for(i in 0 until n)stacks.add(Stack())
    for(line in 0 until m){
        for(i in 0 until n){
            val curline = lines[m-line-1]
            val pos = i*4+1
            if(pos >= curline.length) continue
            val c = curline[pos]
            if(c==' ')continue
            stacks[i].add(c)
        }
    }
    for(line in lines.drop(m+2)){
        val (a,b,c) = Regex("""move (\d+) from (\d+) to (\d+)""").find(line)!!.ints()
        val temp = Stack<Char>();
        for(idk in 0 until a){
            temp.add(stacks[b-1].pop())
        }
        if(part == 2) temp.reverse()
        for(x in temp) stacks[c-1].add(x)
    }
    val p1 = stacks.joinToString("") { it.peek().toString() }
    println(p1)
}

fun main(){
    solve(1)
    solve(2)
}