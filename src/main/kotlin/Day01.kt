import java.io.File
import java.io.InputStream

fun main() {
    val data = readFileDay1("src/main/resources/data_day01.txt")
    solve1A(data)
    solve1B(data)
}

fun solve1A(data: MutableList<Int>) {
    println(
        data.zip(data.drop(1))
            .count { p -> p.first < p.second }
    )
}

fun solve1B(data: MutableList<Int>) {
    println(
        data.windowed(3)
            .zipWithNext()
            .count { p -> p.first.sum() < p.second.sum() }
    )
}

fun readFileDay1(file: String): MutableList<Int> {
    val inputStream: InputStream = File(file).inputStream()
    val lineList = mutableListOf<Int>()

    inputStream.bufferedReader().forEachLine { lineList.add(it.toInt()) }
    return lineList
}
