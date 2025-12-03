fun main() = println(
    generateSequence(::readlnOrNull)
        .flatMap { it.split(",") }
        .map { it.split("-") }
        .flatMap { (f, l) -> f.toLong()..l.toLong() }
        .filter { Regex("^(.+)\\1+$").matches(it.toString()) }
        .sum()
)
