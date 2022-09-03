/*
This class represents the graph between time and infected
 */
package infectsimulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphPanel extends JPanel
{

    private ArrayList<Point> points = new ArrayList<Point>();
    private int population;

    public GraphPanel(int population)
    {
        this.population = population;
        this.setPreferredSize(new Dimension(650, 300));
        this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);

        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        //draw axis and axis titles
        g.setColor(Color.BLACK);
        g.drawLine(50, 250, 50, 70);
        g.drawLine(50, 250, 650, 250);
        g.drawString("Time (days)", 300, 275);
        g.drawString("# of ", 10, 175);
        g.drawString("infected", 5, 190);
        
        g.setColor(Color.GRAY);
        points.add(new Point(SimulationPanel.ms, Individual.numberOfInfected));

        //draw line to represent health capacity
        g.drawLine(50, (300 - (int) (this.population * 0.66 * ((int) (100 / population))) * 2) - 50, 650, (300 - (int) (this.population * 0.66 * ((int) (100 / population))) * 2) - 50);

        //draw points
        for (Point p : points)
        {
            if (p.getInfected() > (int) (this.population * 0.66))
            {
                g.setColor(Color.RED);
                g.drawString("Health capacity reached!", 275, 50);
            }
            else
            {
                g.setColor(Color.BLUE);

            }
            g.fillOval((p.getTime() / 3) + 50, (300 - (p.getInfected() * ((int) (100 / population))) * 2) - 50, 5, 5);
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(700, 900);
        GraphPanel graphPanel = new GraphPanel(75);
        frame.add(graphPanel);
        frame.setVisible(true);
    }

}
