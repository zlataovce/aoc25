fun main() = println(
    generateSequence(::readLine)
        .map { it.first() to it.drop(1).toInt() }
        .fold(50 to 0) { (pos, zer), (dir, step) ->
            ((pos - step).takeIf { dir == 'L' } ?: (pos + step))
                .mod(100)
                .run { this to (zer.inc().takeIf { this == 0 } ?: zer) }
        }
        .second
)
