package ngordnet.ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    // TODO: Add any necessary static/instance variables.

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super(ts.subMap(startYear,true,endYear,true));
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        return new ArrayList<>(keySet());
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Integer> yearsList = years();
        List<Double> dataList = new ArrayList<>();
        for (int i:yearsList){
            dataList.add(get(i));
        }
        return dataList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries sumTime = new TimeSeries();
        sumTime.putAll(this);
        for (int i:ts.years()){
            if (containsKey(i)){
                sumTime.merge(i,ts.get(i),Double::sum);
            } else {
                sumTime.put(i,ts.get(i));
            }
        }
        return sumTime;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries divideTime = new TimeSeries();
        divideTime.putAll(this);
        for (int year: divideTime.years()){
            if (!ts.containsKey(year)){
                throw new IllegalArgumentException();
            } else {
                divideTime.merge(year,ts.get(year),(oldValue,newValue)->oldValue/newValue);
            }
        }
        return divideTime;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
