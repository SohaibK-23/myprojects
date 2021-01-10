#include <queue>
#include <iostream>
#include "chunks.h"
#include <string>
#include <cctype>
#include <algorithm>
#include <bits/stdc++.h>

using namespace std;
//constructor for creating the object of the word chunk class
wordChunk::wordChunk()
{
    nsequence = 6; //If no size is assigned, then set 6 as a default
}

//parameterized constructor for the object of the word chunk class
//Making sure that n is a valid integer
wordChunk::wordChunk(int n)
{
    if(n <= 0) 
    {
      nsequence = 6;
      return;
    }

    nsequence = n;
}

//Divides the filewords vector which contains all the words in the line into n sequence words
int wordChunk::nword(vector<string> &filewords, queue<string> &chunks)
{
    int currentIndex = 0;        //set the current index to zero
    int lastIndex = nsequence - 1;
    int mov_index;               //The mov_index is needed to traverse through the words needed to be sequenced

    int numofwords = filewords.size();    //number of words in a line
    string temp;

    vector<string> chunkVect;  //stores the n-sequence words for a particular line of words stored in filewords

    if(filewords.size() < nsequence){
        return FAIL;    //the only time it will return a failure is when the number of words in a given line is less than the number of words to sequence the line
    }

    while(lastIndex != numofwords)       //while the last index doesn't go out of bounds while creating n-sequences
    {
        for(mov_index = currentIndex; mov_index < (lastIndex + 1); mov_index++)
        {
            chunkVect.push_back(filewords[mov_index]);
        }

        temp = concatenate(chunkVect);   //concatenate the n-word sequence
        chunks.push(temp);  //Push it onto the queue
        chunkVect.clear();   //clearing the current n-word sequence off the chunkvector

        currentIndex++;  //incrementing the current index
        lastIndex++;     //incrementing the last index

    }

    return SUCCESS;
}

string wordChunk::concatenate(const vector<string> &chunkvect)
{
    string chunk = "";
    for(int i = 0; i < chunkvect.size(); i++)
    {
        chunk = chunk + chunkvect[i];         //add to the chunk string
    }

    return chunk;
}


string wordChunk::isNotAlphaNumeric(string word)
{

    //convert the string word to lowercase first
    string lower = lowerCase(word);

    int i = 0;
    string temp = lower;        //setting temp equal to the word that we want to check whether it is alphanumeric
    int len = temp.length();

    while(i < len){            //while the index i is less than the length of the string temp, if the character in the string
                               //is not alphanumeric, then we erase it and decrease the length of the string
        if(!isalnum(temp[i])){
            temp.erase(i,1);
            len--;
        }
        else{
            i++;              //Otherwise, we keep traversing through thee array
        }
    }

    return temp;   //return the alphanumeric string
}

string wordChunk::lowerCase(string lower_case_string)
{
    transform(lower_case_string.begin(), lower_case_string.end(), lower_case_string.begin(), ::tolower);
    return lower_case_string;
}

wordChunk::~wordChunk() {}
