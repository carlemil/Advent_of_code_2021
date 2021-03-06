import java.io.File
import java.io.InputStream
import java.lang.Integer.max
import java.lang.Integer.min

class Day11 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day11.txt").inputStream()
            val data: MutableList<MutableList<Int>> = mutableListOf()
            inputStream.bufferedReader().forEachLine { line ->
                data.add(line.map { Integer.parseInt(it.toString()) }.toMutableList())
            }
            //Day11().solveA(data) // data gets corrupted if we run a before b.
            Day11().solveB(data)
        }
    }

    private fun solveA(data: MutableList<MutableList<Int>>) {
        var flashCounter = 0
        for (c in 0 until 100) {
            forEachOctopusIncreasesEnergyLevel(data)
            flashCounter += forEachOctopusFlash(data)
            forEachOctopusResetEnergyIfToHigh(data)
        }
        println("\nflashCounter $flashCounter")
    }

    private fun solveB(data: MutableList<MutableList<Int>>) {
        var step = 0
        while (!checkAllFlashed(data)) {
            forEachOctopusIncreasesEnergyLevel(data)
            forEachOctopusFlash(data)
            forEachOctopusResetEnergyIfToHigh(data)
            step++
        }
        println(step)
    }

    private fun checkAllFlashed(data: MutableList<MutableList<Int>>): Boolean {
        var energy = 0
        for (i in 0 until data.size) {
            for (j in 0 until data[0].size) {
                energy += data[i][j]
            }
        }
        return energy == 0
    }

    private fun forEachOctopusIncreasesEnergyLevel(data: MutableList<MutableList<Int>>) {
        for (i in 0 until data.size) {
            for (j in 0 until data[0].size) {
                data[i][j] += 1
            }
        }
    }

    private fun forEachOctopusFlash(data: MutableList<MutableList<Int>>): Int {
        var flashCounter = 0
        for (i in 0 until data.size) {
            for (j in 0 until data[0].size) {
                if (data[i][j] > 9) {
                    flashCounter += octopusFlash(data, i, j)
                }
            }
        }
        return flashCounter
    }

    private fun octopusFlash(data: MutableList<MutableList<Int>>, i: Int, j: Int): Int {
        var counter = 0
        if (data[i][j] > 0) {
            data[i][j] = 0
            counter++

            for (a in max(0, i - 1)..min(i + 1, data.size - 1)) {
                for (b in max(0, j - 1)..min(j + 1, data[0].size - 1)) {
                    if (data[a][b] > 0) {
                        data[a][b] += 1
                        if (data[a][b] > 9) {
                            counter += octopusFlash(data, a, b)
                        }
                    }
                }
            }
        }
        return counter
    }

    private fun forEachOctopusResetEnergyIfToHigh(data: MutableList<MutableList<Int>>) {
        for (i in 0 until data.size) {
            for (j in 0 until data[0].size) {
                if (data[i][j] > 9) {
                    data[i][j] = 0
                }
            }
        }
    }
}
