import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AddOneMonthLocale {
    public static String main(String tt) {
        String inputDate = tt.trim();//"Sep 2005"; // Replace with your input date

        // Define a custom date format pattern with English locale
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);

        try {
            // Parse the input date using the custom formatter
            YearMonth yearMonth = YearMonth.parse(inputDate, formatter);

            // Add one month to the parsed year and month
            YearMonth nextYearMonth = yearMonth.plusMonths(1);

            // Get the last day of the next month
            LocalDate lastDayOfNextMonth = nextYearMonth.atEndOfMonth();

            // Format the result in "MMM yyyy" format
            String formattedNextMonth = lastDayOfNextMonth.format(formatter);

            //System.out.println("Input Date: " + inputDate);
           // System.out.println("Next Month: " + formattedNextMonth);
            return formattedNextMonth;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
