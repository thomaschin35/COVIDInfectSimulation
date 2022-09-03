/*
This class represents a single individual in a population
 */
package infectsimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Individual
{

    //position of the individual on the frame
    int x;
    int y;
    //velocity of the individual
    float veloX;
    float veloY;

    final int width;
    final int height;
    //status of individual (Healthy, Infected, Dead, Recovered)
    String status;

    //whether infected will recover or die
    Boolean recovery = false;
    Boolean death = false;

    //methods of simulation
    private Boolean socialDistancing = false;
    private Boolean regionalQuarantine;

    int collisionTime = 0;

    //each person will have a random recovery time
    int recoveryTime;
    int deathTime;

    //static variables to keep track of the number of types of individuals
    static int numberOfHealthy = 0;
    static int numberOfRecovered = 0;
    static int numberOfInfected = 0;
    static int numberOfDead = 0;

    public Individual(int width, int height, boolean socialDistancing, boolean regionalQuarantine)
    {
        this.width = width;
        this.height = height;
        this.socialDistancing = socialDistancing;
        this.regionalQuarantine = regionalQuarantine;
        
        SimulationFrame.dispose = false;

        //random position of individual on the frame
        x = (int) (Math.random() * (this.width - 10));
        y = (int) (Math.random() * (this.height - 10));

        setVelo();

        //used random number formula: Math.random() * (max - min + 1) + min
        //random recovery and death rate
        recoveryTime = (int) (Math.random() * (15000 - 5000 + 1) + 5000); //random recovery time of 5-15 seconds
        deathTime = (int) (Math.random() * (15000 - 5000 + 1) + 5000); //random death time of 5-15 seconds

        //10 % of the individuals are infected
        if (Math.random() < 0.1)
        {
            status = "Infected";
            numberOfInfected++;
            if (regionalQuarantine)
            {
                y = (int) (Math.random() * (this.height/2 - 10));
            }
        }
        else
        {
            status = "Healthy";
            numberOfHealthy++;
        }

        //50% of the infected will recover or die
        if (Math.random() < 0.5)
        {
            if (Math.random() < 0.5)
            {
                recovery = true;
            }
            else
            {
                death = true;
            }
        }

        //if the individual is dead, stop veolcity
        if (status.equals("Dead"))
        {
            veloX = 0;
            veloY = 0;
        }

    }

    //infection when collided
    public void infect(Individual p)
    {

        if (this.status.equals("Infected") && p.status.equals("Healthy"))
        {
            if (Math.random() > 0.5) //50% chance to infect
            {
                p.status = "Infected";
                numberOfInfected++;
                numberOfHealthy--;
            }

        }
        if (this.status.equals("Healthy") && p.status.equals("Infected"))
        {
            if (Math.random() > 0.5) //50% chance to infect
            {
                this.status = "Infected";
                numberOfInfected++;
                numberOfHealthy--;
            }
        }
    }

    //checks if collision is working properly
    public void checkCollision(Individual p)
    {
        collisionTime++;
        if (collisionTime < 2)
        {
            this.x += (int) (Math.random() * (3 + 3 + 1) - 3);
            this.y += (int) (Math.random() * (3 + 3 + 1) - 3);
            p.x += (int) (Math.random() * (3 + 3 + 1) - 3);
            p.y += (int) (Math.random() * (3 + 3 + 1) - 3);
            collisionTime = 0;
        }
    }

    public boolean collided(Individual p)
    {
        double dist = Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));

        boolean collided = (dist < 10 / 2.0 + 10 / 2.0);

        return collided;
    }

    //paint and renders the individuals
    public void paint(Graphics g)
    {
        if (this.status.equals("Healthy"))
        {
            g.setColor(Color.GREEN);
        }
        else if (this.status.equals("Infected"))
        {
            g.setColor(Color.RED);
        }
        else if (this.status.equals("Recovered"))
        {
            g.setColor(Color.BLUE);
        }
        else if (this.status.equals("Dead"))
        {
            g.setColor(Color.GRAY);
        }

        //adds movement to individuals
        x += veloX;
        y += veloY;

        //keeps individuals in frame. If a individual hits the edge of a frame, velocity is reversed
        if (x < 0 || x > this.width - 10)
        {
            veloX *= -1;
        }
        if (y < 0 || y > this.height - 10)
        {
            veloY *= -1;

        }

        //collision for walls
        if (regionalQuarantine)
        {
            if (y < height / 2 && y > height / 2 - 10 && x < width*9/10)
            {
                veloY *= -1;
            }
        }

        //recovery of individuals
        if ((status.equals("Infected")) && (recovery == true))
        {
            recoveryTime = recoveryTime - 20;
            if (recoveryTime < 0)
            {
                status = "Recovered";
                numberOfRecovered++;
                numberOfInfected--;
            }
        }

        //death of individuals
        if ((status.equals("Infected")) && (death == true))
        {
            deathTime = deathTime - 20;
            if (deathTime < 0)
            {
                status = "Dead";
                this.veloX = 0;
                this.veloY = 0;
                numberOfDead++;
                numberOfInfected--;
            }
        }

        //create a oval with position x,y and width and height of 10 pixels
        g.fillOval(x, y, 10, 10);
    }

    public static void main(String[] args)
    {
        Individual person = new Individual(700, 700, false, false);
    }

    public void setPosition()
    {
        x = (int) (Math.random() * (this.width - 10));
        y = (int) (Math.random() * (this.height - 10));
    }

    public void setVeloX(float veloX)
    {
        this.veloX = veloX;

    }

    public float getVeloX()
    {
        return this.veloX;
    }

    public void setVeloY(float veloY)
    {
        this.veloY = veloY;

    }

    public float getVeloY()
    {
        return this.veloY;
    }

    public void setVelo()
    {
        if (!socialDistancing)
        {
            //used random number formula: Math.random() * (max - min + 1) + min
            veloX = (int) (Math.random() * (3 + 3 + 1) - 3); // random velocity between -3 - 3
            veloY = (int) (Math.random() * (3 + 3 + 1) - 3); // random velocity between -3 - 3

        }
        if (veloX == 0)
        {
            if (Math.random() > 0.5)
            {
                veloX = (int) (Math.random() * (3 - 1 + 1) + 1); // random velocity between 1 - 3
            }
            else
            {
                veloX = (int) (Math.random() * (-1 + 3 + 1) - 3); // random velocity between -3 - -1
            }
        }
        if (veloY == 0)
        {
            if (Math.random() > 0.5)
            {
                veloY = (int) (Math.random() * (3 - 1 + 1) + 1); // random velocity between 1 - 3
            }
            else
            {
                veloY = (int) (Math.random() * (-1 + 3 + 1) - 3); // random velocity between -3 - -1
            }
        }
        if (socialDistancing)
        {
            //50% of the population dont move when there is social distancing
            if (Math.random() > 0.5)
            {
                //used random number formula: Math.random() * (max - min + 1) + min
                veloX = (int) (Math.random() * (3 + 3 - 1) - 3); // random velocity between -5-5
                veloY = (int) (Math.random() * (3 + 3 - 1) - 3); // random velocity between -5-5
            }
            else
            {
                veloX = 0;
                veloY = 0;
            }
        }

    }

}
