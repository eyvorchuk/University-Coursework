import java.util.*;
import java.io.*;

/*
 * The class Voice is used
 * to create a voice object,
 * which consists of an instrument and
 * a sequence of notes. This class is only used
 * whenever the input cursor from the file
 * being read is right before the word "instrument".
*/
public class Voice {
    private Note[] notes;
    private String instrument;
    
    /*
     * This constructor sets the initial
     * attributes of the voice object. An
     * array of notes is used to store the notes
     * as read from the input file. Setting duration
     * to input.nextLine() instead of input.next() is 
     * vital for the implementation of the while loop
     * in the TransposeSong class (avoids InputMismatchException).
    */
    public Voice(Scanner input) {
        instrument = input.nextLine();
        input.next();
        int noteCount = input.nextInt();
        notes = new Note[noteCount];
        for (int i = 0; i < notes.length; i++) {
            String pitch = input.next();
            String duration = input.nextLine();
            notes[i] = new Note(pitch, duration);
        }
    }
    
    /*
     * This method transposes each note in the
     * array by the specified number of semitones via
     * repeated calls to the transpose() method in the Note class.
    */
    public void transpose(int semitones) {
        for (int i = 0; i < notes.length; i++) {
            notes[i].transpose(semitones);
        }
    }
    
    /*
     * This method prints the contents of the
     * voice class to the specified output file
     * via a call to the toString() method.
    */
    public void toStream(PrintStream ps) {
        ps.print(toString());
    }
    
    /*
     * This method converts each note in the notes
     * array to one huge string via repeated calls to the
     * toString() method in the Note class. This string is
     * then concatenated with the other contents of the voice object
     * to be outputted to the file.
    */
    public String toString() {
        String noteCollection = notes[0].toString() + "\n";
        for (int i = 1; i < notes.length; i++) {
            noteCollection += notes[i].toString() + "\n";
        }
        return instrument + "\n" + "notes " + notes.length + "\n" + noteCollection;
    }
}           