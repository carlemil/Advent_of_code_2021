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
            Day12().solveA(nodes, mutableSetOf())
            //Day12().solveB(data)
        }
    }

    private fun solveA(nodes: MutableMap<String, MutableList<String>>, visited: MutableSet<String>) {
        println(findPaths("start", nodes, visited))
    }

    private fun findPaths(
        node: String,
        nodes: MutableMap<String, MutableList<String>>,
        visited: MutableSet<String>
    ): Int {
        var paths = 0
        nodes[node]?.forEach { destination ->
            if (destination == "start") {

            } else if (destination == "end") {
                paths += 1
            } else if (destination[0].isLowerCase()) {
                if (!visited.contains(destination)) {
                    visited.add(destination)
                    paths += findPaths(destination, nodes, visited)
                    visited.remove(destination)
                }
            } else {
                paths += findPaths(destination, nodes, visited)
            }
        }
        return paths
    }

    private fun solveB(data: MutableList<MutableList<Int>>) {

    }
}
