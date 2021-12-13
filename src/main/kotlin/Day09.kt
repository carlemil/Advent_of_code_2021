import java.io.File
import java.io.InputStream

class Day09 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day09.txt").inputStream()
            val data: MutableList<MutableList<Int>> = mutableListOf()
            inputStream.bufferedReader().forEachLine { line ->
                data.add(line.toList().map { Integer.parseInt(it.toString()) }.toMutableList())
            }
            Day09().solveA(data)
            Day09().solveB(data)
        }
    }

    private fun solveA(data: MutableList<MutableList<Int>>) {
        var riskLevels = 0
        for (i in 0 until data.size) {
            for (j in 0 until data[0].size) {
                val n = data.getOrNull(i - 1)?.getOrNull(j) ?: 10
                val s = data.getOrNull(i + 1)?.getOrNull(j) ?: 10
                val w = data.getOrNull(i)?.getOrNull(j - 1) ?: 10
                val e = data.getOrNull(i)?.getOrNull(j + 1) ?: 10
                val p = data[i][j]
                if ((p < n) && (p < s) && (p < w) && (p < e)) {
                    riskLevels += p + 1
                }
            }
        }
        println(riskLevels)
    }

    private fun solveB(data: MutableList<MutableList<Int>>) {
        val basinSizes = mutableListOf<Int>()

        for (i in 0 until data.size) {
            for (j in 0 until data[0].size) {
                val n = data.getOrNull(i - 1)?.getOrNull(j) ?: 9
                val s = data.getOrNull(i + 1)?.getOrNull(j) ?: 9
                val w = data.getOrNull(i)?.getOrNull(j - 1) ?: 9
                val e = data.getOrNull(i)?.getOrNull(j + 1) ?: 9
                val p = data[i][j]
                if ((p < n) && (p < s) && (p < w) && (p < e)) {
                    if (data.getOrNull(i)?.getOrNull(j) != 9) {
                        basinSizes.add(getBasinSize(data, i, j))
                    }
                }
            }
        }
        println(basinSizes.sortedDescending().take(3).fold(1) { r, t -> r * t })
    }

    private fun getBasinSize(data: MutableList<MutableList<Int>>, i: Int, j: Int): Int {
        var size = 0
        if ((data.getOrNull(i)?.getOrNull(j) ?: 9) == 9) {
            return 0
        }
        data[i][j] = 9
        size += getBasinSize(data, i - 1, j)
        size += getBasinSize(data, i + 1, j)
        size += getBasinSize(data, i, j - 1)
        size += getBasinSize(data, i, j + 1)
        return size + 1
    }
}
