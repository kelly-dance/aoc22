fun main(){
    val text = readInput(real = true)
    val inp = text.lines().splitWhen { it.length==0 }.map { grp ->
        val (a,b) = grp
    }
}