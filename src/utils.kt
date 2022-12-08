import java.io.File

fun readInput(real: Boolean): String {
    val file = File(if(real)"./real.txt" else "./test.txt")
    return file.readText().replace("\r\n", "\n")
}

fun MatchResult.ints(): List<Int> {
    return this.groupValues.drop(1).map(String::toInt)
}

val matchesToInts = MatchResult::ints

fun<K, V> MutableMap<K, V>.applyOn(key: K, fn: (value: V) -> V) = apply {
    this[key] = fn(this.getValue(key))
}

fun<K> MutableSet<K>.containsAdd(key: K): Boolean {
    if(this.contains(key)) return true
    this.add(key)
    return false
}

class DefaultMap<K, V>(val deriveDefault: (key: K) -> V) : HashMap<K, V>() {
    override operator fun get(key: K): V {
        return super.get(key) ?: deriveDefault(key)
    }
}

fun <T> List<T>.splitWhen(includeBoundary: Boolean = false, predicate: (T) -> Boolean): List<List<T>> {
    return flatMapIndexed { index: Int, value: T ->
        when {
            index == 0 || index == this.lastIndex -> listOf(index)
            predicate(value) -> listOf(index - 1, index + if (includeBoundary) 0 else 1)
            else -> emptyList()
        }
    }.windowed(size = 2, step = 2) { (from, to) -> slice(from..to) }
}

fun <T> List<T>.split(on: T) = splitWhen { it == on }

fun <T> Iterable<T>.cycle() = generateSequence { this }.flatten()

fun <T> Collection<T>.eachCount() = this.groupingBy { it }.eachCount()
