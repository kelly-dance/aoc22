fun main(){
    val text = readInput(real = true)
    var x = 1
    var cycle = 0
    var p1 = 0
    text.lines().forEach {
        fun execute(){
            cycle++
            if(cycle % 40 == 20) p1 += cycle * x
            if((((cycle-1) % 40) - x).abs <= 1) print(Char.box+Char.box.toString())
            else print("  ")
            if(cycle % 40 == 0) println()
        }
        execute()
        if(it != "noop"){
            execute()
            x += it.split(" ")[1].toInt()
        }
    }
    println(p1)
}