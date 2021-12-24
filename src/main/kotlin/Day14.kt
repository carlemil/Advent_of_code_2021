import java.io.File
import java.io.InputStream

class Day14 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day14.txt").inputStream()
            val data: MutableList<Char> = mutableListOf()
            val rules: MutableList<Pair<String, String>> = mutableListOf()
            inputStream.bufferedReader().forEachLine { line ->
                if (line.contains("->")) {
                    rules.add(
                        Pair(
                            line.substringBefore("->").trim(),
                            line.substringAfter("->").trim()
                        )
                    )
                } else if (line.isEmpty()) {
                    // Just skip empty lines
                } else {
                    data.addAll(line.toCharArray().toMutableList())
                }
            }
            Day14().solveA(data, rules)
        }
    }

    private fun solveA(data: MutableList<Char>, fold: MutableList<Pair<String, String>>) {

    }

}
