//
// Created by khans on 4/5/2019.
//

#ifndef SIXTHLAB_PLAYER_H
#define SIXTHLAB_PLAYER_H

#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <cstdlib>
#include "card.h"
using namespace std;

//This is the player class created by Sohaib Khan and Mary Graham
class Player
{
public:
    Player(){                    // Default constructor
        myName = "Player 1";     //player is going to be named "Player 1" automatically if the user of class
    }                            //types in Player()

    Player(string name) {        //Parametrized constructor
        myName = name;
        unsigned int currentTime = (unsigned)time(0);
        srand(currentTime);
    }

    Player(const Player &old_player){        //Copy constructor
        myName = old_player.getName();       //Needed for alternating players
    }

    string getName() const {
        return myName;
    }

    void addCard(Card c);  //adds a card to the hand
    void bookCards(Card c1, Card c2);

    //OPTIONAL
    // comment out if you decide to not use it
    //this function will check a players hand for a pair.
    //If a pair is found, it returns true and populates the two variables with the cards tha make the pair.

    bool checkHandForBook(Card &c1, Card &c2);

    //OPTIONAL
    // comment out if you decide to not use it
    //Does the player have a card with the same rank as c in her hand?
    bool rankInHand(Card c) const;

    //uses some strategy to choose one card from the player's
    //hand so they can say "Do you have a 4?"
    Card chooseCardFromHand() const;

    //Does the player have the card c in her hand?
    bool cardInHand(Card c) const;

    //Remove the card c from the hand and return it to the caller
    Card removeCardFromHand(Card c);

    string showHand() const;
    string showBooks() const;

    int getHandSize() const;
    int getBookSize() const;

    //OPTIONAL
    // comment out if you decide to not use it
    //this function will check a players hand for a pair.
    //If a pair is found, it returns true and populates the two variables with the cards tha make the pair.

//    bool checkHandForPair(Card &c1, Card &c2);

    //OPTIONAL
    // comment out if you decide to not use it
    //Does the player have a card with the same rank as c in her hand?
    //e.g. will return true if the player has a 7d and the parameter is 7c

    bool sameRankInHand(Card c) const;


private:

    vector <Card> myHand;
    vector <Card> myBook;

    string myName;

};

#endif //SIXTHLAB_PLAYER_H
