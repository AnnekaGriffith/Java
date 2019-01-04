/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package matrixoperation;
import static java.sql.DriverManager.println;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

/**
* Matrix Operations Class: general NxM matrix where generic matric class
* accepts user input and generates a random matrix.
*
* example: 2x2 matrix: A = 23 54 where A[i][j] is a random number from 0 to 99
* 98 97
* then design matrix to do:
* Matrix Addition (2x2, 3x5)
* Matrix Multiplication (2x2, 3x5)
*
* @author Anneka Bath
* * date 10/23/17
* Issues: the Read file was difficult because I had to be sure I was receiving 
* integer values and setting them properly. I couldn't just do a try catch 
* exception because the last value was set and stored for the same instance of 
* the class. I would have rewritten my classes different so I wouldn't have such 
* a large main section but due to time I decided this worked and did things correctly. 
* So I should leave it as is. the Matric Multiplication was particularly tricky 
* because it needed to be properly set according to the column and length values 
* of the two matrixes and this was a bit difficult to keep track of. this required 
* a lot of testing a research or how to multiply arrays. eventually I used my k 
* counter to be the shared value in array one and two.  
*/
public class MatrixOperation {

/**
* @param args the command line arguments
*/
//
    private int [][] A;//an Array that stores a Matrix
    private int m,n;//m is the # of columns, n is the # of rows
    private Scanner matrix;
    //******************************************************************
    //OpenFile: opens test file and throws exception if no file is found
    //******************************************************************
    public void openFile()
    {
        try
        {
            matrix = new Scanner (new File ("C:\\Users\\agriffith\\Documents\\"
                    + "NetBeansProjects\\MatrixOperation\\test\\Matrix.txt"));
        }
        catch(Exception e )
        {
        System.out.println("no file");
        }
    }
    //*************************************************************************
    //readFile: reads file and finds n and m values and sets them.Recieves a
    //string value from the file and sends it to validEntry where it is checked
    //to make sure its a valid entry and given an integer value -1 or more. If
    //it's value is less then 0 readFile will get the next 2 items in the file
    //till it gets two integer values that are greater than 0. Once two integer
    //values are retreived they are set to SetN and SetM.
    //**************************************************************************
    public void readFile()
    {
        try
        {

            String numN = matrix.next();
            String numM = matrix.next();
            int validN = validEntry(numN);
            int validM = validEntry(numM);
            while (validN < 0 || validM < 0)
            {
                String numN2 = matrix.next();
                String numM2 = matrix.next();
                validN = validEntry(numN2);
                validM = validEntry(numM2);
            }
            setN(validN);
            setM(validM);

        }
        catch(Exception e )
        {
            System.out.println("\n");
            System.out.println("not an int try again");
        }
    }
    //**************************************
    //closeFile: colses file that was opened
    //**************************************
    public void closeFile()
    {
        matrix.close();
    }
    //***************************
    //Set commands for m, n, & A.
    //***************************
    public void setM (int m)
    {
        this.m = m;
    }
        public void setN (int n)
    {
        this.n = n;
    }
        public void setA (int A[][])
    {
        this.A = A;
    }
    //************************
    //Get calls for m, n, & A.
    //Returns: m,n,& A
    //************************
    public int getM ()
    {
        return m;
    }
    public int getN()
    {
        return n;
    }
    public int[][] getA ()
    {
        return A;
    }

    //***********************************************************************
    //validEntry: Recieves a string from read file once the read file is recieved
    //the lenght is checked through a counter to make sure the string contains an 
    //integer value if not the program sends out a message and num is set to -1. 
    //Else the lenght of tester which is the file's string value is check for 
    //digits at every character if there is a character that is a digit that 
    //information is convertered to an int and stored in num of not then num is 
    //changed to -1.
    //Return : num or -1
    //************************************************************************
    public int validEntry(String testUserInput)
    {
        int num = 0;
        String tester = testUserInput;
        if (tester.length()<1)
        {
            System.out.println("No integer value: Please provide an integer value");
            num = -1;
        }
        else
        {
            for (int i = 0; i<tester.length();i++)
            {
                char c = tester.charAt(i);
                if (Character.isDigit(c))
                {
                    Integer userNum = Integer.valueOf(tester);
                    num = userNum;
                }
                else
                {
                    System.out.println("invalid entry: Please provide an integer value");
                    num = -1;
                }
            }
        }
        return num;
    }

    //**************************************************************************
    //makeMatrix: gives int A array random numbers (0,99) for the N value of rows 
    //and the M value of columns each random number will be stored in a cell 
    //inside the matrix which is tracked by counters i(rows) and j(columns). 
    //Which step through the for loops where the rows will fill up with as many 
    //m values,and there will be as many rows as there are n values.
    // Returns: int A[][]
    //**************************************************************************
    public int[][] makeMatrix(int n, int m )
    {
        int A [][] = new int [n][m];

        for (int i = 0; i<n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                Random value = new Random();
                int cellValue = value.nextInt(99);
                A[i][j] = cellValue;
            }
        }
        return A;
    }
    //**************************************************************************
    //printMatrix: recieves an Array and formats it to print out like a matrix.
    //this is done by using getN and getM to import the n and m values to
    //appropiately space out each Array value.
    //**************************************************************************
    public void printMatrix(int A[][])
    {
        int rows =0;
        int column = 0;
        rows = getN();
        column =getM();
        for (int i = 0; i<rows; i++)
        {
            for (int j = 0; j < column; j++)
            {
                System.out.print(A[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    //*********************************************************************
    //User interface enviornment. it allows the user to create a new matrix
    //with just two lines in the main
    //**********************************************************************
    public void displayUI()
    {
        System.out.println("Please provide values for your rows and columns in "
                + "your matrix:");
        readFile();
        System.out.println("you entered " + getN() + " rows");
        System.out.println("you entered " + getM() + " columns");

        int Matrix1[][] = makeMatrix(getN(),getM());
        setA(Matrix1);
        printMatrix(getA());
    }
    //***********
    //main
    //************
    public static void main(String[] args)
    {
        //Instance MatrixA1 is run to read through the while file. 
        MatrixOperation MatrixA1 = new MatrixOperation ();
        
        //first two matrixes created  
        MatrixA1.openFile();
        MatrixA1.displayUI();
        int [][] A1 = MatrixA1.getA();
        int A1rows = MatrixA1.getN();
        int A1columns = MatrixA1.getM();

        MatrixA1.displayUI();
        int [][] A2 = MatrixA1.getA();
        int A2rows = MatrixA1.getN();
        int A2columns = MatrixA1.getM();

        MatrixMultiplication multi = new MatrixMultiplication ();
        MatrixAddition add = new MatrixAddition ();

        multi.multiCheck(A1, A2, A1rows, A2rows, A1columns, A2columns);
        add.addCheck(A1, A2, A1rows, A2rows, A1columns, A2columns);
        
        //second two matrixes created 
        System.out.println();
        MatrixA1.displayUI();
        int [][] A3 = MatrixA1.getA();
        int A3rows = MatrixA1.getN();
        int A3columns = MatrixA1.getM();

        MatrixA1.displayUI();
        int [][] A4 = MatrixA1.getA();
        int A4rows = MatrixA1.getN();
        int A4columns = MatrixA1.getM();

        MatrixMultiplication multi2 = new MatrixMultiplication ();
        MatrixAddition add2 = new MatrixAddition ();

        multi2.multiCheck(A3, A4, A3rows, A4rows, A3columns, A4columns);
        add2.addCheck(A3, A4, A3rows, A4rows, A3columns, A4columns);
        
        //third two matrixes created 
        System.out.println();
        MatrixA1.displayUI();
        int [][] A5 = MatrixA1.getA();
        int A5rows = MatrixA1.getN();
        int A5columns = MatrixA1.getM();

        MatrixA1.displayUI();
        int [][] A6 = MatrixA1.getA();
        int A6rows = MatrixA1.getN();
        int A6columns = MatrixA1.getM();

        MatrixMultiplication multi3 = new MatrixMultiplication ();
        MatrixAddition add3 = new MatrixAddition ();

        multi3.multiCheck(A5, A6, A5rows, A6rows, A5columns, A6columns);
        add3.addCheck(A5, A6, A5rows, A6rows, A5columns, A6columns);
        MatrixA1.closeFile();

    }

}