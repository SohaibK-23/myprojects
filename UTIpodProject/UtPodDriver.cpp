#include <cstdlib>
#include <iostream>
#include "Song.h"
#include "UtPod.h"

using namespace std;

int main(int argc, char *argv[]) {

    UtPod t(300);
    //The head pointer songs is NULL.

   Song s1("Linkin Park", "New Divide", 50);
   int result = t.addSong(s1);
   cout << "result = " << result << endl;


   Song s2("Linkin Park", "Burn it Down", 150);
   result = t.addSong(s2);
   cout << "result = " << result << endl;
   Song s3("Starset", "Telescope", 50);
   result = t.addSong(s3);
   cout << "result = " << result << endl;
   Song s4("Starset", "My Demons", 100);
   result = t.addSong(s4);
   cout << "result = " << result << endl;

   Song s5("OneRepublic", "Good Life", 200);
   result = t.addSong(s5);
   cout << "result = " << result << endl;

   Song s6("Starset", "Satellite", 100);
   result = t.addSong(s6);
   cout << "result = " << result << endl;

   int mem = t.getRemainingMemory();
   cout << "remaining memory = " << mem << endl;


   result = t.removeSong(s5);         //It will return back from this function because there is no song s5 in the UtPod Linked List
   mem = t.getRemainingMemory();      //The Remaining memory will be the same as above
   cout << "remaining memory = " << mem << endl;


    result = t.removeSong(s4);        //It will start removing the song s4 since we created it
    mem = t.getRemainingMemory();     //the memory should increase by 100 MB
    cout << "remaining memory = " << mem << endl;

    result = t.removeSong(s3);       //Removes the song s3 from the Linked List
    mem = t.getRemainingMemory();    //the memory should increase by 50MB
    cout << "remaining memory = " << mem << endl;


    t.shuffle();
    t.showSongList();    



    /*
    Song s3("Beatles", "Hey Jude3", 6);
    result = t.addSong(s3);
    cout << "result = " << result << endl;

    Song s4("Beatles", "Hey Jude4", 7);
    result = t.addSong(s4);
    cout << "result = " << result << endl;

    Song s5("Beatles", "Hey Jude5", 241);
    result = t.addSong(s5);
    cout << "add result = " << result << endl;

    t.showSongList();

    result = t.removeSong(s2);
    cout << "delete result = " << result << endl;

    result = t.removeSong(s3);
    cout << "delete result = " << result << endl;

    t.showSongList();

    result = t.removeSong(s1);
    cout << "delete result = " << result << endl;

    result = t.removeSong(s5);
    cout << "delete result = " << result << endl;

    result = t.removeSong(s4);
    cout << "delete result = " << result << endl;


    t.showSongList();

    result = t.addSong(s5);
    cout << "add result = " << result << endl;

    t.showSongList();
    cout << "memory = " << t.getRemainingMemory() << endl;

    //bool test = s3<s4;
    //cout << test << endl;




    //cout << "result = " << result << endl;
    t.showSongList();
    //t.removeSong(s1);
    //t.showSongList();




  */
    //std::cout << "Hello, World!" << std::endl;
    return 0;
}
