//the wordchunk class created by Mary Graham and Sohaib Khan


# ifndef LASTLAB_WORDCHUNKS_H
# define LASTLAB_WORDCHUNKS_H

#include <string>
#include <queue>

using namespace std;

class wordChunk {

private:
    int nsequence;   //Determines the size of the sequence
    static const int SUCCESS = 1;
    static const int FAIL = 0;

public:

    wordChunk();                //Default constructor
    wordChunk(int nsequence);   //Parametrized constructor
    
/*  PRE: This function is called when we pass a vector of words for a line in a file and store as many n-sequence chunks as possible 
 *  in the queue
 *  POST: The function will return a full queue of the possible n-word sequences in a certain line of a file. 
 *  The function is successful if the number of words in a line are greater than n. Otherwise, it will fail and return.
 */
    int nword(vector<string> &filewords, queue<string> &chunks);

/* PRE: This function is used to concatenate the possible n-sequence words formed into a single string before it is placed on the queue.
 * POST: Returns a n-sequence string with no spaces
 */
    string concatenate(const vector<string> &chunkvect);

/* PRE: This function will turn a non-alphanumeric word from a particular line in the file to a alphanumeric word.
 * POST: Returns a alphanumeric word for a certain line in a file
 */

    string isNotAlphaNumeric(string word);

/* PRE: This function is called in isNotAlphaNumeric function to make the word lowercase first
 * POST: Simply returns a lowercase word
 */
    string lowerCase(string lower_case_string);

/* Destructor for the wordChunk object */

    ~wordChunk();


};




#endif  //LASTLAB_WORDCHUNKS_H
