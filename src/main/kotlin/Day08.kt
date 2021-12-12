import java.io.File
import java.io.InputStream

class Day08 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day08.txt").inputStream()
            val data: MutableList<Pair<List<String>, List<String>>> = mutableListOf()
            inputStream.bufferedReader().forEachLine { line ->
                val delimiterPosition = line.indexOf('|')
                val signal = line.substring(0 until delimiterPosition)
                    .trim()
                    .split(' ')
                val value = line.substring(delimiterPosition + 1 until line.length)
                    .trim()
                    .split(' ')
                data.add(Pair(signal, value))
            }
            Day08().solveA(data)
            Day08().solveB(data)
        }
    }

    private fun solveA(data: MutableList<Pair<List<String>, List<String>>>) {
        println(
            data.map { listPair ->
                listPair.second.map {
                    if (it.length in setOf(2, 4, 3, 7)) {
                        1
                    } else {
                        0
                    }
                }.sum()
            }.sum()
        )
    }

    private fun solveB(data: MutableList<Pair<List<String>, List<String>>>) {
        println(
            data.map { line ->
                line.second.map { mapDigits(line)[it.toSet()] }
                    .fold("") { r, t -> r + t }.toString()

            }.sumOf { it.toInt() }
        )
    }

    private fun mapDigits(data: Pair<List<String>, List<String>>): MutableMap<Set<Char>, String> {
        var lengthOfStrings = mutableMapOf<Int, MutableSet<String>>()
        for (i in 2..7) {
            lengthOfStrings[i] = mutableSetOf()
        }

        data.first.forEach { string ->
            lengthOfStrings[string.length]!!.add(string)
        }

        val digit1 = lengthOfStrings[2]!!.first().toSet()
        val digit4 = lengthOfStrings[4]!!.first().toSet()
        val digit7 = lengthOfStrings[3]!!.first().toSet()
        val digit8 = lengthOfStrings[7]!!.first().toSet()
        val digit9 = lengthOfStrings[6]!!.first {
            it.toSet().minus(digit4).size == 2
        }.toSet()
        val digit3 = lengthOfStrings[5]!!.first {
            it.toSet().minus(digit1).size == 3
        }.toSet()
        val digit2 = lengthOfStrings[5]!!.first {
            it.toSet().minus(digit4).size == 3
        }.toSet()
        val digit6 = lengthOfStrings[6]!!.first {
            it.toSet().minus(digit1).size == 5
        }.toSet()
        val digit5 = lengthOfStrings[5]!!.first {
            val segBD = digit4.minus(digit1)
            it.toSet().minus(segBD).size == 3
        }.toSet()
        val digit0 = lengthOfStrings[6]!!.first {
            it.toSet().plus(digit5.toSet()).size == 7
        }.toSet()

        return mutableMapOf(
            digit0 to "0", digit1 to "1", digit2 to "2", digit3 to "3", digit4 to "4",
            digit5 to "5", digit6 to "6", digit7 to "7", digit8 to "8", digit9 to "9"
        )
    }
}