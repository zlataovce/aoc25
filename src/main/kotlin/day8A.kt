fun main() = println(
    generateSequence(::readlnOrNull)
        .map { it.split(",").map(String::toLong).let { (x, y, z) -> Triple(x, y, z) } }
        .toList()
        .let { juncBoxes ->
            juncBoxes.indices
                .flatMap { i ->
                    // make pairs of junction boxes with their distances, no duplicates
                    (i + 1 until juncBoxes.size).map { j ->
                        juncBoxes[i].let { (x1, y1, z1) ->
                            juncBoxes[j].let { (x2, y2, z2) ->
                                Triple((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2), i, j)
                            }
                        }
                    }
                }
                .sortedBy { it.first }
                .take(1000)
                .fold(juncBoxes.indices.map { setOf(it) }) { circuits, (_, i, j) ->
                    circuits.map { circuit ->
                        // replace the two circuits with merged circuits
                        circuit.takeIf { it != circuits[i] && it != circuits[j] }
                            ?: (circuits[i] + circuits[j])
                    }
                }
                .toSet() // remove duplicate circuits
                .map { it.size }
                .sortedDescending()
                .take(3)
                .reduce { a, b -> a * b }
        }
)
