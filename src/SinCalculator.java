import java.util.ArrayList;

public class SinCalculator implements IValueCalculator {
    @Override
    public ArrayList<Object> calculateYValues(float a, float b, float c, ArrayList<Float> xValues){
        ArrayList<Object> result = new ArrayList<>();
        ArrayList<Float> values = new ArrayList<>();
        float yMax = (float) (a * Math.sin(xValues.get(0) - (b * Math.PI)) +c);
        float yMin = yMax;
        for (Float xValue:xValues) {
            float y = (float) (a * Math.sin(xValue - (b * Math.PI)) +c);
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
