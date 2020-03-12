#include <stdio.h>
#include <string.h>
#define MAX_LINE_LEN 256

int largest(char *line) {
    char *t;
    int max = 0;

    if (line == NULL || line[0] == '\0') {
        return 0;
    }

    t = strtok(line, " ,.");
    while (t) {
        if (strlen(t) > max) {
            max = strlen(t);
        }
        t = strtok(NULL, " ,.");
    }

    return max;
}


int main() {
    char buffer[MAX_LINE_LEN];
    char *s = NULL;
    int len;

    len = largest(s);
    printf("That weirdo call was %d long\n", len);

    strncpy(buffer, ".bow.wow.", MAX_LINE_LEN);
    len = largest(buffer);
    printf("The largest word in '%s' is %d characters long\n",
        buffer, len);

    strncpy(buffer, "abc defg hi", MAX_LINE_LEN);
    len = largest(buffer);
    printf("The largest word in '%s' is %d characters long\n",
        buffer, len);

    strncpy(buffer, "Here is one shortish line.", MAX_LINE_LEN);
    len = largest(buffer);
    printf("The largest word in '%s' is %d characters long\n",
        buffer, len);

    strncpy(buffer, "Because Americans are dreamers, too.", MAX_LINE_LEN);
    len = largest(buffer);
    printf("The largest word in '%s' is %d characters long\n",
        buffer, len);
}
