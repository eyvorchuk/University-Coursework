#!/usr/bin/env python3

import re

line = "verzierten sofa dessen polster hellgelb ueberzogen waren warf einen"
while(len(line) > 30):
    print(line)
    line = re.sub('\s[^\s]*$', "", line)
print(line)
