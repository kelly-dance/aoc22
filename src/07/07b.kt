
class FileSystem {
    var size = 0
    val folders = HashMap<String, FileSystem>()

    init {
        instances.add(this)
    }

    fun add(path: List<String>, amt: Int) {
        size += amt
        if(path.isNotEmpty()) {
            if(folders.containsKey(path[0])){
                folders[path[0]]!!.add(path.drop(1), amt)
            } else {
                folders[path[0]] = FileSystem()
                folders[path[0]]!!.add(path.drop(1), amt)
            }
        }
    }

    companion object {
        val instances = mutableListOf<FileSystem>()
    }
}

fun main(){
    val text = readInput(real = true)
    val lines = text.lines()
    val path = mutableListOf<String>()
    val root = FileSystem()
    for(line in lines){
        if(line.startsWith("$ ls")) continue
        if(line.startsWith("$")){
            if(line.contains("..")) path.removeLast()
            else path.add(line.substring(5))
        }else{
            if(line.startsWith("dir")) continue
            val (nums, name) = line.split(" ")
            val num = nums.toInt()
            root.add(path, num)
        }
    }
    var p1 = 0
    for(inst in FileSystem.instances){
        if(inst.size <= 100_000) p1 += inst.size
    }
    val minSize = root.size - 40000000
    val p2 = FileSystem.instances
        .map { it.size }
        .filter { it >= minSize }
        .min()
    println(p1)
    println(p2)
}
