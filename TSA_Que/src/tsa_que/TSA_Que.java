package tsa_que;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * simulation of a TSA line while Queues A, B, and C. Program simulates a line 
 * of 50 people and sends them through each check point. Each passenger is given 
 * a time stamp based on when they reach the queue. 
 * 
 * Each Queue has a checker that takes 1-15 minutes to process a person through.
 * each person enters through the queue at either Queue A or Queue B. Once they 
 * have gone through the first checker (either checker A or Checker B) they are 
 * sent to Queue C which is the final check point. Each Queue will have to check 
 * the length of their line to expedite the checker process. 
 * 
 * Each person entering a line will have a unique timestamp to avoid any 
 * confusion on who entered the line first. Once the program has prossed 50 
 * people the simulation ends. Each iteration of the simulation will have a max 
 * length that is set upon opening the attached file.   
 * 
 * ex: Queue A
 *      [][][][][][][]O
 *                      \ _  Queue C
 *     Queue B          /     [][][][][][][][]O
 *      [][][][][][][]O
 *  
 * key: [] = Queue spot     O = Checker
 * 
 * @author: Anneka Bath
 * date: 11/27/17
 * 
 * Issues: learning to set up Queues
 * 
 */
public class TSA_Que {
    private int lineMaxA;
    private int lineMaxB;
    private int TSAline;
    private String [] N;
    private Scanner maxLineQ;
    private Scanner people;

    /***************************************************************************
    OpenFile: opens test file and throws and error if one isn't found
    ***************************************************************************/
    public void openFile()
    {
        try
        {
            maxLineQ = new Scanner (new File ("C:\\Users\\agriffith\\Documents\\"
                    + "NetBeansProjects\\TSA_Que\\test\\TSA.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("no file found");
        }
    }
    /***************************************************************************
    readFile: Reads in the values and assigns then to numA, numB or 
    numC.  If they pass the validEntry test by being > 0 they as set to setA, setB, 
    and setTSALine. people Scanner opens file name.txt and collects a string array 
    of names to store in N which is set by setPeople. 
    ***************************************************************************/
    public void readFile()
    {
        try 
        {
            String numA = maxLineQ.next();
            String numB = maxLineQ.next();
            String numN = maxLineQ.next();
            int maxQueA = validEntry(numA);
            int maxQueB = validEntry(numB);
            int Nline = validEntry(numN);
            while (maxQueA < 0 || maxQueB < 0) 
            {
                String numA2 = maxLineQ.next();
                String numB2 = maxLineQ.next();
                String numN2 = maxLineQ.next();
                
                maxQueA = validEntry(numA2);
                maxQueB = validEntry(numB2);
                Nline = validEntry(numN2);
            } 
            people = new Scanner (new File ("C:\\Users\\agriffith\\Documents\\"
                    + "NetBeansProjects\\TSA_Que\\test\\name.txt"));
            String [] names = new String [Nline];
            for(int i= 0; i<Nline; i++)
            {
                String makeN = people.nextLine();
                names[i] = makeN;
            }
            people.close();
            setPeople(names);
            setA(maxQueA);
            setB(maxQueB);
            setTSAline(Nline);
        }
        catch(FileNotFoundException e)
        {
           System.out.println("not an int try again");
        }
    }
    /***************************************************************************
    closeFile: Closes the file that was opened
    ***************************************************************************/
    public void closeFile()
    {
        maxLineQ.close();  
    }
    /***************************************************************************
    setTSALine: Sets value for TSAline
     * @param testUserInput
     * @return num
    ***************************************************************************/
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
    /*
    setA:
    sets Max Queue capacity for Queue A
    */
    public void setA(int lineMaxA)
    {
        this.lineMaxA = lineMaxA;
    }
    /*
    setB:
    sets Max Queue capacity for Queue B
    */
    public void setB(int lineMaxB)
    {
        this.lineMaxB = lineMaxB;
    }
    /*
    getA;
    returns the max Queue capacity for Queue A 
    */
    public int getA()
    {
        return lineMaxA;
    }
    /*
    getB;
    returns the max Queue capacity for Queue B 
    */
    public int getB()
    {
        return lineMaxB;
    }
    /*
    setPeople;
    sets the string of names conatined within Array N
    */
    public void setPeople(String [] N)
    {
        this.N = N;
    }
    /*
    getPeople;
    returns the string of names conatined within Array N
    */
    public String [] getPeople()
    {
        return N;
    }
    /*
    sets how many people will go through the TSa Queue.
    */
    public void setTSAline(int TSAline)
    {
        this.TSAline = TSAline;
    }
    /***************************************************************************
    getTSAline: Gets value for TSAline
    return: TSAline value
    ***************************************************************************/
    public int getTSAline()
    {
        return TSAline;
    }
    /*
    makeLine:
    created the Linked list line elements by going through the array N and assigning 
    each person a lineValue which is a random value of 1 or 2. Unless A capacity is less 
    then 1 of B capacity is less then 1. Otehrwise ArrayN is assigned to whichever 
    line has capacity (Later on this tells you if it goes to 
    Quque A of QueueB) 
    */
    public LinkedList <Line> makeLine()
    {

        String [] nLine = getPeople();
        double time = 0;
        LinkedList<Line> arrayN = new LinkedList();
        for (String nLine1 : nLine) {
            if (getA() < 1) 
            {    
                int lineValue = 2;
                String name = nLine1;
                Line lineN = new Line(lineValue, name, time);
                arrayN.add(lineN);
            } 
            else if (getB() < 1) 
            {
                Random value = new Random();
                int lineValue = 1;
                String name = nLine1;
                Line lineN = new Line(lineValue, name, time);
                arrayN.add(lineN);
            } 
            else 
            {
                Random value = new Random();
                String name = nLine1;
                Random AorB = new Random();
                int lineValue = AorB.nextInt(2)+ 1;
                Line lineN = new Line(lineValue, name, time);
                arrayN.add(lineN);
            }
        }        
        return arrayN;
    }
    /*
    displayUI:
    runs the readfile and displays what is conatined within the files.
    */
    public void displayUI()
    {
        System.out.println("Please provide values for your Queue A, Queue B and "
                + "how many people are going through the TSA");
        readFile();
        System.out.println("you gave Queue A a max of " + getA() + " people.");
        System.out.println("you gave Queue B a max of " + getB() + " people.");
        System.out.println("The simulation will handle " + getTSAline() + " people.");
    }

    public static void main(String[] args) {
        
        TSA_Que TSA = new TSA_Que();
        TSA.openFile();
        
        //makes first test with: QueA's max 20, QueB'a max 30, tsaline has 50 
        TSA.displayUI();LinkedList <Line> N = TSA.makeLine();

        LinkedBlockingQueue <Line> QueA = new LinkedBlockingQueue<>(TSA.getA());
        LinkedBlockingQueue <Line> QueB = new LinkedBlockingQueue<>(TSA.getB());
        LinkedBlockingQueue <Line> QueC = new LinkedBlockingQueue<>();
        MakeQue QueLine = new MakeQue(QueA, QueB);
        QueLine.setQue(N);      
        
        CheckerA checkA = new CheckerA(QueA, QueC, QueLine);
        CheckerB checkB =new CheckerB(QueB, QueC, QueLine);
        CheckerC checkC =new CheckerC(QueC, QueLine);
        
        Thread MakeQThread = new Thread(QueLine);
        Thread AThread = new Thread(checkA);
        Thread BThread = new Thread(checkB);
        Thread CThread = new Thread(checkC);
        
        MakeQThread.start();
        AThread.start();
        BThread.start();
        CThread.start();
        TSA.closeFile();

    }
}
