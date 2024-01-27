package aoa.guessers;

import java.util.*;

public class GetMap {
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

    public static List<String> filterWords(String pattern, List<String> words){
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
                            break;
                        }
                    }
                    pos++;
                }
                if (patternR){
                    match.add(word);
                }
            }
        }
        return match;
    }

    public static List<String> removeUnexistWord(String pattern, List<String> words, List<Character> guesses){
        List<String> replacewords = new ArrayList<>();
        for (char c:guesses){
            if (!pattern.contains(String.valueOf(c))){
                for (String word:words){
                    if (word.contains(String.valueOf(c))){
                        replacewords.add(word);
                    }
                }
            }
        }
        for (String word:replacewords){
            words.remove(word);
        }
        return words;
    }

    public static char getMax(Map<Character,Integer> FreqMap, List<Character> guesses){
        int MaxNum = 0;
        char MaxC = '0';
        for (char c:FreqMap.keySet()){
            if (guesses.contains(c)){
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

    public static boolean booleanMax(char c,Map<Character,Integer> FreqMap){
        int Maxnum = 0;

        if (FreqMap.containsKey(c)){
            Maxnum = FreqMap.get(c);
        } else {
            return false;
        }
        for (char x:FreqMap.keySet()){
            if (Maxnum < FreqMap.get(x)){
                return false;
            }
        }
        return true;
    }

    public static Map<Character,Integer> getFrequencyMap(String pattern, List<String> words, List<Character> guesses){
        List<String > keywords1 = filterWords(pattern,words);
        keywords1 = removeUnexistWord(pattern,keywords1,guesses);
        Map<Character,Integer> FreqMap1 = getFrequencyMap(keywords1);
        return FreqMap1;
    }
}
