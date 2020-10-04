//
// Created by khans on 3/27/2019.
//
#include "UtPod.h"
#include "Song.h"
#include <iostream>
#include <math.h>
#include <ctime>
#include <cstdlib>

using namespace std;

//Default constructor
//set the memory size to MAX_MEMORY
UtPod:: UtPod()
{   songs = NULL;    //Setting the songs pointer to NULL.
    podMemSize = MAX_MEMORY; //Setting the memory size of the pod to Maximum memory
    memSize = 0;  //Set memSize to 0
    Seed();  //Initialize the Random Number generator
};
/*
UtPod::UtPod(const UtPod &t2) {
//copy the contents from the song linked list to the linked lists of the other object.
}
*/
//Constructor with size parameter
//The user of the class will pass in a size.
//If the size is greater than MAX_MEMORY or less than or equal to 0,
//set the size to MAX_MEMORY.
UtPod:: UtPod(int size){
    songs = NULL;     //Performing the same functions as the above constructor except we are inputting in a valid size to podMemSize.
    //podMemSize = MAX_MEMORY;
    memSize = 0;
    Seed();  //Initialize the random number generator

    if((size > MAX_MEMORY) || (size <= 0)){
        podMemSize = MAX_MEMORY;
    }
    else{
        podMemSize = size;
    }
};
/* FUNCTION - int addSong
     * attempts to add a new song to the UtPod
         o returns a 0 if successful
         o returns -1 if not enough memory to add the song

     precondition: s is a valid Song

     input parms - Passing in a song object with a valid title, artist, and size

     output parms - If it is successful, it returns a 0 otherwise it returns a -1.
    */
int UtPod:: addSong(Song const &s){
    SongNode *temp = NULL;      //
    if(s.getSize() > getRemainingMemory() || getRemainingMemory() < 0){
        return -1;     //If the size of the UtPod is not enough to add a new song, then return a -1/
    }

    temp = new SongNode;      //Create a new Song entry
    temp -> next = NULL;      //Set it's next pointer to NULL

    
    if(songs == NULL){        //If the head pointer is NULL, then you have an empty list
        songs = temp;
        songs -> s1.setTitle(s.getTitle());
        songs -> s1.setArtist(s.getArtist());
        songs -> s1.setSize(s.getSize());
        memSize = memSize + s.getSize();    //Add to the size of the UtPod memory
        return 0;
    }
    else{
        SongNode *p = songs;      //Otherwise, traverse to the end of the linked list by starting from the head pointer songs.

        while(p -> next != NULL){    //Go to the end of the linked list
            p = p -> next;
        }

        p -> next = temp;     //Insert a node at the end of the linked list
        temp -> s1.setTitle(s.getTitle());
        temp -> s1.setArtist(s.getArtist());
        temp -> s1.setSize(s.getSize());
        memSize = memSize + s.getSize();    //Add to the size of the memory
        return 0;
    }
}


/* FUNCTION - int removeSong
 * attempts to remove a song from the UtPod
 * removes the first instance of a song that is in the the UtPod multiple times
     o returns 0 if successful
     o returns -1 if nothing is removed


   input parms -  Inputting a valid song with valid title, artist, and size

   output parms - Returns a 0 if the song is removed and a -1 if it isn't
*/
int UtPod:: removeSong(Song const &s){

/* First Case 
 * If there is no song in the UtPod 
*/

 if(songs == NULL){
    return NO_MEMORY;
 }
   
 else{
    SongNode *prev = NULL;
    SongNode *p = songs;

   while((p != NULL) && (!((p -> s1) == s))){
       prev = p;
       p = p->next;
    }
     
//If there is only one song in the Linked List and that song is the same as the one in the linked list   
   if(prev == NULL){    //Indicates that the previous pointer stays NULL and is not updated
      songs = p->next;
      memSize = memSize - s.getSize();     //Substract from memSize from the songs's size
      delete p;                            //Freeing the song by deleting the pointer thus deallocating the utpod's entry from the List
      return SUCCESS;
    }
   else if(p == NULL){                     //If you aren't able to find any song that you want to remove in the linked list
       return NOT_FOUND;                   //return NOT_FOUND
   }
   else if((p -> s1) == s){              //If the song that you wish to delete is in the middle of the linked list, then you have to adjust the previous
       prev->next = p->next;              //node's next pointer to point to the same memory location as the deleted node's next pointer.
       memSize = memSize - s.getSize();
       delete p;
       return SUCCESS;
    }
  }
}
/* FUNCTION - void showSongList
     * prints the current list of songs in order from first to last to standard output
     * format - Title, Artist, size in MB (one song per line)

       input parms - No input parameters
   
       output parms - No Output Parameters
*/

void UtPod:: showSongList(){
    //First we are sorting the linked list, then we are printing it.
    sortSongList();
    for(SongNode *p = songs; p != NULL; p = p -> next){
        cout <<  p -> s1.getTitle() << " "  <<  p -> s1.getArtist()  <<  " " <<  p -> s1.getSize() << endl;
    }
}
/* FUNCTION - void clearMemory
   * clears all the songs from memory

     input parms - No Input Parameters
  
     output parms - No Output Parameters
  */
void UtPod:: clearMemory(){
    for(SongNode *p = songs; p != NULL; p = p -> next){
      delete p;
    }
}
/* FUNCTION - void sortSongList
     *  sorts the songs in ascending order
        o will do nothing if there are less than two songs in the current list

   input parms -   No Input Parameters
   
   output parms -  No Output Parameters
*/
void UtPod:: sortSongList(){
    SongNode *start = songs;

    if(start == NULL){       //no song in UTPod
        return;
    }
   
    else if(start -> next == NULL) {      //If there is only one song in the UtPod linked list
	    return;
    }

    else {

/* Selection Sort Algorithm */

        for(SongNode *ptr1 = songs;  ptr1 != NULL; ptr1 = ptr1 -> next){
            for(SongNode *ptr2 = ptr1->next; ptr2 != NULL; ptr2 = ptr2 -> next){
                if(ptr2 -> s1 < ptr1 -> s1){
                    swap(ptr2 -> s1, ptr1 -> s1);     //Swap if the second song is less than the first song
                }
            }
        }
   }
}


/* FUNCTION - void shuffle
   *  shuffles the songs into random order
      o will do nothing if there are less than two songs in the current list

     input parms -  No Input Parameters
    
     output parms -  No Output Parameters
 */
void UtPod:: shuffle(){
    if(songs == NULL){       //No song in the Utpod
        return;
    }
   
    SongNode *ptr = songs;   //Initialize a pointer to traverse the linked list
    SongNode *ptr1, *ptr2;  
    int distance1, distance2;  //These will serve as the numbers which indicate how far the node will be from the head pointer.
    int SongCount = 0;         //Indicating how many songs are in the UtPod.
   
    if(songs -> next == NULL){   //If there is only one song in the linked list, don't shuffle
        return;
    }

    while(ptr != NULL){         //Keep counting the songs(instances of the UtPod) in the UtPod until the ptr is NULL. 
      SongCount++;
      ptr = ptr -> next;        //Keep traversing by updating ptr
    }

    for(int i = 0; i < SongCount; i++){     //The for loop is to ensure randomness for what node in the UtPod is ptr is going to point to.
        distance1 = rand() % SongCount;
        distance2 = rand() % SongCount;
        ptr1 = getptr(distance1);     //Gets the pointer ptr1 based on distance1 for one of the songs(nodes) in the UtPod
        ptr2 = getptr(distance2);     //ptr2 will point to another random song in the UtPod
        swap(ptr2 -> s1, ptr1-> s1);   //Making sure to swap the songs (ONLY the songs inside the UtPod) 
    }
}

/* This function swaps the two songs that are inside the UtPod */
//PRE: Making sure they are valid songs with a reasonable title, artist, and size
//POST: Swaps the two songs in the UtPod Linked List 
void UtPod:: Swap(Song &s2, Song &s1){
    Song copy;    //Need to create a song instance inside the UtPod function
    copy = s1;    //Need to overload an assignment operator to be able to swap songs
    s1 = s2;      //Written in the Song.cpp file
    s2 = copy;    
}

/* PRE: Enter a valid number from the caller function shuffle to determine the pointer which lies head_dist away from the head pointer 
 * POST: Returns a pointer that is head_dist away from the head pointer
*/
UtPod::SongNode * UtPod:: getptr(int head_dist) {
   SongNode *ptr = songs;  //start from the beginning of the Utpod linked list 
/* While the number is greater than zero, keep updating the ptr of the linked list */
    while(head_dist > 0) {
        ptr = ptr->next;
        head_dist--;     //Making sure to substract the number 
        }
     return ptr;
}

/* Intializing the Random number generator */
//This function passes in a seed to the srand function to initialize the random number generator. This function is called by the constructors above
void UtPod:: Seed(){
    unsigned int currentTime = (unsigned)time(0);
    srand(currentTime);
}






