package cinema
const val PRICE_FIRST_SEATS = 10
const val PRICE_SECOND_SEATS = 8
const val TOTAL_NUMBER_SEATS = 60

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val column = readln().toInt()
    val cinemaGrid = creatingGrid (rows, column)

    printCinemaGrid(rows,column, cinemaGrid)


    println("Enter a row number:")
    val rowNumber = readln().toInt()
    println("Enter a seat number in that row:")
    val  seatNumber = readln().toInt()

    buySeat(rowNumber, rows, column)
    cinemaGrid[rowNumber-1][seatNumber-1] = 'B'
    printCinemaGrid(rows, column, cinemaGrid)
}

@Suppress("RemoveExplicitTypeArguments")
fun creatingGrid (rows: Int, column: Int) : MutableList<MutableList<Char>> {
    return MutableList(rows){
        MutableList<Char>(column){'S'}
    }
}

fun printCinemaGrid (rows: Int, column: Int, grid: MutableList<MutableList<Char>>){
    println("Cinema:")
    print("  ")
    for (element in 1..column){
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

fun buySeat (rowNumber: Int, rows: Int, column: Int) {
    val totalSeats = rows*column
    val price : Int = if (totalSeats >= TOTAL_NUMBER_SEATS && rowNumber > rows/2) {
        PRICE_SECOND_SEATS
    } else PRICE_FIRST_SEATS

    println("Ticket price: \$${price}")
}
