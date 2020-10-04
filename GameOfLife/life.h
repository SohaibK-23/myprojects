// life.h
// -- EE 312 Project 2

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
 *///
// Created by priebe on 9/5/2018.
//

#ifndef UNTITLED1_LIFE_H
#define UNTITLED1_LIFE_H

#endif //UNTITLED1_LIFE_H

void populateWorld(char fName[], char *grid[], int *numRows, int *numCols);

void showWorld(char *grid[], int numRows, int numCols);

void iterateGeneration(char *grid[], int numRows, int numCols);

int navigate(char *grid[], int *currentRow, int *currentCol, int numRows, int numCols);
