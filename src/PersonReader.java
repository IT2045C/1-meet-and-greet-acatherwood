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
//        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//
//        int returnValue = jfc.showOpenDialog(null);
//        // int returnValue = jfc.showSaveDialog(null);
//
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = jfc.getSelectedFile();
//            jfc.showOpenDialog(selectedFile);
//            System.out.println(selectedFile.getAbsolutePath());
//            System.out.println(selectedFile.toString());
//        }

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
            String capitalizeWord="";

            for (String value: values) {
                String first =value.substring(0,1);
                String afterFirst =value.substring(1);
                capitalizeWord += first.toUpperCase()+afterFirst+" ";

            }
     //       System.out.println(capitalizeWord);
            String[] columns = capitalizeWord.split(" ");
            String x = "";
            String y = "";
            for (String column: columns){
              x =  String.format("%10s", column);
              y += x;
            }
            System.out.println(y.trim());
            String divider = "====================================================";
            System.out.println(divider);
          //  System.out.format( "%-15s %15s %15s %15s %n", columns[0], columns[1], columns[2], columns[3]);

            String people = scan.nextLine();

            String[] persons = people.split(",");
            for (String person: persons) {
                String.format(person);
            }
            //System.out.format( "%-15s %15s %25s %15s %n", persons[0], persons[1], persons[2], persons[3]);
            //System.out.println(headers);


        }
    }
}
