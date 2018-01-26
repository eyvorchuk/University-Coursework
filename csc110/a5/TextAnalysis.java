import java.util.*;
import java.io.*;

/*
 * The class TextAnalysis
 * takes a text file inputted
 * from the command line and
 * outputs its longest word and
 * how long it is, how many different
 * words and total words it has, how many
 * words have a specific length, and how
 * often each word appears. Each word (duplicate or not) 
 * in the file is stored in an array which is 
 * called to other methods to create arrays 
 * for the wordList and word frequency.
*/ 
public class TextAnalysis {
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
        int wordListCount = findWordListCount(words);
        System.out.println("Length of longest word: " + longestLength + " (\"" 
                           + longestWord + "\")");
        System.out.println("Number of words in file wordlist: " + wordListCount);
        System.out.println("Number of words in file: " + wordCount + "\n");
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
        String[] wordList = new String[wordListCount];
        int[] frequency = new int[wordListCount];
        for (int index = 0; index < words.length; index++) {
            computeWordList(wordList, words[index]);
        } 
        for (int index = 0; index < wordList.length; index++) {
            frequency[index] = computeFrequency(words, wordList[index]);
        }
        System.out.println("Wordlist dump:");
        for (int index = 0; index < wordList.length; index++) {
            System.out.println(wordList[index].toLowerCase() + ":" + frequency[index]);
        }            
    }   
 
    /*
     * This method uses the total words array
     * to determine how many different
     * words there are. Since the counter always
     * increases after each iteration of the
     * outer loop, any duplicate word will
     * counteract the incrementation so
     * that the counter only increases
     * permanently after each new word.
     * This in turn determines the
     * length of the wordList array and the frequency array.
    */ 
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
     * This method determines how many words in the
     * file have a specific length (from 1 to 9 letters).
     * The various numbers returned are used for their relevant
     * lengths in the for loop that calls this method.
    */ 
    public static int findLengthCount(String[] words, int length) {
        int lengthCount = 0;
        for (int index = 0; index < words.length; index++) {
            if (words[index].length() == length) {
                lengthCount++;
            }
        }
        return lengthCount; 
    }
    
    /*
     * This method creates the contents of
     * the wordList array by taking a word from
     * the file and seeing if it already
     * exists in the wordList array. If so,
     * the method is abandoned and in comes
     * the next word. If not, the relevant
     * index of the wordList array is set
     * to that word.
    */ 
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
    
    /*
     * This method determines the frequency of
     * each word by taking each word of the
     * wordList array and seeing how many times
     * it appears in the total words array. The
     * frequency counter is incremented after each sighting
     * and is returned to the for loop that calls this method
     * to store in the relevant index.
    */ 
    public static int computeFrequency(String[] words, String listWord) {
        int frequencyCount = 0;
        for (int index = 0; index < words.length; index ++) {
            if (words[index].equalsIgnoreCase(listWord)) {
                frequencyCount++;
            }
        }
        return frequencyCount;
    }                                                  
}         
