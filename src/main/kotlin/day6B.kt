fun main() = println(
    generateSequence(::readlnOrNull)
        .toList()
        .let { ls -> ls.map { l -> l.padEnd(ls.maxOf { it.length }) } } // IntelliJ is removing trailing whitespace on save
        .let { ls ->
            ls.last()
                .withIndex()
                .filter { (_, c) -> c != ' ' }
                .let { indices ->
                    indices
                        .map { it.index }
                        .plus(ls.last().length + 1) // end marker
                        .zipWithNext()
                        .map { (f, t) -> ls.map { it.substring(f, t - 1) } }
                }
        }
        .sumOf { r ->
            (0..<r.maxOf { it.length })
                .map { i ->
                    r.dropLast(1)
                        .mapNotNull { it[i].digitToIntOrNull() }
                        .joinToString("")
                        .toLong()
                }
                .let {
                    it.sum().takeIf { r.last().trim() == "+" }
                        ?: it.reduce { a, b -> a * b }
                }
        }
)
