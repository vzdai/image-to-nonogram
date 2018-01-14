package ui;

import java.util.ArrayList;
import java.util.List;

public class Hint {

    private List<Integer> mValues = new ArrayList<>();

    public void addValue(int value) {
        mValues.add(value);
    }

    public List<Integer> getValues() {
        return mValues;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Integer i : mValues) {
            builder.append(i);
            builder.append(" ");
        }

        builder.deleteCharAt(builder.length() - 1);
        System.out.println("returning hint: " + builder.toString());
        return builder.toString();
    }
}
