fun main() = println(
    generateSequence(::readLine)
        .map { it.first() to it.drop(1).toInt() }
        .fold(50 to 0) { pz, (dir, step) ->
            (1..step).fold(pz) { (pos, zer), _ ->
                ((pos.dec().takeIf { it >= 0 } ?: 99).takeIf { dir == 'L' } ?: pos.inc().takeIf { it < 100 } ?: 0)
                    .run { this to (zer.inc().takeIf { this == 0 } ?: zer) }
            }
        }
        .second
)
