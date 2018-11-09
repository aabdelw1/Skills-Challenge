import java.io.*;
import java.util.*;
import java.text.DateFormatSymbols;

public class Readfile {

    private Scanner x;
    String FileName;

    ArrayList<Double> temp_dub = new ArrayList<>();
    ArrayList<Integer> all_temps = new ArrayList<>();
    ArrayList<String> all_years = new ArrayList<>();


    public void openFile(){
        try{
            x = new Scanner(new File(this.FileName));
        }
        catch(Exception e){
            System.out.println("could not find file");
        }
    }

    public Readfile(String FileName){
        this.FileName = FileName;
    }

    public void Readfile(String function, String month) {

        //int int_month = Integer.parseInt(month);


        if (Integer.parseInt(month) <= 13 || month.equals("all")) {
            while (x.hasNext()) {
                String a = x.nextLine();
                String[] values = a.split(" +");

                for (int i = 0; i < values.length; i++) {
                    if(Integer.parseInt(month) == Integer.parseInt(values[1])) {
                        if(!values[4].equals("-9999")) {
                            switch (function){
                            case "Temperature":
                                temp_dub.add(Double.parseDouble(values[4])/10.0);
                                    break;
                            case "Sea_Level":
                                temp_dub.add(Double.parseDouble(values[6])/10.0);
                                break;

                            case "Wind_Direction":
                                temp_dub.add(Double.parseDouble(values[7]));
                                break;

                            case "Wind_Speed":
                                temp_dub.add(Double.parseDouble(values[8])/10.0);
                                break;
                            }
                        }
                        //If you input '13' for months, it will return the average for the whole year
                    } else if(month.equals("13")){
                        if(!values[4].equals("-9999")) {
                            switch (function){
                                case "Temperature":
                                    temp_dub.add(Double.parseDouble(values[4])/10.0);
                                    break;
                                case "Sea_Level":
                                    temp_dub.add(Double.parseDouble(values[6])/10.0);
                                    break;

                                case "Wind_Direction":
                                    temp_dub.add(Double.parseDouble(values[7]));
                                    break;

                                case "Wind_Speed":
                                    temp_dub.add(Double.parseDouble(values[8])/10.0);
                                    break;
                            }
                            all_years.add(values[0]);
                        }
                        break;
                    }
                    break;
                }
            }

            average(function, month);
        }else
            System.out.printf("Is there more than 12 months?\n");

    }

    //takes in function and month from the input file and returns the average based on the inpuit provided
    public void average(String function, String month){

        double sum = 0.0;
        double average_c;
        String str_month = getMonth(Integer.parseInt(month));
        String time = "";

        Collections.sort(temp_dub);
        double minimum = temp_dub.get(0);
        double maximum = temp_dub.get(temp_dub.size()-1);

        for(int i=0; i < temp_dub.size(); i++){
            sum = sum + temp_dub.get(i);
        }
        average_c = ((double)sum/temp_dub.size());
        double rounded_avr_c = round(average_c, 2);
        double rounded_avr_f = round((rounded_avr_c *9)/5.0 + 32, 2);

        if(month.equals("13")) {
            time = all_years.get(0);
        } else
            time = str_month;

        switch(function){
            case "Temperature":
                System.out.printf("The average %s of %s is %s°C or %s°F\n", function, time, rounded_avr_c, rounded_avr_f);
                System.out.printf("Minimum %s: %s°C\nMaximum %s: %s°C\n", function, minimum, function, maximum);
                break;
            case "Sea_Level":
                System.out.printf("The average %s of %s is %s Hectopascals\n", function, time, rounded_avr_c);
                System.out.printf("Minimum %s: %s Hectopascals\nMaximum %s: %s Hectopascals\n", function, minimum, function, maximum);
                break;
            case "Wind_Direction":
                System.out.printf("The average %s of %s is %s°\n", function, time, rounded_avr_c);
                System.out.printf("Minimum %s: %s°\nMaximum %s: %s°\n", function, minimum, function, maximum);
                break;

            case "Wind_Speed":
                System.out.printf("The average %s of %s is %sm/s\n", function, time, rounded_avr_c);
                System.out.printf("Minimum %s: %sm/s\nMaximum %s: %sm/s\n", function, minimum, function, maximum);
                break;
        }

    }
    //converts numerical month into the month name
    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }
    //rounds corresponding value to n places
    public static double round(double value, int places) {
        double scale = Math.pow(10, places);
        double new_average = Math.round(value * scale) / scale;
        return new_average;
    }


    public void closeFile(){
        x.close();
    }
}

