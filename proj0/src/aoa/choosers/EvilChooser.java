package aoa.choosers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static aoa.utils.FileUtils.readWordsOfLength;

public class EvilChooser implements Chooser {
    private String pattern;

    private List<String> wordPool;

    // current word whether match localPattern
    private boolean validPatternWord(String word,String localPattern){
        String patternWord = word;
        for (char c:word.toCharArray()){
            if (!localPattern.contains(String.valueOf(c))){
                patternWord = patternWord.replace(c,'-');
            }
        }
        return patternWord.equals(localPattern);
    }

    private String fusionPattern(String newPattern){
        char[] nPattern = pattern.toCharArray();
        for (int i = 0; i<nPattern.length;i++){
            if (nPattern[i] == '-'){
                nPattern[i] = newPattern.charAt(i);
            }
        }
        return new String(nPattern);
    }

    private String getLocalPattern(char guessChar,String word){
        char[] localPattern = new char[pattern.length()];
        for (int i = 0; i< localPattern.length;i+=1){
            if (word.charAt(i)==guessChar){
                localPattern[i] = guessChar;
            } else {
                localPattern[i] = '-';
            }
        }
        return new String(localPattern);
    }

    private TreeMap<String,List<String>> getWordMap(char guessChar,List<String> wordList){
        TreeMap<String,List<String>> pools = new TreeMap<>();
        String nullPattern = "-".repeat(pattern.length());
        for (String word:wordList){
            if (word.contains(String.valueOf(guessChar))){
                String localPattern = getLocalPattern(guessChar,word);
                appendPattern(pools, localPattern, word);
            } else {
                appendPattern(pools, nullPattern, word);
            }
        }
        return pools;
    }

    private void appendPattern(TreeMap<String, List<String>> pools, String nullPattern, String word) {
        if (pools.containsKey(nullPattern)){
            List<String> nullPatternList = pools.get(nullPattern);
            nullPatternList.add(word);
            pools.replace(nullPattern,nullPatternList);
        } else {
            List<String> nullPatternList = new ArrayList<>();
            nullPatternList.add(word);
            pools.put(nullPattern,nullPatternList);
        }
    }

    public EvilChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in this constructor.
        if (wordLength<1){
            throw new IllegalArgumentException("WordLength must bigger than 1");
        }
        List<String> listWordsOfLength = readWordsOfLength(dictionaryFile,wordLength);
        if (listWordsOfLength.isEmpty()){
            throw new IllegalStateException("No match length words");
        }
        int numWords = listWordsOfLength.size();
        wordPool = listWordsOfLength;
        pattern = "-".repeat(wordLength);
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        TreeMap<String,List<String>> pools;
        pools = getWordMap(letter,wordPool);
        int Maxnum = 0;
        String newPattern = "-".repeat(pattern.length());
        for (String localPattern:pools.keySet()){
            int localNum = pools.get(localPattern).size();
            if (localNum>Maxnum){
                Maxnum = localNum;
                newPattern = localPattern;
            }
        }
        pattern = fusionPattern(newPattern);
        wordPool = pools.get(newPattern);
        int occuranceNum = 0;
        for (char c:pattern.toCharArray()){
            if (c == letter){
                occuranceNum+=1;
            }
        }
        return occuranceNum;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return wordPool.get(0);
    }
}
