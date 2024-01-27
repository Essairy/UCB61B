package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;

import java.util.List;

import static aoa.utils.FileUtils.readWordsOfLength;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.
        if (wordLength<1){
            throw new IllegalArgumentException("WordLength must bigger than 1");
        }
        List<String> listWordsOfLength = readWordsOfLength(dictionaryFile,wordLength);
        if (listWordsOfLength.isEmpty()){
            throw new IllegalStateException("No match length words");
        }
        int numWords = listWordsOfLength.size();
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        chosenWord = listWordsOfLength.get(randomlyChosenWordNumber);
        pattern = "-".repeat(chosenWord.length());
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        int num = 0;
        char[] arrayPattern = pattern.toCharArray();
        for (int i = 0; i< chosenWord.length();i+=1){
            if (letter == chosenWord.charAt(i)){
                num += 1;
                arrayPattern[i] = letter;
            }
        }
        pattern = new String(arrayPattern);
        return num;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }
}
