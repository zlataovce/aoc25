fun main() = println(
    generateSequence(::readlnOrNull)
        .map { it.split(",").map(String::toLong).let { (x, y) -> x to y } }
        .toList()
        .let { coords ->
            coords.indices
                .flatMap { i ->
                    (i + 1..coords.lastIndex).map { j ->
                        coords[i].let { (x1, y1) ->
                            coords[j].let { (x2, y2) ->
                                (kotlin.math.abs(x2 - x1) + 1) * (kotlin.math.abs(y2 - y1) + 1)
                            }
                        }
                    }
                }
                .max()
        }
)
