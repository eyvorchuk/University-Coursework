import java.util.*;
import java.io.*;

/*
 * The class TextAnalysisExtra
 * takes a text file inputted
 * from the command line and
 * outputs the same stuff from
 * TextAnalysis as well as its most
 * frequent letter and how many times
 * each letter shows up. Each new section of
 * output takes a certain amount of time to show up 
 * (depending on the length of the previous section) 
 * on the console using System.currentTimeMillis()
 * to give the user time to read each section.
*/ 
public class TextAnalysisExtra {
    public static void main(String[] args) {
        Scanner file = new Scanner(System.in);
        try { 
            file = new Scanner(new File(args[0]));
        }
        catch (FileNotFoundException ex) {
            System.out.println("Error. File not found.");
            System.exit(-1);
        }        
        System.out.println("TEXT FILE STATISTICS");
        for (int i = 1; i <= 20; i++) {
            System.out.print("-");
        }
        System.out.println();
        int maxArraySize = 60000;
        String[] maxWords = new String[maxArraySize];
        int maxIndex = 0;
        while (file.hasNext()) {
            maxWords[maxIndex] = file.next();
            maxIndex++;
        }
        int nullCount = 0;
        for (int index = 0; index < maxArraySize; index++) {
            if (maxWords[index] == null) {
                nullCount++;
            }
        }
        int wordCount = maxArraySize - nullCount;    
        String[] words = new String[wordCount];
        for (int index = 0; index < words.length; index++) {
            words[index] = maxWords[index];
        }
        String longestWord = "";
        int longestLength = 0;
        for (int index = 0; index < words.length; index++) {
            if (words[index].length() > longestLength) {
                longestWord = words[index];
                longestLength = words[index].length();
            }
        }
        String content = Arrays.toString(words);
        char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int[] letterFrequency = new int[26];
        for (int index = 0; index < 26; index++) {
            letterFrequency[index] = computeLetterFrequency(content, letters[index]);
        }
        int numTimes = 0;
        char mostSeenCharacter = ' ';
        for (int index = 0; index < 26; index++) {
            if (letterFrequency[index] > numTimes) {
                numTimes = letterFrequency[index];
                mostSeenCharacter = letters[index];
            }
        }       
        int wordListCount = findWordListCount(words);
        System.out.println("Length of longest word: " + longestLength + " (\"" 
                           + longestWord + "\")");
        System.out.println("Most frequent character: " + mostSeenCharacter);
        System.out.println("Number of words in file wordlist: " + wordListCount);
        System.out.println("Number of words in file: " + wordCount + "\n");
        pauseOutput(6000);
        System.out.println("Word-frequency statistics");
        for (int i = 1; i <= 9; i++) {
            System.out.println("  Word-length " + i + ": " + findLengthCount(words, i));
        }
        int lengthTenOrGreater = 0;
        for (int index = 0; index < words.length; index++) {
            if (words[index].length() >= 10) {
                lengthTenOrGreater++;
            }
        }        
        System.out.println("  Words-length >= 10: " + lengthTenOrGreater + "\n"); 
        pauseOutput(9000);
        System.out.println("Letter dump:");
        for (int index = 0; index < 26; index++) {
            System.out.println(letters[index] + ":" + letterFrequency[index]);
        }
        System.out.println();
        pauseOutput(11500);  
        String[] wordList = new String[wordListCount];
        int[] wordFrequency = new int[wordListCount];
        for (int index = 0; index < words.length; index++) {
            computeWordList(wordList, words[index]);
        } 
        for (int index = 0; index < wordList.length; index++) {
            wordFrequency[index] = computeWordFrequency(words, wordList[index]);
        }
        System.out.println("Wordlist dump:");
        for (int index = 0; index < wordList.length; index++) {
            System.out.println(wordList[index].toLowerCase() + ":" + wordFrequency[index]);
        }            
    }
    
    /* 
     * This method determines the frequency
     * of each letter by passing the file's
     * content as a single string and incrementing
     * the frequency count whenever the relevant
     * letter is found. This count is then returned
     * to be used as the value for the relevant
     * letterFrequency index.
    */
    public static int computeLetterFrequency(String words, char letter) {
        int frequencyCount = 0;
        for (int index = 0; index < words.length(); index++) {
            if (words.charAt(index) == letter) {
                frequencyCount++;
            }
        }
        return frequencyCount;
    }   
  
    public static int findWordListCount(String[] words) {
        int wordListCount = 1;
        for (int currentWord = 1; currentWord < words.length; currentWord++) {
            for (int firstWord = 0; firstWord < currentWord; firstWord++) {
                if (words[currentWord].equalsIgnoreCase(words[firstWord])) {
                    wordListCount--;
                    break;
                }
            }
            wordListCount++;         
        }
        return wordListCount;            
    } 
    
    /* 
     * This method is called after each section of output
     * to pause the program so that the user can read the
     * currently given output.
    */ 
    public static void pauseOutput(double pauseTime) {
        double then, now, difference = 0;
        then = System.currentTimeMillis();
        do {
            now = System.currentTimeMillis();
            difference = now - then;
        } while (difference < pauseTime);
    }
            
    public static int findLengthCount(String[] words, int length) {
        int lengthCount = 0;
        for (int index = 0; index < words.length; index++) {
            if (words[index].length() == length) {
                lengthCount++;
            }
        }
        return lengthCount; 
    }
   
    public static void computeWordList(String[] wordList, String word) {
        for (int index = 0; index < wordList.length; index ++) {
            if (wordList[index] == null) {
                wordList[index] = word;
                break;
            } else if (wordList[index].equalsIgnoreCase(word)) {
                break;
            }        
        }
    }
    
    public static int computeWordFrequency(String[] words, String listWord) {
        int frequencyCount = 0;
        for (int index = 0; index < words.length; index ++) {
            if (words[index].equalsIgnoreCase(listWord)) {
                frequencyCount++;
            }
        }
        return frequencyCount;
    }                                                  
}         
