#include <stdio.h>
#include <string.h>
#include <strings.h> // For use of strcasecmp.
#include <stdlib.h>
#include <ctype.h>
#include "linkedlist.h"

#define MAX_CHARS_BEFORE 20
#define MAX_CHARS_AFTER 30
#define MAX_SPACES 29

/* This is an alternate solution that
does not use regexes. This exists so that
it works on the lab machines properly. */

wordnode_t* createExcl(wordnode_t* excl, size_t len) {
  /* Creates the list of excluded words. */
  char* excl_word = NULL;
  getline(&excl_word, &len, stdin);
  while (strncmp(excl_word, "::\n", len)) { // :: has not been found.
    excl_word[strlen(excl_word) - 1] = '\0'; // Get rid of new line character.
    excl_word[0] = tolower(excl_word[0]);
    excl = add_end(excl, new_word(excl_word));
    getline(&excl_word, &len, stdin);
  }
  return excl;
}

int check(wordnode_t* list, char* word) {
  /* Checks whether or not a word is in a given list. */
  for (wordnode_t* curr = list; curr; curr = curr->next) {
    if (!strcmp(curr->w, word)) { // Word has been found.
      return 1;
    }
  }
  // At this point, the word is not in the list.
  return 0;
}

wordnode_t* insertionSort(wordnode_t* list, wordnode_t* new) {
  /* Continuously sorts the list of inclusion words
  after a new word is added. */
  if (list == NULL) {
    return add_front(list, new);
  }
  wordnode_t* prev = NULL; // Keep track of where the previous node's next pointer should go.
  for (wordnode_t* curr = list; curr; curr = curr->next) {
    if (strcmp(curr->w, new->w) > 0) { // The new node should come before the current node.
      if (prev == NULL) { // Currently at beginning of list.
        return add_front(list, new);
      }
      new->next = curr;
      prev->next = new;
      return list;
    }
    if (prev == NULL) {
      prev = list;
    } else {
      prev = prev->next;
    }
  }
  // At this point, the word must go at the end of the list.
  return add_end(list, new);
}

wordnode_t* createWords(wordnode_t* words, size_t len, wordnode_t* excl, wordnode_t** lines) {
  /* Creates the lists of inclusion words and lines. */
  char* line = NULL;
  while (getline(&line, &len, stdin) != -1) { // Terminates at end of file.
    line[strlen(line) - 1] = '\0';
    *lines = add_end(*lines, new_word(line));
    for (char* curr = strtok(line, " "); curr; curr = strtok(NULL, " ")) {
      for (int i = 0; i < strlen(curr); i++) {
        curr[i] = tolower(curr[i]); // In case a word has multiple capital letters.
      }
      if (!check(excl, curr) && !check(words, curr)) { // Second condition checks for duplicates.
        words = insertionSort(words, new_word(curr));
      }
    }
  }
  return words;
}

void createLists(wordnode_t** word_list, wordnode_t** excl_list, wordnode_t** lines) {
  /* Serves as the starting point for creating the
  necessary lists. */
  char* line = NULL;
  size_t len = 0;
  for (int i = 1; i <= 2; i++) { // Ignore version number and first colon pair.
    getline(&line, &len, stdin);
  }
  *excl_list = createExcl(*excl_list, len);
  *word_list = createWords(*word_list, len, *excl_list, lines);
}

char* formatBegin(char* begin) {
  /* Alter the contents before the
  search word so that it has the proper length. */
  while(strlen(begin) > MAX_CHARS_BEFORE) { // Too many characters before search word.
    char* new_begin = strchr(begin, ' '); // Remove first word and first space.
    begin = new_begin + 1; // +1 to begin after first space.
  }
  return begin;
}

char* capitalize(char* word) {
  /* Capitalizes the search word. */
  for (int i = 0; i < strlen(word); i++) {
    word[i] = toupper(word[i]);
  }
  return word;
}

char* formatEnd(char* end, char* word) {
  /* Alters the contents after the search
  word so that it has the appropriate length. */
  char* old_end = (char*)emalloc(sizeof(char)*(strlen(end) + 1)); // Used to change ending string.
  while(strlen(word) + strlen(end) - 1 > MAX_CHARS_AFTER) { /* Too many characters after search word.
    -1 because of ignoring search word's first character */
    strncpy(old_end, end, strlen(end));
    strncpy(end, "", 1);
    int last_word_length = 0; // Determine how many characters to remove.
    for (char* new_end = strtok(old_end, " "); new_end; new_end = strtok(NULL, " ")) {
      strncat(end, " ", 1);
      strncat(end, new_end, strlen(new_end));
      last_word_length = strlen(new_end); 
    }
    end[strlen(end) - last_word_length - 1] = '\0'; // Get rid of very last word and last space.
  }
  free(old_end);
  return end;
}

void separate(char* line_copy, char** begin, char* search, char** end) {
  /* Seprate the line into its respective groups. */
  strncpy(*begin, "", 1); // Initialize to empty string.
  for (char* word_before = strtok(line_copy, " "); word_before; word_before = strtok(NULL, " ")) {
    if (!strcasecmp(word_before, search)) { // Begin adding to end string.
      strncpy(*end, "", 1);
      for (char* word_after = strtok(NULL, " "); word_after; word_after = strtok(NULL, " ")) {
        strncat(*end, " ", 1);
        strncat(*end, word_after, strlen(word_after));
      }
      break;
    } else {
      strncat(*begin, word_before, strlen(word_before));
      strncat(*begin, " ", 1);
    }
  }
}

void format(char* line, char* search) {
  /* Serves as the basis for formatting the
  line properly. */
  char* line_copy = (char*)calloc(strlen(line) + 1, sizeof(char));
  char* begin = (char*)emalloc(sizeof(char)*(strlen(line))); // Stores temporary contents before search word.
  char* end = (char*)emalloc(sizeof(char)*(strlen(line))); // Stores temporary contents after search word.
  strncpy(line_copy, line, strlen(line));
  separate(line_copy, &begin, search, &end);
  char* formatted_begin = formatBegin(begin);
  // Print the appropriate number of spaces.
  for (int i = 1; i <= MAX_SPACES - strlen(formatted_begin); i++) {   
    printf("%s", " ");
  }
  search = capitalize(search);
  char* formatted_end = formatEnd(end, search);
  printf("%s%s%s\n", formatted_begin, search, formatted_end);
  free(line_copy);
  free(begin);
  free(end);
}

void search(wordnode_t* words, wordnode_t* lines) {
  /* Begin formatting process by searching
  for every inclusion word in every line. */
  for (wordnode_t* search = words; search; search = search->next) {
    for (wordnode_t* line = lines; line; line = line->next) {
      char* line_copy = (char*)calloc(strlen(line->w) + 1, sizeof(char)); // To avoid altering original line via strtok.
      strncpy(line_copy, line->w, strlen(line->w));
      for (char* curr = strtok(line_copy, " "); curr; curr = strtok(NULL, " ")) {
        if (!strcasecmp(search->w, curr)) { // Word is in line.
          format(line->w, curr);
          break;
        }
      }
      free(line_copy);
    }
  }
}

void freeAll(wordnode_t* list) {
  /* Free the contents of all nodes
  in a list. */
  while (list) {
    wordnode_t* temp = peek_front(list);
    list = remove_front(list);
    free_word(temp);
  }
}

int main(int argc, char *argv[]) {
    wordnode_t *words = NULL;
    wordnode_t *excl = NULL;
    wordnode_t *lines = NULL;
    createLists(&words, &excl, &lines);
    freeAll(excl); // Don't need exclusion words for formatting.
    search(words, lines);
    // Completed everything.
    freeAll(words);
    freeAll(lines);
    exit(0);
}
