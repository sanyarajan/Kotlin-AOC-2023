package day01

import checkInput
import println
import readInput

const val DAY = "01"

fun main() {

    fun extractNumberFromFirstAndLastDigit(line: String): Int {
        val firstDigit = line.first { it.isDigit() }.digitToInt()
        val lastDigit = line.last { it.isDigit() }.digitToInt()
        return firstDigit * 10 + lastDigit
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            sum += extractNumberFromFirstAndLastDigit(line)
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val digits = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        var sum = 0
        input.forEach { line ->
            // the first digit is either a number or the digit spelt out
            // we find the index of the first number in the string
            val firstDigitIndex = (line.indexOfFirst { it.isDigit() }).takeIf { it != -1 } ?: line.length
            var firstWordDigitIndex = line.length
            var firstWordDigit = 0
            // find the first digit word in the line
            for(digit in digits) {
                val index = line.indexOf(digit).takeIf { it != -1 } ?: line.length
                if (index <= firstWordDigitIndex) {
                    firstWordDigitIndex = index
                    firstWordDigit = digits.indexOf(digit) + 1
                }
            }
            val firstDigit = if (firstDigitIndex <= firstWordDigitIndex) {
                line[firstDigitIndex].digitToInt()
            }
            else {
                firstWordDigit
            }

            val lastDigitIndex = line.indexOfLast { it.isDigit() }.takeIf { it != -1 } ?: 0
            // find the last digit word in the line
            var lastWordDigitIndex =0
            var lastWordDigit = 0
            for(digit in digits) {
                val index = line.lastIndexOf(digit)
                if (index > lastWordDigitIndex) {
                    lastWordDigitIndex = index
                    lastWordDigit = digits.indexOf(digit) + 1
                }
            }
            val lastDigit = if (lastDigitIndex >= lastWordDigitIndex) {
                line[lastDigitIndex].digitToInt()
            }
            else {
                lastWordDigit
            }
            val number = firstDigit * 10 + lastDigit
            sum += number
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    checkInput("$DAY/test_part1", ::part1,142)

    val input = readInput("$DAY/input")
    part1(input).println("Part1")

    checkInput("$DAY/test_part2", ::part2, 281)

    part2(input).println("Part2")
}
