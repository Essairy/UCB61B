package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    public static Map<Character, Integer> getFrequencyMap(List<String> words) {
        Map<Character, Integer> m = new TreeMap<>();
        for (String word:words){
            for (char c:word.toCharArray()){
                if (m.containsKey(c)){
                    int num = m.get(c);
                    m.replace(c,num+1);
                } else {
                    m.put(c,1);
                }
            }
        }
        return m;
    }

    public Map<Character,Integer> getFreqMapThatMatchesPattern(String pattern){
        List<String> match = new ArrayList<>();
        Map<Character,Integer> FreqMap;
        for (String word:words){
            if (word.length() == pattern.length()){
                int pos = 0;
                boolean patternR = true;
                for (char c:pattern.toCharArray()){
                    if (c!='-'){
                        if (word.charAt(pos) != c){
                            patternR = false;
                        }
                    }
                    pos++;
                }
                if (patternR){
                    match.add(word);
                }
            }
        }
        FreqMap = getFrequencyMap(match);
        return FreqMap;
    }
    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        Map<Character,Integer> FreqMap = getFreqMapThatMatchesPattern(pattern);
        Set<Character> s = new HashSet<>(guesses);
        int MaxNum = 0;
        char MaxC = '0';
        for (char c:FreqMap.keySet()){
            if (s.contains(c)){
                continue;
            } else {
                int currNum = FreqMap.get(c);
                if (currNum>MaxNum){
                    MaxNum = currNum;
                    MaxC = c;
                }
            }
        }
        return MaxC;
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}