/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework_calendar;

/**
 *
 * @author agriffith
 */
public class Date {
    int year, month, day;
    //constructor
    public Date(int yr, int mth, int dy){
    year = yr;
    if (yr < 2000  || yr > 2100)
    {
        System.out.println("Wrong Calander Year");
        System.exit(1);
    }
    month = mth;
    if (mth < 1 || mth > 12)
    {
        System.out.println("Wrong Month");
        System.exit(1);
    }
    day = dy;
    if (dy < 1 || dy > 31)
    {
        System.out.println("Wrong Day");
        System.exit(1);
    }

    }
    //accessor methods
    public int getYear()
    {
        return year;
    }
    public int getMonth()
    {
        return month;
    }
    public int getDay()
    {
        return day;
    }
    //returns date in correct format
    public String toString()
    {
        return "" + month + "/" + day + "/" + year;
    }
    }