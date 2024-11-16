package day02

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val splitList = input.map { it.split(" ") }
        val horizontalPosition = splitList.filter { it.first() == "forward" }
            .sumOf { it.last().toInt() }
        val depth = splitList.filter { it.first() == "down" || it.first() == "up" }
            .sumOf {
                if (it.first() == "down") it.last().toInt()
                else
                    it.last().toInt() * -1
            }
        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {
        val splitList = input.map { it.split(" ") }
        var horizontalPosition = 0
        var depth = 0
        var aim = 0
        splitList.map {
            val amount = it.last().toInt()
            when (it.first()) {
                "down" -> aim += amount
                "up" -> aim -= amount
                "forward" -> {
                    horizontalPosition += it.last().toInt()
                    depth += aim * amount
                }
            }
        }
        return horizontalPosition * depth
    }


    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))

}