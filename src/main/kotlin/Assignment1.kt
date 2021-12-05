import java.io.File
import java.io.InputStream

fun main() {
    val data = readFile("src/main/resources/data1.txt")
    solveA(data)
    solveB(data)
}

fun solveA(data: MutableList<Int>) {
    println("a: " +
            (data.zip(data.drop(1))
                .count { p -> p.first < p.second })
    )
}

fun solveB(data: MutableList<Int>) {
    println("b: " +
            (data.windowed(3)
                .zipWithNext()
                .count { p -> compare(p) })
    )
}

fun compare(p: Pair<List<Int>, List<Int>>): Boolean {
    val a = p.first.sum()
    val b = p.second.sum()
    return a < b
}

fun readFile(file: String): MutableList<Int> {
    val inputStream: InputStream = File(file).inputStream()
    val lineList = mutableListOf<Int>()

    inputStream.bufferedReader().forEachLine { lineList.add(it.toInt()) }
    return lineList
}
