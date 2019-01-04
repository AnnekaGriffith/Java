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
public class Event {
    Date date;
    int hour;
    String activity;

    Event(int year, int month, int day, int hour, String activity)
    {
        if (year < 2000  || year > 2100)
        {
            System.out.println("Wrong Calander Year");
            System.exit(1);
        }
        if (month < 1 || month > 12)
        {
            System.out.println("Wrong Month");
            System.exit(1);
        }
        if (day < 1 || day > 31)
        {
            System.out.println("Wrong Day");
            System.exit(1);
        }
        this.date = new Date(year, month, day);
        this.hour = hour;
        this.activity = activity;

    }
    public Date getDate()
    {
    return date;    
    }
    public int getHour()
    {
        return hour;    
    }
    public String getActivity()
    {
        return activity;
    }
    void setActivity(String newActivity) 
    {
        this.activity = newActivity;
    }
    public String toString()
    {
    return "" + date +" " + "@" + hour +":" + " " + activity;
    }
    public boolean equals(Object obj)
    {
    if (obj instanceof Event)   
    {
        return true;
    }
    else return false;
    }
    }
