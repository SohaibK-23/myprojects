~Mary and Sohaib's Cheater Checker~

Files Included
--- 
makefile
plagiarismCatcher.cpp
HashTable.cpp
HashTable.h
chunks.cpp
chunks.h

To Run
---
Navigate to the directory containing the checker program files. We've included a makefile so just type 'make' and enter and it should compile.

Then type:
./plagiarismChecker [folder_name] [word_chunks] [similarity_threshold]

[folder_name] should be a folder of the files you're checking for plagiarism located in the same place as your executable. Don't include any slashes or characters other than the name of the folder itself.

[word_chunks] is the number of consecutive words that have to match for a chunk to be considered plagiarism.

[similarity_threshold] is the number of matching chunks two files must contain to be shown as plagiarizing.

The program should return a list of file pairs preceded by the number of matching word chunks above the threshold outlined in the command-line arguments.