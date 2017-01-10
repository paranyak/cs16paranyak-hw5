package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;
import java.util.Arrays;

public class AsIntStream implements IntStream {
    public ArrayList<Integer> array;

    private AsIntStream(int... numbers) {
        array = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            array.add(numbers[i]);
        }
    }

    public AsIntStream(ArrayList<Integer> newList) {
        array = newList;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        int sum = 0;
        for (int i = 0; i < array.size(); i++) {
            sum += array.get(i);
        }
        return (double) sum / array.size();
    }

    @Override
    public Integer max() {
        int maxNumber = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) > maxNumber) {
                maxNumber = array.get(i);
            }
        }
        return maxNumber;
    }

    @Override
    public Integer min() {
        int minNumber = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) > minNumber) {
                minNumber = array.get(i);
            }
        }
        return minNumber;
    }

    @Override
    public long count() {
        return array.size();
    }

    @Override
    public Integer sum() {
        int sum = 0;
        for (int i = 0; i < array.size(); i++) {
            sum += array.get(i);
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            if (predicate.test(array.get(i))) {
                newList.add(array.get(i));
            }
        }
        return new AsIntStream(newList);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i = 0; i < count(); i++) {
            action.accept(array.get(i));
        }

    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        ArrayList<Integer> arrayNew = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            arrayNew.add(mapper.apply(array.get(i)));
        }
        return new AsIntStream(arrayNew);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<IntStream> arrayI = new ArrayList<>();
        for (int i = 0; i < array.size() ; i++){
            arrayI.add(func.applyAsIntStream(array.get(i)));
        }
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0 ; i < arrayI.size(); i++){
            for (int j = 0; j < arrayI.get(i).toArray().length; j ++){
                a.add(arrayI.get(i).toArray()[j]);
            }
        }
        return new AsIntStream(a);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for (int i = 0; i < array.size(); i++) {
            identity = op.apply(identity, array.get(i));
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        int[] myNumbers = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            myNumbers[i] = array.get(i);
        }
        return myNumbers;
    }

}
