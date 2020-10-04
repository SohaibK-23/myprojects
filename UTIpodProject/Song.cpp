//
// Created by khans on 3/26/2019.
//
#include "Song.h"
#include <iostream>


using namespace std;

    Song:: Song()    //default constructor
    {
        artist = "";
        title = "";
        size = 0;
    }

    Song::Song(string _artist, string _title, int _size) {      //parameterized constructor

        if(_size < 0)          //In case, if someone types a negative size
            _size = 0;

        artist = _artist;
        title = _title;
        size = _size;

    }
    Song::Song(const Song &s_old){              //copy constructor
        artist = s_old.artist;
        title = s_old.title;
        size = s_old.size;
    }

    string Song::getArtist() const {      //get the artist
        return artist;
    }

    void Song::setArtist(string art) {    //set the artist
        artist = art;
    }

    int Song::getSize() const {         //get the size
        return size;
    }
    void Song::setSize(int s) {         //set the size
        size = s;
    }

    bool Song::operator ==(Song const &rhs) {  //check whether the artist, title, and the size are equal of the two songs s1 and s2
        return (artist == rhs.artist && title == rhs.title && size == rhs.size);
    }

    bool Song::operator < (Song const &rhs) {    //check whether the artist, title, and the size are less than
        if(artist < rhs.artist){                 // the artist, title, and the size of another song. If s1 < s2
            return true;
        }
      	//Check the artist first
        else if(artist == rhs.artist){
            if(title < rhs.title){
                return true;
            }
	      //Check the title 
            else if(title == rhs.title){
                return (size < rhs.size);        //return a true or false statement of the size of the songs 
            }

        }
        else{
            return false;                         //Otherwise, return a false statement if the size and title of are equal of a song or greater than the other song
        }
    }

    bool Song::operator > (Song const &rhs) {        //check whether the artist, title, and the size are greater than the artist
        if(artist > rhs.artist){                     //size, and the title of another song. If s1 > s2
            return true;
        }
	//Check the artist first
        else if(artist == rhs.artist){
            if(title > rhs.title){
                return true;
            }
	   //Check the title 
            else if(title == rhs.title){
                return (size > rhs.size);   //return a true or false statement of the size of the songs
            }

        }
        else{
            return false;        //Otherwise, return a false statement if the size and the title are the equal of one song or less than the other song
        }

    }

    void Song:: operator = (Song const &rhs){               //assignment operator. NEEDED FOR THE SWAP FUNCTION
        artist = rhs.artist;
        title = rhs.title;
        size = rhs.size;
    }

    Song:: ~Song(){}               //destructor


ostream& operator << (ostream &out, const Song &s)
{
    out << s.getTitle() << " " << s.getArtist() << " " << s.getSize() << endl;     //Whenever you want to display the song's artist, title, and the size
    return out;                                                                    //Display it in the following manner
}
