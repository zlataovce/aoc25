fun main() = println(
    generateSequence(::readLine)
        .map { it.first() to it.drop(1).toInt() }
        .fold(50 to 0) { (pos, zer), (dir, step) ->
            (1..step).fold(pos to zer) { (p, z), _ ->
                ((p.dec().takeIf { it >= 0 } ?: 99).takeIf { dir == 'L' } ?: p.inc().takeIf { it < 100 } ?: 0)
                    .run { this to (z.inc().takeIf { this == 0 } ?: z) }
            }
        }
        .second
)
