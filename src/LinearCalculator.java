import java.util.ArrayList;

public class LinearCalculator implements IValueCalculator {
    @Override
    public ArrayList<Object> calculateYValues(float a, float b, float c, ArrayList<Float> xValues){
        ArrayList<Object> result = new ArrayList<>();
        ArrayList<Float> values = new ArrayList<>();
        float yMax = a * xValues.get(0)+ b;
        float yMin = yMax;
        for (Float xValue:xValues) {
            float y = a * xValue + b;
            values.add(y);
            if(yMax < y){
                yMax = y;
            }
            if (yMin > y){
                yMin = y;
            }
        }
        result.add(yMin);
        result.add(yMax);
        result.add(values);
        return result;
    }
}
