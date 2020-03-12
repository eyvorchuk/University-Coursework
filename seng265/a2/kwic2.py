#!/usr/bin/env python3

# Student Name: Eric Yvorchuk

from sys import stdin

def initializeLists(word_list, lines):
    """Creates the lists of indexed words
    and lines to be used for determining
    the output.
    """
    for i in range(2):
        stdin.readline() # Ignores version number and first colon
    excl_list = []
    excl_word = stdin.readline().strip() # Remove new line character at end
    while (excl_word != "::"): # Marks end of exclusion list
        excl_list.append(excl_word.lower())
        # So that capital and lower-cased versions are considered the same
        excl_word = stdin.readline().strip()
    lines = [line.strip() for line in stdin]
    for line in lines:
        for word in line.split():
            if word.lower() not in word_list and word.lower() not in excl_list:
                # The first condition checks for possible duplicates
                word_list.append(word.lower())
    word_list.sort() # Process indexed words in alphabetical order
    return word_list, lines

def formatLine(key_word, key_index,divided_line):
    """Prints the line containing the
    indexed word where the indexed word is
    capitalized at position 30, and no output
    appears below position 10 and after position 60
    """
    start = key_index # Keeps track of where to begin output
    end = key_index # Keeps track of where to end output
    chars_before = 0
    chars_after = len(key_word) - 1
    """The while loop below determines what words are included before the indexed word.
    The '+ 1' is used because of the extra space after each word.
    The 'start - 1' is used in case adding the word before would cause too many characters.
    Since output appears no earlier than position 10, there are at most 20 characters
    before the indexed word.
    """
    while (start > 0 and chars_before + len(divided_line[start - 1])  + 1 <= 20):
        start -= 1
        chars_before += len(divided_line[start]) + 1
    """The while loop below determines what words are included after the indexed word.
    The 'end + 1' is used in case adding the word after would cause too many characters.
    Since output appears no later than position 60, there are at most 30 characters after
    the indexed word.
    """
    while (end < len(divided_line) - 1 and chars_after + len(divided_line[end + 1]) + 1 <= 30):
        end += 1
        chars_after += len(divided_line[end]) + 1
    divided_line[key_index] = divided_line[key_index].upper()
    reduced_line = divided_line[start:end + 1]
    spaces = ' ' * (29 - chars_before) # Ensures the indexed word appears at position 30
    print(spaces + " ".join(reduced_line))

def main():
    word_list, lines = initializeLists([], [])
    for word in word_list:
        for line in lines:
            key_index = 0 # Used to determine where indexed word is in line
            line = line.split()
            for search in line: # Used in case of finding capital letter
                if (word == search.lower()):
                    formatLine(word, key_index, line)
                    break # An indexed word only appears once per line
                else:
                    key_index += 1
if __name__ == "__main__":
    main()
