import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PanelForAddingFuntions extends JPanel {
        private JFormattedTextField aTextBox, bTextBox, cTextBox;

        private JRadioButton linearFunction, quadraticFuntion, sinFunction;

        private JSlider sizeSlider, redSlider, blueSlider, greenSlider;

        private JLabel aLabel, bLabel, cLabel, redSliderLabel, blueSliderLabel, greenSliderLabel, sizeSliderLabel,  errorLabel;

        private NumberFormat format = NumberFormat.getNumberInstance();

        ButtonGroup group = new ButtonGroup();

        JPanel selectedColor = new JPanel();

        private NumberFormatter mask;

        public PanelForAddingFuntions() {
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(5, 5, 5, 5);

                Color backgroundColor = new Color(39, 40, 43);
                Color fontColor = new Color(255,255,255);

                format.setGroupingUsed(false);
                mask = new NumberFormatter(format);
                mask.setValueClass(Double.class);
                mask.setAllowsInvalid(true);
                mask.setMinimum(null);
                mask.setMaximum(null);

                aTextBox = new JFormattedTextField(mask);
                aTextBox.setValue(0);
                aTextBox.setColumns(4);

                bTextBox = new JFormattedTextField(mask);
                bTextBox.setValue(0);
                bTextBox.setColumns(4);

                cTextBox = new JFormattedTextField(mask);
                cTextBox.setValue(0);
                cTextBox.setColumns(4);


                aLabel = new JLabel("a:");
                aLabel.setForeground(fontColor);
                bLabel = new JLabel("b:");
                bLabel.setForeground(fontColor);
                cLabel = new JLabel("c:");
                cLabel.setForeground(fontColor);
                redSliderLabel = new JLabel("czerwony");
                redSliderLabel.setForeground(fontColor);
                greenSliderLabel = new JLabel("zielony");
                greenSliderLabel.setForeground(fontColor);
                blueSliderLabel = new JLabel("niebieski");
                blueSliderLabel.setForeground(fontColor);
                sizeSliderLabel = new JLabel("rozmiar");
                sizeSliderLabel.setForeground(fontColor);

                errorLabel = new JLabel();
                errorLabel.setForeground(Color.RED);
                errorLabel.setVisible(false);

                linearFunction = new JRadioButton("<html>y = ax+b</html>");
                linearFunction.setBackground(backgroundColor);
                linearFunction.setForeground(fontColor);
                quadraticFuntion = new JRadioButton("<html>y = ax<sup>2</sup> + bx + c</html>");
                quadraticFuntion.setBackground(backgroundColor);
                quadraticFuntion.setForeground(fontColor);
                sinFunction = new JRadioButton("<html>y = asin(x-bπ)+c</html>");
                sinFunction.setBackground(backgroundColor);
                sinFunction.setForeground(fontColor);

                linearFunction.setSelected(true);
                group.add(linearFunction);
                group.add(quadraticFuntion);
                group.add(sinFunction);

                sizeSlider = new JSlider(1, 20, 1);
                sizeSlider.setBackground(backgroundColor);

                redSlider = new JSlider(0,255,0);
                redSlider.setBackground(new Color(39, 40, 43));
                blueSlider = new JSlider(0,255,0);
                blueSlider.setBackground(backgroundColor);
                greenSlider = new JSlider(0,255,0);
                greenSlider.setBackground(backgroundColor);

                redSlider.setPaintTicks(true);
                redSlider.setMajorTickSpacing(50);
                greenSlider.setPaintTicks(true);
                greenSlider.setMajorTickSpacing(50);
                blueSlider.setPaintTicks(true);
                blueSlider.setMajorTickSpacing(50);
                sizeSlider.setPaintTicks(true);
                sizeSlider.setMajorTickSpacing(5);

                selectedColor.setBounds(new Rectangle(getWidth(),30));
                selectedColor.setMinimumSize(new Dimension(getWidth(),1));
                selectedColor.setBackground(Color.BLACK);

                ChangeListener previewChange = e -> {
                        selectedColor.setBackground(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
                        selectedColor.setSize(getWidth(), sizeSlider.getValue());
                        selectedColor.repaint();
                };

                redSlider.addChangeListener(previewChange);
                blueSlider.addChangeListener(previewChange);
                greenSlider.addChangeListener(previewChange);
                sizeSlider.addChangeListener(previewChange);

                gbc.weightx = 1.0;
                gbc.weighty = 1.0;

                gbc.gridx = 0;
                gbc.gridy = 0;
                add(aLabel, gbc);

                gbc.gridx = 1;
                add(aTextBox, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                add(bLabel, gbc);

                gbc.gridx = 1;
                add(bTextBox, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                add(cLabel, gbc);

                gbc.gridx = 1;
                add(cTextBox, gbc);

                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                add(linearFunction, gbc);

                gbc.gridy = 4;
                add(quadraticFuntion, gbc);

                gbc.gridy = 5;
                add(sinFunction, gbc);

                gbc.gridy = 6;
                gbc.gridwidth = 1;
                add(redSliderLabel, gbc);

                gbc.gridx = 1;
                add(redSlider, gbc);

                gbc.gridx = 0;
                gbc.gridy = 7;
                add(greenSliderLabel, gbc);

                gbc.gridx = 1;
                add(greenSlider, gbc);

                gbc.gridx = 0;
                gbc.gridy = 8;
                add(blueSliderLabel, gbc);

                gbc.gridx = 1;
                add(blueSlider, gbc);

                gbc.gridx = 0;
                gbc.gridy = 9;
                add(sizeSliderLabel, gbc);

                gbc.gridx = 1;
                add(sizeSlider, gbc);

                gbc.gridx = 0;
                gbc.gridy = 10;
                gbc.gridwidth = 2;
                add(selectedColor, gbc);

                setBackground(new Color(39, 40, 43));
                setEnabled(false);

        }
        public Funkcja nowaFunkcja(ArrayList<Float> wartosciX){
                float a = ((Number) aTextBox.getValue()).floatValue();
                float b = ((Number) bTextBox.getValue()).floatValue();
                float c = ((Number) cTextBox.getValue()).floatValue();
                int red = redSlider.getValue();
                int green = greenSlider.getValue();
                int blue = blueSlider.getValue();
                int szerokoscLini = sizeSlider.getValue();

                IValueCalculator iwartosciFunkcji;
                String typFunkcji;

                if (linearFunction.isSelected()) {
                        typFunkcji = String.format("y = %.2fx + %.2f", a, b);
                        iwartosciFunkcji = new LinearCalculator();
                } else if (quadraticFuntion.isSelected()) {
                        typFunkcji = String.format("<html>y = %.2fx<sup>2</sup> + %.2fx + %.2f</html>", a, b, c);
                        iwartosciFunkcji = new QuadraticCalculator();
                } else {
                        typFunkcji = String.format("y = %.2fsin(x-%.2fπ)+%.2f",a, b, c);
                        iwartosciFunkcji = new SinCalculator();
                }

                return new Funkcja(iwartosciFunkcji, a, b, c, wartosciX, red, green, blue, szerokoscLini, typFunkcji);
        }
        //[Enables/Disables] elements in panel instrad of panel itself (disabling whole panel does not prevent input to parrent comepnents)
        @Override
        public void setEnabled(boolean isEnabled) {
                aTextBox.setEnabled(isEnabled);
                bTextBox.setEnabled(isEnabled);
                cTextBox.setEnabled(isEnabled);
                linearFunction.setEnabled(isEnabled);
                quadraticFuntion.setEnabled(isEnabled);
                sinFunction.setEnabled(isEnabled);
                redSlider.setEnabled(isEnabled);
                blueSlider.setEnabled(isEnabled);
                greenSlider.setEnabled(isEnabled);
                sizeSlider.setEnabled(isEnabled);
                selectedColor.setEnabled(isEnabled);
                aLabel.setEnabled(isEnabled);
                bLabel.setEnabled(isEnabled);
                cLabel.setEnabled(isEnabled);
                redSliderLabel.setEnabled(isEnabled);
                blueSliderLabel.setEnabled(isEnabled);
                greenSliderLabel.setEnabled(isEnabled);
                sizeSliderLabel.setEnabled(isEnabled);
                errorLabel.setEnabled(isEnabled);
        }

}
