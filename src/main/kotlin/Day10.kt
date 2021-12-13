import java.io.File
import java.io.InputStream

class Day10 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputStream: InputStream = File("src/main/resources/data_day10.txt").inputStream()
            val data: MutableList<MutableList<Char>> = mutableListOf()
            inputStream.bufferedReader().forEachLine { line ->
                data.add(line.toMutableList())
            }
            Day10().solveA(data)
            Day10().solveB(data)
        }
    }

    private fun solveA(data: MutableList<MutableList<Char>>) {
        data.forEach { line ->
            println(line)
        }

        val stack = mutableListOf<Char>()

        data.forEach { line ->
            line.forEach { t ->
                if (t in setOf('(','[','<','{')) {
                    stack.push(t)
                }
                if (t == ')') {
                    val pop = stack.pop()
                    if (pop != '(') {
                        println("( but no ) $pop")
                        //return@forEach
                    }
                }

            }
        }
    }

    private fun solveB(data: MutableList<MutableList<Char>>) {

    }

    private fun MutableList<Char>.push(string: Char) {
        this.add(this.size, string)
    }

    private fun MutableList<Char>.pop(): Char {
        val index = this.size - 1
        return if (index > 0)
            this.removeAt(index)
        else
            '-'
    }
}
