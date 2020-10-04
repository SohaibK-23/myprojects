//life_driver.c
//
//Life Driver program
//Roger Priebe 9/5/2018
//EE 312


/* Student information for project:
 *
 * Replace <NAME> with your name.
 *
 * On my honor, Sohaib Khan, this programming project is my own work
 * and I have not provided this code to any other student.
 *
 * Name:Sohaib Khan
 * email address:Khansohaib98@utexas.edu
 * UTEID:sk39944
 * Section 5 digit ID:16030
 *
 */

//This file is a driver program for the life.c module.
//This program must work with your implementations life.h and life.c
//Do not change the function definitions for populateWorld, showWorld or
//iterateGeneration.
//

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#include "life.h"


int main(int argc, char *argv[]) {

   //the pointer fName is pointing to the address of the text file that we are going to read from.
   char *fName = argv[1];
  // char fName[] = "world_test.txt" This is the text file that it is currently reading from
   const int numGenerations  = atoi(argv[2]); //set to a smaller number for debugging
  //The number of generations is stated from the 3rd argument while executing the program
   const int MAX_ROWS = 80; //we want the world to fit on one screen

   
   //If the number of arguments entered are less than 3, the file will not open and it will exit the program
   if(argc <  3) {
       printf("The file cannot open\n");
       exit(-1);
   }
   else{
       printf("File open\n");
   }



   //array of strings that will hold the grid
   char *world[MAX_ROWS];
   int numRows = 0;
   int numCols = 0;
   int delay = 500000;

   populateWorld(fName, world, &numRows, &numCols);
   //intially we populate the world by reading the grid from the file.
   showWorld(world, numRows, numCols);
   //Then we show it
   //After that for each iteration, we update the world by playing according to the rules.

   for (int iteration = 0; iteration < numGenerations; iteration++) {

       
       for(int i = 0; i < delay; i++){     //The delay loop is meant for delaying as we transition from one generation to the next.
            delay--;
         }

       printf("before\n");

       //uncommment for Windows

       /*
       system("cls"); //Windows only
       printf("WIN\n");
       */

       
       system("clear"); //Linux only
        // printf("LINUX\n");

       printf("after\n");

       
       iterateGeneration(world, numRows, numCols);

       showWorld(world, numRows, numCols);
  
}

      for(int i = 0; i < numRows; i++) {           
  	    free(world[i]);                   //Free the pointers from the world array
       } 

      

  return 0;

}





