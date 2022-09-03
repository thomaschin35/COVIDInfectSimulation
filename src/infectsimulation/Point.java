/*
This class represents a point in a line graph
 */
package infectsimulation;

import java.awt.Color;
import java.awt.Graphics;


public class Point
{
    private int time = 0;
    private int infected = 0;
    public Point(int time, int infected)
    {
        this.time = time;
        this.infected = infected;
        
    }
    public int getTime()
    {
        return this.time;
    }
    public int getInfected()
    {
        return this.infected;
    }
    
}
