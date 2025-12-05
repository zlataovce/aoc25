fun main() = println(
    generateSequence(::readlnOrNull)
        .toList()
        .let { l -> l.indexOf("").let { i -> l.take(i) to l.drop(i + 1) } }
        .let { (rs, ing) ->
            rs.map { it.split("-").let { (a, b) -> a.toLong()..b.toLong() } }
                .let { rs -> ing.filter { i -> rs.any { i.toLong() in it } } }
                .count()
        }
)
