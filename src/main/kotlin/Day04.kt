import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/resources/data_day04.txt").inputStream()
    val data = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { data.add(it) }
    solve4A(data)
    solve4B(data)
}

fun solve4A(data: MutableList<String>) {

    print("")
}

fun solve4B(data: MutableList<String>) {

}
