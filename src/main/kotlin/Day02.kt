import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/resources/data_day02.txt").inputStream()
    val data = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { data.add(it) }
    solve2A(data)
    solve2B(data)
}

fun solve2A(data: MutableList<String>) {
    val (a, b) =
        data.map { p -> p.split(" ") }
            .fold(Pair(0, 0)) { p, t ->
                when (t[0]) {
                    "down" -> Pair(p.first, p.second + t[1].toInt())
                    "up" -> Pair(p.first, p.second - t[1].toInt())
                    else -> Pair(t[1].toInt() + p.first, p.second)
                }
            }
    println(a * b)
}

fun solve2B(data: MutableList<String>) {
    val (a, b) =
        data.map { p -> p.split(" ") }
            .fold(Triple(0, 0, 0)) { p, t ->
                when (t[0]) {
                    "down" -> Triple(p.first, p.second, p.third + t[1].toInt())
                    "up" -> Triple(p.first, p.second, p.third - t[1].toInt())
                    else -> Triple(t[1].toInt() + p.first, p.second + p.third * t[1].toInt(), p.third)
                }
            }
    println(a * b)
}
