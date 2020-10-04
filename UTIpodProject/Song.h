//
// Created by khans on 3/26/2019.
//


#ifndef FIFTHLAB_SONG_H
#define FIFTHLAB_SONG_H

#include <string>

using namespace std;

class Song{

private:
    string title;    //This is the title of the song
    string artist;   //This is the artist of the song
    int size;        //This is the size of the song

   // static const int MAX_SIZE = 512;    
public:
    Song();  //Initializing the default constructor
    Song(string artist, string title, int size);  //initializing the parametrized constructor
    Song(const Song &s_old);  //copy constructor

/* Public methods for the song class */
    string getTitle() const     //getting the title
    {
        return title;
    };

    void setTitle(string t)     //setting the title
    {
        title = t;
    };

    string getArtist() const;      //getting the artist
    void setArtist(string art);    //setting the artist
    int getSize() const;           //getting the size
    void setSize(int s);           //setting the size 

    bool operator > (Song const &rhs);      //overloading the greater than operator
    bool operator == (Song const &rhs);     //overloading the equal operator
    bool operator < (Song const &rhs);      //overloading the less than operator
    void operator = (Song const &rhs);      //overloading the assignment operator (SWAP function)

    ~Song(); //destructor. NEEDS TO EXIST TO DELETE OBJECT ALLOCATED IN THE HEAP.

};



#endif //FIFTHLAB_SONG_H
