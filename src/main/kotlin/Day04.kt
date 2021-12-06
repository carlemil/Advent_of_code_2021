import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/resources/data_day04.txt").inputStream()
    val data = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { data.add(it) }
    val numbers = data.first().split(",")
    val boards = data.drop(1) // Drop the numbers
        .windowed(size = 6, step = 6) // Cut out the boards
        .map { board -> // Map the boards
            board.drop(1) //  Drop first row, its empty
                .map { boardRow -> // Map rows in each board
                    boardRow.trim().split(Regex("\\s+")) // Split out numbers from each row
                        .map { it.trim().toInt() } // Map each number to an int
                }
        }
    solve4A(numbers, boards)
    solve4B(data)
}

fun solve4A(numbers: List<String>, data: List<List<List<Int>>>) {

    print("")
}

fun solve4B(data: MutableList<String>) {

}
