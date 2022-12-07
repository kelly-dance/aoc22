fun main(){
    val text = readInput(true)
    val path = mutableListOf<String>()
    val files = mutableSetOf<String>()
    val dirSizes = DefaultMap<String, Int> { 0 }
    for(line in text.lines()){
        if(line.startsWith("$ ls")) continue
        if(line.startsWith("$")){
            if(line.contains("..")) path.removeLast()
            else path.add(line.substring(5))
        }else{
            if(line.startsWith("dir")) continue
            val (nums, name) = line.split(" ")
            if(files.containsAdd(path.joinToString("/") + "/ " + name)) continue
            path.runningReduce { a, c -> "$a/$c" }.forEach { dirSizes[it] += nums.toInt() }
        }
    }
    println(dirSizes.values.filter { it <= 100_000 }.sum())
    println(dirSizes.values.filter { it >= dirSizes["/"]!! - 40_000_000 }.min())
}