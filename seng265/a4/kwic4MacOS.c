#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <regex.h>
#include "linkedlist.h"

#define MAX_CHARS_BEFORE 20
#define MAX_CHARS_AFTER 30
#define MAX_SPACES 29

/* This solution stores the individual
lines as strings in a linked list to make
regexes easier. As such, the printword method
has been removed. Also, the emalloc method has been
added to the "linkedlist.h" file and some additional flags
have been added to the makefile to eliminate errors and warnings. */

/* Since the regex module is out of date on the lab machines, the word
boundary patterns will not compile. As such, an additional version of the
solution (without regexes) is also in this directory. */

void compilePattern1(regex_t* re, char* matcher) {
  /* Creates the pattern to look for an
  individual word. */
  int size = 2 * strlen("[[:<:]]"); // For word boundary.
  size += strlen(matcher);
  char* pattern = (char*)emalloc(sizeof(char)*size);
  strncat(pattern, "[[:<:]]", strlen("[[:<:]]"));
  strncat(pattern, matcher, strlen(matcher));
  strncat(pattern, "[[:>:]]", strlen("[[:>:]]"));
  if (regcomp(re, pattern, REG_EXTENDED|REG_ICASE)) { // Treat upper and lower-case words as the same.
    fprintf(stderr, "Could not compile pattern\n");
    exit(1);
  }
  free(pattern);
}

void compilePattern2(regex_t* re, char* matcher) {
  /* Creates the pattern to separate a line
  to be formatted into three groups: the content
  before the search word, the search word itself,
  and the content after the search word. */
  int size = strlen("(.*)([[:<:]]");
  size *= 2;
  size += strlen(matcher);
  char* pattern = (char*)emalloc(sizeof(char)*size);
  strncat(pattern, "(.*)([[:<:]]", strlen("(.*)([[:<:]]"));
  strncat(pattern, matcher, strlen(matcher));
  strncat(pattern, "[[:>:]])(.*)", strlen("[[:>:]])(.*)"));
  if (regcomp(re, pattern, REG_EXTENDED|REG_ICASE)) {
    fprintf(stderr, "Could not compile pattern\n");
    exit(1);
  }
  free(pattern);
}

wordnode_t* createExcl(wordnode_t* excl, size_t len) {
  /* Creates the list of excluded words. */
  regex_t re;
  regmatch_t match[1];
  char* excl_word = NULL;
  regcomp(&re, "::\n", REG_EXTENDED); // Determines end of exclusion list.
  getline(&excl_word, &len, stdin);
  while (regexec(&re, excl_word, 1, match, 0)) { // :: has not been found.
    excl_word[strlen(excl_word) - 1] = '\0'; // Get rid of new line character.
    excl_word[0] = tolower(excl_word[0]);
    excl = add_end(excl, new_word(excl_word));
    getline(&excl_word, &len, stdin);
  }
  regfree(&re);
  return excl;
}

int check(wordnode_t* list, char* word) {
  /* Checks whether or not a word is in a given list. */
  regex_t re;
  regmatch_t match[1];
  compilePattern1(&re, word);
  for (wordnode_t* curr = list; curr; curr = curr->next) {
    if (!regexec(&re, curr->w, 1, match, 0)) { // Word has been found.
      regfree(&re);
      return 1;
    }
  }
  // At this point, the word is not in the list.
  regfree(&re);
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
        curr[i] = tolower(curr[i]); // In case word has multiple capital letters.
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
  regex_t re;
  regmatch_t match[3]; // Second group is the very first word and space afterward.
  char* pattern = "(^[^[[:space:]]*[[:space:]])(.*)";
  if(regcomp(&re, pattern, REG_EXTENDED)) {
    fprintf(stderr, "Failed to compile\n");
    exit(1);
  }
  char* old_begin = (char*)emalloc(sizeof(char)*(strlen(begin) + 1)); // Used to change beginning string.
  while(strlen(begin) > MAX_CHARS_BEFORE) { // Too many characters before search word.
    regexec(&re, begin, 3, match, 0);
    int new_length = match[2].rm_eo - match[2].rm_so;
    strncpy(old_begin, begin, strlen(begin));
    strncpy(begin, old_begin + match[2].rm_so, new_length); // Remove very first word and space.
    begin[new_length] = '\0';
  }
  free(old_begin);
  regfree(&re);
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
  regex_t re;
  regmatch_t match[3]; // Last group is the very last word and space beforehand.
  char* pattern = "(.*)([[:space:]][^[[:space:]]*$)";
  if (regcomp(&re, pattern, REG_EXTENDED)) {
    fprintf(stderr, "Failed to compile\n");
    exit(1);
  }
  char* old_end = (char*)emalloc(sizeof(char)*(strlen(end) + 1)); // Used to change ending string.
  while(strlen(word) + strlen(end) - 1 > MAX_CHARS_AFTER) { /* Too many characters after search word.
    -1 because of ignoring search word's first character */
    regexec(&re, end, 3, match, 0);
    int new_length = match[1].rm_eo - match[1].rm_so;
    strncpy(old_end, end, strlen(end));
    strncpy(end, old_end + match[1].rm_so, new_length); // Remove very last word and space.
    end[new_length] = '\0';
  }
  free(old_end);
  regfree(&re);
  return end;
}

void format(char* line, char* curr) {
  /* Serves as the basis for formatting the
  line properly. */
  regex_t re;
  regmatch_t match[4];
  compilePattern2(&re, curr);
  regexec(&re, line, 4, match, 0);
  int begin_length = match[1].rm_eo - match[1].rm_so;
  char* begin = (char*)emalloc(sizeof(char)*(begin_length + 1)); // Stores contents before search word.
  strncpy(begin, line + match[1].rm_so, begin_length);
  begin[begin_length] = '\0';
  begin = formatBegin(begin);
  // Print the appropriate number of spaces
  for (int i = 1; i <= MAX_SPACES - strlen(begin); i++) { 
    printf("%s", " ");
  }
  curr = capitalize(curr);
  int end_length = match[3].rm_eo - match[3].rm_so;
  char* end = (char*)emalloc(sizeof(char)*(end_length + 1)); // Stores contents after search word.
  strncpy(end, line + match[3].rm_so, end_length);
  end[end_length] = '\0';
  end = formatEnd(end, curr);
  printf("%s%s%s\n", begin, curr, end);
  regfree(&re);
  free(begin);
  free(end);
}

void search(wordnode_t* words, wordnode_t* lines) {
  /* Begin formatting process by searching
  for every inclusion word in every line. */
  for (wordnode_t* search = words; search; search = search->next) {
    regex_t re;
    regmatch_t match[1];
    compilePattern1(&re, search->w);
    for (wordnode_t* line = lines; line; line = line->next) {
      char* line_copy = (char*)calloc(strlen(line->w) + 1, sizeof(char)); // To avoid altering original line via strtok.
      strncpy(line_copy, line->w, strlen(line->w));
      for (char* curr = strtok(line_copy, " "); curr; curr = strtok(NULL, " ")) {
        for (int i = 0; i < strlen(curr); i++) {
          curr[i] = tolower(curr[i]);
        }
        if (!regexec(&re, curr, 1, match, 0)) { // Word is in line.
          format(line->w, curr);
          break;
        }
      }
      free(line_copy);
    }
    regfree(&re);
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
