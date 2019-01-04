
package tsa_que;

/**
 *
 * @author Anneka Bath
 */

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class CheckerC implements Runnable {
    
    private LinkedBlockingQueue <Line> queueC;
    private MakeQue QueC;
    double totalTime;
    /*
    CheckerC:
    constructs CheckerC and recives : queueC, and QueC. These two items are 
    instantiated in the constructor. 
    */
    public CheckerC(LinkedBlockingQueue <Line> queueC, MakeQue QueC)
    {
        this.queueC= queueC;
        this.QueC = QueC;
    }
 /*
    run:
    runs CheckerC class which needs an override to become an abstract. This 
    operation praforms when Que (class MakeQue) is running. If its running it 
    checks to see if queueC is empty. if it isn't the then it performs the method within:
    -checks to see if queueC's size is less than 5 if it is random number is 
    generated between 1-15.
    -checks to see if queueC's size is less than 10 if it is random number is 
    generated between 1-10.
    -else if queueC's size is greater than 10 then random number is generated between 1-5.
    then time is set to line.
    once everything in the Queue is processed tells completion time, total wait times,  
    and final element
    */
  
@Override
    public void run() {
        
        // As long as the producer is running,
        // we want to check for elements.
        while (QueC.isRunning()) {
            try 
            {
                while(!queueC.isEmpty())
                {
                    System.out.println("C\tElements right now C: " + queueC);
                    int wait =0;
                    if(queueC.size()< 5)
                    {
                        Random value = new Random();
                        wait = value.nextInt(15)+ 1;
                    }
                    else if(queueC.size()< 10)
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
                    System.out.print("person being removed from C: ");
                    double waitTime = queueC.peek().getTime();
                    waitTime = waitTime + wait;
                    queueC.peek().setTime(waitTime);
                    totalTime = totalTime + queueC.peek().getTime();
                    queueC.peek().print();
                    System.out.println("C--\tRemoving element C: "  + queueC.take() + " wait time:" + wait + " minutes");
                } 
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        //once QueueC is completed program tells you total wait times, completion time, and final element in Queue C which should be empty.
        double newtime = System.nanoTime();
        System.out.println("Queue C Completed.");
        DecimalFormat newFormat = new DecimalFormat("#.##");
        double twoDecimal =  Double.valueOf(newFormat.format(totalTime));
        System.out.println("Queue C totalTime:" + twoDecimal);
        System.out.println("Queue C Completed time: " + newtime);
        System.out.println("Final elements in the queue C: " + queueC);  
    }

}
