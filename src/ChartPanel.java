import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChartPanel extends JPanel {
    public ChartPanel() {
        setBackground(new Color(140, 140, 140));
    }

    private double calculateDelta(float minValue, float maxValue) {
        int range = (int) Math.abs(maxValue - minValue);
        if (range == 0) {
            return 1;
        }

        double delta = 1;

        while (range / delta > 10) {
            delta *= 10;
        }

        while (range / delta < 2) {
            delta /= 2;
        }

        return delta;
    }

    private ArrayList<Funkcja> funkcje = new ArrayList<>();

    public void setFunkcje(ArrayList<Funkcja> funkcje) {
        this.funkcje = funkcje;
        repaint(); // Repiant chart after reseting funtions
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int minX = -20;
        int maxX = 20;
        float minY = -20;
        float maxY = 20;

        if (!funkcje.isEmpty()) {
            minX = (int) Math.floor(funkcje.get(0).getWarrtosciX().get(0));
            maxX = (int) Math.ceil(funkcje.get(0).getWarrtosciX().get(funkcje.get(0).getWarrtosciX().size() - 1));
            minY = (float) Math.floor(funkcje.get(0).getMinY());
            maxY = (float) Math.ceil(funkcje.get(0).getMaxY());
            for (Funkcja funkcja : funkcje) {
                if (Math.floor(funkcja.getMinY()) < minY) {
                    minY = (float) Math.floor(funkcja.getMinY());
                }
                if (Math.ceil(funkcja.getMaxY()) > maxY) {
                    maxY = (float) Math.ceil(funkcja.getMaxY());
                }
            }
            if (maxY == minY){
                maxY += 50;
                minY -= 50;
            }
        }



        int padding = 20;

        int chartWidth = getWidth();
        int chartHeight = getHeight();



        // Scaling
        double scaleX = (double) (chartWidth - (padding * 2)) / (maxX - minX);
        double scaleY = (double) (chartHeight - (padding * 2)) / (maxY - minY);

        int originX = padding + (int) ((-minX) * scaleX);
        int originY = chartHeight - padding - (int) ((-minY) * scaleY);

        g2.setStroke(new BasicStroke(2));
        g2.drawLine(padding, originY, chartWidth - padding, originY); // X axis
        g2.drawLine(originX, padding, originX, chartHeight - padding); // Y axis

        g2.setStroke(new BasicStroke(1));

        double deltaX = calculateDelta(minX, maxX);
        double deltaY = calculateDelta(minY, maxY);


        for (double xValue = deltaX; xValue <= maxX; xValue += deltaX) {
            int x = originX + (int) (xValue * scaleX);
            g2.drawLine(x, originY - 5, x, originY + 5);
            g2.drawString(String.format("%.2f", xValue), x - 10, originY + 15); // Format to 2 decimal places
        }
        for (double xValue = -deltaX; xValue >= minX; xValue -= deltaX) {
            int x = originX + (int) (xValue * scaleX);
            g2.drawLine(x, originY - 5, x, originY + 5);
            g2.drawString(String.format("%.2f", xValue), x - 15, originY + 15);
        }

        for (double yValue = +deltaY; yValue <= maxY; yValue += deltaY) {
            int y = originY - (int) (yValue * scaleY);
            g2.drawLine(originX - 5, y, originX + 5, y);
            g2.drawString(String.format("%.2f", yValue), originX - 30, y + 5);
        }
        for (double yValue = -deltaY; yValue >= minY; yValue -= deltaY) {
            int y = originY - (int) (yValue * scaleY);
            g2.drawLine(originX - 5, y, originX + 5, y);
            g2.drawString(String.format("%.2f", yValue), originX - 30, y + 5);
        }

        g2.setColor(Color.BLACK);
        g2.fillOval(originX - 5, originY - 5, 10, 10);
        if (!funkcje.isEmpty()) {
            for (Funkcja funkcja : funkcje) {
                g2.setColor(new Color(funkcja.getRed(), funkcja.getGreen(), funkcja.getBlue()));
                g2.setStroke(new BasicStroke(funkcja.getSzerokoscLini()));

                // Rysowanie funkcji
                for (int i = 1; i < funkcja.getWarrtosciY().size(); i++) {
                    int x1 = originX + (int) (funkcja.getWarrtosciX().get(i - 1) * scaleX);
                    int y1 = originY - (int) (funkcja.getWarrtosciY().get(i - 1) * scaleY);
                    int x2 = originX + (int) (funkcja.getWarrtosciX().get(i) * scaleX);
                    int y2 = originY - (int) (funkcja.getWarrtosciY().get(i) * scaleY);

                    g2.drawLine(x1, y1, x2, y2);
                }
            }
        }
    }


}
