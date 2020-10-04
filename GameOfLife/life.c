#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#include "life.h"

//Written by Sohaib Khan
//UT EID: sk39944


/* Populate the grid with strings read from the text file
   PRE: fName[] contains the text file entered in the linux command prompt, *grid[] is an array of strings where the pointers of those strings aren't initialized,
   *numRows and *numCols are addresses to the variables numRows and numCols which are going to specify the dimensions of the grid
   POST: return the grid with an array of strings that read from the text file
*/
 
void populateWorld(char fName[], char *grid[], int *numRows, int *numCols){
    FILE *fptr;                                 //Declaring a file pointer fptr pointing to a file
    fptr = fopen(fName, "r");                   //Initializing the file pointer to the text file specified in the command prompt
    if(fptr == NULL) {
        printf("You've screwed up\n");
        exit(-1);
    }
    else {
        printf("Success\n");
    } 


    char buf[BUFSIZ];                           //Initializing a buffer to store the string read from the text file



   while(fgets(buf, sizeof(buf), fptr)!= '\0') {
        *numCols = strlen(buf) - 1;                               //Have to specify the number of columns in numCols
        // grid[*numRows] = (char*) malloc((*numCols * sizeof(char)) + 2);                                                         
        grid[*numRows] = (char*) calloc (((*numCols)) + 2, sizeof(char)); 
 /* Allocating space in the heap for the strings being read from the text file t create the grid*/     
        strcpy(grid[*numRows], buf);                               
        (*numRows)++;                                             //Incrementing the number of rows by 1 everytime a string has been read
     }

      fclose(fptr);                                               //closing the file
} 

/* Display the grid with * and . to indicate whether the cell is alive or dead.
   PRE: *grid[] now contains the array of strings consisting of 1's and 0's, numRows and numCols now specify the dimensions of the grid determined from the previous 
   function.
   POST: Displayes the grid with the characters * and . to specify the status for each cell
*/ 

void showWorld(char *grid[], int numRows, int numCols) {
const char SUBSCRIPT = '*';    //This character is for specifying a alive cell
const char DOT = '.';         // This is for specifying a dead cell

    for(int i = 0; i < numRows; i++){
        for(int k = 0; k < numCols; k++){     //As you go through the 2D array, If the cell has a character '1', then it is displayed by a subscript.
            if(grid[i][k] == '1'){            //Otherwise, it displays a dot for the status dead
                printf("%c", SUBSCRIPT);
            }
            else{
                printf("%c", DOT);
            }

        }
      printf("\n");                           //Printing out a new line after the string is done
    
  }
}

/* 
 Updating the grid for the next iteration by creating a temporary 2D array and writing the appropriate characters based on the rules of the game
 PRE: *grid[] will be the grid based on the previous iteration. numRows and numCols are known to create a temprorary grid of the apporopriate dimension
 POST: returns the new grid stored in the grid array with the new strings.
*/  

void iterateGeneration(char *grid[], int numRows, int numCols){     
    int currentRow;
    int currentCol;
    char *tempGrid[numRows];   // number of rows and columns are known to me at this point
    int i;
    int result;
    char current_status;
    for(i = 0; i < numRows; i++){        
        tempGrid[i] = calloc((numCols + 2), sizeof(char));  //allocating the memory for the strings being pointed by the each of the pointers in the tempGrid array 
    }

/* By going through the array, the characters '1' and '0' are written by following the rules of the game */


     for(currentRow = 0; currentRow < numRows; currentRow++) {                     
         for (currentCol = 0; currentCol < numCols; currentCol++) {
              current_status = grid[currentRow][currentCol];  //Specifying the whether the cell is alive or dead       
              result = navigate(grid, &currentRow, &currentCol, numRows, numCols);  //Result is assigned the number of alive neighboring cells
         //We need to know the whether the character is alive or dead in the previous generation to determine whether to place an '1' if there are 3 alive neighbors
             if((result == 3) && (current_status == '0')){
                  tempGrid[currentRow][currentCol] = '1';
              }
        
         //If the current status of the cell is '0' or the result is not 3, then it will go through the following block of code 
             else if((result == 3) || (result == 2)){
                 if(current_status == '1'){
                     tempGrid[currentRow][currentCol] = '1';
                   }
                 else {
                      tempGrid[currentRow][currentCol] = '0';
                   }
              } 
         //Overcrowding of neighbors leads to cell's death
             else if(result >= 4){
                    tempGrid[currentRow][currentCol] = '0';
                   }
         //The next block of code is executed when the cell either doesn't have enough neighbors or the current status of the cell is '0' 
             else {
                   tempGrid[currentRow][currentCol] = '0';
              }
          }
      }

//After the temporary grid has been updated, then we can copy the strings from tempGrid into the original grid using strcpy
    for(i = 0; i < numRows; i++){
        strcpy(grid[i], tempGrid[i]);
    }

//We also have to inform that we are not using the pointers in the tempGrid array anymore. 
    for(i = 0; i < numRows; i++){
          free(tempGrid[i]);
    }


}

/* Navigates through the 2D array and returns the number of neighbors that are alive based on its location.
 PRE: *grid[] is the old grid which we want to navigate so that we can determine the amount of neighbors for each cell. 
      *currentRow and *currentCol are addresses to the variables declared and initiated by the caller function IterateGeneration
      numRows and numCols specify the dimensions of the grid

 POST: Returns the amount of alive neighbors for the cell whose location is specified by the currentRow and currentCol values
*/
 

int navigate(char *grid[], int *currentRow, int *currentCol, int numRows, int numCols){

    int sum;                  //determines the number of neighbors that are alive
    char cell_1 = 0;          //Initalizing each of the cells to 0. These cells represent the neighboring cells for a particular cell we are trying to navigate
    char cell_2 = 0;
    char cell_3 = 0;
    char cell_4 = 0;
    char cell_5 = 0;
    char cell_6 = 0;
    char cell_7 = 0;
    char cell_8 = 0;

/* Based on the position described by the currentRow and currentCol values, we can determine the number of alive neighbors
   By doing a series of if else statements, we can see whether a cell we are trying to count the neghbors of is one of the corner cells, the cells on the edge,
   or the cell in the middle. Once we access the characters for each of the neighboring cells, we can convert them to integers and add them up to get the total 
   amount of alive neighbors for that particular cell 
*/


    if((*currentRow == 0) && (*currentCol == 0)){              //upper left hand corner cell 
        cell_1 = grid[*currentRow][(*currentCol) + 1];         //accessing each of the characters for the particular cell's neighbors.
        cell_2 = grid[(*currentRow)+ 1][*currentCol];          //three neighbors for each of the corner cells
        cell_3 = grid[(*currentRow)+ 1][(*currentCol) + 1];
        int num1 = cell_1 - '0';                               //num1,num2,and num3 represent the integer values for the characters stored in cell_1, cell_2, and cell_3
        int num2 = cell_2 - '0';                           
        int num3 = cell_3 - '0';
        sum = num1 + num2 + num3;                              //adding the integers stored in num1, num2, and num3 and storing in the sum variable
        return sum;                                            // returning the sum variable
    } 


    else if((*currentRow == 0) && (*currentCol == numCols - 1)){        //upper right hand corner cell
        cell_1 = grid[*currentRow][(*currentCol) - 1];                  //accessing the cell's neighbors 
        cell_2 = grid[(*currentRow) + 1][(*currentCol) - 1];            
        cell_3 = grid[(*currentRow) + 1][*currentCol];
        int num1 = cell_1 - '0';                                        //Converting the contents for the cell's neighbors to integers.
        int num2 = cell_2 - '0';
        int num3 = cell_3 - '0';
        sum = num1 + num2 + num3;                                       //adding the value of the neighbors 
        return sum;
    }


    else if((*currentRow == numRows - 1) && (*currentCol == 0)){      //bottom left hand corner cell
        cell_1 = grid[(*currentRow) - 1][*currentCol];                //accessing the cell's neighbors
        cell_2 = grid[*currentRow][(*currentCol) + 1];         //num1,num2,and num3 represent the integer values for the characters stored in cell_1, cell_2, and cell_3
        cell_3 = grid[(*currentRow) - 1][(*currentCol) + 1];
        int num1 = cell_1 - '0';
        int num2 = cell_2 - '0';
        int num3 = cell_3 - '0';
        sum = num1 + num2 + num3;
        return sum;
    } 


    else if((*currentRow == numRows - 1) && (*currentCol == numCols - 1)) {    //bottom right hand corner cell
        cell_1 = grid[*currentRow][(*currentCol) - 1];
        cell_2 = grid[(*currentRow) - 1][*currentCol];
        cell_3 = grid[(*currentRow) - 1][(*currentCol) - 1];                   //Same thing as said above
        int num1 = cell_1 - '0';
        int num2 = cell_2 - '0';
        int num3 = cell_3 - '0';
        sum = num1 + num2 + num3;                                             //adding the value of the neighbors
        return sum;
    } 


    else if(*currentRow == 0){                                                //top edge of the grid
        cell_1 = grid[*currentRow][(*currentCol) - 1];                        //accessing the cell's neighbors. This time there are 5 of them
        cell_2 = grid[*currentRow][(*currentCol) + 1];                        
        cell_3 = grid[(*currentRow) + 1][(*currentCol) - 1];
        cell_4 = grid[(*currentRow) + 1][*currentCol];
        cell_5 = grid[(*currentRow) + 1][(*currentCol) + 1];
        int num1 = cell_1 - '0';                                              //converting the contents of the cell's neighbors into integers
        int num2 = cell_2 - '0';
        int num3 = cell_3 - '0';
        int num4 = cell_4 - '0';
        int num5 = cell_5 - '0';

        sum = num1 + num2 + num3 + num4 + num5;                               //adding up the values of the neghbors of num1, num2, num3, num4, and num5
        return sum;
    }


    else if(*currentCol == 0){                                                //left edge of the grid
        cell_1 = grid[(*currentRow) + 1][*currentCol];                        //accessing the cell's neighbors. This time there are 5 of them.
        cell_2 = grid[(*currentRow) - 1][*currentCol];
        cell_3 = grid[(*currentRow) - 1][(*currentCol) + 1];
        cell_4 = grid[*currentRow][(*currentCol) + 1];                        //converting the contents of the cell's neighbors into integers
        cell_5 = grid[(*currentRow) + 1][(*currentCol) + 1];
        int num1 = cell_1 - '0';
        int num2 = cell_2 - '0';
        int num3 = cell_3 - '0';
        int num4 = cell_4 - '0';
        int num5 = cell_5 - '0';
        sum = num1 + num2 + num3 + num4 + num5;
        return sum;
    }


    else if(*currentRow == numRows - 1){                                      //bottom edge of the grid
        cell_1 = grid[*currentRow][(*currentCol) - 1];                        //accessing the cell's neighbors
        cell_2 = grid[*currentRow][(*currentCol) + 1];                        // same thing said above
        cell_3 = grid[(*currentRow) - 1][(*currentCol) - 1];
        cell_4 = grid[(*currentRow) - 1][*currentCol];
        cell_5 = grid[(*currentRow) - 1][(*currentCol) + 1];
        int num1 = cell_1 - '0';                                              // converting the cell's neighbors into integers.
        int num2 = cell_2 - '0';
        int num3 = cell_3 - '0';
        int num4 = cell_4 - '0';
        int num5 = cell_5 - '0';
        sum = num1 + num2 + num3 + num4 + num5;                              
        return sum;
    }


    else if(*currentCol == numCols - 1){                                     //right edge of the grid
        cell_1 = grid[*currentRow][(*currentCol) - 1];                       //accessing the cell's neighbors
        cell_2 = grid[(*currentRow) + 1][*currentCol];                       // same thing said above
        cell_3 = grid[(*currentRow) - 1][*currentCol];
        cell_4 = grid[(*currentRow) + 1][(*currentCol) - 1];
        cell_5 = grid[(*currentRow) - 1][(*currentCol) - 1];
        int num1 = cell_1 - '0';
        int num2 = cell_2 - '0';
        int num3 = cell_3 - '0';
        int num4 = cell_4 - '0';
        int num5 = cell_5 - '0';
        sum = num1 + num2 + num3 + num4 + num5;
        return sum;
    }


    else{                                                             //If a cell happens to be in the middle of the grid, then it will have all 8 neighbors.
        cell_1 = grid[*currentRow][(*currentCol) - 1];                //That is why I initalized all 8 cells in the beginning of the function because
        cell_2 = grid[*currentRow][(*currentCol) + 1];                //the maximum number of neighbors a cell will have is 8
        cell_3 = grid[(*currentRow) + 1][(*currentCol) - 1];
        cell_4 = grid[(*currentRow) + 1][(*currentCol) + 1];          //accessing all 8 neighbors of the cell in the middle of the grid
        cell_5 = grid[(*currentRow) + 1][*currentCol];
        cell_6 = grid[(*currentRow) - 1][*currentCol];
        cell_7 = grid[(*currentRow) - 1][(*currentCol) - 1];
        cell_8 = grid[(*currentRow) - 1][(*currentCol) + 1];
        int num1 = cell_1 - '0';                                      //converting all of their contents into integers
        int num2 = cell_2 - '0';
        int num3 = cell_3 - '0';
        int num4 = cell_4 - '0';
        int num5 = cell_5 - '0';
        int num6 = cell_6 - '0';
        int num7 = cell_7 - '0';
        int num8 = cell_8 - '0';
        sum = num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8;      //adding the values of all 8 neighbors
        return sum;

    }

}

