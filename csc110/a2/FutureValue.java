import java.util.Scanner;
import java.text.DecimalFormat;

/*
 * The class FutureValue prompts the 
 * user to input their starting investment,
 * interest rate, and number of years for 
 * this investment to display how much the
 * investment interest increases for that time.
*/ 
public class FutureValue {
    public static void main(String[] args) {
        Scanner investment = new Scanner(System.in);
        DecimalFormat formatter = new DecimalFormat(".00");
        System.out.print("The starting amount: "); 
        double startAmount = investment.nextDouble();
        System.out.print("The interest rate in percent: ");
        int percent = investment.nextInt();
        System.out.print("The number of years: ");
        int years = investment.nextInt();
        System.out.println("Y $");
        for (int i = 0; i <= years; i++) {
            String formattedAmount = formatter.format(startAmount);
            System.out.println("" + i + " " + formattedAmount);
            startAmount = startAmount + startAmount * 0.01 * percent;
        }
    }
}            
            
        