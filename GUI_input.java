package osproject;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Gui_Input extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3173308932476770685L;
	ArrayList<Integer> devices = new ArrayList<>();
    public ArrayList<JTextField> jTextFields = new ArrayList<>();
    int counter = 0, n = 0, start = 1;
    String label;
    JTextField TextFieldPanel1 = new JTextField();
    JLabel jLabel;
    JPanel jPanel = new JPanel();
    JPanel jPanel2 = new JPanel();
    JTextArea jTextArea = new JTextArea();
    JScrollPane scrollPane1 = new JScrollPane(jPanel2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    Gui_Input() {
        Frame_Setting();
        this.add(setJPanel1());
    }

    JPanel setJPanel1() {
        jPanel.setLayout(null);
        jPanel.setSize(500, 120);
        jPanel.setBounds(0, 0, getWidth(), getHeight());
        label = "Enter number of requests: ";
        jPanel.add(LabelSetting(label));
        jPanel.add(TextFieldSetting(TextFieldPanel1, 40));
        jPanel.add(ButtonSetting(80));
        jPanel.setBackground(new Color(128, 128, 128)); // Salmon background color
        jPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 5)); // Bronze border
        return jPanel;
    }

    void Frame_Setting() {
        this.setTitle("Disk Scheduling Algorithms");
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
    }

    JLabel LabelSetting(String LabelName) {
        Border Bevel = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        jLabel = new JLabel(LabelName);
        jLabel.setBounds(0, 0, getWidth(), 30);
        jLabel.setForeground(Color.WHITE); // White text color
        jLabel.setBackground(new Color(0, 0, 0)); // Salmon background color
        jLabel.setOpaque(true);
        jLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Arial font, larger size
        jLabel.setBorder(Bevel);
        return jLabel;
    }

    JTextField TextFieldSetting(JTextField jTextField1, int y) {
        Border Bevel = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        jTextField1.setBounds(5, y, getWidth(), 35);
        jTextField1.setForeground(Color.BLACK); // Black text color
        jTextField1.setBackground(new Color(255, 255, 255)); // Beige background color
        jTextField1.setOpaque(true);
        jTextField1.setFont(new Font("Arial", Font.BOLD, 40)); // Arial font
        jTextField1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Black border
        jTextField1.addActionListener(this);
        return jTextField1;
    }

    JButton ButtonSetting(int y) {
        JButton jButton = new JButton("Next");
        jButton.addActionListener(this);
        jButton.setBounds(5, 90, getWidth(), 35);
        jButton.setBackground(new Color(0, 0, 0)); // Chocolate background color
        jButton.setForeground(Color.WHITE); // White text color
        jButton.setFont(new Font("Arial", Font.PLAIN, 15)); // Arial font
        jButton.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 3)); // White border
        return jButton;
    }

    void setPanel2(int ch2) {
        if (ch2 * 50 > getHeight()) {
            jPanel2.setLayout(new GridLayout(0, 1));
        } else {
            jPanel2.setLayout(null);
        }

        jPanel2.setSize(500, 120);
        jPanel2.setBounds(0, 0, getWidth(), getHeight());
        jPanel2.setVisible(false);

        label = "Enter " + ch2 + " requests:";
        jPanel2.add(LabelSetting(label));
        int y = 40;

        for (int i = 0; i < ch2; i++) {
            JTextField jTextField1 = new JTextField();
            jPanel2.add(TextFieldSetting(jTextField1, y));
            jTextFields.add(jTextField1);
            y = y + 40;
        }
        jPanel2.setBackground(new Color(128, 128, 128)); // Salmon background color
        jPanel2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 5)); // Bronze border
        this.add(scrollPane1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Next") && counter == 0) {
            try {
                n = Integer.parseInt(TextFieldPanel1.getText());
                if (n <= 0) {
                    throw new NumberFormatException("Please enter a valid positive integer for the number of requests.");
                }
                label = "Enter position of head pointer: ";
                jLabel.setText(label);
                counter++;
                TextFieldPanel1.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                TextFieldPanel1.setText("");
            }
        } else if (e.getActionCommand().equals("Next") && counter == 1) {
            try {
                start = validatePositiveInteger(TextFieldPanel1.getText());
                TextFieldPanel1.setText("");
                jPanel.setVisible(false);
                setPanel2(n);
                jPanel2.setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                TextFieldPanel1.setText("");
            }
        } else if (e.getSource() == TextFieldPanel1) {
            JOptionPane.showMessageDialog(null, "Please Enter Next");
        } else {
            for (int i = 0; i < n; i++) {
                if (e.getSource() == jTextFields.get(i)) {
                    try {
                        int value = validateRequestValue(jTextFields.get(i).getText());
                        devices.add(value);
                        jTextFields.get(i).setEditable(false);
                        if (i == n - 1) {
                            jPanel2.setVisible(false);
                            scrollPane1.setVisible(false);
                            disksceduler look = new look(devices, start);
                            disksceduler lookright = new lookright(devices, start);
                            disksceduler C_look = new clook(devices, start);
                            disksceduler FCFS = new fcfs(devices, start);
                            disksceduler SSTF = new sstf(devices, start);
                            disksceduler Scan = new scan(devices, start);
                            disksceduler ScanRight = new ScanRight(devices, start);
                            disksceduler C_scan = new cscan(devices, start);
                            ArrayList<ArrayList<Integer>> input = new ArrayList<>();
                            ArrayList<Integer> output;
                            output = FCFS.schedule();
                            input.add(output);
                            output = SSTF.schedule();
                            input.add(output);
                            output = Scan.schedule();
                            input.add(output);
                            output = C_scan.schedule();
                            input.add(output);
                            output = look.schedule();
                            input.add(output);
                            output = lookright.schedule();
                            input.add(output);
                            output = C_look.schedule();
                            input.add(output);
                            output = ScanRight.schedule();
                            input.add(output);
                            this.setVisible(false);
                            new GUI(input);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        jTextFields.get(i).setText("");
                    }
                }
            }
        }
    }

    private int validatePositiveInteger(String input) throws NumberFormatException {
        int value = Integer.parseInt(input);
        if (value <= 0) {
            throw new NumberFormatException("Non-positive integer entered.");
        } else if (value > 199) {
            throw new NumberFormatException("Out of range. Please enter a value between 0 and 200.");
        }
        return value;
    }

    private int validateRequestValue(String input) throws NumberFormatException {
        int value = Integer.parseInt(input);
        if (value < 0 || value > 199) {
            throw new NumberFormatException("Out of range. Please enter a value below 200.");
        }
        return value;
    }
}
