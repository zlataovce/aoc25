fun main() = println(
    generateSequence(::readlnOrNull)
        .toList()
        .let { l ->
            l.take(l.indexOf(""))
                .map {
                    it.split("-")
                        .let { (a, b) -> a.toLong()..b.toLong() }
                }
        }
        .fold(emptyList<LongRange>()) { acc, range ->
            acc.toMutableList().apply {
                add(
                    acc.fold(range) { newRange, r ->
                        (minOf(r.first, newRange.first)..maxOf(r.last, newRange.last))
                            .takeUnless { r.last < newRange.first || r.first > newRange.last }
                            ?.also { remove(r) }
                            ?: newRange
                    }
                )
            }
        }
        .sumOf { it.last - it.first + 1 }
)
