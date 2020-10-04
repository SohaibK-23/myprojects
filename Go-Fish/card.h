//
// Created by khans on 4/5/2019.
//


#ifndef SIXTHLAB_CARD_H
#define SIXTHLAB_CARD_H

#include <iostream>
#include <string>
using namespace std;

//The card class needed for creating the deck
//Created by Sohaib Khan and Mary Graham

class Card
{
public:


    enum Suit {spades, hearts, diamonds, clubs};

    Card();          // default, ace of spades
    ~Card();

    Card(int rank, Suit s); //parmaterized constructor
    Card(string s);
    Card(const Card &c_old);  //copy constructor

    string toString()              const;  // return string version e.g. Ac 4h Js
    bool sameSuitAs(const Card& c) const;  // true if suit same as c
    int  getRank()                 const;  // return rank, 1..13
    string suitString(Suit s)      const;  // return "s", "h",...

    string rankString(int r)       const;  // return "A", "2", ..."Q"


    bool operator == (const Card& rhs) const;  //Determining whether the rank and suit of two cards are the same
    bool operator != (const Card& rhs) const;  //Determining whether the rank and suit of two cards are different



private:

    int myRank;  //rank of card
    Suit mySuit;  //suit of card
    string Joke;


};

ostream& operator << (ostream& out, const Card& c);



#endif //SIXTHLAB_CARD_H