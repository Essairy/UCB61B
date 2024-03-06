package ngordnet.ngrams;

import java.sql.Time;
import java.util.Collection;
import java.util.TreeMap;

import edu.princeton.cs.algs4.In;
/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;

    private TreeMap<String,TimeSeries> wordsYear;
    private TimeSeries totalWords;
    // TODO: Add any necessary static/instance variables.

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        In wordsFile = new In(wordsFilename);

        wordsYear = new TreeMap<>();
        String oldword = "";
        TimeSeries currentword = null;

        while(wordsFile.hasNextLine()){
            String newLine = wordsFile.readLine();
            String[] words = newLine.split("\t");

            String word = words[0];
            int year = Integer.parseInt(words[1]);
            double number = Double.parseDouble(words[2]);

            if (word.equals(oldword)){
                currentword.put(year,number);
            } else {
                if (currentword != null){
                    wordsYear.put(oldword,currentword);
                }
                currentword = new TimeSeries();
                currentword.put(year,number);
                oldword = word;
            }
        }

        In yearCounts = new In(countsFilename);
        totalWords = new TimeSeries();
        while(yearCounts.hasNextLine()){
            String newLine = yearCounts.readLine();
            String[] cal = newLine.split(",");
            int year = Integer.parseInt(cal[0]);
            double number = Double.parseDouble(cal[1]);
            totalWords.put(year,number);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(wordsYear.get(word),startYear,endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy,
     * not a link to this NGramMap's TimeSeries. In other words, changes made
     * to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word) {
        TimeSeries tt = new TimeSeries();
        tt.putAll(wordsYear.get(word));
        return tt;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return totalWords;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries wordCounts = countHistory(word,startYear,endYear);
        TimeSeries totalCounts = totalCountHistory();
        return wordCounts.dividedBy(totalCounts);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to
     * all words recorded in that year. If the word is not in the data files, return an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries wordCounts = countHistory(word);
        TimeSeries totalCounts = totalCountHistory();
        return wordCounts.dividedBy(totalCounts);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS
     * between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     * this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries allWordsCounts = new TimeSeries();
        for (String w:words){
            TimeSeries currentWordCounts = weightHistory(w,startYear,endYear);
            allWordsCounts = allWordsCounts.plus(currentWordCounts);
        }
        return allWordsCounts;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries allWordsCounts = new TimeSeries();
        for (String w:words){
            TimeSeries currentWordCounts = weightHistory(w);
            allWordsCounts = allWordsCounts.plus(currentWordCounts);
        }
        return allWordsCounts;
    }

    public boolean countainsWord(String word){
        return this.wordsYear.containsKey(word);
    }
    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
