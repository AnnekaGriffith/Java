package tsa_que;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author ozzma
 */
public class CheckerA implements Runnable {
    private LinkedBlockingQueue <Line>queueA;
    private LinkedBlockingQueue <Line> queueC;
    private MakeQue Que;
    double totalTime;
    /*
    CheckerA:
    constructs CheckerB and recives :queueA, queueC, and Que. These three items 
    are instantiated in the constructor. 
    */
    public CheckerA(LinkedBlockingQueue <Line> queueA,LinkedBlockingQueue <Line> queueC, MakeQue Que) {
        this.queueA= queueA;
        this.queueC = queueC;
        this.Que = Que;
    }
    /*
    run:
    runs CheckerA class which needs an override to become an abstract. This 
    operation praforms when Que (class MakeQue) is running. If its running it 
    checks to see if queueA is empty. if it isn't the then it performs the method within:
    -checks to see if remainingCapacity in queueA is greater than 10 if it is random 
    number is generated between 1-15.
    -checks to see if remainingCapacity in queueA is greater than 5 if it is random 
    number is generated between 1-10.
    -else if remaining capacity > 5 then random number is generated between 1-5.
    then time is set to line.
    once everything in the Queue is processed tells completion time, and final element
    */
    @Override
    public void run() {
        while (Que.isRunning()) {
            try 
            {  
                while(!queueA.isEmpty()){
                    
                    System.out.println("A\t Elements right now A: " + queueA);
                    int wait =0;
                    if(queueA.remainingCapacity()> 10)
                    {
                        Random value = new Random();
                        wait = value.nextInt(15)+ 1;
                    }
                    else if(queueA.remainingCapacity()> 5)
                    {
                        Random value = new Random();
                        wait = value.nextInt(10)+ 1;
                    }
                    else
                    {
                        Random value = new Random();
                        wait = value.nextInt(5)+ 1;
                    }

                    Thread.sleep(wait * 1000);
                    System.out.print("person being removed from A: ");
                    double waitTime = queueA.peek().getTime();
                    waitTime = waitTime + wait;
                    queueA.peek().setTime(waitTime);
                    queueA.peek().print();
                    Line value = queueA.peek();
                    System.out.println("A--\tRemoving element A: "  +  queueA.take() + " wait time:" + wait + " minutes");
                    queueC.put(value);
                }
                
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //once QueueA is completed program tells completion time, and final element in QueueA which should be empty.
        double newtime = System.nanoTime();
        System.out.println("Queue A Completed.");
        System.out.println("Queue A Completed time: " + newtime);//completion time
        System.out.println("Final elements in the queue A: " + queueA);//final element(empty)
        
    }
   
}
