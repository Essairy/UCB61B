package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.List;
import java.util.Map;

import static aoa.guessers.GetMap.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        Map<Character,Integer> FreqMap = getFrequencyMap(pattern,words,guesses);
        Map<Character,Integer> FreqMapOrder = getFrequencyMap(words);
        for (char c:guesses){
            FreqMapOrder.remove(c);
            FreqMap.remove(c);
        }
        char MaxOrder = getMax(FreqMap,guesses);
//        while (!booleanMax(MaxOrder,FreqMap)){
//            FreqMapOrder.remove(MaxOrder);
//            MaxOrder = getMax(FreqMapOrder,guesses);
//        }

        return MaxOrder;
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
