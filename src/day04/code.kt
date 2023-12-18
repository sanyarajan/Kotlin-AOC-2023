package day04

import checkInput
import println
import readInput
import toIntList
import kotlin.math.pow

const val DAY = "04"

fun main() {

    fun getMatchesPerCard(card: String): Int {
        val winningNumbers = card.substringAfter(": ").substringBefore(" |").toIntList()
        val yourNumbers = card.substringAfter("| ").toIntList()
        val numberOfMatches = yourNumbers.count { it in winningNumbers }
        return numberOfMatches
    }

    fun part1(input: List<String>): Int {
        var sum = 0

        // the input is a list of cards with winning numbers separated by spaces and then a pipe symbol and then a list of numbers you have separated by spaces
        // we need to check if any of the numbers you have match any of the winning numbers
        for (card in input) {
            val numberOfMatches = getMatchesPerCard(card)
            if (numberOfMatches > 0) {
                // the card score is 1 for the first match then doubled for every match after that
                val cardScore = 2.0.pow(numberOfMatches - 1).toInt()
                sum += cardScore
            }
        }

        return sum
    }

    data class Card(val cardNumber: Int, val numberOfMatches: Int)

    fun part2(input: List<String>): Int {
        val cardsList = mutableListOf<Card>()
        for (card in input) {
            val cardNumber = card.substringAfter("Card ").substringBefore(":").trim().toInt()
            val numberOfMatches = getMatchesPerCard(card)
            cardsList.add(Card(cardNumber, numberOfMatches))
        }

        // create a copy of the list
        val originalCardsList = cardsList.toList()

        val cardIterator = cardsList.listIterator()
        for (card in cardIterator) {
            val cardNumber = card.cardNumber
            val numberOfMatches = card.numberOfMatches

            if (numberOfMatches == 0) {
                continue
            }

            for (i in 1..numberOfMatches) {
                cardIterator.add(originalCardsList.first { it.cardNumber == cardNumber + i })
                cardIterator.previous()
            }
        }

        return cardsList.count()

    }

    // test if implementation meets criteria from the description, like:
    checkInput("$DAY/test_part1", ::part1, 13)

    val input = readInput("$DAY/input")
    part1(input).println("Part1")

    checkInput("$DAY/test_part2", ::part2, 30)

    part2(input).println("Part2")
}


