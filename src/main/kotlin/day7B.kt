fun main() = println(
    generateSequence(::readlnOrNull)
        .map { it.toCharArray() }
        .toList()
        .let { ls ->
            ls.indices
                .drop(1)
                .reversed()
                .fold(LongArray(ls.maxOf { it.size }) { 1L }) { below, r ->
                    ls[r].indices
                        .map { c ->
                            (ls[r][c])
                                .takeIf { it == '^' }
                                ?.let { below[c - 1] + below[c + 1] }
                                ?: below[c]
                        }
                        .toLongArray()
                }
                .get(ls[0].indexOf('S'))
        }
)
