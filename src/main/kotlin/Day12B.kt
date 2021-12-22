import java.io.File
import java.io.InputStream

class Day12B {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day12.txt").inputStream()
            val data: MutableList<Pair<String, String>> = mutableListOf()
            inputStream.bufferedReader().forEachLine { line ->
                data.add(Pair(line.substringBefore('-').trim(), line.substringAfter('-').trim()))
            }
            val nodes: MutableMap<String, MutableList<String>> = mutableMapOf()
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
            Day12B().solveB(nodes, mutableMapOf())
        }
    }

    private fun solveB(nodes: MutableMap<String, MutableList<String>>, visited: MutableMap<String, Int>) {
        println(findPaths("start", nodes, visited))
    }

    private fun findPaths(
        node: String,
        nodes: MutableMap<String, MutableList<String>>,
        visited: MutableMap<String, Int>
    ): Int {
        var paths = 0
        nodes[node]?.forEach { destination ->
            if (destination == "start") {
                // Don't allow a return to start.
            } else if (destination == "end") {
                paths += 1
            } else if (destination[0].isLowerCase()) {
                val v = visited[destination] ?: 0
                visited[destination] = 1 + v
                if (checkVisited(visited)) {
                    paths += findPaths(destination, nodes, visited)
                }
                visited[destination] = v
            } else {
                paths += findPaths(destination, nodes, visited)
            }
        }
        return paths
    }

    private fun checkVisited(visited: MutableMap<String, Int>): Boolean {
        return visited.values.count { it == 2 } <= 1 && visited.values.all { it <= 2 }
    }
}
