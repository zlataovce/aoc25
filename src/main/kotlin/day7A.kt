fun main() = println(
    generateSequence(::readlnOrNull)
        .map { it.toCharArray() }
        .toList()
        .also { ls ->
            ls[1][ls[0].indexOf('S')] = '|'
            ls.withIndex()
                .drop(1)
                .forEach { (li, l) ->
                    l.forEachIndexed { i, c ->
                        (ls[li - 1][i])
                            .takeIf { it == '|' }
                            ?.let {
                                c.takeIf { it == '^' }
                                    ?.let {
                                        l[i - 1] = '|'
                                        l[i + 1] = '|'
                                    }
                                    ?: run { l[i] = '|' }
                            }
                    }
                }
        }
        .let { ls ->
            ls.withIndex()
                .sumOf { (li, l) ->
                    l.withIndex().count { (i, c) ->
                        c == '^' && ls[li - 1][i] == '|'
                    }
                }
        }
)
