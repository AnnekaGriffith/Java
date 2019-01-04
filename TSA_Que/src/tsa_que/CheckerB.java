package tsa_que;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Anneka Bath
 */

public class CheckerB implements Runnable{
    
    private LinkedBlockingQueue <Line> queueB;
    private LinkedBlockingQueue <Line> queueC;
    private MakeQue Que;
    double totalTime;
    /*
    CheckerB:
    constructs CheckerB and recives :queueB, queueC, and Que. These three items 
    are instantiated in the constructor. 
    */
    public CheckerB(LinkedBlockingQueue <Line> queueB, LinkedBlockingQueue <Line> 
            queueC, MakeQue Que) {
        
        this.queueB = queueB;
        this.queueC = queueC;
        this.Que = Que;
    }
    /*
    run:
    runs CheckerB class which needs an override to become an abstract. This 
    operation praforms when Que (class MakeQue) is running. If its running it 
    checks to see if queueB is empty. if it isn't the then it performs the method within:
    -checks to see if remainingCapacity in queueB is greater than 10 if it is random 
    number is generated between 1-15.
    -checks to see if remainingCapacity in queueB is greater than 5 if it is random 
    number is generated between 1-10.
    -else if remaining capacity > 5 then random number is generated between 1-5.
    then time is set to line.
    once everything in the Queue is processed tells completion time, and final element
    */
    @Override
    public void run() {
        while (Que.isRunning()) {
            try {
                
                while(!queueB.isEmpty())
                {
                    System.out.println("B\tElements right now B: " + queueB);
                    int wait =0;
                    if(queueB.remainingCapacity()> 10)
                    {
                        Random value = new Random();
                        wait = value.nextInt(15)+ 1;
                    }
                    else if(queueB.remainingCapacity()> 5)
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
                    System.out.print("person being removed from B: ");
                    double waitTime = queueB.peek().getTime();
                    waitTime = waitTime + wait;
                    queueB.peek().setTime(waitTime);
                    queueB.peek().print();
                    Line value = queueB.peek();
                    System.out.println("B--\tRemoving element B: "  +  
                            queueB.take() + " wait time:" + wait + " minutes");
                    queueC.put(value);  
                }
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
        double newtime = System.nanoTime();
        System.out.println("Queue B Completed.");
        System.out.println("Queue B Completed time: " + newtime); //completion time
        System.out.println("Final elements in the queue B: " + queueB); //final element(empty)
    }
}