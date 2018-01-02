Assignment:

In this assignment you will discover the circles of friends from actual (anonymised) Facebook data, obtained from the SLN Dataset.

A Facebook friendship is an undirected relationship between two people. In this assignment we will consider that "my friend's friend is my friend"; i.e., that the friendship relationship is transitive. A Facebook user's circle of friends is the largest set of her friends. Note that two facebook users who are friends have the same circle of friends.

Given that the average Facebook user has a few hundred direct friends, and that many people believe the six-degress-of-separation theory, is it true that all Facebook users are in a huge circle of friends?

Well, we don't have the data to answer this question, but we can answer related questions in samples of Facebook's data.

Q1: Implementation
Download the template FacebookCircles.java and implement the following methods:

public FacebookCircles(int numberOfFacebookUsers): the constructor creates a new object of the class initialised to a fixed number of facebook users. Each user will be represented by an integer from 0 to numberOfFacebookUsers - 1.
public void friends( int user1, int user2 ): declares that two users are friends.
public int numberOfCircles(): returns the number of friendship circles.
public int sizeOfLargestCircle(): returns the size of the the largest friendship circle.
public int sizeOfAverageCircle(): returns the average size of the friendship circles. The average should be computed as (s1 + s2 + ... + sn) /n, where "/" is integer division.
public int sizeOfSmallestCircle(): returns the size of the smallest friendship circle.
You should add at least one test per method in the FacebookCirclesTest.java file.

You should select what you think is the most efficient implementation for this assignment, revising the implementations we have discussed in the module so far.

Q2: Compute Friendship statistics
The file FacebookCirclesTest.java contains five testXXXX() methods, where XXXX is a number, and one testAll() method. These methods load actual anonymised friendship data from the files you will find in facebook-data.zip. Place these files in the same directory as your source code (or import them in your Eclipse project) and test that your code computes the right values.

You will also be marked for computing this value in a reasonable amount of time (few seconds). Thus to get full marks for this assignment you should implement the most efficient algorithms in Q1. The submission server will timeout your program if your tests take too long. The largest input data contain ~76.000 users and ~230.000 friendship relations.
