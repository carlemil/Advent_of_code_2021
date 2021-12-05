import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/resources/data_day03.txt").inputStream()
    val data = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { data.add(it) }
    //solve3A(data)
    solve3B(data)
}

fun solve3A(data: MutableList<String>) {
    val gammaString =
        data.fold(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)) { p, t ->
            t.forEachIndexed { i, c ->
                if (c == '1') {
                    p[i]++
                }
            }
            p
        }
            .map { it / (data.size / 2) }
            .joinToString(separator = "") { it.toString() }

    val epsilon = Integer.parseInt(
        gammaString.map {
            when (it) {
                '0' -> '1'
                else -> '0'
            }
        }
            .joinToString(separator = "") { it.toString() }, 2
    )
    val gamma = Integer.parseInt(gammaString, 2)

    print(gamma * epsilon)
}

fun solve3B(data: MutableList<String>) {
    var o2 = 0
    var reducedData = data
    for (i in data[0].indices) {
        println("---- $i ----")
        val ones = reducedData.filter { it[i] == '1' }.count()
        val zeros = reducedData.size - ones
        val mostCommon = if (ones >= zeros) '1' else '0'
        println("most common: $mostCommon $ones $zeros")
        var newReducedData = reducedData.filter { it[i] == mostCommon }.toMutableList()
        if (newReducedData.size == 0) newReducedData = mutableListOf(reducedData.last())
        newReducedData.forEach { println(it) }
        reducedData = newReducedData
        o2 = Integer.parseInt(newReducedData[0], 2)
    }


    print("o2 $o2 * co2")
}
