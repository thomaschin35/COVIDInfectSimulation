/*
This panel simulates an infection in a community
 */
package infectsimulation;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulationPanel extends JPanel implements ActionListener
{

    private Timer timer;
    private ArrayList<Individual> population = new ArrayList<Individual>();
    private int width;
    private int height;
    
    private boolean socialDistancing;
    private boolean regionalQuarantine;
    
    
    public static int number = 0;
    public static int days = 0;
    public static int ms = 0;

    public SimulationPanel(int width, int height, int numberOfPeople, boolean socialDistancing, boolean regionalQuarantine)
    {
        this.width = width;
        this.height = height;
        this.socialDistancing = socialDistancing;
        this.regionalQuarantine = regionalQuarantine;
        
        this.setPreferredSize(new Dimension(this.width,this.height));
        this.setLayout(new FlowLayout());
        this.setBackground(Color.LIGHT_GRAY);

        //creating a population
        for (int i = 0; i < numberOfPeople; i++)
        {
            population.add(new Individual(this.width, this.height,this.socialDistancing, this.regionalQuarantine));
        }

        //Timer for animation
        this.timer = new Timer(20, this); //1st parameter = miliseconds. Activates actionPerformed everytime timer restarts (20ms)
        this.timer.restart();
        
    }

    //coloring the population
    @Override
    public void paint(Graphics g)
    {
        
        //paints all the individuals
        super.paintComponent(g);

        for (Individual person : population)
        {
            person.paint(g);
        }
        if(regionalQuarantine)
        {
            drawWalls(g);
        }
      
        //creates pairs to detect collision
        for (int i = 0; i < population.size(); i++)
        {
            for (int j = 0; j < population.size(); j++)
            {
                if (i != j)
                {

                    Individual p1 = population.get(i);
                    Individual p2 = population.get(j);

                    boolean collided = p1.collided(p2);

                    if (collided)
                    {
                        
                        p1.veloX *= -1;
                        p1.veloY *= -1;
                        p1.infect(p2);
//                        p1.checkCollision(p2);
                    }

                }

            }
        }

    }
    private void drawWalls(Graphics g)
    {
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g; // a more complex Graphics class used to draw Objects
        
        //constructing a dotted line:
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 10 }, 0);
        g2d.setStroke(dashed);
        
        //draw wall
        g.drawLine(0,this.height/2,this.width*9/10, this.height/2);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.repaint();
        ms++;
        if(ms % 50 == 0)
        {
            days++;
        }
    }
    public void stopTimer()
    {
        this.timer.stop();
    }
    public void startTimer()
    {
        this.timer.restart();
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setBounds(600, 150, 700, 700);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        SimulationPanel simulationPanel = new SimulationPanel(700,700,75,false,false);
        
        frame.add(simulationPanel, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
}
