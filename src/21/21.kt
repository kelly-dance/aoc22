data class Idk(
    var type: Int,
    var value: Long?,
    val left: String?,
    val right: String?,
    var op: String?)

fun main(){
    val text = readInput(real = true)
    val lines = text.lines()
    val vals = lines.map { l ->
        if (l.matches(Regex("""\w{4}: \d+"""))) {
            val (name, value) = Regex("""(\w{4}): (\d+)""").find(l)!!.destructured
            return@map name to Idk(0, value.toLong(), null, null, null)
        }
        val (name, left, op, right) = Regex("""(\w{4}): (\w{4}) (.) (\w{4})""").find(l)!!.destructured
        return@map name to Idk(1, null, left, right, op)
    }.toMap()
    vals["root"]!!.op="-"
    fun eval(name:String):Long{
        val entry = vals[name]!!
        if(entry.type==0) return entry.value!!
        else{
            val l = eval(entry.left!!)
            val r = eval(entry.right!!)
            return when(entry.op!!){
                "*" -> l*r
                "-" -> l-r
                "+" -> l+r
                "/" -> l/r
                else -> 0
            }
        }
    }
    fun attempt(value:Long):Long{
        vals["humn"]!!.value=value
        return eval("root")
    }
    var hi = 1000000000000000L
    var lo = -1000000000000000L
    while(lo<hi){
        val m = (lo+hi)/2
        val v = attempt(m)
        if(v==0L){
            println(m)
            return
        }
        if(v>0)lo=m+1
        else hi=m-1
    }
}