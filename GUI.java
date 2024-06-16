
package osproject; // Package declaration


import javax.swing.*;
//import javax.swing.border.BevelBorder;
//import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// GUI class
public class GUI implements ActionListener {
    // Member variables
    private JFrame f;
    private JButton b1, b2, b3, b4, b5, b6, b7,b8,b9; // Add new button
    private ArrayList<ArrayList<Integer>> Sequence;
    private JTextArea outputTextArea;

    // Constructor
    GUI(ArrayList<ArrayList<Integer>> sequence) {
        Sequence = sequence;
        f = new JFrame("Disk Scheduling");

        JLabel diskLabel = new JLabel("Disk Scheduling Algorithms");
        diskLabel.setFont(new Font("Arial", Font.BOLD, 35));
        diskLabel.setHorizontalAlignment(SwingConstants.CENTER);
        f.add(diskLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        b1 = new JButton("FCFS ALGORITHM");
        b1.setFont(new Font("Arial", Font.PLAIN, 18));
        b1.addActionListener(this);
        buttonPanel.add(b1);

        b2 = new JButton("SSTF ALGORITHM");
        b2.setFont(new Font("Arial", Font.PLAIN, 18));
        b2.addActionListener(this);
        buttonPanel.add(b2);

        b3 = new JButton("SCAN LEFT ALGORITHM");
        b3.setFont(new Font("Arial", Font.PLAIN, 18));
        b3.addActionListener(this);
        buttonPanel.add(b3);

        b4 = new JButton("SCAN RIGHT ALGORITHM");
        b4.setFont(new Font("Arial", Font.PLAIN, 18));
        b4.addActionListener(this);
        buttonPanel.add(b4);
        
        b5 = new JButton("C-SCAN ALGORITHM");
        b5.setFont(new Font("Arial", Font.PLAIN, 18));
        b5.addActionListener(this);
        buttonPanel.add(b5);

        b6 = new JButton("LOOK LEFT ALGORITHM");
        b6.setFont(new Font("Arial", Font.PLAIN, 18));
        b6.addActionListener(this);
        buttonPanel.add(b6);
        
        b7 = new JButton("LOOK RIGHT ALGORITHM"); // New button
        b7.setFont(new Font("Arial", Font.PLAIN, 18));
        b7.addActionListener(this);
        buttonPanel.add(b7);
        
        b8 = new JButton("C-LOOK ALGORITHM");
        b8.setFont(new Font("Arial", Font.PLAIN, 18));
        b8.addActionListener(this);
        buttonPanel.add(b8);

        b9 = new JButton("OPTIMAL ALGORITHM"); // New button
        b9.setFont(new Font("Arial", Font.PLAIN, 18));
        b9.addActionListener(this);
        buttonPanel.add(b9);

        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        f.add(scrollPane, BorderLayout.CENTER);

        outputTextArea = new JTextArea();
        outputTextArea.setFont(new Font("Arial", Font.PLAIN, 25)); // Set output text size to medium
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setPreferredSize(new Dimension(300, 200)); // Increase the size of the output display area
        f.add(outputScrollPane, BorderLayout.SOUTH);

        f.setSize(600, 600);
        f.getContentPane().setBackground(new Color(128,128,128)); // Tan background color
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b9) {
            findLowestMovement();
        } else {
            // Handle other button actions
        	  if (e.getSource() == b1) {
                  displayOutput("FCFS Algorithm output:\n" + Sequence.get(0), fcfs.getTotalMovement(),fcfs.getTotalTime());
                  new GUI_draw(Sequence.get(0)); 
        	  } else if (e.getSource() == b2) {
                  displayOutput("SSTF Algorithm output:\n" + Sequence.get(1), sstf.getTotalMovement(),sstf.getTotalTime());
                  new GUI_draw(Sequence.get(1)); 
        	  } else if (e.getSource() == b3) {
                  displayOutput("SCAN LEFT  output:\n" + Sequence.get(2), scan.getTotalMovement(),scan.getTotalTime());
                  new GUI_draw(Sequence.get(2)); 
        	  }else if (e.getSource() == b4) {
                  displayOutput("SCAN RIGHT Algorithm output:\n" + Sequence.get(7), ScanRight.getTotalMovement(),ScanRight.getTotalTime());
                  new GUI_draw(Sequence.get(7)); 
        	  } 
        	  else if (e.getSource() == b5) {
                 
            	 displayOutput("C_SCAN Algorithm output:\n" + Sequence.get(3), cscan.getTotalMovement(),cscan.getTotalTime());
            	 new GUI_draw(Sequence.get(3)); 
              } else if (e.getSource() == b6) {  
                  displayOutput("LOOK LEFT Algorithm output:\n" + Sequence.get(4), look.getTotalMovement(),look.getTotalTime());
                  new GUI_draw(Sequence.get(4)); 
              }else if (e.getSource() == b7) {  
                  displayOutput("LOOK RIGHT Algorithm output:\n" + Sequence.get(5), lookright.getTotalMovement(),lookright.getTotalTime());
                  new GUI_draw(Sequence.get(5)); 
              } 
              else if (e.getSource() == b8) {
                  displayOutput("C_LOOK Algorithm output:\n" + Sequence.get(6),clook.getTotalMovement(),clook.getTotalTime());
                  new GUI_draw(Sequence.get(6)); 
              }}
              
          }

          private void displayOutput(String output, int seekdistance,double seektime) {
              outputTextArea.setText(output + "\nSeek Distance: " + seekdistance+ "\nSeek Time: " + seektime);
          }
        
    
          private void findLowestMovement() {
        	    int fcfsMovement = fcfs.getTotalMovement();
        	    int sstfMovement = sstf.getTotalMovement();
        	    int scanMovement = scan.getTotalMovement();
        	    int scanrightMovement = ScanRight.getTotalMovement();
        	    int cscanMovement = cscan.getTotalMovement();
        	    int lookMovement = look.getTotalMovement();
        	    int lookrightMovement = lookright.getTotalMovement();

        	    int clookMovement = clook.getTotalMovement();

        	    int lowestMovement = Integer.MAX_VALUE;
        	    ArrayList<String> algorithms = new ArrayList<>();

        	    if (fcfsMovement <= lowestMovement) {
        	        lowestMovement = fcfsMovement;
        	        algorithms.add("FCFS");
        	    }
        	    if (sstfMovement <= lowestMovement) {
        	        if (sstfMovement < lowestMovement) {
        	            algorithms.clear();
        	        }
        	        lowestMovement = sstfMovement;
        	        algorithms.add("SSTF");
        	    }
        	    if (scanMovement <= lowestMovement) {
        	        if (scanMovement < lowestMovement) {
        	            algorithms.clear();
        	        }
        	        lowestMovement = scanMovement;
        	        algorithms.add("SCAN LEFT");
        	    }
        	    if (scanrightMovement <= lowestMovement) {
        	        if (scanrightMovement < lowestMovement) {
        	            algorithms.clear();
        	        }
        	        lowestMovement = scanrightMovement;
        	        algorithms.add("SCAN RIGHT");
        	    }

        	    if (cscanMovement <= lowestMovement) {
        	        if (cscanMovement < lowestMovement) {
        	            algorithms.clear();
        	        }
        	        lowestMovement = cscanMovement;
        	        algorithms.add("C-SCAN");
        	    }
        	    if (lookMovement <= lowestMovement) {
        	        if (lookMovement < lowestMovement) {
        	            algorithms.clear();
        	        }
        	        lowestMovement = lookMovement;
        	        algorithms.add("LOOK LEFT");
        	    }
        	    if (lookrightMovement <= lowestMovement) {
        	        if (lookrightMovement < lowestMovement) {
        	            algorithms.clear();
        	        }
        	        lowestMovement = lookrightMovement;
        	        algorithms.add("LOOK RIGHT");
        	    }

        	    if (clookMovement <= lowestMovement) {
        	        if (clookMovement < lowestMovement) {
        	            algorithms.clear();
        	        }
        	        lowestMovement = clookMovement;
        	        algorithms.add("C-LOOK");
        	    }

        	    StringBuilder output = new StringBuilder();
        	    output.append("Algorithm(s) with lowest movement:\n");
        	    for (String algorithm : algorithms) {
        	        output.append(algorithm).append("\n");
        	    }
        	    output.append("Total SEEK distance: ").append(lowestMovement).append("\n");
        	    output.append("Total SEEK Time: ").append(lowestMovement * 0.5);

        	    outputTextArea.setText(output.toString());

          }}
