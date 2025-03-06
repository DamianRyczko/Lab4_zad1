import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class PanelForChartSpecInput extends JPanel{
    PanelForManagingFuntions panelForManagingFuntions;
    private final int DEFAULT_X_MAX = 200;
    private final int DEFAULT_X_MIN = -200;
    private final int DEFAULT_K = 100;

    private JFormattedTextField xMinTextBox, xMaxTextBox, kTextBox;

    private JLabel xMinLabel, xMaxLabel, kLabel, errorLabel;

    private NumberFormat intFormat = NumberFormat.getIntegerInstance();

    private NumberFormatter mask;
    public PanelForChartSpecInput() {
        intFormat.setGroupingUsed(false);
        mask = new NumberFormatter(intFormat);
        mask.setValueClass(Integer.class);
        mask.setAllowsInvalid(false);
        mask.setMinimum(null);
        mask.setMaximum(null);

        xMinTextBox = new JFormattedTextField(mask);
        xMinTextBox.setValue(DEFAULT_X_MIN);
        xMinTextBox.setColumns(8);
        xMinTextBox.addPropertyChangeListener("value", evt -> PrzekazZmienioeWartosci());


        xMaxTextBox = new JFormattedTextField(mask);
        xMaxTextBox.setValue(DEFAULT_X_MAX);
        xMaxTextBox.setColumns(8);
        xMaxTextBox.addPropertyChangeListener("value", evt -> PrzekazZmienioeWartosci());

        kTextBox = new JFormattedTextField(mask);
        kTextBox.setValue(DEFAULT_K);
        kTextBox.setColumns(7);
        kTextBox.addPropertyChangeListener("value", evt -> PrzekazZmienioeWartosci());
        Color fontColor = new Color(255,255,255);

        xMinLabel = new JLabel("X Min:");
        xMinLabel.setForeground(fontColor);
        xMaxLabel = new JLabel("X Max:");
        xMaxLabel.setForeground(fontColor);
        kLabel = new JLabel("K:");
        kLabel.setForeground(fontColor);
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        setBackground(new Color(39, 40, 43));

        add(xMinLabel);
        add(xMinTextBox);
        add(xMaxLabel);
        add(xMaxTextBox);
        add(kLabel);
        add(kTextBox);
        add(errorLabel);
    }
    public void setPanelZarzadzaniaFunkcjami(PanelForManagingFuntions panelForManagingFuntions) {
        this.panelForManagingFuntions = panelForManagingFuntions;
    }


    public boolean validateInput(){
        StringBuilder errors = new StringBuilder();
        boolean isXMinInt = isInteger(xMinTextBox.getText());
        boolean isXMaxInt = isInteger(xMaxTextBox.getText());

        if (!isXMinInt) {
            errors.append("Wprowadź wartość liczbową dla X Min\n");
        }
        if (!isXMaxInt) {
            errors.append("Wprowadź wartość liczbową dla X Max\n");
        }

        if (isXMinInt && isXMaxInt) {
            if (getxMax() < getxMin()) {
                errors.append("Wartość minimalna X nie może przekraczać wartości maksymalnej\n");
            }
        }

        if (!isInteger(kTextBox.getText())) {
            errors.append("Wprowadź wartość liczbową dla K\n");
        } else if (getK() < 100) {
            errors.append("K powinno być nie mniejsze od 100\n");
        }

        if (errors.length() > 0) {
            errorLabel.setText("<html>" + errors.toString().replace("\n", "<br>") + "</html>"); // Supports multiline
            errorLabel.setVisible(true);
            return false;
        } else {
            errorLabel.setVisible(false);
            return true;
        }
    }

    private void PrzekazZmienioeWartosci() {
        if (validateInput()) {
            ArrayList<Float> newParams = pobierzParametryWykresu();
            panelForManagingFuntions.updateFunctions(newParams);
        }
    }
    private boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getxMin() {
        if(xMinTextBox.getText().isEmpty()){
            return DEFAULT_X_MIN;
        }
        return Integer.parseInt(xMinTextBox.getText());
    }

    public int getxMax() {
        if(xMaxTextBox.getText().isEmpty()){
            return DEFAULT_X_MAX;
        }
        return Integer.parseInt(xMaxTextBox.getText());
    }

    public int getK() {
        if(kTextBox.getText().isEmpty()){
            return DEFAULT_K;
        }
        return Integer.parseInt(kTextBox.getText());
    }

    ArrayList<Float> pobierzParametryWykresu(){
        ArrayList<Float> wartosciX = new ArrayList<>();
        float deltaX = (float) (getxMax() - getxMin()) / (getK() - 1);
        for (int i = 0; i < getK(); i++) {
            float wartoscX = getxMin() + i * deltaX;
            wartosciX.add(wartoscX);
        }
        return wartosciX;
    }
}
