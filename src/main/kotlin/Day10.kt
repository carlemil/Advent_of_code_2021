import java.io.File
import java.io.InputStream
import java.math.BigDecimal
import java.math.BigInteger

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
        val map = mutableMapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
        val scoringMap = mutableMapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        var score = 0
        val stack = mutableListOf<Char>()
        data.forEach { line ->
            line.forEachIndexed { i, t ->
                if (t in setOf('(', '[', '<', '{')) {
                    stack.push(t)
                } else {
                    val p = stack.pop()
                    if (p != map[t]) {
                        score += scoringMap[t] ?: 0
                        return@forEach
                    }
                }
            }
        }
        println(score)
    }

    private fun solveB(data: MutableList<MutableList<Char>>) {
        val map = mutableMapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
        val scoringMap = mutableMapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)
        var score = mutableListOf<BigInteger>()
        data.filter { line ->
            var corrupted = false
            val stack = mutableListOf<Char>()
            line.forEach { t ->
                if (t in setOf('(', '[', '<', '{')) {
                    stack.push(t)
                } else {
                    val p = stack.pop()
                    if (p != map[t]) {
                        corrupted = true
                    }
                }
            }
            !corrupted
        }.forEachIndexed { i, line ->
            val stack = mutableListOf<Char>()
            line.forEach { t ->
                if (t in setOf('(', '[', '<', '{')) {
                    stack.push(t)
                } else {
                    stack.pop()
                }
            }
            stack.reverse()
            score.add(score.size, BigInteger.ZERO)
            stack.forEach {
                val lineScore = (scoringMap[it] ?: -1)
                score[i] = score[i].multiply(BigInteger.valueOf(5))
                score[i] = score[i].add(BigInteger.valueOf(lineScore.toLong()))
            }
        }
        val s = score.sorted()[score.size / 2 - 1]
        println(BigDecimal(s).toPlainString())
    }

    private fun MutableList<Char>.push(string: Char) {
        this.add(this.size, string)
    }

    private fun MutableList<Char>.pop(): Char {
        val index = this.size - 1
        return if (index >= 0)
            this.removeAt(index)
        else
            '-'
    }
}
