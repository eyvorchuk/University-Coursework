import re

class Kwic:

    def __init__(self, excluded, lines):
        self.excluded = excluded
        self.index_lines = lines

    def check(self,word,this_list):
        ''' Check if the word being examined
        is in one of the exclusion lists.
        '''
        pattern = re.compile(r'\b'+word+r'\b') # \b to avoid matching substrings
        for item in this_list:
            if (pattern.match(item)): # Word is in the list
                return True
        return False

    def get_words(self):
        ''' Return the sorted list of
        words to be indexed.
        '''
        word_list = []
        for line in self.index_lines:
            for word in line.split(" "):
                word = word.lower() # Upper and lower-cased words are considered the same
                include = not self.check(word,self.excluded) and not self.check(word,word_list)
                if (include):
                    word_list.append(word)
        word_list.sort()
        return word_list

    def formatBegin(self,begin):
        ''' Edit the substring before the
        indexed word so that it has the right
        number of spaces and has an appropriate length.
        '''
        while (len(begin) > 20): # Too many characters before position 30
            begin = re.sub('^[^\s]*\s', "", begin) # Make the very first word and space after it an empty string
        spaces = 29 - len(begin) # So that indexed word appears at position 30
        if (len(begin) == 0):
            begin = " " * 29
        else:
            begin = re.sub(r'^\b', " " * spaces, begin) # Places spaces at very beginning of string
        return begin

    def formatEnd(self,end,word):
        ''' Edit the substring after the
        indexed word so that it has the appropriate length.
        '''
        while(len(end)+len(word) > 31): # Too many characters after position 30
            end = re.sub('\s[^\s]*$', "", end) # Make the very last word and space before it an empty string
        return end

    def formatLine(self, line, word):
        ''' Format the line so that it appears
        as desired in the output.
        '''
        matchobj = re.match(r'(.*)(\b'+word+r'\b)(.*)', line, re.IGNORECASE) # Separate into three groups
        (begin, cap, end) = matchobj.groups()
        begin = self.formatBegin(begin);
        cap = cap.upper() # Indexed word
        end = self.formatEnd(end,word)
        formattedLine = "%s%s%s" % (begin, cap, end)
        return formattedLine

    def output(self):
        ''' Return the list of formatted
        lines to be outputted.
        '''
        formatted = []
        index_words = self.get_words()
        for word in index_words:
            for line in self.index_lines:
                if re.search(r'\b'+word+r'\b', line, re.IGNORECASE):
                    formatted.append(self.formatLine(line,word))
        return formatted
