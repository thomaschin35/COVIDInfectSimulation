/*
This frame displays the simulation
 */
package infectsimulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulationFrame extends JFrame implements ActionListener
{

    private JPanel buttonPanel;
    private JPanel centerPanel;
    private JPanel borderCenterPanel;
    private JPanel borderEastPanel;
    private JPanel eastPanel;
    private JPanel flowPanel;
    private Box eastFlowBox;
    private Box eastBox;
    private JButton pauseButton;
    private JButton exitButton;
    private JButton returnButton;
    private JLabel healthyLabel;
    private JLabel infectedLabel;
    private JLabel recoveredLabel;
    private JLabel deadLabel;
    private JLabel timerLabel;

    private SimulationPanel simulationPanel;
    private GraphPanel graphPanel;

    private int numberOfPeople;
    private int simulationWidth;
    private int simulationHeight;
    private boolean socialDistancing;
    private boolean regionalQuarantine;

    //declaring colors
    public static final Color WHITE = new Color(236, 240, 241);
    public static final Color DARK_GREY = new Color(52, 73, 94);
    public static final Color LIGHT_GREY = new Color(127, 140, 141);
    public static final Color LIGHT_BLUE = new Color(52, 152, 219);
    public static final Color DARK_RED = new Color(192, 57, 43);
    public static final Color GREEN = new Color(46, 204, 113);

    //declaring fonts
    public static final Font MEDIUM_FONT = new Font("Berlin Sans FB", Font.PLAIN, 26);

    public static boolean repaint = true;
    public static boolean dispose = false;

    public SimulationFrame(int numberOfPeople, int simulationWidth, int simulationHeight, boolean socialDistancing, boolean regionalQuarantine)
    {
        this.numberOfPeople = numberOfPeople;
        this.simulationWidth = simulationWidth;
        this.simulationHeight = simulationHeight;
        this.socialDistancing = socialDistancing;
        this.regionalQuarantine = regionalQuarantine;

        this.setSize(1400, 900);
        this.getContentPane().setBackground(DARK_GREY);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //constructing JPanel
        this.buttonPanel = new JPanel(new FlowLayout());
        this.buttonPanel.setBackground(DARK_GREY);
        
        this.centerPanel = new JPanel(new FlowLayout());
        this.centerPanel.setBackground(DARK_GREY);
        
        this.eastPanel = new JPanel(new BorderLayout());
        this.eastPanel.setBackground(DARK_GREY);
        
        this.borderCenterPanel = new JPanel(new BorderLayout());
        this.borderCenterPanel.setBackground(DARK_GREY);
        this.borderCenterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        
        this.borderEastPanel = new JPanel(new BorderLayout());
        this.borderEastPanel.setBackground(WHITE);
        this.borderEastPanel.setSize(650,300);
        this.borderEastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));

        this.flowPanel = new JPanel(new FlowLayout());
        this.flowPanel.setBackground(DARK_GREY);

        this.simulationPanel = new SimulationPanel(simulationWidth, simulationHeight, numberOfPeople, socialDistancing, regionalQuarantine);
        this.graphPanel = new GraphPanel(numberOfPeople);
        
        //constructing box
        this.eastBox = Box.createVerticalBox();
        this.eastFlowBox = Box.createVerticalBox();  

        //constructing JLabel
        this.healthyLabel = new JLabel("" + Individual.numberOfHealthy);
        this.healthyLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.healthyLabel.setForeground(SimulationFrame.WHITE);
        
        this.infectedLabel = new JLabel("" + Individual.numberOfInfected);
        this.infectedLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.infectedLabel.setForeground(SimulationFrame.WHITE);
        
        this.recoveredLabel = new JLabel("" + Individual.numberOfRecovered);
        this.recoveredLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.recoveredLabel.setForeground(SimulationFrame.WHITE);
        
        this.deadLabel = new JLabel("" + Individual.numberOfDead);
        this.deadLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.deadLabel.setForeground(SimulationFrame.WHITE);
        
        this.timerLabel = new JLabel("" + SimulationPanel.days);
        this.timerLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.timerLabel.setForeground(SimulationFrame.WHITE);

        
        //constructing events
        event e = new event();

        //constructing JButton
        this.pauseButton = new JButton("Pause");
        this.pauseButton.setBackground(DARK_RED);
        this.pauseButton.setForeground(WHITE);
        this.pauseButton.addActionListener(e);

        this.exitButton = new JButton("Exit");
        this.exitButton.setBackground(DARK_RED);
        this.exitButton.setForeground(WHITE);
        this.exitButton.addActionListener(e);

        this.returnButton = new JButton("Return");
        this.returnButton.setBackground(DARK_RED);
        this.returnButton.setForeground(WHITE);
        this.returnButton.addActionListener(e);

        //adding objects to panel
        this.buttonPanel.add(pauseButton);
        this.buttonPanel.add(new JLabel("    "));
        this.buttonPanel.add(healthyLabel);
        this.buttonPanel.add(new JLabel("    "));
        this.buttonPanel.add(infectedLabel);
        this.buttonPanel.add(new JLabel("    "));
        this.buttonPanel.add(recoveredLabel);
        this.buttonPanel.add(new JLabel("    "));
        this.buttonPanel.add(deadLabel);
        this.buttonPanel.add(new JLabel("    "));
        this.buttonPanel.add(exitButton);
        this.buttonPanel.add(new JLabel("                             "));
        this.buttonPanel.add(returnButton);

        this.borderCenterPanel.add(simulationPanel, BorderLayout.CENTER);
        this.borderEastPanel.add(graphPanel, BorderLayout.CENTER);

        this.centerPanel.add(borderCenterPanel);
        this.eastPanel.add(borderEastPanel);
     
        this.eastFlowBox.add(timerLabel);
        this.eastFlowBox.add(new JLabel(" "));
        this.eastFlowBox.add(healthyLabel);
        this.eastFlowBox.add(new JLabel(" "));
        this.eastFlowBox.add(infectedLabel);
        this.eastFlowBox.add(new JLabel(" "));
        this.eastFlowBox.add(recoveredLabel);
        this.eastFlowBox.add(new JLabel(" "));
        this.eastFlowBox.add(deadLabel);
        
        this.flowPanel.add(new JLabel("                      "));
        this.flowPanel.add(eastFlowBox);
        
        this.eastBox.add(eastPanel);
        
        for(int i = 0; i < 10; i++)
        {
            this.eastBox.add(new JLabel(" "));
        }
        
        this.eastBox.add(flowPanel);
        
        for(int i = 0; i < 10; i++)
        {
            this.eastBox.add(new JLabel(" "));
        }
        

        //adding objects to frame
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(eastBox, BorderLayout.EAST);

        //Timer for animation
        Timer timer = new Timer(20, this); //1st parameter = miliseconds. Activates actionPerformed everytime timer restarts (20ms)
        timer.restart();
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new SimulationFrame(10, 200, 200, false, false);
    }

    //action performed for timer
    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.healthyLabel.setText("Healthy: " + Individual.numberOfHealthy);
        this.infectedLabel.setText("Infected: " + Individual.numberOfInfected);
        this.recoveredLabel.setText("Recovered: " + Individual.numberOfRecovered);
        this.deadLabel.setText("Dead: " + Individual.numberOfDead);
        this.timerLabel.setText("Day: " + SimulationPanel.days);
        if (repaint)
        {
            this.repaint();
        }
        if (dispose)
        {
            this.dispose();
        }
    }

    public class event implements ActionListener
    {

        //action performed for buttons
        public void actionPerformed(ActionEvent e)
        {
            String command = e.getActionCommand();
            if (command.equals("Pause"))
            {
                SimulationFrame.repaint = false;
                simulationPanel.stopTimer();
                pauseButton.setText("Resume");
                pauseButton.setBackground(GREEN);
            }
            if (command.equals("Resume"))
            {
                SimulationFrame.repaint = true;
                simulationPanel.startTimer();
                pauseButton.setText("Pause");
                pauseButton.setBackground(DARK_RED);

            }
            if (command.equals("Exit"))
            {
                System.exit(0);
            }
            if (command.equals("Return"))
            {
                new InputFrame();
                SimulationFrame.dispose = true;
                Individual.numberOfDead = 0;
                Individual.numberOfHealthy = 0;
                Individual.numberOfInfected = 0;
                Individual.numberOfRecovered = 0;

            }
        }
    }

}
