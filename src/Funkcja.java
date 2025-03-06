import java.util.ArrayList;

public class Funkcja {
    private ArrayList<Float> warrtosciX = new ArrayList<>();
    private ArrayList<Float> warrtosciY = new ArrayList<>();
    private int red, green, blue, szerokoscLini;
    private float maxY, minY, a, b, c;

    public Funkcja(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private String typFunkcji;

    IValueCalculator iwartosciFunkcji;

    public Funkcja(IValueCalculator iwartosciFunkcji, float a, float b, float c, ArrayList<Float> warrtosciX, int red, int green, int blue, int szerokoscLini, String typFunkcji) {
        this.warrtosciX = warrtosciX;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.szerokoscLini = szerokoscLini;
        this.typFunkcji = typFunkcji;
        this.iwartosciFunkcji = iwartosciFunkcji;
        this.a = a;
        this.b = b;
        this.c = c;
        obliczWartosci();
    }

    public void obliczWartosci(){
        ArrayList<Object> wyniki = iwartosciFunkcji.calculateYValues(a,b,c, warrtosciX);
        minY = (float)wyniki.get(0);
        maxY = (float)wyniki.get(1);
        warrtosciY = (ArrayList<Float>) wyniki.get(2);
    }

    public ArrayList<Float> getWarrtosciX() {
        return warrtosciX;
    }

    public void setWarrtosciX(ArrayList<Float> warrtosciX) {
        this.warrtosciX = warrtosciX;
    }

    public ArrayList<Float> getWarrtosciY() {
        return warrtosciY;
    }

    public void setWarrtosciY(ArrayList<Float> warrtosciY) {
        this.warrtosciY = warrtosciY;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getSzerokoscLini() {
        return szerokoscLini;
    }

    public void setSzerokoscLini(int szerokoscLini) {
        this.szerokoscLini = szerokoscLini;
    }

    public float getMaxY() {
        return maxY;
    }

    public void setMaxY(float maxY) {
        this.maxY = maxY;
    }

    public float getMinY() {
        return minY;
    }

    public void setMinY(float minY) {
        this.minY = minY;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    public String getTypFunkcji() {
        return typFunkcji;
    }

    public void setTypFunkcji(String typFunkcji) {
        this.typFunkcji = typFunkcji;
    }

    public IValueCalculator getIwartosciFunkcji() {
        return iwartosciFunkcji;
    }

    public void setIwartosciFunkcji(IValueCalculator iwartosciFunkcji) {
        this.iwartosciFunkcji = iwartosciFunkcji;
    }
}
