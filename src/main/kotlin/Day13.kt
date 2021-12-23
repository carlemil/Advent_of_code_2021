import java.io.File
import java.io.InputStream
import java.lang.Integer.max

class Day13 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day13.txt").inputStream()
            val data: MutableList<Pair<Int, Int>> = mutableListOf()
            val fold: MutableList<Pair<String, Int>> = mutableListOf()
            inputStream.bufferedReader().forEachLine { line ->
                if (line.startsWith("fold")) {
                    fold.add(
                        Pair(
                            line.substringBefore('=').substring(line.indexOf('=') - 2).trim(),
                            Integer.parseInt(line.substringAfter('=').trim())
                        )
                    )
                } else if (line.isEmpty()) {
                    // Just skip empty lines
                } else {
                    data.add(
                        Pair(
                            Integer.parseInt(line.substringBefore(',').trim()),
                            Integer.parseInt(line.substringAfter(',').trim())
                        )
                    )
                }
            }
            Day13().solveA(data, fold)
        }
    }

    private fun solveA(data: MutableList<Pair<Int, Int>>, fold: MutableList<Pair<String, Int>>) {
        var height = data.fold(0) { r, t -> max(r, t.second) } + 1
        var width = data.fold(0) { r, t -> max(r, t.first) } + 1
        var plotted = plotData(data, height, width)
        fold.forEach {
            plotted = if (it.first == "y") {
                foldYPlot(plotted, it.second)
            } else {
                foldXPlot(plotted, it.second)
            }
            println("Dots: " + countDots(plotted))
        }
        printData(plotted)
    }

    private fun countDots(plotted: Array<IntArray>): Int {
        return plotted.flatMap { it.toList() }.sum()
    }

    private fun foldYPlot(plotted: Array<IntArray>, y: Int): Array<IntArray> {
        val folded = Array(y) { IntArray(plotted[0].size) }
        for (i in 0 until y) {
            for (j in 0 until plotted[0].size) {
                if (plotted[i][j] == 1 || plotted[(plotted.size - 1) - i][j] == 1) {
                    folded[i][j] = 1
                }
            }
        }

        return folded
    }

    private fun foldXPlot(plotted: Array<IntArray>, x: Int): Array<IntArray> {
        val folded = Array(plotted.size) { IntArray(x) }
        for (i in plotted.indices) {
            for (j in 0 until x) {
                if (plotted[i][j] == 1 || plotted[i][(plotted[0].size - 1) - j] == 1) {
                    folded[i][j] = 1
                }
            }
        }

        return folded
    }

    private fun plotData(data: MutableList<Pair<Int, Int>>, height: Int, width: Int): Array<IntArray> {
        val plotted = Array(height) { IntArray(width) }
        data.forEach {
            plotted[it.second][it.first] = 1
        }
        return plotted
    }

    private fun printData(data: Array<IntArray>) {
        data.forEach { line ->
            println()
            line.forEach {
                if (it == 1) print('#') else print(" ")
            }
        }
    }

}
