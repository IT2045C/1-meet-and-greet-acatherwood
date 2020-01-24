import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PortfolioGenerator {
    public static void main(String[] args) {
        //create file - defaulted to assignment requirements
        String fileName = "acatherwood.txt";
        creatFile(fileName);
        //Get user input
        recordUserInput(fileName);

    }

    public static void creatFile(String filename){
        try {
            File myObj = new File(filename);
            //debug purposes only
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists - writing to existing file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void recordUserInput(String fileName){
        String prompt;
        String newLine = "\n";
        Scanner scan = new Scanner(System.in);

        prompt = "Please enter your name";
        String userName = SafeInput.getString(scan, prompt);
        userName = uppercaseFirstLetter(userName);
        writeToFileFormattedName(fileName, userName);

        prompt = "Please enter your email";
        String userEmail = SafeInput.getEmail(scan, prompt);
        userEmail = uppercaseFirstLetter(userEmail);
        userEmail = "email: " + userEmail + newLine;
        writeToFile(fileName, userEmail);

        prompt = "Please give provide a brief personal background";
        String personalBackgroundHeader = "** Personal Background:" + newLine + "------------------------";
        writeToFile(fileName, personalBackgroundHeader);
        String userBackground = SafeInput.getString(scan, prompt);
        //word wrap at 100 characters
        List formatted = splitString(userBackground, 100);
        String formattedResponse = "";
        for(int i = 0; i < formatted.size(); i++) {
           formattedResponse += formatted.get(i).toString() + "\n";
        }
        writeToFile(fileName, formattedResponse);

        prompt = ("Programming Languages(type stop to move on to the next question: )");
        String programmingLanguagesHeader = "** Programming Languages:" + newLine + "------------------------";
        writeToFile(fileName, programmingLanguagesHeader);
        ArrayList userProgrammingLanguages = SafeInput.getArrayOfStrings(scan, prompt);
        writeArrayToFile(fileName, userProgrammingLanguages);

        prompt = ("Achievements and Interests(type stop to move on to the next question: )");
        String achievementsHeader = newLine +  "** Achievements and Things I would like to share:" + newLine + "------------------------";
        writeToFile(fileName, achievementsHeader);
        ArrayList userAchievements = SafeInput.getArrayOfStrings(scan, prompt);
        writeArrayToFile(fileName, userAchievements);

        //add closing stars
        String footer = newLine + "****************************************************" ;
        writeToFile(fileName, footer);
    }

    public static void writeToFileFormattedName(String filename, String userInput){
        try {
            String newLine = "\n";
            String special = "**";
            String headerStars = "****************************************************";
            FileWriter myWriter = new FileWriter(filename, true);
            PrintWriter printWriter = new PrintWriter(myWriter);
            printWriter.println(headerStars);
            printWriter.printf( "%-15s %15s %20s %n", special, userInput, special);
            printWriter.println(headerStars);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String filename, String userInput){
        try {
            String newLine = "\n";
            FileWriter myWriter = new FileWriter(filename, true);
            PrintWriter printWriter = new PrintWriter(myWriter);
            printWriter.println(userInput);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeArrayToFile(String filename, ArrayList userInput){
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            PrintWriter printWriter = new PrintWriter(myWriter);
            //sort list so that items appear alphabetically
            Collections.sort(userInput);
            int counter = 0;
            for(int i = 0; i < userInput.size(); i++) {
                counter ++;
                printWriter.println(counter + ". " + userInput.get(i));
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static List splitString(String msg, int lineSize) {
        List res = new ArrayList();

        Pattern p = Pattern.compile("\\b.{1," + (lineSize-1) + "}\\b\\W?");
        Matcher m = p.matcher(msg);

        while(m.find()) {
            res.add(m.group().trim());
        }
        return res;
    }

    public static String uppercaseFirstLetter(String msg){
        String formatted = msg.substring(0,1).toUpperCase() + msg.substring(1);

        return formatted;
    }

}
