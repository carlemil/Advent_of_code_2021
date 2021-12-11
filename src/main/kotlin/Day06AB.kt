import java.io.File
import java.io.InputStream
import java.math.BigDecimal

class Day06AB {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day06.txt").inputStream()
            val data: MutableList<String> = mutableListOf()
            inputStream.bufferedReader().forEachLine { data.add(it) }
            val allFish = data.first().split(",").map { it.toInt() }
            val fishSums = DoubleArray(9)
            allFish.forEach {
                fishSums[it] += 1.0
            }
            Day06AB().solve(fishSums, 80)
            Day06AB().solve(fishSums, 256 - 80)
        }
    }

    fun solve(fish: DoubleArray, iterations: Int) {
        for (i in 1..iterations) {
            nextGeneration(fish)
        }
        println(BigDecimal(fish.sum()).toPlainString())
    }

    private fun nextGeneration(fish: DoubleArray) {
        val newFishes = fish[0]
        for (i in 0..7) {
            fish[i] = fish[i + 1]
        }
        fish[6] += newFishes
        fish[8] = newFishes
    }
}