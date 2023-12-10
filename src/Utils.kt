import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.system.exitProcess

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/day$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println(prefix:String? = null) {
    if(prefix != null) print("$prefix: ")
    println(this)
}

/**
 * Checks the input file using a specified check function and compares the result with the expected result.
 *
 * @param inputFile The name of the input txt file to read lines from.
 * @param checkFunction A function that takes a list of strings as input and returns an integer.
 * @param expectedResult The expected result of the check function.
 */
fun checkInput(inputFile: String, checkFunction: (List<String>) -> Int, expectedResult: Int) {
    val testInput = readInput(inputFile)
    if(testInput.isEmpty()) {
        println("Failed - No input")
        exitProcess(0)
    }
    val result = checkFunction(testInput)
    try {
        check(result == expectedResult)
    }
    catch(e:IllegalStateException) {
        println("Failed - Expected $expectedResult, got $result")
        exitProcess(1)
    }
}
