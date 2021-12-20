import java.io.File
import java.io.InputStream

class Day11 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day11.txt").inputStream()
            val data: MutableList<MutableList<Int>> = mutableListOf()
            inputStream.bufferedReader().forEachLine { line ->
                data.add(line.map { Integer.parseInt(it.toString()) }.toMutableList())
            }
            Day11().solveA(data)
            //Day11().solveB(data)
        }
    }

    private fun solveA(data: MutableList<MutableList<Int>>) {
        var flashCounter = 0
        for (c in 0 .. 100) {
            for (i in 0 until data.size) {
                println()
                for (j in 0 until data[0].size)
                    print(if (data[i][j] == 0) " " else data[i][j].toString())

            }
            forEachOctopusIncreasesEnergyLevel(data)
            flashCounter += forEachOctopusFlash(data)
            forEachOctopusResetEnergyIfToHigh(data)
            print("\n\nGeneration $c")
            println("\nflashCounter $flashCounter")
        }
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

            for (a in i - 1..i + 1) {
                for (b in j - 1..j + 1) {
                    if ((a >= 0 && a < data.size && b >= 0 && b < data[0].size)) {
                        if (data[a][b] > 0) {
                            data[a][b] += 1
                            if (data[a][b] > 9) {
                                counter += octopusFlash(data, a, b)
                            }
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

    private fun solveB(data: MutableList<MutableList<Char>>) {

    }

}
