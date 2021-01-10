//
// Created by khans on 5/1/2019.
//

#include "HashTable.h"
#include <iterator>
#include <iostream>
#include <cmath>

using namespace std;

HashTable::HashTable()
{
    setTableSize(100001);  //Creating a hash table of over 1000000 entries so that there are less collisions

    for(int i = 0; i < getTableSize(); i++)
    {
        hashtable.push_back(NULL);  //Setting all the pointers to th node NULL
    }

}

int HashTable::Hashfunction(string key)
{
    unsigned long int result = 0;
    int SCALAR_NUM = 1;   //Scaling factor used to create equal distribution among the n-word sequences mapped to the hash table


    for(int index = 0; index < key.length(); index++ )
    {
        result = result + key[index] * SCALAR_NUM;
        SCALAR_NUM *= 7;
    }

    result = result % getTableSize();
    return result;
}

void HashTable::Hashit(string file_type, int index_to_file, int index_hash)
{
    filenode* head = NULL;
    filenode* temp = hashtable[index_hash];

    if(temp == NULL) {
        temp = new filenode;
        temp->file_type = file_type;   //specifying the file that the chunk came from
        temp->file_num = index_to_file;
        temp->next = hashtable[index_hash];
        hashtable[index_hash] = temp;
    }
    else{
        head = new filenode;    //updates the head pointer
        head -> file_type = file_type;
        head -> file_num = index_to_file;
        head -> next = temp;
        hashtable[index_hash] = head;
    }

}

void HashTable::createMatrix(HashTable h, vector<vector<int> > &similarity_matrix)
{
    filenode* p = NULL;    //Setting p and q to be NULL
    filenode* q = NULL;
    for(int entry = 0; entry < TableSize; entry++){
        p = hashtable[entry];    //For every entry in the hash table, look at the amount of nodes in the linked list
        while(p != NULL)         //Determine the simlarities or collisions between each pair of filenodes for every node. Except with itself of course
        {
            q = p -> next;
            while(q != NULL)
            {
                if(p -> file_num > q -> file_num){
                    similarity_matrix[q -> file_num][p -> file_num]++;   //For every collision increment the simlarity or collision count at the entry specified 
                    //cout << p -> file_type << "and " << q -> file_type << endl;
                    q = q -> next;     //by the index file_num in the matrix. Look at the next file and compare it with the one being pointed by p
                }
                else if(p -> file_num == q -> file_num){
                    q = q -> next;
                }
                else {
                    similarity_matrix[p->file_num][q->file_num]++;
                    //cout << p -> file_type << "and " << q -> file_type << endl;
                    q = q->next;
                }
            }

            p = p -> next;  //Update the pointer to move on to the next file to compare to
        }
    }
}

void HashTable::placecollisionresults(const vector<vector<int> > &similarity_matrix, const vector<string> &files, int limit)
{
    int count = 0;
    file_pairs set;
    for(int file1 = 0; file1 < similarity_matrix.size(); file1++)
    {
        for(int file2 = file1 + 1; file2 < similarity_matrix.size(); file2++){

            if(similarity_matrix[file1][file2] >  limit)
            {
                set.similarities = similarity_matrix[file1][file2];
                set.file1 = files[file1];
                set.file2 = files[file2];
                ordering_similarities.push_back(set);
                count++;  //Number of collisions in the hash table
            }
        }
    }

    cout << "Number of collisions in the hash table: " <<  count << endl;

}

void HashTable::SortCollisions()
{
   file_pairs temp;

   for(int i = 0; i < ordering_similarities.size(); i++)
   {
       for(int k = i + 1; k < ordering_similarities.size(); k++)
       {
           if(ordering_similarities[i].similarities < ordering_similarities[k].similarities)
           {
               temp = ordering_similarities[i];
               ordering_similarities[i] = ordering_similarities[k];
               ordering_similarities[k] = temp;
           }
       }
   }

   displayCollisions();

}

void HashTable::displayCollisions()
{
    file_pairs show_info;
    for(vector<file_pairs> :: iterator show = ordering_similarities.begin(); show != ordering_similarities.end(); show++)
    {
        show_info = *show;
        cout << show_info.similarities << ": " << show_info.file1 << " and " << show_info.file2 << endl;
    }
}


//destructor for the hash table
HashTable:: ~HashTable()
{
 
}







