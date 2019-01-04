package tsa_que;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
/**
 *
 * @author Anneka Bath
 */
public class MakeQue implements Runnable {
    private LinkedList <Line> N;
    private LinkedBlockingQueue queueA;
    private LinkedBlockingQueue queueB;
    private boolean running;
    /*
    setQue:
    recieves Linked list N from TSA_Que and instantiates linkedList N.
    */
    public void setQue (LinkedList <Line> N)
    {
        this.N = N;
    }
    /*
    getQue:
    retuns Linikedlist N when called.
    */
    public LinkedList <Line> getQue()
    {
        return N;
    }
    /*
    MakeQue:
    constructs MakeQue and recives :queueB, and queueA. These two items 
    are instantiated in the constructor.
    */
    public MakeQue(LinkedBlockingQueue queueA, LinkedBlockingQueue queueB) {
        this.queueA = queueA;
        this.queueB = queueB;
        running = true;
    }
    
    /*
    isRunning:
    checks to see if running by returning the boolean value running.
    */
    public boolean isRunning() {
        return running;
    }
    /*
    run:
    runs MakeQue class which needs an override to become an abstract. This 
    operation preforms while NLine (getQue: N) is not empty. if it isn't the 
    then it performs the method within:
    -checks to see if Nline peeks TSA value is 1. if it is 1 then its put to queue A.
    A random number between 1-10 is created if queueA is not empty and assigned to wait if 
    queueA was empty them wait is 0. Then Nline removes its peek item
    -else then its put to queue B.A random number between 1-10 is created if queueB 
    is not empty and assigned to wait if queueB was empty them wait is 0. Then 
    Nline removes its peek item
    once everything in the Queue is processed tells us it's completed and running is 
    set to false. 
    */

    @Override
    public void run() {
        
        // We are adding elements using put() which waits
        // until it can actually insert elements if there is
        // not space in the queue.
        LinkedList <Line> Nline = getQue();

        while(!Nline.isEmpty())
        {   
            
            try 
            {
                if ( (Nline.peek().getTSALine())== 1)
                {   
                    Line Avalue = Nline.peek();
                    double wait =0;
                    double newtime = System.nanoTime();
                    if(!queueA.isEmpty())
                    {
                        Random value = new Random();
                        wait = 1.0 + (10.0 - 1.0) * value.nextDouble();                    
                        Thread.sleep((long) (1000 * wait));// casted to long
                        newtime = System.nanoTime();//shows that these items are put in at a unique time
                    }
                    System.out.println("A\t" + Avalue + " enter line: " + newtime + " minutes");                    
                    Avalue.setTime(wait);
                    System.out.println("A++\tAdding element A: " );
                    Avalue.print();
                    Thread.sleep(0);
                    queueA.put(Avalue);
                    Nline.remove();
                }
                else
                {
                    Line Bvalue = Nline.peek();
                    double wait = 0;
                    double newtime = System.nanoTime();
                    if(!queueB.isEmpty())
                    {
                        Random value = new Random();
                        wait = 1.0 + (10.0 - 1.0) * value.nextDouble();
                        Thread.sleep((long) (1000 * wait));//casted to long
                        newtime = System.nanoTime();//shows that these items are put in at a unique time
                    }
                    System.out.println("A\t" + Bvalue + " enter line: " + newtime + " minutes");  
                    Bvalue.setTime(wait);
                    System.out.println("B++\tAdding element B: ");
                    Bvalue.print();
                    Thread.sleep(0);
                    queueB.put(Bvalue);
                    Nline.remove(); 
                }                        
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("N MakeQue Completed.");
        running = false;
    }

}
