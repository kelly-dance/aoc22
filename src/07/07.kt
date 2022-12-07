
fun main(){
    val text = readInput(true)
    val lines = text.lines()
    val path = mutableListOf<String>()
    val vis = HashSet<String>()
    val cts = HashMap<String, Int>()
    for(line in lines){
        if(line.startsWith("$ ls")) continue
        if(line.startsWith("$")){
            if(line.contains("..")) path.removeLast()
            else path.add(line.substring(5))
        }else{
            if(line.startsWith("dir")) continue
            val toHere = path.joinToString("/")
            val (nums, name) = line.split(" ")
            val num = nums.toInt()
            val fullname = "$toHere/$name"
            if(vis.contains(fullname)) continue
            vis.add(fullname)
            for(i in 1..path.size){
                val key = path.take(i).joinToString("/")
                if(cts.containsKey(key)) cts[key] = cts[key]!! + num
                else cts[key]=num
            }
        }
    }
    var p1 = 0
    for(v in cts.values){
        if(v <= 100_000) p1 += v
    }
    println(p1)
    val minSize = 30000000 - (70000000 - cts["/"]!!)
    val p2 = cts.values.filter { it >= minSize }.min()
    println(p2)
}