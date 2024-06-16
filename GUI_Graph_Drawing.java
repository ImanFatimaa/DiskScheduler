package osproject;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI_draw extends JFrame {
    private JPanel drawingPanel;
    private ArrayList<Integer> result; // Store the result of the C-LOOK algorithm
    private ArrayList<Integer> input; // Store the input for the algorithm

    public GUI_draw(ArrayList<Integer> input) {
        super("Algorithm Visualization");
        this.input = input; // Set the input list
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a container panel to hold the drawing panel
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Create the drawing panel
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawLines(g, result); // Draw lines representing the movement with the result
            }
        };
        drawingPanel.setPreferredSize(new Dimension(600, 400));
        drawingPanel.setBackground(Color.black);

        // Add the drawing panel to the content panel
        contentPanel.add(drawingPanel, BorderLayout.CENTER);

        // Create a label panel for the axis labels
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.setBackground(Color.BLACK);

        // Add the label panel to the content panel
        contentPanel.add(labelPanel, BorderLayout.SOUTH);

        // Add the content panel to the frame
        add(contentPanel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
        setVisible(true);

        // Initialize the graph automatically
        result = input;
        drawingPanel.repaint();
    }

    private void drawLines(Graphics g, ArrayList<Integer> result) {
        if (result == null || result.isEmpty()) {
            return; // No valid result to draw
        }

        Graphics2D g2d = (Graphics2D) g;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int padding = 50; // Padding from panel edges
        int xAxisHeight = panelHeight - padding; // Height of the x-axis line
        int yAxisWidth = padding; // Width of the y-axis line

        // Enable anti-aliasing for smoother lines
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw y-axis
       // g2d.drawLine(yAxisWidth, padding, yAxisWidth, xAxisHeight);

        // Draw labels for y-axis
        int maxY = 200; // Maximum value for the y-axis
        int scaleY = (panelHeight - 2 * padding) / maxY;
       /* for (int i = 0; i <= 10; i++) {
            int yLabel = padding + i * (panelHeight - 2 * padding) / 10;
            int yValue = maxY - i * (maxY / 10);

            // Draw result points adjacent to the y-axis labels
            if (result.contains(yValue)) {
                int x = yAxisWidth; // X-coordinate adjacent to the y-axis
                int y = yLabel - result.indexOf(yValue) * scaleY; // Y-coordinate based on the index of the result point
                g2d.setColor(Color.RED);
                g2d.fillOval(x - 5, y - 5, 10, 10); // Draw result point adjacent to the label
                g2d.drawString(String.valueOf(yValue), x - 30, y + 5); // Draw label beside the point
            }
        }*/

        // Draw lines connecting the points
        g2d.setColor(Color.WHITE); // Set custom color for the lines
        g2d.setStroke(new BasicStroke(2)); // Set thicker stroke for the lines
        int prevX = yAxisWidth + 50; // Initial x-coordinate
        int prevY = xAxisHeight - result.get(0) * scaleY;
        for (int i = 1; i < result.size(); i++) {
            int nextX = yAxisWidth + 50 + i * 20; // Next x-coordinate
            int nextY = xAxisHeight - result.get(i) * scaleY;
            g2d.drawLine(prevX, prevY, nextX, nextY); // Draw line
            prevX = nextX;
            prevY = nextY;
        }

        // Draw result points
        g2d.setColor(Color.RED); // Set custom color for the points
        g2d.setStroke(new BasicStroke(5)); // Set thicker stroke for the points
        for (int i = 0; i < result.size(); i++) {
            int x = yAxisWidth + 50 + i * 20; // Draw points at a distance of 20 pixels
            int y = xAxisHeight - result.get(i) * scaleY;
            g2d.drawLine(x, y, x, y); // Draw point
            g2d.drawString(String.valueOf(result.get(i)), x, y + 5); // Draw label above the point
        }
    }
}
