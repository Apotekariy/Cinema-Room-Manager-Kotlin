package cinema
const val PRICE_FIRST_SEATS = 10
const val PRICE_SECOND_HALF_SEATS = 8
const val TOTAL_NUMBER_SEATS = 60

class AlreadyPurchasedTicket: Exception("That ticket has already been purchased")
class RowOrColumnOutOfBound: Exception("Wrong input")

class Cinema(_rows: Int, _columns: Int, _name: String = "Cinema") {
    private val rows = _rows
    private val columns = _columns
    private val name = _name
    private val totalSeats = rows * columns
    private val bigCinema = totalSeats > TOTAL_NUMBER_SEATS

    private var grid = MutableList(rows) { MutableList(columns) { 'S' } }
    private var numberOfPurchasedTickets = 0
    private var currentIncome = 0
    private var totalIncome = when (bigCinema) {
        true -> columns * (rows / 2) * PRICE_FIRST_SEATS + (columns * (rows - (rows / 2)))* PRICE_SECOND_HALF_SEATS
        false -> totalSeats * PRICE_FIRST_SEATS
    }

    fun printCinemaGrid() {
        println("$name:")
        print("  ")
        for (element in 1..columns) {
            print("$element ")
        }
        println()
        var count = 1
        for (row in grid) {
            print("${count++} ")
            for (element in row) {
                print("$element ")
            }
            println()
        }
    }

    fun buyTicket() {
        println("Enter a row number:")
        val rowNumber = readln().toInt()
        println("Enter a seat number in that row:")
        val seatNumber = readln().toInt()
        try {
            if (rowNumber !in 1..rows || seatNumber !in 1..columns) throw RowOrColumnOutOfBound()
            else if (grid[rowNumber - 1][seatNumber - 1] == 'B') throw AlreadyPurchasedTicket()

            grid[rowNumber - 1][seatNumber - 1] = 'B'
            numberOfPurchasedTickets++ // total count of tickets
            val price: Int = if (bigCinema && rowNumber > rows / 2) {
                PRICE_SECOND_HALF_SEATS
            } else PRICE_FIRST_SEATS
            currentIncome += price // total count of income
            println("Ticket price: \$${price}")

        } catch (e: RowOrColumnOutOfBound) {
            println(e.message)
            buyTicket()
        } catch (e: AlreadyPurchasedTicket) {
            println(e.message)
            buyTicket()
        }
    }

    fun statistics () {
        println("Number of purchased tickets: $numberOfPurchasedTickets")
        val percentage = numberOfPurchasedTickets.toDouble()/totalSeats * 100
        val formatPercentage = String.format("%.2f", percentage)
        println("Percentage: $formatPercentage%")
        println("Current income: \$$currentIncome")
        println("Total income: \$$totalIncome")
    }
}



fun main() {
        println("Enter the number of rows:")
        val rows = readln().toInt()
        println("Enter the number of seats in each row:")
        val columns = readln().toInt()
        val cinema = Cinema(rows, columns)
        menu(cinema)
    }

fun menu(cinema: Cinema) {
    while (true) {
        println(
            """
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit
    """.trimIndent()
        )
        val input = readln().toInt()
        when (input) {
            1 -> cinema.printCinemaGrid()
            2 -> cinema.buyTicket()
            3 -> cinema.statistics()
            0 -> break
            else -> println("Please enter the number ")
        }
    }
}