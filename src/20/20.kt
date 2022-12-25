fun mix(vals: List<Pair<Long,Int>>): List<Pair<Long,Int>> {
    val deque = ArrayDeque<Pair<Long,Int>>()
    deque.addAll(vals)
    val n = vals.size
    repeat(n) {
        while(deque.first().second != it) deque.addLast(deque.removeFirst())
        val p = deque.removeFirst()
        val v = p.first
        val ops = (if(v>=0)v%(n-1) else ((-v)%(n-1))).toInt()
        if(v >= 0) repeat(ops) { deque.addLast(deque.removeFirst()) }
        else repeat(ops) { deque.addFirst(deque.removeLast()) }
        deque.addFirst(p)
    }
    return deque.toList()
}

fun main(){
    val text = readInput(real = true)
    val lines = text.lines()
    val key = 811589153L
    var vals = lines.mapIndexed{ i, e -> e.toLong()*key to i }
    repeat(10) {  vals=mix(vals) }
    val fin = vals.map { it.first }
    val loc0 = fin.indexOf(0)
    val ans = listOf(1000,2000,3000).sumOf { fin[(loc0+it)%vals.size] }
    println(ans)
}