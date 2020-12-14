import java.util.*;

class Person {
	protected String firstName;
	protected String lastName;
	protected int idNumber;
	
	// Constructor
	Person(String firstName, String lastName, int identification){
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = identification;
	}
	
	// Print person data
	public void printPerson(){
		 System.out.println(
				"Name: " + lastName + ", " + firstName 
			+ 	"\nID: " + idNumber); 
	}
	 
}

class Student extends Person{
    
	private int[] testScores;
    /*	
    *   Class Constructor
    *   
    *   @param firstName - A string denoting the Person's first name.
    *   @param lastName - A string denoting the Person's last name.
    *   @param id - An integer denoting the Person's ID number.
    *   @param scores - An array of integers denoting the Person's test scores.
    */
    // Write your constructor here
    public Student(String firstName, String lastName, int IDnum, int[] testScores) 
    {
        super(firstName, lastName, IDnum);
        this.testScores = testScores;
    }

    /*	
    *   Method Name: calculate
    *   @return A character denoting the grade.
    */
    // Write your method here
    public  char calculate() 
    {
        double a = Arrays.stream(testScores).average().orElse(Double.NaN);
        if (a < 40) 
        {
            return 'T';
        }
        else if (a > 39 && a < 55)
        {
            return 'D';
        }
        else if (a > 54 && a < 70)
        {
            return 'P';
        }
        else if (a > 69 && a < 80)
        {
            return 'A';
        }
        else if (a > 79 && a < 90)
        {
            return 'E';
        }
        else
        {
            return 'O';
        }
    }
}

class Solution {