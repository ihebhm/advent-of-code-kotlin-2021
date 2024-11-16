package day03

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val columns = input.first().indices
        val gamma = buildString {
            for (column in columns) {
                val (zeros, ones) = input.countBitsInColumn(column)

                val commonBit = if (zeros > ones) '0' else '1'
                append(commonBit)
            }
        }

        val epsilon = gamma.invertBinaryString()
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun part2(input: List<String>): Int {
        fun getRating(ratingType: RatingType): Int {
            val columns = input[0].indices
            var candidates = input

            for (column in columns) {
                val bitCount = candidates.countBitsInColumn(column)

                val mostCommon =
                    if (ratingType == RatingType.OXYGEN) {
                        if (bitCount.zeros > bitCount.ones) '0' else '1'
                    } else {
                        if (bitCount.zeros > bitCount.ones) '1' else '0'
                    }
                candidates = candidates.filter { it[column] == mostCommon }
                if (candidates.size == 1) break
            }

            return candidates.single().toInt(2)
        }

        return getRating(RatingType.OXYGEN) * getRating(RatingType.CO2)
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

private fun String.invertBinaryString(): String = asIterable()
    .joinToString("") { if (it == '0') "1" else "0" }


private fun List<String>.countBitsInColumn(column: Int): BitCount {
    var zeroCounts = 0
    var oneCounts = 0
    for (line in this) {
        if (line[column] == '0') zeroCounts++ else oneCounts++
    }

    return BitCount(zeroCounts, oneCounts)
}

enum class RatingType {
    OXYGEN, CO2
}

data class BitCount(val zeros: Int, val ones: Int)