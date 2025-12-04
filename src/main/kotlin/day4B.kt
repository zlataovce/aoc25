fun main() = println(
    generateSequence(::readlnOrNull)
        .map { it.toCharArray() }
        .toList()
        .let { rows ->
            generateSequence(0, Int::inc)
                .fold(0) { acc, _ ->
                    acc + rows.withIndex()
                        .flatMap { (r, row) ->
                            row.withIndex()
                                .filter { (_, ch) -> ch == '@' }
                                .mapNotNull { (c, _) ->
                                    (r to c).takeIf {
                                        (-1..1).sumOf { dr ->
                                            (-1..1).count { dc ->
                                                rows.getOrNull(r + dr)
                                                    ?.getOrNull(c + dc)
                                                    .let { it == '@' && !(dr == 0 && dc == 0) }
                                            }
                                        } < 4
                                    }
                                }
                        }
                        .onEach { (r, c) -> rows[r][c] = '.' }
                        .ifEmpty { return@let acc }
                        .size
                }
        }
)
