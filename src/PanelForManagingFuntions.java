import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class PanelForManagingFuntions extends JPanel {
    private PanelForChartSpecInput panelForChartSpecInput;
    private PanelForAddingFuntions panelForAddingFuntions;
    private ChartPanel chartPanel;
    private ArrayList<Funkcja> fukcje = new ArrayList<>();
    private JList<String> listafunkcji;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private JButton addButton, modifyButton, deleteButton;
    private boolean isSetToAdd = true;
    private boolean isSetToModify = true;
    private boolean isSetToDelete = true;
    private JLabel errorLabel;

    public PanelForManagingFuntions(PanelForChartSpecInput panelForChartSpecInput, PanelForAddingFuntions panelForAddingFuntions, ChartPanel chartPanel) {
        this.panelForChartSpecInput = panelForChartSpecInput;
        this.panelForAddingFuntions = panelForAddingFuntions;
        this.chartPanel = chartPanel;

        Color backgroundColor = new Color(39, 40, 43);

        panelForChartSpecInput.setPanelZarzadzaniaFunkcjami(this);

        setLayout(new BorderLayout(5, 5));

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);

        listModel = new DefaultListModel<>();
        listafunkcji = new JList<>(listModel);
        scrollPane = new JScrollPane(listafunkcji);
        listafunkcji.setVisibleRowCount(15);
        scrollPane.setPreferredSize(new Dimension(200,400));

        listafunkcji.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                errorLabel.setVisible(false);
                boolean isSelected = listafunkcji.getSelectedIndex() != -1; // Chceck if somehing is selected
                modifyButton.setEnabled(isSelected);
                deleteButton.setEnabled(isSelected);
            }
        });

        Color buttonColor = new Color(29, 40, 74);
        Color buttonFontColor = new Color(255,255,255);
        addButton = new JButton("Dodaj");
        addButton.setBackground(buttonColor);
        addButton.setForeground(buttonFontColor);
        addButton.setBorder(new LineBorder(backgroundColor,10)); // padding for butttons


        addButton.addActionListener(e -> {
            if(isSetToAdd){
                isSetToAdd = false;
                addButton.setText("Rysuj");
                modifyButton.setEnabled(false);
                deleteButton.setText("Wyjdz");
                isSetToDelete = false;

                panelForAddingFuntions.setEnabled(true);
            }
            else{
                isSetToAdd = true;
                isSetToDelete = true;
                addButton.setText("Dodaj");
                deleteButton.setText("Usuń");

                Funkcja newFunkcja = panelForAddingFuntions.nowaFunkcja(panelForChartSpecInput.pobierzParametryWykresu());
                if (newFunkcja != null) {
                    fukcje.add(newFunkcja);
                    listModel.addElement(newFunkcja.getTypFunkcji());
                    chartPanel.setFunkcje(fukcje);
                }

                panelForAddingFuntions.setEnabled(false);
            }
        });


        modifyButton = new JButton("Modyfikuj");
        modifyButton.setBackground(buttonColor);
        modifyButton.setForeground(buttonFontColor);
        modifyButton.setBorder(new LineBorder(backgroundColor,10));
        modifyButton.setEnabled(false);

        modifyButton.addActionListener(e -> {
            if(isSetToModify){
                if (listafunkcji.getSelectedIndex() == -1) {
                    errorLabel.setText("Nie wybrano funkcji");
                    errorLabel.setVisible(true);
                    return;
                }
                isSetToModify = false;
                modifyButton.setText("Zapisz");
                addButton.setEnabled(false);
                deleteButton.setEnabled(false);
                panelForAddingFuntions.setEnabled(true);
            }
            else{
                isSetToModify = true;
                modifyButton.setText("Modyfikuj");
                addButton.setEnabled(true);
                deleteButton.setEnabled(true);
                Funkcja modyfikowanaFunkcja = panelForAddingFuntions.nowaFunkcja(panelForChartSpecInput.pobierzParametryWykresu());
                if (modyfikowanaFunkcja != null) {
                    int index = listafunkcji.getSelectedIndex();
                    fukcje.set(index,modyfikowanaFunkcja);
                    listModel.set(index,modyfikowanaFunkcja.getTypFunkcji());
                    chartPanel.setFunkcje(fukcje);
                }


                panelForAddingFuntions.setEnabled(false);
            }
        });

        deleteButton = new JButton("Usuń");
        deleteButton.setBackground(buttonColor);
        deleteButton.setForeground(buttonFontColor);
        deleteButton.setBorder(new LineBorder(backgroundColor,10));
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> {
            if(isSetToDelete){
                if (listafunkcji.getSelectedIndex() == -1) {
                    errorLabel.setText("Nie wybrano funkcji");
                    errorLabel.setVisible(true);
                    return;
                }
                int index = listafunkcji.getSelectedIndex();
                fukcje.remove(index);
                listModel.remove(index);
                chartPanel.setFunkcje(fukcje);
            }
            else{
                addButton.setText("Dodaj");
                modifyButton.setEnabled(true);
                deleteButton.setText("Usuń");
                isSetToAdd = true;
                isSetToDelete = true;
                panelForAddingFuntions.setEnabled(false);
            }
        });

        listafunkcji.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                errorLabel.setVisible(false);
            }
        });

        scrollPane.setBorder(new LineBorder(backgroundColor,10));//padding for scrollPane in scrollPaneWraper
        JPanel scrollPaneWrapper = new JPanel(new BorderLayout());
        scrollPaneWrapper.add(scrollPane, BorderLayout.CENTER);
        scrollPaneWrapper.setBackground(backgroundColor);
        add(scrollPaneWrapper, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(150,400));
        buttonPanel.setLayout(new GridLayout(4, 1, 5, 5));


        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(errorLabel);
        buttonPanel.setBackground(backgroundColor);

        add(buttonPanel,BorderLayout.CENTER);
        setBackground(backgroundColor);
        setVisible(true);

    };

    public void updateFunctions(ArrayList<Float> newParameters) {
        for (int i = 0; i < fukcje.size(); i++) {
            Funkcja updatedFunkcja = fukcje.get(i);
            updatedFunkcja.setWarrtosciX(newParameters);
            updatedFunkcja.obliczWartosci();
            fukcje.set(i, updatedFunkcja);
            listModel.set(i, updatedFunkcja.getTypFunkcji());
        }

        // Update the graph with the new functions
        chartPanel.setFunkcje(fukcje);
    }



}
