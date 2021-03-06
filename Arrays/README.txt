It's New Year's Day and everyone's in line for the Wonderland rollercoaster ride! There are a number of people queued up, and each person wears a sticker indicating their initial position in the queue. Initial positions increment by 1 from 1 at the front of the line to n at the back.

Any person in the queue can bribe the person directly in front of them to swap positions. If two people swap positions, they still wear the same sticker denoting their original places in line. One person can bribe at most two others. For example, if n =5 and person 5 bribes person 4, the queue will look like this: 1 2 3 4 5 6 7 8.

Fascinated by this chaotic queue, you decide you must know the minimum number of bribes that took place to get the queue into its current state!

Function Description

Complete the function minimumBribes in the editor below. It must print an integer representing the minimum number of bribes necessary, or Too chaotic if the line configuration is not possible.

minimumBribes has the following parameter(s):

q: an array of integers
Input Format

The first line contains an integer t, the number of test cases.

Each of the next t pairs of lines are as follows:
- The first line contains an integer t, the number of people in the queue
- The second line has n space-separated integers describing the final state of the queue.



Output Format

Print an integer denoting the minimum number of bribes needed to get the queue into its final state. Print Too chaotic if the state is invalid, i.e. it requires a person to have bribed more than 2 people.

Sample Input

2
5
2 1 5 3 4
5
2 5 1 3 4
Sample Output

3
Too chaotic
Explanation

Test Case 1

The initial state:

https://s3.amazonaws.com/hr-challenge-images/494/1451665589-31d436ba19-pic11.png

After person  moves one position ahead by bribing person :

https://s3.amazonaws.com/hr-challenge-images/494/1451665679-6504422ed9-pic2.png

Now person  moves another position ahead by bribing person :

https://s3.amazonaws.com/hr-challenge-images/494/1451665818-27bd62bb0d-pic3.png

And person  moves one position ahead by bribing person :

https://s3.amazonaws.com/hr-challenge-images/494/1451666025-02a2395a00-pic5.png

So the final state is 2 1 5 3 4  after three bribing operations.

Test Case 2

No person can bribe more than two people, so its not possible to achieve the input state.

