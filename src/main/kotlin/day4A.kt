fun main() = println(
    generateSequence(::readlnOrNull)
        .map { it.toCharArray() }
        .toList()
        .let { rows ->
            rows.withIndex()
                .sumOf { (r, row) ->
                    row.withIndex()
                        .filter { (_, ch) -> ch == '@' }
                        .count { (c, _) ->
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
)