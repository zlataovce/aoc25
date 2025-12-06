fun main() = println(
    generateSequence(::readlnOrNull)
        .map { it.trim().split("\\s+".toRegex()) }
        .toList()
        .let { r -> List(r[0].size) { j -> List(r.size) { i -> r[i][j] } } }
        .sumOf { r ->
            r.dropLast(1)
                .map { it.toLong() }
                .reduce { a, b -> (a + b).takeIf { r.last() == "+" } ?: (a * b) }
        }
)
