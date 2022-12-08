fun main(){
    val text = readInput(real = true)
    val path = mutableListOf<String>()
    val dirSizes = DefaultMap<String, Int> { 0 }
    text.lines().splitWhen(includeBoundary = true) { it.startsWith("$") }.forEach {
        when {
            ".." in it.first() -> path.removeLast()
            "cd" in it.first() -> path.add(it.first().substring(5))
            "ls" in it.first() -> it.drop(1).filter { !it.startsWith("dir") }.forEach { file ->
                path.runningReduce { a, c -> "$a/$c" }.forEach { dirSizes[it] += file.split(" ")[0].toInt() }
            }
        }
    }
    println(dirSizes.values.filter { it <= 100_000 }.sum())
    println(dirSizes.values.filter { it >= dirSizes["/"] - 40_000_000 }.min())
}