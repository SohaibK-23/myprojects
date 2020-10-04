#include <iostream>    // Provides cout and cin
#include <cstdlib>     // Provides EXIT_SUCCESS
#include <fstream>
#include <vector>
#include "card.h"
#include "player.h"
#include "deck.h"


//This file is the driver of the program used to test the game
// created by Sohaib Khan and Mary Graham
//Class Section: 16030
using namespace std;
void winningCondition(const Player &p1, const Player &p2); //Used to determine the winner between player 1 and player 2


int main() {

    int numCards = 14;  //number of cards passed to each player. Since we are distributing 7 cards per player, we would need
                        //to take 14 cards out of our deck
    Player p1("Sohaib");
    Player p2("Jim");
    //Creating the vector of players
    vector <Player> players;
    players.push_back(p1);
    players.push_back(p2);

    //Setting the turn index to 0
    int turn = 0;
    int specfic_suit = 0;  //Creating a card of a certain suit. This is used in the while loop to find the card that the
                           //opponent has in his hand if that opponent has the same ranking as the seeking card of the player
    bool found;

    ofstream log;
    log.open("gofish_results.txt");


    log << "Welcome to our first edition of the Go-Fish game with our contestants: " << players.at(0).getName() << " and " << players.at(1).getName() << endl;
    log << endl;

    Deck d;  //create a deck of cards
    d.shuffle();  //shuffling a deck of cards


    for(int i = 0; i < numCards; i++){
        turn = (turn+1) % 2;   //alternating turns between each player
        players.at(turn).addCard(d.dealCard()); //adding 7 cards to each player's hand
    }

    cout <<  players.at(0).getName() << "'s hand: " << players.at(0).showHand() << endl;
    log <<   players.at(0).getName() << "'s hand: " << players.at(0).showHand() << endl;
    cout <<  players.at(1).getName() << "'s hand: " << players.at(1).showHand() << endl;
    log <<   players.at(1).getName() << "'s hand: " << players.at(1).showHand() << endl;
    cout << endl;
    log << endl;

    Card pairA;  //these are just temporary card objects used for assigning cards
    Card pairB;


    /* We first check whether each player has a book in their hand or not */
    while(players.at(0).checkHandForBook(pairA, pairB)){
        players.at(0).bookCards(players.at(0).removeCardFromHand(pairA), players.at(0).removeCardFromHand(pairB));
    }

    cout << players.at(0).getName() << "'s books: " << players.at(0).showBooks() << endl;
    log <<  players.at(0).getName() << "'s books: " << players.at(0).showBooks() << endl;
    //cout << players.at(0).getBookSize() << endl;

    while(players.at(1).checkHandForBook(pairA, pairB)){
        players.at(1).bookCards(players.at(1).removeCardFromHand(pairA), players.at(1).removeCardFromHand(pairB));
    }

    cout << players.at(1).getName() << "'s books: " << players.at(1).showBooks() << endl;
    log <<  players.at(1).getName() << "'s books: " << players.at(1).showBooks() << endl;
    //cout << players.at(1).getBookSize() << endl;
    cout << endl;
    log << endl;
    /* Actual Execution or driver for the Game */

    log << "Plays from each player:" << endl;
    log << endl;
    //This while loop will keep being executed until each player's book size adds up to greater than or equal
    //to 52 cards
    while((players.at(0).getBookSize() + players.at(1).getBookSize() < 52)){
        /* The two players show their books everytime */
        cout << players.at(0).getName() << " has Books - " << players.at(0).showBooks() << endl;
        cout << players.at(1).getName() << " has Books - " << players.at(1).showBooks() << endl;
        cout << endl;
        log << players.at(0).getName() << " has Books - " << players.at(0).showBooks() << endl;
        log << players.at(1).getName() << " has Books - " << players.at(1).showBooks() << endl;
        log << endl;

        log << "It is " << players.at(turn).getName() << "'s turn." << endl;
        /* This is the case where it is important to check whether either of the players has no cards in their hand */
        /* If there are no cards in their hand remaining, then we must display the message that the player must draw another card
         * from their hand.
         * Note: They can't form a book in this case because they only chose one card only
         * The turn alternates to the next player
        */
        if(players.at(turn).getHandSize() == 0){
            log << players.at(turn).getName() << " has no cards in their hand. They must draw another card." << endl;
            Card must_draw = d.dealCard();
            log << players.at(turn). getName() << " draws the " << must_draw.toString() << "." << endl;
            players.at(turn).addCard(must_draw);
            turn = (turn+1)%2;  //Since the player has drawn a card from their deck, it alternates the turn to the other player.
            continue;          //this will just continue the execution of the outer while loop. In other words, the game will still go on
        }


        Card seeking = players.at(turn).chooseCardFromHand(); //the player who has his or her turn chooses any one card from their hand
        int opponent = (turn + 1) % 2;  //opponent gets the alternating index
        found = false;   //Setting the boolean variable found to be false because we have not found the card from the opponent's hand that has
        //the same ranking as the seeking card

        log << players.at(turn).getName() << " asks - Do you have any " << seeking.rankString(seeking.getRank()) << "'s?" << endl;
        //player asks the opponent for the card that has similar ranking to the seeking card

        if(players.at(opponent).rankInHand(seeking)){
        log << players.at(opponent).getName() << " says - Yes. I have a " << seeking.rankString(seeking.getRank()) << "." << endl;
        //Opponent has the card of the same ranking as the player who has his turn

        /* Now we need to find what card the opponent is holding in his hand that is of the same rank as the seeking card but
         * not the same suit as the seeking card. There are 3 possiblities that the opponent has the certain card of the same rank
         * The purpose for this while loop is to keep going through these possibilities so that once we find the card
         * We set the boolean variable found to be true
        */
            while(!found){
                Card match = Card(seeking.getRank(),(Card::Suit)specfic_suit);  //Creating a matching card that the opponent has in his hand
                if(players.at(opponent).cardInHand(match)){
                    log << players.at(turn).getName() << " books the pair of " << seeking.rankString(seeking.getRank()) << "s." << endl;
                    Card testSeeking = players.at(turn).removeCardFromHand(seeking);  //testSeeking is the seeking card that the player who has his turn is going to remove
                    Card testMatch = players.at(opponent).removeCardFromHand(match);  //testMatch is the card with the same rank as the seeking card that the opponent will remove
                    players.at(turn).bookCards(testMatch, testSeeking); //We use testMatch and testSeeking cards to form a book and add it to the book pile of the player who has his turn
                    found = true; //Since we formed a book, we will step out of this while loop
                }
                specfic_suit++;  //Increasing the specific suit variable which is to keep match the suit of the card in the
                                 //opponent's hand
            }
            specfic_suit = 0;   //We need to set the specific suit back to 0 after the while loop ends
        }

        /* If the opponent has doesn't have the card with the same ranking as the seeking card, then it will execute this part of the code
         * Here the opponent is going to draw a card from the deck and add it to his hand
         * If that card happens to have the same ranking as any one of the cards in his hand, he will book those pair of cards.
         * Also, the player will end his turn
        */
        else{
            log << players.at(opponent).getName() << " says - Go Fish." << endl;
            Card deal = d.dealCard();  //deals the card puts it in the temporary card object named deal
            log << players.at(turn). getName() << " draws the " << deal.toString() << "." << endl;
            players.at(turn).addCard(deal); //adds it to the hand
            if(players.at(turn).checkHandForBook(pairA, pairB)) {
                players.at(turn).bookCards(players.at(turn).removeCardFromHand(pairA), players.at(turn).removeCardFromHand(pairB));
                log << players.at(turn).getName() << " books the pair of " << deal.rankString(deal.getRank()) << "s." << endl;
            }

            turn = (turn+1) % 2;  //Alternating the turns between the players

        }
    }

    /* After the game ends, the player will show their books and their hands and the player who has formed the
     * most books will win the game
    */
    log << endl;
    log << "Final Results";
    cout << endl;
    log << endl;
    cout << players.at(0).getName() << " has Books: " << players.at(0).showBooks() << endl;
    cout << players.at(1).getName() << " has Books: " << players.at(1).showBooks() << endl;
    log  << players.at(0).getName() << " has Books: " << players.at(0).showBooks() << endl;
    log  << players.at(1).getName() << " has Books: " << players.at(1).showBooks() << endl;

    /* Determining the winning condition */
    /* Please note that these if statements are just to print the winner and their books in the gofish_results.txt file */
    /* If there is a tie between the two players, then both of their books will be displayed */

    if(players.at(0).getBookSize() > players.at(1).getBookSize()){
        log << players.at(0).getName() << " has " << (players.at(0).getBookSize() / 2 ) << " books." << endl;
        log << players.at(0).getName() << " is the winner!!!" << endl;
    }
    else if(players.at(0).getBookSize() < players.at(1).getBookSize()){
        log << players.at(1).getName() << " has " << (players.at(1).getBookSize() / 2 ) << " books." << endl;
        log <<  players.at(1).getName() << " is the winner!!!" << endl;
    }
    else if(players.at(0).getBookSize() == players.at(1).getBookSize()){
        log << players.at(0).getName() << " has " << (players.at(0).getBookSize() / 2 ) << " books." <<  endl;
        log << players.at(1).getName() << " has " << (players.at(1).getBookSize() / 2 ) << " books." << endl;
        log << "IT IS A TIE!!!" << " What a match between our contestants " << players.at(0).getName() << " and " << players.at(1).getName() << endl;
    }

    winningCondition(players.at(0), players.at(1));
    log << endl;
    log << "Thank you for Playing Go-Fish. We hope that you all come back for the next edition of the game. Until then, Happy fishing!!" << endl;
    return EXIT_SUCCESS;
}
/* Determining the winning condition */
void winningCondition(const Player &p1, const Player &p2){
    //This function displays the winner and their books on the console. If it is a tie, then the books for both players
    //are displayed
    if(p1.getBookSize() > p2.getBookSize()){
        cout << p1.getName() << " has " << (p1.getBookSize() / 2 ) << " books" << endl;
        cout << p1.getName() << " is the winner!!!" << endl;
    }
    else if(p1.getBookSize() < p2.getBookSize()){
        cout << p2.getName() << " has " << (p2.getBookSize() / 2 ) << " books" << endl;
        cout << p2.getName() << " is the winner!!!" << endl;
    }
    else if(p1.getBookSize() == p2.getBookSize()){
        cout << p1.getName() << " has " << (p1.getBookSize() / 2 ) << " books" <<  endl;
        cout << p2.getName() << " has " << (p2.getBookSize() / 2 ) << " books" << endl;
        cout << "IT IS A TIE!!!" << " What a match between our contestants " << p1.getName() << " and " << p2.getName() << endl;
    }
}
