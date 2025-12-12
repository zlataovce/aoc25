// not going to bother with the functional style anymore

fun main() = println(
    generateSequence(::readlnOrNull)
        .associate { it.split(": ").let { (d, o) -> d to o.split(" ") } }
        .let { devices ->
            fun find(device: String, visited: Set<String> = emptySet()): Set<List<String>> {
                if (device == "out") {
                    return setOf(listOf("out"))
                }
                if (device in visited) {
                    return emptySet()
                }

                return devices[device]
                    ?.flatMap { find(it, visited + device).map { path -> listOf(device) + path } }
                    ?.toSet()
                    ?: emptySet()
            }

            find("you").size
        }
)
