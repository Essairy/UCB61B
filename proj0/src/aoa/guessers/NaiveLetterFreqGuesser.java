package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
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

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        Set<Character> s = new HashSet<>(guesses);
        Map<Character, Integer> currentm;
        currentm = getFrequencyMap();
        int MaxNum = 0;
        char MaxC = '0';
        for (char c:currentm.keySet()){
            if (s.contains(c)){
                continue;
            } else {
                int currNum = currentm.get(c);
                if (currNum>MaxNum){
                    MaxNum = currNum;
                    MaxC = c;
                }
            }
        }
        return MaxC;
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
