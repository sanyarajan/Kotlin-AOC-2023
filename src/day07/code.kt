package day07

import checkInput
import println
import readInput

const val DAY = "07"

fun main() {

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    checkInput("$DAY/test_part1", ::part1,142)

    val input = readInput("$DAY/input")
    part1(input).println("Part1")

    checkInput("$DAY/test_part2", ::part2, 281)

    part2(input).println("Part2")
}


