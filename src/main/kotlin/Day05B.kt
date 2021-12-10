import java.io.File
import java.io.InputStream

class Day05B {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day05.txt").inputStream()
            val parseRegex = Regex("""(\d+),(\d+) -> (\d+),(\d+)""")
            val data: MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = mutableListOf()
            inputStream.bufferedReader().forEachLine {
                val gv = parseRegex.find(it)?.groupValues
                val p0 = Pair(gv?.get(1)?.toInt() ?: -1, gv?.get(2)?.toInt() ?: -1)
                val p1 = Pair(gv?.get(3)?.toInt() ?: -1, gv?.get(4)?.toInt() ?: -1)
                val line = Pair(p0, p1)
                data.add(line)
            }

            Day05B().solve(data)
        }
    }

    fun solve(data: MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>>) {
        val grid = MutableList(1000) { MutableList(1000) { 0 } }
        data.forEach { pair ->
            val p0 = pair.first
            val p1 = pair.second
            if (p0.first == p1.first) {
                for (i in p0.second.toward(p1.second)) {
                    grid[p0.first][i] += 1
                }
            }
            if (p0.second == p1.second) {
                for (i in p0.first.toward(p1.first)) {
                    grid[i][p0.second] += 1
                }
            }
            if (kotlin.math.abs(p0.first - p1.first) == kotlin.math.abs(p0.second - p1.second)) {
                val xList: IntArray = p0.first.toward(p1.first).toList().toIntArray()
                val yList: IntArray = p0.second.toward(p1.second).toList().toIntArray()
                for (i in 0..kotlin.math.abs(p0.first - p1.first)) {
                    grid[xList[i]][yList[i]] += 1
                }
            }
        }

        println(
            grid.sumOf { line ->
                line.count { it > 1 }
            }
        )
    }

    private infix fun Int.toward(to: Int): IntProgression {
        val step = if (this > to) -1 else 1
        return IntProgression.fromClosedRange(this, to, step)
    }
}
