// Name: Eric Yvorchuk

#include <ctype.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_EXCL_WORDS 20
#define MAX_WORD_LEN 21 // Extra for null character
#define MAX_LINES 100
#define MAX_WORDS 100
#define MAX_LINE_LEN 71

/* Checks if a scanned word is part of
	the exclusion array. If not, it will be
	copied into the words array provided
	it is not already present.
*/
int checkExcl(char *word, int excl_count, char excl[MAX_WORDS][MAX_WORD_LEN]) {
	for (int i = 0; i < excl_count; i++) {
		if (strncmp(word, excl[i], MAX_WORD_LEN) == 0) {
			return 1;
		}
	}
	return 0;
}

/* Sorts the words array by alphabetical
	order to capitalize them in the lines
	in the proper order.
*/
void bubbleSort(int word_count, char words[MAX_WORDS][MAX_WORD_LEN]) {
	for (int i = 0; i < word_count; i++) {
		for (int j = 0; j < word_count - 1; j++) {
			char *word1 = words[j];
			char *word2 = words[j + 1];
			if (strncmp(word1, word2, MAX_WORD_LEN) > 0) {
				char temp[MAX_WORD_LEN];
				strncpy(temp, word1, MAX_WORD_LEN);
				strncpy(word1, word2, MAX_WORD_LEN);
				strncpy(word2, temp, MAX_WORD_LEN);
			}
		}
	}
}

/* Checks if the word to be capitalized
	is in a given line.
*/
int containsWord(char *search_word, char line[MAX_LINE_LEN]) {
	char *curr_word = strtok(line, " ");
	while(curr_word) {
		if (strncmp(curr_word, search_word, MAX_WORD_LEN) == 0) {
			return 1;
		}
		curr_word = strtok(NULL, " ");
	}
	return 0;
}

/* Prints out the contents of a line
	with the necessary word capitalized.
	Only called when word is in the line.
*/
void capitalize(char *search_word, char line[MAX_LINE_LEN]) {
	char *curr_word = strtok(line, " ");
	int printed_words = 0;
	while (curr_word) {
		if (strncmp(curr_word, search_word, MAX_WORD_LEN) == 0) {
			char temp[MAX_WORD_LEN];
			strncpy(temp, search_word, MAX_WORD_LEN);
			for (int j = 0; j < strlen(temp); j++) {
				temp[j] = toupper(temp[j]);
			}
			if (printed_words == 0) {
				printf("%s", temp);
			} else {
				printf(" %s", temp); // Avoid extra space at the end of the line.
			}
		} else if (printed_words == 0) {
			printf("%s", curr_word);
		} else {
			printf(" %s", curr_word);
		}
		curr_word = strtok(NULL, " ");
		printed_words++;
	}
	printf("\n");
}


int main(int argc, char const *argv[]) {
	char curr_word_array[MAX_WORD_LEN];
	char *curr_word = curr_word_array;
	char excl[MAX_EXCL_WORDS][MAX_WORD_LEN];
	char words[MAX_WORDS][MAX_WORD_LEN], lines[MAX_LINES][MAX_LINE_LEN];
	for (int i = 1; i <= 3; i++) { // Get past first number and first colon pair.
		scanf("%s\n", curr_word);
	}
	int excl_count = 0;
	while (strncmp(curr_word, "::", 2) != 0) { // Marks end of exclusion words.
		strncpy(excl[excl_count], curr_word, MAX_WORD_LEN);
		excl_count++;
		scanf("%s\n", curr_word);
	}
	int word_count = 0;
	int line_count = 0;
	while (fgets(lines[line_count], MAX_LINE_LEN, stdin)) {
		char copy_line[MAX_LINE_LEN];
		strncpy(copy_line, lines[line_count], MAX_LINE_LEN);
		char *curr_line = copy_line;
		curr_line[strlen(curr_line) - 1] = '\0'; // Get rid of new line character.
		curr_word = strtok(curr_line, " ");
		while(curr_word) {
			if (!checkExcl(curr_word, excl_count, excl) && !checkExcl(curr_word, word_count, words)) {
				// The second condition checks for possible duplicates.
				strncpy(words[word_count], curr_word, MAX_WORD_LEN);
				word_count++;
			}
			curr_word = strtok(NULL, " ");
		}
		line_count++;
	}
	bubbleSort(word_count, words);
	for (int i = 0; i < word_count; i++) {
		for (int j = 0; j < line_count; j++) {
			char copy_line[MAX_LINE_LEN];
			strncpy(copy_line, lines[j], MAX_LINE_LEN);
			char *curr_line = copy_line;
			curr_line[strlen(curr_line) - 1] = '\0';
			if (containsWord(words[i], curr_line)) {
				strncpy(copy_line, lines[j], MAX_LINE_LEN);
				curr_line[strlen(curr_line) - 1] = '\0';
				capitalize(words[i], curr_line);
			}
		}
	}
	exit(0);
}
