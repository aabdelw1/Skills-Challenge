import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        System.out.printf("Select Average or Forecast: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String[] var1 = new String[2];
        String[] var2 = new String[2];

        if(input.equals("Average")){
            System.out.print("\nEnter one of the Metrics and the corresponding month(1-13)\n");
            System.out.print("Temperature, Sea_Level, Wind_Direction, Wind_Speed: ");
            for(int i = 0; i < var1.length; i++)
                var1[i] = scanner.next();

            Readfile r = new Readfile(args[0]);
            r.openFile();
            r.Readfile(var1[0], var1[1]);
            r.closeFile();

        } else if(input.equals("Forecast")){
            System.out.print("\nEnter Date in the following MM/dd HH:MM or 'today's date' \n");

            for(int i = 0; i < var2.length; i++)
                var2[i] = scanner.next();
            Forecast r = new Forecast(args[0]);
            r.openFile();
            r.Forecast(var2[0], var2[1]);
            r.closeFile();
        }


    }
}

