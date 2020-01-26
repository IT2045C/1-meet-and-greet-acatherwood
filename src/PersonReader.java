import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class PersonReader {
    public static void main(String[] args) throws Exception {

        JFileChooser chooser = new JFileChooser();
        int fileName = chooser.showOpenDialog(null);
        try{
            if(fileName == JFileChooser.APPROVE_OPTION){
                File f = chooser.getSelectedFile();
                Scanner scan = new Scanner(new FileReader(f));
                String headers = scan.nextLine();

                String formattedHeaders = formatHeadings(headers);
                System.out.print(formattedHeaders);
                String divider = "===========================================================";
                System.out.println(divider);

                GetRows(scan);
            }

            }catch(FileNotFoundException e) {
                var message = "File not found";
                System.out.println(message);
            }
        }

        public static String formatHeadings (String columnHeaders){
            //remove underscore in column header
            columnHeaders = columnHeaders.replace("_", "");
            //remove comma between words
            String[] values = columnHeaders.split(",");
            //Uppercase first letter of each header
            String capitalizeWord="";
            for (String value: values) {
                String first =value.substring(0,1);
                String afterFirst =value.substring(1);
                capitalizeWord += first.toUpperCase()+afterFirst+" ";
            }

            //create a new array of formatted headers to further format
            String[] formattedValue = capitalizeWord.split(" ");
            String formattedHeaders = String.format("%-10s %-10s %-10s %-10s\n", formattedValue[0], formattedValue[1], formattedValue[2], formattedValue[3]);

            return formattedHeaders;
        }

        public static void GetRows(Scanner scan) {
            try {
                //format list of people
                do {
                    String people = scan.nextLine();
                    String[] persons = people.split(",");
                    var formattedPeople = String.format("%-10s %-10s %-10s %-10s\n", persons[0], persons[1], persons[2], persons[3]);
                    System.out.print(formattedPeople);
                } while (scan.hasNextLine() == true);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

