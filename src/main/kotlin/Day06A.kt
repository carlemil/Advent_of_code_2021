import java.io.File
import java.io.InputStream

class Day06A {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day06.txt").inputStream()
            val data: MutableList<String> = mutableListOf()
            inputStream.bufferedReader().forEachLine { data.add(it) }
            val fish = data.first().split(",").map { it.toInt() }

            Day06A().solve(fish.toMutableList())
        }
    }

    fun solve(fish: MutableList<Int>) {
        for(i in 1..80) {
            nextGeneration(fish)
        }
        println(fish.size)
    }

    private fun nextGeneration(fish: MutableList<Int>) {
        var nbrOfNextGenFish = 0
        for (i in 0 until fish.size) {
            fish[i] = when (fish[i]) {
                0 -> {
                    nbrOfNextGenFish += 1
                    6
                }
                else -> fish[i] - 1
            }
        }
        for (i in 1..nbrOfNextGenFish) {
            fish.add(8)
        }
//        println(fish)
//        println(fish.size)
    }
}
