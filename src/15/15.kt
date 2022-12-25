import kotlin.system.measureTimeMillis

fun main(){
    val real = true
    val text = readInput(real)
    val timeInMillis = measureTimeMillis {
        val lines = text.lines()
        val vals = lines.map {
            Regex("""Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)""").find(it)!!.ints()
        }
        val lim = if (real) 4000000 else 20
        for (x in 0..lim) {
            var y = 0
            while (y <= lim) {
                var fl = false
                for ((a, b, c, d) in vals) {
                    val r = (a - c).abs + (b - d).abs
                    val s = (x - a).abs + (y - b).abs
                    if (s <= r) {
                        y += r - s + 1
                        fl = true
                        break
                    }
                }
                if (!fl) {
                    print("ANS!")
                    println(x to y)
                    return@measureTimeMillis
                }

            }
        }
    }
    println(timeInMillis)
}