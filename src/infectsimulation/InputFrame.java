/*
This class is responsible for the input
 */
package infectsimulation;


import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.*;


public class InputFrame extends JFrame implements ActionListener
{
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel eastPanel;
    private JPanel centerPanel;
    private JPanel westPanel;
    private JPanel populationPanel;
    private JPanel widthPanel;
    private JPanel heightPanel;
    private JPanel socialPanel;
    private JPanel quarantinePanel;
    private JLabel titleLabel;
    private JLabel populationNumberLabel;
    private JLabel populationLabel;
    private JLabel widthNumberLabel;
    private JLabel widthLabel;
    private JLabel heightNumberLabel;
    private JLabel heightLabel;
    private JButton startButton;
    private JButton returnButton;
    private JButton exitButton;
    private Box westBox;
    private JSlider populationSlider;
    private JSlider widthSlider;
    private JSlider heightSlider;
    private JCheckBox socialCheck;
    private JCheckBox quarantineCheck;
    
    //variables for input
    private int populationValue = 55;
    private int widthValue = 450;
    private int heightValue = 450;
    private boolean socialDistancing = false;
    private boolean regionalQuarantine = false;
    
    
    public InputFrame()
    {
        //setting up frame
        this.setBounds(200, 200, 700, 400);
        this.getContentPane().setBackground(SimulationFrame.DARK_GREY);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //constructing event
        event e = new event();
        
        //contructing JPanel
        this.southPanel = new JPanel(new FlowLayout());
        this.southPanel.setBackground(SimulationFrame.DARK_GREY);
        
        this.westPanel = new JPanel(new BorderLayout());
        this.westPanel.setBackground(SimulationFrame.DARK_GREY);
        
        this.populationPanel = new JPanel(new BorderLayout());
        this.populationPanel.setBackground(SimulationFrame.DARK_GREY);
        this.populationPanel.setPreferredSize(new Dimension(500,100));
        
        this.widthPanel = new JPanel(new BorderLayout());
        this.widthPanel.setBackground(SimulationFrame.DARK_GREY);
        this.widthPanel.setPreferredSize(new Dimension(500,100));
        
        this.heightPanel = new JPanel(new BorderLayout());
        this.heightPanel.setBackground(SimulationFrame.DARK_GREY);
        this.heightPanel.setPreferredSize(new Dimension(500,100));
        
        this.socialPanel = new JPanel(new BorderLayout());
        this.socialPanel.setBackground(SimulationFrame.DARK_GREY);
        this.socialPanel.setPreferredSize(new Dimension(500,100));
        
        this.quarantinePanel = new JPanel(new BorderLayout());
        this.quarantinePanel.setBackground(SimulationFrame.DARK_GREY);
        this.quarantinePanel.setPreferredSize(new Dimension(500,100));
 
        //constructing Box
        this.westBox = Box.createVerticalBox();
        
        //constructing JLabel
        this.populationLabel = new JLabel("  Number of people:  ");
        this.populationLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.populationLabel.setForeground(SimulationFrame.WHITE);
        
        this.populationNumberLabel = new JLabel("  55");
        this.populationNumberLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.populationNumberLabel.setForeground(SimulationFrame.WHITE);
        
        this.widthLabel = new JLabel(" Width of Simulation: ");
        this.widthLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.widthLabel.setForeground(SimulationFrame.WHITE);
        
        this.widthNumberLabel = new JLabel("  450");
        this.widthNumberLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.widthNumberLabel.setForeground(SimulationFrame.WHITE);
        
        this.heightLabel = new JLabel("Height of Simulation: ");
        this.heightLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.heightLabel.setForeground(SimulationFrame.WHITE);
        
        this.heightNumberLabel = new JLabel("  450");
        this.heightNumberLabel.setFont(SimulationFrame.MEDIUM_FONT);
        this.heightNumberLabel.setForeground(SimulationFrame.WHITE);
        
        //constructing JSlider
        this.populationSlider = new JSlider(JSlider.HORIZONTAL,10,100,55);
        this.populationSlider.setMajorTickSpacing(5);
        this.populationSlider.setPaintTicks(true);
        this.populationSlider.setBackground(SimulationFrame.DARK_GREY);
        this.populationSlider.addChangeListener(e);
        
        this.widthSlider = new JSlider(JSlider.HORIZONTAL,200,700,450);
        this.widthSlider.setMajorTickSpacing(50);
        this.widthSlider.setPaintTicks(true);
        this.widthSlider.setBackground(SimulationFrame.DARK_GREY);
        this.widthSlider.addChangeListener(e);
        
        this.heightSlider = new JSlider(JSlider.HORIZONTAL,200,700,450);
        this.heightSlider.setMajorTickSpacing(50);
        this.heightSlider.setPaintTicks(true);
        this.heightSlider.setBackground(SimulationFrame.DARK_GREY);
        this.heightSlider.addChangeListener(e);
        
        //constructing JCheckbox
        this.socialCheck = new JCheckBox(" Enable social distancing");
        this.socialCheck.setFont(SimulationFrame.MEDIUM_FONT);
        this.socialCheck.setForeground(SimulationFrame.WHITE);
        this.socialCheck.setBackground(SimulationFrame.DARK_GREY);
        this.socialCheck.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        this.quarantineCheck = new JCheckBox(" Enable regional quarantine");
        this.quarantineCheck.setFont(SimulationFrame.MEDIUM_FONT);
        this.quarantineCheck.setForeground(SimulationFrame.WHITE);
        this.quarantineCheck.setBackground(SimulationFrame.DARK_GREY);
        this.quarantineCheck.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        //constructing JButton
        this.exitButton = new JButton("Exit");
        this.exitButton.addActionListener(this);
        this.exitButton.setBackground(SimulationFrame.DARK_RED);
        this.exitButton.setForeground(SimulationFrame.WHITE);
        
        this.returnButton = new JButton("Return");
        this.returnButton.addActionListener(this);
        this.returnButton.setBackground(SimulationFrame.DARK_RED);
        this.returnButton.setForeground(SimulationFrame.WHITE);
        
        this.startButton = new JButton("Start Simulation");
        this.startButton.addActionListener(this);
        this.startButton.setBackground(SimulationFrame.GREEN);
        this.startButton.setForeground(SimulationFrame.WHITE);

        //adding objects to panel and box
        this.populationPanel.add(populationLabel, BorderLayout.WEST);
        this.populationPanel.add(populationSlider, BorderLayout.CENTER);
        this.populationPanel.add(populationNumberLabel, BorderLayout.EAST);
        
        this.widthPanel.add(widthLabel, BorderLayout.WEST);
        this.widthPanel.add(widthSlider, BorderLayout.CENTER);
        this.widthPanel.add(widthNumberLabel, BorderLayout.EAST);
        
        this.heightPanel.add(heightLabel, BorderLayout.WEST);
        this.heightPanel.add(heightSlider, BorderLayout.CENTER);
        this.heightPanel.add(heightNumberLabel, BorderLayout.EAST);
        
        this.socialPanel.add(socialCheck, BorderLayout.WEST);
        
        this.quarantinePanel.add(quarantineCheck, BorderLayout.WEST);
        
        this.westBox.add(populationPanel);
        this.westBox.add(widthPanel);
        this.westBox.add(heightPanel);
        this.westBox.add(socialPanel);
        this.westBox.add(quarantinePanel);
               
        this.westPanel.add(westBox, BorderLayout.CENTER);
        this.westPanel.add(new JLabel(" "), BorderLayout.NORTH);
        this.westPanel.add(new JLabel(" "), BorderLayout.SOUTH);
        
        this.southPanel.add(returnButton);
        this.southPanel.add(new JLabel("        "));
        this.southPanel.add(startButton);
        this.southPanel.add(new JLabel("        "));
        this.southPanel.add(exitButton);

        //adding panels to frame
        this.add(westPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);
        
        this.setVisible(true);

    }
    
    //update slider
    public class event implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e)
        {
            populationValue = populationSlider.getValue(); 
            populationNumberLabel.setText("  " + populationValue);
            
            widthValue = widthSlider.getValue();
            widthNumberLabel.setText("  " + widthValue);
            
            heightValue = heightSlider.getValue();
            heightNumberLabel.setText("  " + heightValue);
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       String command = e.getActionCommand();
       if(command.equals("Exit"))
       {
           System.exit(0);
       }
       if(command.equals("Return"))
       {
           
       }
       if(command.equals("Start Simulation"))
       {
           if(socialCheck.isSelected())
           {
               socialDistancing = true;
           }
           if(quarantineCheck.isSelected())
           {
               regionalQuarantine = true;
           }
           new SimulationFrame(populationValue, widthValue, heightValue, socialDistancing, regionalQuarantine);
           SimulationPanel.ms = 0;
           SimulationPanel.days = 0;
           this.dispose();
       }
    }
    
    public static void main(String[] args)
    {
        new InputFrame();
    }
}
