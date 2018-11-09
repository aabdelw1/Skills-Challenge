import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Forecast {

    private Scanner x;
    String FileName;

    private static final DateFormat todays_date = new SimpleDateFormat("MM/dd");
    private static final DateFormat current_time = new SimpleDateFormat("HH:mm:ss");
    ArrayList<ArrayList<String>> all_values = new ArrayList<ArrayList<String>>();
    ArrayList<String> the_date = new ArrayList<String>();
    Date date = new Date();

    public void openFile(){
        try{
            x = new Scanner(new File(this.FileName));
        }
        catch(Exception e){
            System.out.println("could not find file");
        }
    }

    public Forecast(String FileName){
        this.FileName = FileName;
    }

    //read in the file and load the all_values Array with appropriate information
    public void Forecast(String Date, String Time) {

        if(Date.equals("today's") && Time.equals("date")){
            Date = todays_date.format(date);
            Time = current_time.format(date);
            parseDate(Date, Time);
        } else
            parseDate(Date, Time);

        while (x.hasNext()) {
            String a = x.nextLine();
            String[] values = a.split(" +");
            ArrayList<String> line = new ArrayList<>();
            for (int i = 0; i < values.length; i++) {

                line.add(values[0]);
                line.add(values[1]);
                line.add(values[2]);
                line.add(values[3]);
                line.add(values[9]);
                break;
            }
            all_values.add(line);
        }
        findForecast(Date, Time);
    }

    //parse current date and place it in an array and to round to nearest hour
    public void parseDate(String Date, String Time){

        String parts[] = Date.split("/");
        String parts2[] = Time.split(":");
        String month = parts[0];
        String day = parts[1];
        String hours = parts2[0];
        String minutes = parts2[1];

        the_date.add(month);
        the_date.add(day);
        int hours_int = Integer.parseInt(hours);


        if(Integer.parseInt(minutes) >= 30){
            hours_int = hours_int + 1;
            the_date.add(Integer.toString(hours_int));
        } else
            the_date.add(hours);

        //System.out.printf("The current date and time is %s/%s  %s:%s\n", month, day, hours, minutes);
    }

    //find date in the file that matches the array date and send to the code to returnForecast
    public void findForecast(String Date, String Time){

       //send the three hour marks of the day and send it to ClosetNumber method
       int num1 = Integer.parseInt(all_values.get(0).get(3));
       int num2 = Integer.parseInt(all_values.get(1).get(3));
       int num3 = Integer.parseInt(all_values.get(2).get(3));
       String ClosestNumber = ClosestNumber(num1, num2, num3);
       int time = Integer.parseInt(ClosestNumber);


       int counter = 0;


      for(int i = 0; i < all_values.size(); i++){
              if(all_values.get(i).get(1).equals(the_date.get(0)) &&
                      all_values.get(i).get(2).equals(the_date.get(1)) &&
                        Integer.parseInt(all_values.get(i).get(3)) == time){

                  String code = all_values.get(i).get(4);
                  returnForecast(code, Date, Time);
                  break;
              }else if(counter == all_values.size()-1)
                  System.out.println("Date Invalid");
      }
      counter++;
    }

    //the current time is then compared to the three numbers received up top and returns the number
    //that the current hour is closest to.
    public String ClosestNumber(int num1, int num2, int num3) {

       ArrayList<Integer> numbers = new ArrayList<>();
       numbers.add(num1);
       numbers.add(num2);
       numbers.add(num3);
       int numberToCompare = Integer.parseInt(the_date.get(2));
       int distance = Math.abs(numbers.get(0) - numberToCompare);
       int index = 0;

        for(int c = 1; c < numbers.size(); c++){
            int cdistance = Math.abs(numbers.get(c) - numberToCompare);
            if(cdistance < distance){
                index = c;
                distance = cdistance;
            }
        }
        int theNumber = numbers.get(index);

        return Integer.toString(theNumber);

    }

    //finally when a code is received, it gets sent here to return the accureate forecast
    public void returnForecast(String Code, String Date, String Time){

        String Date1;
        String Time1;

        if(Date.equals("today's") && Time.equals("date")) {
            Date1 = todays_date.format(date);
            Time1 = current_time.format(date);
        } else {
            Date1 = Date;
            Time1 = Time;
        }
        String Year = all_values.get(0).get(0);

        if(Code.equals("-9999")){
            System.out.println("No Data on this date");
        } else {
            System.out.printf("\nSky Condition on %s at %s of year %s is:\n", Date1, Time1, Year);

            switch (Code) {
                case "0":
                    System.out.println("None, SKC or CLR");
                    break;
                case "1":
                    System.out.println("One okta - 1/10 or less but not zero");
                    break;
                case "2":
                    System.out.println("Two oktas - 2/10 - 3/10, or FEW");
                    break;
                case "3":
                    System.out.println("three oktas - 4/10");
                    break;
                case "4":
                    System.out.println("four oktas - 5/10, or SCT");
                    break;
                case "5":
                    System.out.println("five oktas - 6/10");
                    break;
                case "6":
                    System.out.println("Six oktas - 7/10 - 8/10");
                    break;
                case "7":
                    System.out.println("Seven oktas - 9/10 or more but not 10/10, or BKN");
                    break;
                case "8":
                    System.out.println("Eight oktas - 10/10, or OVC");
                    break;
                case "9":
                    System.out.println("Sky obscured, or cloud amount cannot be estimated");
                    break;
                case "10":
                    System.out.println("Partial obscuration");
                    break;
                case "11":
                    System.out.println("Thin scattered");
                    break;
                case "12":
                    System.out.println("Scattered");
                    break;
                case "13":
                    System.out.println("Dark scattered");
                    break;
                case "14":
                    System.out.println("Thin broken");
                    break;
                case "15":
                    System.out.println("Broken");
                    break;
                case "16":
                    System.out.println("Dark Broken");
                    break;
                case "17":
                    System.out.println("Thin overcast");
                    break;
                case "18":
                    System.out.printf("Overcast");
                    break;
                case "19":
                    System.out.println("Dark overcast");
                    break;
            }
        }

    }

    public void closeFile(){
        x.close();
    }
}

