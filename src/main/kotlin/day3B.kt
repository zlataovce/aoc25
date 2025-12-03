fun main() = println(
    generateSequence(::readlnOrNull)
        .map { it.map(Char::digitToInt) }
        .map { ds ->
            ds.fold(emptyList<Int>() to 0) { (s, r), d ->
                s.takeLastWhile { it < d }.size
                    .coerceAtMost(ds.size - 12 - r)
                    .let { s.dropLast(it) + d to (r + it) }
            }
        }
        .sumOf { (s, _) -> s.take(12).joinToString("").toLong() }
)
