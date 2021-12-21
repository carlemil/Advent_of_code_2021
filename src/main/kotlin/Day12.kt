import java.io.File
import java.io.InputStream

class Day12 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day12.txt").inputStream()
            val data: MutableList<Pair<String, String>> = mutableListOf()
            inputStream.bufferedReader().forEachLine { line ->
                data.add(Pair(line.substringBefore('-').trim(), line.substringAfter('-').trim()))
            }
            var nodes: MutableMap<String, MutableList<String>> = mutableMapOf<String, MutableList<String>>()
            data.forEach { pair ->
                if (nodes.containsKey(pair.first)) {
                    nodes[pair.first]?.add(pair.second)
                } else {
                    nodes[pair.first] = mutableListOf(pair.second)
                }
                if (nodes.containsKey(pair.second)) {
                    nodes[pair.second]?.add(pair.first)
                } else {
                    nodes[pair.second] = mutableListOf(pair.first)
                }
            }
            Day12().solveA(nodes)
            //Day12().solveB(data)
        }
    }

    private fun solveA(nodes: MutableMap<String, MutableList<String>>, visSmallN: MutableSet<String>) {
        print(nodes)

    }

    private fun solveB(data: MutableList<MutableList<Int>>) {

    }
}
