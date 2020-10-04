//
// Created by khans on 4/5/2019.
//

#include "card.h"
#include <iostream>

/* Created by Sohaib Khan and Mary Graham */
using namespace std;

    Card:: Card()       //default constructor
    {
        myRank = 1;
        mySuit = spades;

    }

    /* Parametrized Constructor which sets the rank and suit of the rank and suit passed in */
    Card:: Card(int _rank, Suit _s)
    {
        myRank = _rank;
        mySuit = _s;
    }

    Card:: Card(string _s)
    {
        Joke = _s;
    }

    /* Copy constructor which sets the rank and suit of the c_old's card */
    Card:: Card(const Card &c_old)
    {
        myRank = c_old.myRank;
        mySuit = c_old.mySuit;
    }

    /* An accessor function gets the rank of the card */
    int Card::getRank() const
    {
        return myRank;

    }

    /*  This member function is used to convert the suit of your card to a string
     * For Example, spades means "s", hearts means "h", clubs means "c", diamonds means "d"
    */
    string Card::suitString(Card::Suit s) const
    {
        switch(s) {
            case Card::spades:
                return "s";
            case Card::hearts:
                return "h";
            case Card::clubs:
                return "c";
            case Card:: diamonds:
                return "d";
            default:
                return "x";
        }

    }
    /* This member function is used to convert the rank of your card to a string
     * For Example, 1 means "A", 11 means "J", 12 means "Q", and etc
    */
    string Card::rankString(int r) const
    {
        switch(r){
            case 1:
                return "A";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "7";
            case 8:
                return "8";
            case 9:
                return "9";
            case 10:
                return "10";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return "X";  //Any other rank above 13 will be the default

        }
    }

    /* This is a member function from the card class that checks whether the card has the same suit or not */

    bool Card::sameSuitAs(const Card &c) const
    {
        //string currentSuit = suitString(mySuit);
        //string CardSuit = suitString(c.mySuit);
        if(mySuit == c.mySuit){
            return true;
        }
        else{
            return false;
        }

    }

    /* This is a member function which takes the string rank and the string suit of your card and concatenates the both of
     * them.
     * For instance: "Ac" for Ace of clubs, "Ks" for King of Spades
    */

    string Card::toString() const
    {
        string rank = rankString(myRank);  //gets the rank as as a string
        string suit = suitString(mySuit);  //gets the suit as a string
        return rank + suit;  //concatenate the two strings

    }

    /* An operator for checking whether the ranks of the cards are the same */

    bool Card::operator==(const Card &rhs) const
    {
        if(myRank == rhs.myRank && mySuit == rhs.mySuit){
            return true;
        }
        else{
            return false;
        }
    }

    /* An operator for checking whether the ranks of the cards are NOT the same */
    bool Card::operator != (const Card &rhs) const
    {
        if(myRank != rhs.myRank && mySuit != rhs.mySuit){
            return true;    //this is the expected result
        }
        else{
            return false;
        }
    }
    Card::~Card() {}    //destructor









