fun Char.v(): Int{
    return this.code-'0'.code
}

fun main(){
    val real = true
    val text = readInput(real)
    val lines = text.lines()
    val w = if(real) 99 else 5
    val p1 = (0 until w).cartesianProduct(0 until w)

    // solution was so gross i was embarrassed and deleted it.
    // if you are seeing this text then I guess I never went back to it.
}