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
            String headers = scan.nextLine();
            headers = headers.replace("_", "");
            String[] values = headers.split(",");
            var formattedValue = String.format("%-10s %-10s %-10s %-10s\n", values[0], values[1], values[2], values[3]);
            System.out.printf(formattedValue);
            String capitalizeWord="";

            for (String value: values) {
                String first =value.substring(0,1);
                String afterFirst =value.substring(1);
                capitalizeWord += first.toUpperCase()+afterFirst+" ";

            }
                  System.out.println(capitalizeWord);
//            String[] columns = capitalizeWord.split(" ");
//            String columnHeader = "";
//            String columnHeaders = "";
//            for (String column: columns){
//                columnHeader =  String.format("%15s", column);
//                columnHeaders += columnHeader;
//            }
//            System.out.println(columnHeaders.trim());
            String divider = "==================================================================";
            System.out.println(divider);
            //System.out.format( "%-15s %15s %15s %15s %n", columns[0], columns[1], columns[2], columns[3]);

                do{
                    String people = scan.nextLine();
                    //System.out.println(people);
                    int index = 0;
                    String[] persons = people.split(",");
                    var formattedPeople = String.format("%-10s %-10s %-10s %-10s\n", persons[0], persons[1], persons[2], persons[3]);
                    System.out.printf(formattedPeople);



                }while (scan.hasNextLine() == true);

            //System.out.format( "%5s %10s %10s %10s %n", persons[0], persons[1], persons[2], persons[3]);
            //System.out.format( "%-15s %15s %25s %15s %n", persons[0], persons[1], persons[2], persons[3]);
            //System.out.println(headers);
        }
    }
}
