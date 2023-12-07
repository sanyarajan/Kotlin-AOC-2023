package day02

import checkInput
import println
import readInput

const val DAY = "02"

fun main() {

    fun part1(input: List<String>): Int {
        val cubeMaxCounts = mapOf(Pair("red", 12), Pair("green", 13), Pair("blue", 14))

        var sum = 0
        for (game in input) {
            // each game has a comma separated list of games
            // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            var gamePossible = true
            val gameNumber = game.substringAfter("Game ").substringBefore(':').toInt()
            val handfuls = game.substringAfter(':').split(";")
            handful@ for (handful in handfuls) {
                val cubes = handful.split(',').map { it.trim() }
                for (cube in cubes) {
                    val cubeColour = cube.substringAfter(' ')
                    val cubeCount = cube.substringBefore(' ').toInt()
                    if (cubeCount > (cubeMaxCounts[cubeColour] ?: Int.MAX_VALUE)) {
                        gamePossible = false
                        break@handful
                    }
                }
            }
            if (gamePossible) sum += gameNumber
        }

        return sum
    }

    fun part2(input: List<String>): Int {


        var sum = 0
        for (game in input) {
            val cubeMinCounts = mutableMapOf(Pair("red", 0), Pair("green", 0), Pair("blue", 0))
            // each game has a comma separated list of games
            val gameNumber = game.substringAfter("Game ").substringBefore(':').toInt()
            var gamePower = 1
            val handfuls = game.substringAfter(':').split(";")
            for (handful in handfuls) {
                val cubes = handful.split(',').map { it.trim() }
                for (cube in cubes) {
                    val cubeColour = cube.substringAfter(' ')
                    val cubeCount = cube.substringBefore(' ').toInt()
                    if( cubeCount > (cubeMinCounts[cubeColour]!!)){
                        cubeMinCounts[cubeColour] = cubeCount
                    }
                }
            }
            gamePower = cubeMinCounts.values.reduce{acc: Int, i: Int -> acc * i }
            sum += gamePower
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    checkInput("$DAY/test_part1", ::part1, 8)

    val input = readInput("$DAY/input")
    part1(input).println("Part1")

    checkInput("$DAY/test_part2", ::part2, 2286)

    part2(input).println("Part2")
}


