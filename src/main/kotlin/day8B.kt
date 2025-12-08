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
                .fold(juncBoxes.indices.map { setOf(it) } to (0 to 0)) { (circuits, lastPair), (_, i, j) ->
                    (circuits to lastPair)
                        .takeIf { circuits[i] == circuits[j] }
                        ?: Pair(
                            circuits.map { circuit ->
                                // replace the two circuits with merged circuits
                                circuit.takeIf { it != circuits[i] && it != circuits[j] }
                                    ?: (circuits[i] + circuits[j])
                            },
                            i to j
                        )
                }
                .second
                .let { (i, j) -> juncBoxes[i].first * juncBoxes[j].first }
        }
)
