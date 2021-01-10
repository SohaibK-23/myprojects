//
// Created by khans on 5/1/2019.
//
#include <queue>
#include <string>

#ifndef LASTLAB_HASHTABLE_H
#define LASTLAB_HASHTABLE_H

using namespace std;
class HashTable
{
private:
   int TableSize;

/* This is the struct that will contain the file that is mapped from each n-word sequence and its index to determine where it belongs in the vector of files
 */ 
    struct filenode
    {
        string file_type;
        int file_num;
        filenode* next;
    };

    struct file_pairs
    {
        int similarities;
        string file1;
        string file2;
    };


//Creating a hash table by using a vector of pointers where each pointer is going to point to the filenode
   vector<filenode*> hashtable;
   vector<file_pairs> ordering_similarities;


public:

    HashTable();  //Default constructor
    ~HashTable(); //Destructor

/* PRE: The hash function passes a key which is the n-sequence word chunk stored in the queue for a certain line in a file
 * POST: It will return an index to the hash table by asome operation described in the function
 */
    int Hashfunction(string key);

/* PRE: Passing a certain file and its index to see where it belongs in the vector of files and the index obtained from the hash function
 * to see at which entry of the hash table we are going to start the chaining process (Create a Linked List)
 * POST: Maps a n-sequence word from the queue for a line to the hash table to specify from which text file the n-sequence word is coming from
 */ 
    void Hashit(string file_type, int index_to_file, int index_hash);

/* PRE: After we have created a Hash table, we go through each entry of the hash table and determine whether there are similarities or collisions between the files
 * POST: Created a 2D Array that will show the number of collisions between each pair of files in the directory
 */
    void createMatrix(HashTable h, vector<vector<int> > &num_of_similarities);

/* PRE: Displaying the results by passing the 2D Array created by the createMatrix function and the vector of files to specify what sets of files have 
 * n-word sequences that are greater than the limit 
 * POST: Displays the similarity results between all the files in the given directory
 */

    void placecollisionresults(const vector<vector<int> > &num_of_similarities, const vector<string> &files, int limit);
/* By passing the vector of structs ordering_similarities, we are able to sort the number of similarities between each files
 * from descending to ascending order.
*/
    void SortCollisions();

/* Runs through vector of similarities, printing number of collisions and the pair of file names to the console after they've been sorted by SortCollisions. */

    void displayCollisions();



    int getTableSize()
    {
        return TableSize;   //returns the table size
    }

//Enter a valid size
    void setTableSize(int size)
    {
        TableSize = size;   //sets the table size
    }




};


#endif //LASTLAB_HASHTABLE_H
