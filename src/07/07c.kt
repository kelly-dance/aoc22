fun main(){
    val text = readInput(real = true)
    val path = mutableListOf<String>()
    val files = mutableSetOf<String>()
    val dirSizes = DefaultMap<String, Int> { 0 }
    text.lines().splitWhen(includeBoundary = true) { it.startsWith("$") }.forEach {
        when {
            ".." in it.first() -> path.removeLast()
            "cd" in it.first() -> path.add(it.first().substring(5))
            "ls" in it.first() -> it.drop(1).filter { !it.startsWith("dir") }.forEach {
                val (nums, name) = it.split(" ")
                if(files.containsAdd(path.joinToString("/") + "/ " + name)) return@forEach
                path.runningReduce { a, c -> "$a/$c" }.forEach { dirSizes[it] += nums.toInt() }
            }
        }
    }
    println(dirSizes.values.filter { it <= 100_000 }.sum())
    println(dirSizes.values.filter { it >= dirSizes["/"] - 40_000_000 }.min())
}