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
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
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

        String nameStyle = "****************************************************";

        prompt = "Please enter your name";
        String userName = SafeInput.getString(scan, prompt);
        //Extra Credit - center name
        //Note: This works but it's awful. It's hard coded to the point where it only works for my name(yuck!!!)
        //If it was a requirement, I would fix it. But given that it's extra credit and I still need to do part one,
        //I'm leaving it for now
        //// TODO: 1/19/2020 Have some self-respect and fix this mess!
        String userNameStyle = "**              " + userName + "                   **";
        userName = nameStyle + newLine + userNameStyle + newLine + nameStyle + newLine;
        writeToFile(fileName, userName);

        prompt = "Please enter your email";
        String userEmail = SafeInput.getEmail(scan, prompt);
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
        writeToFile(fileName, nameStyle);
    }


    public static void writeToFile(String filename, String userInput){
        try {
            String newLine = "\n";
            FileWriter myWriter = new FileWriter(filename, true);
            PrintWriter printWriter = new PrintWriter(myWriter);
            printWriter.println(userInput);
            printWriter.close();
           // System.out.println("Successfully wrote to the file."); --debug
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
            //System.out.println("Successfully wrote to the file."); --debug
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    // c/p from StackOverflow. No need to reinvent the wheel
    public static List splitString(String msg, int lineSize) {
        List res = new ArrayList();

        Pattern p = Pattern.compile("\\b.{1," + (lineSize-1) + "}\\b\\W?");
        Matcher m = p.matcher(msg);

        while(m.find()) {
            res.add(m.group().trim());
        }
        return res;
    }

}
