package day01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val inputInteger = input.map { it.toInt() }
        return inputInteger.windowed(2).count { (a, b) -> a < b }
    }

    fun part2(input: List<String>): Int {
        val inputInteger = input.map { it.toInt() }
        return inputInteger.windowed(3).windowed(2).count { (a, b) -> a.sum() < b.sum() }
    }


    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))

}
