import java.io.File
import java.io.InputStream
import kotlin.math.abs

class Day07 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day07.txt").inputStream()
            val data: MutableList<String> = mutableListOf()
            inputStream.bufferedReader().forEachLine { data.add(it) }
            val crabPoss = data.first().split(",").map { it.toInt() }
            Day07().solve(crabPoss)
        }
    }

    fun solve(crabs: List<Int>) {
        val min = crabs.minOrNull()!!
        val max = crabs.maxOrNull()!!
        val minFuel = IntRange(min, max).minOfOrNull { pos ->
            crabs.sumOf { crabPos ->
                crabSumCost(abs(crabPos - pos))
            }
        }
        println(minFuel)
    }

    private fun crabSumCost(n: Int): Int {
        return ((n+1) * n) / 2
    }
}