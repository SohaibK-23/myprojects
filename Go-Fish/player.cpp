//
// Created by khans on 4/5/2019.
//
#include "player.h"
#include <vector>
#include <math.h>
#include <iterator>
#include <cstdlib>

/* This is the player.cpp consisting of the member functions declared in the player.h file
 * Created by Sohaib Khan and Mary Graham
*/

/* PRE: Two cards with a valid suit and rank are being passed by reference to denote the fact that they are going to contain
 * the pair of cards with the same rank that is going to form a book
 * POST: This function will return the two cards that form a book(pair) in your hand and a true or false value on whether a book
 * has been formed. When a book is formed, we should expect a return statement of true and the two cards c1 and c2 should contain
 * information about the two cards that form a book.
*/
bool Player::checkHandForBook(Card &c1, Card &c2) {
    for(vector<Card>::iterator first = myHand.begin(); first != myHand.end(); first++){
        for(vector<Card>::iterator second = myHand.begin(); second != myHand.end(); second++){
            if((first -> getRank() == second -> getRank()) && !(first == second)){
                c1 = *first;
                c2 = *second;
                return true;
            }
        }
    }
 return false;
}

/* Checks whether the player has that card in his hand. Returns true if he does have the card c and false if he doesn't */
bool Player::cardInHand(Card c) const {
    for(int k = 0; k < myHand.size(); k++){
        if(myHand[k] == c){
            return true;
        }
    }
    return false;
}
void Player::addCard(Card c) {        //adds the card to the end of the vector myHand
    myHand.push_back(c);
}

void Player::bookCards(Card c1, Card c2) {
    myBook.push_back(c1);      //adds the two cards to the end of the vector myBook
    myBook.push_back(c2);
}


/* PRE: Making sure it is valid card with an appropriate rank and suit
 * POST: returns the last card in your hand which had the similar ranking and suit as Card c
 * Essentially, removing the card c from your hand and returning that card to perform other functions.
*/
Card Player::removeCardFromHand(Card c) {

    Card seeking;

    for(vector<Card>::iterator it = myHand.begin(); it!= myHand.end(); it++) {
        if (it->getRank() == c.getRank() && it->sameSuitAs(c)){
            seeking = *it;  //assignment operator for card needed
            myHand.erase(it);
            return seeking;
        }
    }
}

//RETURNS THE SIZE OF THE VECTOR myBook
int Player::getBookSize() const {
    return myBook.size();  //accessor function that returns the book size
}

//Returns the size of the vector myHand
int Player::getHandSize() const {
    return myHand.size();
}

//It chooses a card at random from your hand that the player would ask for.
Card Player::chooseCardFromHand() const {

    int num = rand() % myHand.size();
    return myHand[num];
}
//Returns the string which shows the hand of the player
string Player::showHand() const {
    string hand = "";
    for(int show = 0; show < myHand.size(); show++){
        hand = hand + myHand[show].toString() + " ";
    }
    return hand;
}
//Returns the string which shows the books that the player made so far
string Player::showBooks() const {
    string book = "";
    for(int showBook = 0; showBook < myBook.size(); showBook++){
        book = book + myBook[showBook].toString() + " ";
    }
    return book;

}
/* PRE: Have a card with a valid rank and suit passed as a parameter to the function. This is the card to determine
 * whether the ranking of card c is the same as any one of the cards in the player's hand.
 * POST: Returns a true or false value determining whether the card has been found in the player's hand
*/
bool Player::rankInHand(Card c) const {
    for(int find = 0; find < myHand.size(); find++){
        if(myHand[find].getRank() == c.getRank()){
            return true;
        }
    }
    return false;
}







