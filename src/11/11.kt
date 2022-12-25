


data class Monkey(val items: MutableList<Long>, val op: (old:Long) -> Long, val test: Long, val t: Long, val f: Long, var activity: Long = 0)

fun main(){
    val text = readInput(real = true)
    val monkeys = text.lines().splitWhen { it.length==0 }.map { grp ->
        val starting = grp[1].substring(18).split(", ").map { it.toLong() }.toMutableList()
        val opinfo = """(old|\d+) (\+|\*) (old|\d+)""".toRegex().find(grp[2].substring(19))
        val op = fun(old: Long): Long {
            val (a,op,b) = opinfo!!.destructured
            val av = if(a == "old") old else a.toLong()
            val bv = if(b == "old") old else b.toLong()
            return if(op=="*") av*bv else av+bv
        }
        val test = grp[3].substring(21).toLong()
        val tpath=grp[4].substring(29).toLong()
        val fpath=grp[5].substring(30).toLong()
        Monkey(starting, op, test, tpath, fpath);
    }
    val modulo = monkeys.map { it.test }.reduce { a, b -> a * b}
    println(monkeys)
    for(round in 1..10_000){
        for(monkey in monkeys){
            while(monkey.items.isNotEmpty()){
                monkey.activity+=1
                val cur = (monkey.op(monkey.items.removeAt(0)))%modulo;
                if(cur%monkey.test==0L) monkeys[monkey.t.toInt()].items.add(cur);
                else monkeys[monkey.f.toInt()].items.add(cur);
            }

        }
    }
    val (a,b)=monkeys.map { it.activity }.sortedDescending().take(2)
    println(a*b)
//    val groups = text.split("\n\n")
//        .map { it.lines() }
//    val vals = lines.map { it.toInt() }
//    val vals = lines.map { Regex("""()""").find(it)!!.ints() }
    val p1 = 0
    val p2 = 0
    println(p1)
    println(p2)
}