import java.io.File
import java.io.InputStream

fun main() {
    val data = readFileDay2("src/main/resources/data_day_02.txt")
    solve2A(data)
    solve2B(data)
}

fun solve2A(data: MutableList<Int>) {
    println(
        data.zip(data.drop(1))
            .count { p -> p.first < p.second }
    )
}

fun solve2B(data: MutableList<Int>) {
    println(
        data.windowed(3)
            .zipWithNext()
            .count { p -> p.first.sum() < p.second.sum() }
    )
}

fun readFileDay2(file: String): MutableList<Int> {
    val inputStream: InputStream = File(file).inputStream()
    val lineList = mutableListOf<Int>()

    inputStream.bufferedReader().forEachLine { lineList.add(it.toInt()) }
    return lineList
}
