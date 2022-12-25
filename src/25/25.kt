fun pow(b: Long, p: Int):Long {
    if(p==0)return 1
    return b*pow(b,p-1)
}

fun convert(x: Long): String {
    if(x==0L) return ""
    return when(mod(x,5)){
        0L -> convert(x/5)+"0"
        1L -> convert((x-1)/5)+"1"
        2L -> convert((x-2)/5)+"2"
        3L -> convert((x+2)/5)+"="
        4L -> convert((x+1)/5)+"-"
        else -> ""
    }
}

fun main(){
    val real = true
    val text = readInput(real)
    val vals = text.lines().map{
        it.reversed().mapIndexed {i,d ->
            when(d){
                '0' -> 0
                '1' -> pow(5,i)
                '2' -> 2*pow(5,i)
                '-' -> -1*pow(5,i)
                '=' -> -2*pow(5,i)
                else -> 0
            }
        }.sum()
    }
    val ans = vals.sum()
    println(ans)
    println(convert(ans))
}