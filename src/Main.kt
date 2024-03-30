import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Function to calculate the days until the next full moon
fun calculateDaysUntilNextFullMoon(today: LocalDate): Number {
    // Full moons roughly occur every 29.53 days
    val averageDaysBetweenFullMoons = 29.53
    // Calculate the days since the last full moon (change the date as needed)
    val daysSinceLastFullMoon = today.until(LocalDate.of(2024, 3, 29)).days
    // Calculate the days until the next full moon
    val daysToNextFullMoon = averageDaysBetweenFullMoons - (daysSinceLastFullMoon % averageDaysBetweenFullMoons)
    return if (daysToNextFullMoon < 0) {
        // If the remaining days are negative, add the average days between full moons
        (daysToNextFullMoon + averageDaysBetweenFullMoons.toLong()).toLong()
    } else daysToNextFullMoon.toLong()
}

fun main() {
    // Define a date formatter
    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

    // Get the current date from the user
    println("Enter today's date (MM/dd/yyyy): ")
    val inputDate = readlnOrNull()
    val today = if (inputDate != null) {
        LocalDate.parse(inputDate, formatter)
    } else {
        LocalDate.now()
    }

    // Format today's date
    val formattedToday = today.format(formatter)
    // Print today's date
    println("Today is $formattedToday")

    // Calculate the days until the next full moon
    val daysUntilNextFullMoon = calculateDaysUntilNextFullMoon(today)
    // Calculate the date of the next full moon by adding the days until the next full moon to today's date
    val nextFullMoonDate = today.plusDays(daysUntilNextFullMoon as Long)
    // Format the date of the next full moon
    val formattedNextFullMoon = nextFullMoonDate.format(formatter)

    // Print the date of the next full moon
    println("The next full moon will be: $formattedNextFullMoon")
    // Print a reminder to wear silver or stay inside three days after the full moon
    println("Wear silver or stay inside at night until ${nextFullMoonDate.plusDays(3).format(formatter)}")
}
