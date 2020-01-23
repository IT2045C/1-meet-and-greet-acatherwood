import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class PersonReader {
    public static void main(String[] args) throws Exception {

        //// TODO: 1/19/2020 Validate! Make sure if the file cant be found it doesn't explode
        JFileChooser chooser = new JFileChooser();
        int fileName = chooser.showOpenDialog(null);
        if(fileName == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();
            Scanner scan = new Scanner(new FileReader(f));
            //format headers
            //get first line
            String headers = scan.nextLine();
            //remove underscore in column header
            headers = headers.replace("_", "");
            //remove comma between words
            String[] values = headers.split(",");
            //Uppercase first letter of each header
            String capitalizeWord="";
            for (String value: values) {
                String first =value.substring(0,1);
                String afterFirst =value.substring(1);
                capitalizeWord += first.toUpperCase()+afterFirst+" ";
            }

            //create a new array of formatted headers to further format
            String[] columnHeaders = capitalizeWord.split(" ");
            var formattedValue = String.format("%-10s %-10s %-10s %-10s\n", columnHeaders[0], columnHeaders[1], columnHeaders[2], columnHeaders[3]);
            System.out.print(formattedValue);
            String divider = "===========================================================";
            System.out.println(divider);
            //format list of people
            do{
                String people = scan.nextLine();
                //System.out.println(people);
                int index = 0;
                String[] persons = people.split(",");
                var formattedPeople = String.format("%-10s %-10s %-10s %-10s\n", persons[0], persons[1], persons[2], persons[3]);
                System.out.print(formattedPeople);
            }while (scan.hasNextLine() == true);
        }
    }
}
