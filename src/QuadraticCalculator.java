import java.util.ArrayList;

public class QuadraticCalculator implements IValueCalculator {
    @Override
    public ArrayList<Object> calculateYValues(float a, float b, float c, ArrayList<Float> xValues){
        ArrayList<Object> results = new ArrayList<>();
        ArrayList<Float> values = new ArrayList<>();
        float yMax = a * xValues.get(0)*xValues.get(0) + b*xValues.get(0) +c;
        float yMin = yMax;
        for (Float xValue:xValues) {
            float y = a * (xValue * xValue) + (b * xValue) + c;
            values.add(y);
            if(yMax < y){
                yMax = y;
            }
            if (yMin > y){
                yMin = y;
            }
        }
        results.add(yMin);
        results.add(yMax);
        results.add(values);
        return results;
    }
}
