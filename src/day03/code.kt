package day03

import checkInput
import println
import readInput

const val DAY = "03"

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        // the input represents a 2-dimensional array with numbers, symbols (which are not numbers or .) and dots for empty spaces
        // get the size of the array
        val width = input[0].length
        val height = input.size
        // store the input as a 2D array of characters
        val map = Array(height) { CharArray(width) }
        for (y in 0 until height) {
            for (x in 0 until width) {
                map[y][x] = input[y][x]
            }
        }

//        // print the array
//        for (y in 0 until height) {
//            for (x in 0 until width) {
//                print(map[y][x])
//            }
//            println()
//        }


        // loop through the array and if we get a number in a row we check whether there is a symbol in the surrounding squares
        // if there is a symbol we add the number to the sum
        // then we move to the next number
        // if we get a dot we move to the next number
        // if we get to the end of the array we stop
        var x: Int
        var y = 0
        var numberFound: Boolean
        var numberLength: Int
        var symbolFound: Boolean
        while (y < height) {
            numberLength = 0
            numberFound = false
            symbolFound = false
            x = 0
            while (x < width) {
                if (map[y][x].isDigit()) {
                    numberLength++
                    // check if there is a symbol in the surrounding squares

                    symbolSearch@ for (yOffset in -1..1) {
                        for (xOffset in -1..1) {
                            if (xOffset == 0 && yOffset == 0) continue
                            if (y + yOffset < 0 || y + yOffset >= height) continue
                            if (x + xOffset < 0 || x + xOffset >= width) continue
                            if (map[y + yOffset][x + xOffset] !in "0123456789.") {
                                symbolFound = true
                                break@symbolSearch
                            }
                        }
                    }

                } else if (numberLength > 0) {
                    // we got to the end of the number
                    numberFound = true
                }

                if (numberLength > 0 && x == (width - 1)) {
                    numberFound = true
                }


                if (symbolFound && numberFound) {
                    val numberString = if (x == width - 1 && map[y][x].isDigit()) {
                        map[y].slice(x - numberLength + 1 until x + 1).joinToString("")
                    } else {
                        map[y].slice(x - numberLength until x).joinToString("")

                    }

                    //println("$numberString $y,$x")
                    sum += numberString.toInt()
                    symbolFound = false
                    numberFound = false
                    numberLength = 0
                } else if (numberFound && !symbolFound) {
                    numberFound = false
                    numberLength = 0

                }
                x++
            }
            y++
        }


        return sum

    }

    fun part2(input: List<String>): Int {
        var sum = 0

        val gearMap = mutableMapOf<Pair<Int, Int>, Set<Int>>()
        // the input represents a 2-dimensional array with numbers, symbols (which are not numbers or .) and dots for empty spaces
        // get the size of the array
        val width = input[0].length
        val height = input.size
        // store the input as a 2D array of characters
        val map = Array(height) { CharArray(width) }
        for (y in 0 until height) {
            for (x in 0 until width) {
                map[y][x] = input[y][x]
            }
        }

//        // print the array
//        for (y in 0 until height) {
//            for (x in 0 until width) {
//                print(map[y][x])
//            }
//            println()
//        }


        // loop through the array and if we get a number in a row we check whether there is a symbol in the surrounding squares
        // if there is a symbol we add the number to the sum
        // then we move to the next number
        // if we get a dot we move to the next number
        // if we get to the end of the array we stop
        var x: Int
        var y = 0
        var symbolPosition = Pair(0, 0)
        var numberFound: Boolean
        var numberLength: Int
        var symbolFound: Boolean
        while (y < height) {
            numberLength = 0
            numberFound = false
            symbolFound = false
            x = 0
            while (x < width) {
                if (map[y][x].isDigit()) {
                    numberLength++
                    // check if there is a * symbol in the surrounding squares

                    symbolSearch@ for (yOffset in -1..1) {
                        for (xOffset in -1..1) {
                            if (xOffset == 0 && yOffset == 0) continue
                            if (y + yOffset < 0 || y + yOffset >= height) continue
                            if (x + xOffset < 0 || x + xOffset >= width) continue
                            if (map[y + yOffset][x + xOffset] == '*') {
                                symbolFound = true
                                symbolPosition = Pair(y + yOffset, x + xOffset)
                                break@symbolSearch
                            }
                        }
                    }

                } else if (numberLength > 0) {
                    // we got to the end of the number
                    numberFound = true
                }

                if (numberLength > 0 && x == (width - 1)) {
                    numberFound = true
                }


                if (symbolFound && numberFound) {
                    val numberString = if (x == width - 1 && map[y][x].isDigit()) {
                        map[y].slice(x - numberLength + 1 until x + 1).joinToString("")
                    } else {
                        map[y].slice(x - numberLength until x).joinToString("")

                    }

                    //println("$numberString $y,$x")
                    if (gearMap.containsKey(symbolPosition)) {
                        gearMap[symbolPosition] = gearMap[symbolPosition]!!.plus(numberString.toInt())
                    } else gearMap[symbolPosition] = setOf(numberString.toInt())
                    symbolFound = false
                    numberFound = false
                    numberLength = 0
                } else if (numberFound && !symbolFound) {
                    numberFound = false
                    numberLength = 0

                }
                x++
            }
            y++
        }

        for (gear in gearMap) {
            if (gear.value.size == 2) {
                println("${gear.key} ${gear.value}")
                sum += gear.value.reduce { acc, i -> acc * i }
            }
        }


        return sum
    }

    // test if implementation meets criteria from the description, like:
    checkInput("$DAY/test_part1", ::part1, 4361)

    val input = readInput("$DAY/input")
    part1(input).println("Part1")

    checkInput("$DAY/test_part2", ::part2, 467835)

    part2(input).println("Part2")
}


