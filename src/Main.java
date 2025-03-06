import java.awt.*;
import javax.swing.*;
public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Damianowy porgram do rysowania wykresów");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel lewyPanel = new JPanel();
        lewyPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        PanelForChartSpecInput panelForChartSpecInput = new PanelForChartSpecInput();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        lewyPanel.add(panelForChartSpecInput, gbc);

        ChartPanel chartPanel = new ChartPanel();
        gbc.gridy = 1;
        gbc.weighty = 0.95;
        lewyPanel.add(chartPanel, gbc);

        PanelForAddingFuntions panelForAddingFuntions = new PanelForAddingFuntions();
        JPanel prawyPanel = new JPanel(new GridLayout(2, 1));
        PanelForManagingFuntions panelFunkcje = new PanelForManagingFuntions(panelForChartSpecInput, panelForAddingFuntions, chartPanel);

        panelForAddingFuntions.setEnabled(false);

        prawyPanel.add(panelFunkcje);
        prawyPanel.add(panelForAddingFuntions);

        mainPanel.add(lewyPanel, BorderLayout.CENTER);
        mainPanel.add(prawyPanel, BorderLayout.EAST);

        frame.add(mainPanel);

        frame.setVisible(true);
    }
}
