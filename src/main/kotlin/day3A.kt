fun main() = println(
    generateSequence(::readLine)
        .map { it.map(Char::digitToInt) }
        .map {
            it.slice(0..<it.lastIndex).max()
                .let { m -> "$m${it.slice(it.indexOf(m).inc()..it.lastIndex).max()}".toInt() }
        }
        .sum()
)
