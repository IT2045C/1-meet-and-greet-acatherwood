import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Wulft
 */
public class SafeInput {
    /**
     * @param console - Scanner instance to read the data System.in in most cases
     * @param prompt  - input prompt msg should not include range info
     * @param low     - low end of inclusive range
     * @param high    - high end of inclusive range
     * @return - int value within the inclusive range
     */
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (console.hasNextInt()) {
                retVal = console.nextInt();
                console.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                trash = console.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * @param console - Scanner instance to read the data System.in in most cases
     * @param prompt  - input prompt msg should not contain range info
     * @param low     - low value inclusive
     * @param high    - high value inclusive
     * @return - double value within the specified inclusive range
     */
    public static double getRangedDouble(Scanner console, String prompt, int low, int high) {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (console.hasNextDouble()) {
                retVal = console.nextDouble();
                console.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                trash = console.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while (!done);

        return retVal;
    }

    //extra credit
    public static String getEmail(Scanner console, String prompt) {
        String retval = "";
        String trash = "";
        boolean done = false;

        String emailRegex = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})";
        System.out.println(prompt);


        do{
            String userInput = console.nextLine();
            retval = userInput.trim();

                if (retval.matches(emailRegex)) {
                    done = true;

                }else {
                    System.out.println("provide valid email address");
                }

        }while (!done);

        return retval;

    }



    public static String getString(Scanner console, String prompt) {
        String retval = "";
        boolean done = false;

        //make sure the user enters a first and last name
        String nameRegex = "(\\w.+\\s).+";
        System.out.println(prompt);


        do{
            String userInput = console.nextLine();
             retval = userInput.trim();
            if (prompt.contains("name"))
            {
                if (retval.matches(nameRegex)) {
                    done = true;

                }else {
                    System.out.println("provide first and last name");
                }
            }
            else if (prompt.contains("personal background"))
            {
                //make sure personal background statement isn't blank or null.
                if (!(retval.isEmpty()) || (retval == null)){
                    done = true;
                }
                else{
                    System.out.println("you must provide a personal background");
                }
            }

        }while (!done);

        return retval;
    }

    public static ArrayList<String> getArrayOfStrings(Scanner console, String prompt) {
        ArrayList<String> retval = new ArrayList<>();
        Boolean done = false;

        System.out.println(prompt);
        do {
            String userInput = console.nextLine();
            if (userInput.isEmpty()){
                System.out.println("Please add a programming language, blank lines are not accepted");
            }
            else if (userInput.equalsIgnoreCase("stop")){
                done = true;
            }else{
                retval.add(userInput.trim());
            }
        }while (!done);
        return retval;
    }

    public static int getRangedIntDialog(String prompt, int low, int high) {
        int retVal = 0;
        String inputString = "";
        boolean done = false;

        do {
            inputString = JOptionPane.showInputDialog(null, prompt + " [" + low + "-" + high + "]: ");

            try {
                retVal = Integer.parseInt(inputString);

                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Number is out of range [" + low + "-" + high + "]: " + inputString);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "You must enter a number [" + low + "-" + high + "]: " + inputString);

            }
        } while (!done);

        return retVal;
    }

    public static double getRangedDoubleDialog(String prompt, int low, int high) {
        double retVal = 0;
        String inputString = "";
        boolean done = false;

        do {
            inputString = JOptionPane.showInputDialog(null, prompt + " [" + low + "-" + high + "]: ");

            try {
                retVal = Double.parseDouble(inputString);

                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Number is out of range [" + low + "-" + high + "]: " + inputString);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "You must enter a number [" + low + "-" + high + "]: " + inputString);

            }
        } while (!done);

        return retVal;
    }


    /**
     * @param console - Scanner instance to read the data System.in in most cases
     * @param prompt  -input prompt msg for user does not need [Y/N]
     * @return - true for yes false for no
     */
    public static boolean getYNConfirm(Scanner console, String prompt) {
        boolean retVal = true;
        String response = "";
        boolean gotAVal = false;

        do {
            System.out.print("\n" + prompt + " [Y/N] ");
            response = console.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                gotAVal = true;
                retVal = true;
            } else if (response.equalsIgnoreCase("N")) {
                gotAVal = true;
                retVal = false;
            } else {
                System.out.println("You must answere [Y/N]! " + response);
            }

        } while (!gotAVal);

        return retVal;
    }

    public static boolean getYNConfirmDialog(String prompt) {
        boolean retVal = true;

        int dialogResponse = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (dialogResponse == JOptionPane.NO_OPTION) {
            retVal = false;
        } else if (dialogResponse == JOptionPane.YES_OPTION) {
            retVal = true;
        } else if (dialogResponse == JOptionPane.CLOSED_OPTION) {
            retVal = false;
        }

        return retVal;
    }
}