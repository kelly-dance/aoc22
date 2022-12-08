
class FileSystem {
    var size = 0
    val files = HashSet<String>()
    val folders = HashMap<String, FileSystem>()

    init {
        instances.add(this)
    }

    fun contains(path: List<String>, file: String): Boolean {
        if(path.size == 0) return files.contains(file)
        else return folders[path[0]]?.contains(path.drop(1), file) ?: false
    }

    fun add(path: List<String>, file: String, amt: Int) {
        size += amt
        if(path.isNotEmpty()) {
            if(folders.containsKey(path[0])){
                folders[path[0]]!!.add(path.drop(1), file, amt)
            } else {
                folders[path[0]] = FileSystem()
                folders[path[0]]!!.add(path.drop(1), file, amt)
            }
        }else{
            files.add(file)
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
            if(!root.contains(path, name))
                root.add(path, name, num)
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
