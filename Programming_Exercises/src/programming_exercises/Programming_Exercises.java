/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming_exercises;

import java.util.*;
import java.lang.*;

/**
 *
 * @author allandao
 */
public class Programming_Exercises {

    /**
     * @param args the command line arguments
     */
       
    public static void main(String[] args) { //since this method is static, the referenced must either be static or referenced using an instantiated object
        // TODO code application logic here
        // Initialize scanner for keyboard input
        Scanner keyboard = new Scanner(System.in);
        
        //Initialize instance of this class object
        Programming_Exercises methods = new Programming_Exercises();
        
        //Bowling scores program - after the user inputs a line representing the bowling scores of an entire game, the program will output the scores
        //Sample input: 9, 1, 0, 10, 10, 10, 6, 2, 7, 3, 8, 2, 10, 9, 0, 9, 1, 10
        
        System.out.print("Paste the scores and press enter after inputting each bowler's scores for each of their bowls (for all frames), seperated by a comma: ");
        String readString = "score";
        while (readString != null) {
            if (keyboard.hasNextLine()) {
                readString = keyboard.nextLine();
                String[] scoresString = readString.replaceAll("\\s", "").split(",");
                int[] gameScoreArray = new int[scoresString.length];

                for (int i = 0; i < scoresString.length; i++) {
                    try {
                        gameScoreArray [i] = Integer.parseInt(scoresString[i]);
                    } catch (NumberFormatException nfe) {
                        //NOTE: write something here if you need to recover from formatting errors
                        readString = null;
                        System.out.println("Program stops when there is no input or the inputted data is not valid.");
                        System.exit(0); // in this instance, I can simply exit to skip over this method calling methods.bowlingScore() and printing 
                        // However, in a different program, stopping the program may not be desired. See below to see how I can break of nested loops (AKA double break/break out completely)
                        // https://stackoverflow.com/questions/886955/how-do-i-break-out-of-nested-loops-in-java
                    }
                }
                methods.bowlingScore(gameScoreArray);
            } else {
                readString = null;
                System.out.println("Program stops when there is no input.");
            }
        }
        
        //Find prime number exercise
//        methods.findPrime();
        
        //Reverse a word or string
//        System.out.println(reverseWord("Argument goes here"));
//        System.out.println(reverseWord("aq"));
//        System.out.println(reverseWord("Yo0"));

        //Letter Changes problem. Makes letters go one forward (a -> d, f -> g and capitalize all vowels)
//        System.out.println(methods.LetterChanges("test right?"));

        //Display stars
//        stars();

        //Remove the spaces in front of a word or phrase
//        System.out.println(removeSpaceFromWord("  add me to the call")); // using this.____ references the object and thus the method does not need to be static in the case it is not used within a static void method
        
        //fizz-buzz problem
//        methods.fizzbuzz(15);

    }
    
    static int numStrikeSpare = 10;
    static int framesInBowlingGame = 10;
    
    public int returnFrameScore (int[] frameArray, int arrayNum, int include) {
        switch (include) {
            case 2:
                return frameArray[arrayNum] + frameArray[arrayNum + 1] + frameArray[arrayNum + 2];
            case 1:
                return frameArray[arrayNum] + frameArray[arrayNum + 1];
            default:
                return frameArray[arrayNum];
        }
    }
    
    public void bowlingScore(int[] frameScores) {    
        int score = 0;
        int framesPlayed = 0;
        // assume that the number of scores on the line is valid. there may be multiple lines of input
        //int[] frameScores = {9, 1, 0, 10, 10, 10, 6, 2, 7, 3, 8, 2, 10, 9, 0, 9, 1, 10};
        System.out.println("Frame   Score");
        System.out.println("-----   -----");
        for (int f = 0; f < frameScores.length - 1; f++) {
            framesPlayed++;
            System.out.print("  " + framesPlayed);
            if (framesPlayed < framesInBowlingGame) {
                if (frameScores[f] == numStrikeSpare) {
                    //Strike on regular frame
                    score += returnFrameScore(frameScores, f, 2);
                } else if ((frameScores[f] + frameScores[f + 1]) == numStrikeSpare) {
                    score += returnFrameScore(frameScores, f, 2);
                    f += 1;
                } else {
                    score += returnFrameScore(frameScores, f, 1);
                    f += 1;
                }
                // if less than frame 10 then add?
                System.out.println("      " + score);
            }
            else {
                if ((frameScores[f] == numStrikeSpare) || (frameScores[f] + frameScores[f+1] == numStrikeSpare)) {
                    score += returnFrameScore(frameScores, f, 2);
                } else {
                    score += returnFrameScore(frameScores, f, 1);
                }
                System.out.println("     " + score);
                break;
            }
        }
    }
    
    public void fizzbuzz(int endNumber) {
        // Version 1
//        for (int i = 1; i <= endNumber; i++ ) {
//            if ((i%5==00) && (i%3==00)) {
//                System.out.println("fizzbuzz");
//            }
//            else if ((i%3==00) && !(i%5==00)) {
//                System.out.println("fizz");
//            }
//            else if ((i%5==00) && !(i%3==00)){
//                System.out.println("buzz");
//            }
//            else {
//                System.out.println(i);
//            } 
//        }

        // Version 2
        for (int i = 1; i <= endNumber; i++) {
            //Set booleans so that the statements are not evaluated in the if else statement multiple times
            boolean divisibleByFive = false;
            if (i%5==0) {
                divisibleByFive = true;
            }
            boolean divisibleByThree = false;
            if (i%3==0) {
                divisibleByThree = true;
            }
            
            if (divisibleByThree && divisibleByFive) {
                System.out.println("fizzbuzz");
            }
            else if (divisibleByThree && !divisibleByFive) {
                System.out.println("fizz");
            }
            else if (!divisibleByThree && divisibleByFive){
                System.out.println("buzz");
            }
            else {
                System.out.println(i);
            } 
        }
        
        //Version 3: see further reading below. can revisit fizzbuzz in the future after learning and reading more
        //switch can be used if I represent each potential case by an integer or similar
        //Further reading:
        //https://ditam.github.io/posts/fizzbuzz/ - consider using var output to simplify this via print fizz then buzz without needing to test the fizzbuzz case
        //https://codereview.stackexchange.com/questions/60145/which-fizzbuzz-is-better-and-why
        //may need to use concentation when using var output implementation
        //https://leetcode.com/problems/fizz-buzz/discuss/?currentPage=1&orderBy=most_votes&query= - consider outputting via String list or ArrayList
    }
    
    public static String removeSpaceFromWord(String word) {
        int wordWithoutSpaceStart = 0;
        //String wordWithoutSpace = "   add"; would be returned as the string add, example test case
        for (int i = 0; i < word.length(); i++) {
            while (word.substring(wordWithoutSpaceStart, i).equalsIgnoreCase(" ")) {
                wordWithoutSpaceStart++;
            }
        }
        return word.substring(wordWithoutSpaceStart, word.length());
    }
    
    private static final List<Character> VOWEL = Arrays.asList('a','e', 'i', 'o', 'u');
    //https://stackoverflow.com/questions/7279887/what-is-the-use-of-a-private-static-variable-in-java referencing keywords
    /* private static final char [] VOWEL = {'a', 'e', 'i', 'o', 'u'}; can use char here instead of string as the array only contains characters.
    Also, consider not using a primitive array [] here as it does not contain some methods such as .contains(). Use an array list instead */
    /*    Single quoted is to define char literals e.g char a = ‘a’;
    Double quoted is to define String literals e.g String hello = “Hello”;
    If you want to use single quoted in String literals you can: “ ‘Hello’ ”
    If you want to use double quoted in String literal you have to escape it: " \"Hello\" " */
    
    public String LetterChanges (String str) {
        //use the StringBuilder to manipulate a String
        //use .toString() to return it to the String class
        StringBuilder letterChanges = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isAlphabetic(c)) {
                    c++;
                }
                if (VOWEL.contains(c)) {
                    c = Character.toUpperCase(c);
                }
                letterChanges.append(c);
            }
            return letterChanges.toString();
        }
    
//        Unrelated to other programs - Print out letters corresponding with numbers 1-26
//        for (int i = 'A'; i <= 'Z'; i++) {
//            System.out.println((char) i + " : " + (i - 'A' + 1));
//        }
    
    public static String reverseWord(String str) {
        String word = str.toString(); //toString() in the case of an input with numbers
        String reverse = new String();
        for (int i = word.length() - 1; i >= 0; i--) {
            reverse += word.substring(i, i+1);
        }
        return reverse;
    }
    
    public void findPrime() {
            //create a program that recognizes when there is a prime number
            //this array could be initialized with a for loop to automatically add each number
            int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
                30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
                40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
                50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
                60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
                70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
                80, 81, 82, 83, 84, 85, 86, 87, 88, 89,
                90, 91, 92, 93, 94, 95, 96, 97, 98, 99,
                100, 107, 109, 110};

            //Testing square roots with user input
            //Scanner keyboard = new Scanner(System.in);
            //System.out.print("Enter a number: ");
            //double number = keyboard.nextDouble();
            //double squareRoot = Math.sqrt(number);
            //System.out.println("no rounding: " + squareRoot);
            //System.out.println("round: " + Math.round(squareRoot));
            
            //Note: .length used for numbers/array objects, .length() used for string objects
            //Using square root implementation
            for (int i = 0; i < numbers.length; i++) {
                boolean prime = false;
                double squareRoot = Math.sqrt(numbers[i]);
                //Don't need to check if divisible by 0 and 1
                for (int x = 2; x <= Math.floor(squareRoot); x++) {
                    //inside for loop tests if the number is prime. set x to start at 2 since 1 would always lead to the boolean being true
                    //using Math.floor since we only need to test factors up to the square root. 
                    //For 37, we need to check by finding the modulus from 2 up to 6 (since 6x6=36 so the square-root of 37 is only a little bit larger). If the modulus is never 0, the number is prime
                    //Suppose one number is a factor of N and that it is smaller than the square-root of the number N. Then the second factor must be larger than the square-root. 
                    if ((numbers[i] % x) == 0) {
                        // if one of the factors leads to no remainder, it is not a prime number
                        prime = false;
                        break;
                    } else {
                        prime = true;
                    }
                }
                if ((numbers[i] == 2) | (numbers[i] == 3)) {
                    //1 is not prime, but 2 and 3 are, special cases for this program. could be integrated into sout statements as well
                    prime = true;
                }
                if (prime == true) {
                    System.out.println(numbers[i] + " is a prime number");
                }
            }

            //list of prime numbers: 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, ...
            /* Example: 67 -> closest square root below 67 is 64. Square root of 64 is 8. 
               Use modulo operation between 67 and 2 through 6 to check if the remainder is ever 0
               Since it is never 0, 67 is prime */
            
            

    }
    
    public static void stars() {
        int rows = 5; // change as desired
        for (int i = 0; i < 5; i++)
            System.out.println("    *************************".substring(i, rows + 2 * i));
    }
    
}
