/*
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: December 1st 2016
 * Filename: Note.java
 * Details: CSC110 Assignment 7
*/

/*
 * The class Note is used as
 * the basis for creating note
 * objects based on what is read from
 * the input file. Each note object consists
 * of a pitch and duration.
*/
public class Note {
    private String pitch;
    private String duration;
    private static final String[][] transposeTable = { {"b", "c"},  
    {"c", "d_"}, {"c", "c^"}, {"c^", "d"}, {"d_", "d"}, {"d", "d^"}, {"d", "e_"}, 
    {"d^", "e"}, {"e_", "e"}, {"e", "e^"}, {"e_", "f_"}, {"e", "f"}, {"e^", "f^"}, 
    {"f", "f^"}, {"f", "g_"}, {"f^", "g"}, {"g", "g^"}, {"g", "a_"}, {"g^", "a"}, 
    {"a_", "a"}, {"a", "a^"}, {"a", "b_"}, {"a^", "b"}, {"b_", "b"}, };
    private int octave;
    
    /*
     * This constructor creates a note
     * object to be stored in the array in
     * the voice class. The tone and octave are
     * dealt with separately to make the transposition
     * easier. Rests are given an arbitrary octave number
     * so that they have the same properties as a standard note.
    */
    public Note(String pitch, String duration) {
        if (pitch.equals("r")) {
            this.pitch = pitch;
            octave = -1;
        } else if (pitch.charAt(1) == '_' || pitch.charAt(1) == '^') {
            this.pitch = pitch.substring(0,2);
            octave = Character.getNumericValue(pitch.charAt(2));
        } else {
            this.pitch = pitch.substring(0,1);
            octave = Character.getNumericValue(pitch.charAt(1));
        }
        this.duration = duration;
    }
    
    /* 
     * This method transposes each note
     * object by the specified number of 
     * semitones through repeated calls to the
     * transposeUp() or transposeDown() method 
     * depending on if the number is positive or negative.
    */
    public void transpose(int semitones) {
        if (semitones < 0) {
            for (int i = -1; i >= semitones; i--) {
                transposeDown();
            }
        } else if (semitones > 0) {
            for (int i = 1; i <= semitones; i++) {
                transposeUp();
            }
        }
    }
    
    /*
     * This method is only called if the
     * number of semitones is positive. Each
     * element in the first column of the transposeTable array 
     * is searched one by one until the pitch is found.
     * This pitch is then converted to the pitch adjacent
     * to it (in this case, to its right). If the new pitch
     * is a 'c', the octave number increases.
    */
    private void transposeUp() {
        for (int i = 0; i < transposeTable.length; i++) {
            if (pitch.equals(transposeTable[i][0])) {
                pitch = transposeTable[i][1];
                if (pitch.equals("c")) {
                    octave++;
                }
                break;
            }
        }
    }
    
    /*
     * This method works largely the same as
     * the transposeUp() method, but it is only called
     * if the number of semitones is negative, it searches
     * each element in the array's second column, and each 
     * pitch is converted to the one on its left. If 
     * the new pitch is a 'b', the octave number decreases.
    */
    private void transposeDown() {
        for (int i = 0; i < transposeTable.length; i++) {
            if (pitch.equals(transposeTable[i][1])) {
                pitch = transposeTable[i][0];
                if (pitch.equals("b")) {
                    octave--;
                }
                break;
            }
        }
    }
    
    /*
     * This method returns a string
     * representation of each note object
     * to be stored in the specified output file.
     * While a rest object has an octave number, this
     * is not shown in the string since they do not 
     * technically have octaves.
    */
    public String toString() {
        if (pitch.equals("r")) {
            return pitch + duration;
        }
        return pitch + octave + duration;
    }
}