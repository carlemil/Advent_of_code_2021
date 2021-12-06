import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/resources/data_day04.txt").inputStream()
    val data = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { data.add(it) }
    val numbers = data.first().split(",").map { it.toInt() }
    val boards = data.drop(1) // Drop the numbers
        .windowed(size = 6, step = 6) // Cut out the boards
        .map { board -> // Map the boards
            board.drop(1) //  Drop first row, its empty
                .map { boardRow -> // Map rows in each board
                    boardRow.trim().split(Regex("\\s+")) // Split out numbers from each row
                        .map { it.trim().toInt() } // Map each number to an int
                }
        }
    val markBoards = MutableList(boards.size) { MutableList(5) { MutableList(5) { 0 } } }

    solve4A(numbers, boards, markBoards)
    solve4B(numbers, boards)
}

fun solve4A(numbers: List<Int>, boards: List<List<List<Int>>>, mbs: MutableList<MutableList<MutableList<Int>>>) {
    numbers.forEach { n ->
        boards.forEachIndexed { i, _ ->
            markInBoard(n, boards[i], mbs[i])
        }
    }
    print(mbs)
}

fun markInBoard(n: Int, b: List<List<Int>>, mb: MutableList<MutableList<Int>>) {
    for (i in 0..4) {
        for (j in 0..4) {
            if (b[i][j] == n) {
                mb[i][j] = 1
                if (checkWinningCondition(mb, i, j)) {
                    printScore(b, mb, n)
                }
            }
        }
    }
}

fun printScore(b: List<List<Int>>, mb: MutableList<MutableList<Int>>, n: Int) {
    var score = 0

    for (i in 0..4) {
        for (j in 0..4) {
            if (mb[i][j] == 0) {
                score += b[i][j]
            }
        }
    }
    score *= n

    println(score)

    System.exit(0)
}

fun checkWinningCondition(mb: MutableList<MutableList<Int>>, i: Int, j: Int): Boolean {
    return ((mb[0][j] == 1) and (mb[1][j] == 1) and (mb[2][j] == 1) and (mb[3][j] == 1) and (mb[4][j] == 1)) ||
            ((mb[i][0] == 1) and (mb[i][1] == 1) and (mb[i][2] == 1) and (mb[i][3] == 1) and (mb[i][4] == 1))

}

fun solve4B(numbers: List<Int>, data: List<List<List<Int>>>) {

}
