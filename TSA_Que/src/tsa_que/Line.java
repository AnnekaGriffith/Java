package tsa_que;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Anneka Bath
 */

public class Line {
private int tsaLine;
private String name;
private double time; 
/*
Line:
Constructor line is my valiable container for the Queue sequence.
*/
    public Line (int tsaLine, String name, double time)
    {
        this.tsaLine = tsaLine;
        this.name = name;
        this.time =time;
    }
    /*
    getTSALine:
    returns int tsaline. This tells you if the line goes throught Queue A or Queue B.
    */
    public int getTSALine ()
    {
        return tsaLine;
    }
    /*
    getName:
    returns name string contained in line. 
    */
    public String getName ()
    {
        return name;
    }
    /*
    getTime:
    returns double time contained in line.
    */
    public double getTime ()
    {
        return time;
    }
    /*
    setTime:
    updates the double time contained in line.
    */
    public void setTime(double time)
    {
        this.time = time;
    }
    /*
    print:
    formats time to two decimal places and prints out the variables conatined within a line.
    */
    public void print ()
    {
        DecimalFormat newFormat = new DecimalFormat("#.##");
        double twoDecimal =  Double.valueOf(newFormat.format(time));
        System.out.println(" " + tsaLine + " " + name +" "+ twoDecimal + " minutes");
    }
    
}