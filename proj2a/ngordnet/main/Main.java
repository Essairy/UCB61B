package ngordnet.main;

import ngordnet.browser.NgordnetServer;
import ngordnet.ngrams.NGramMap;
import ngordnet.nhyponyms.Ngraph;

public class Main {
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();

        String wordFile = "./data/ngrams/top_49887_words.csv";
        String countFile = "./data/ngrams/total_counts.csv";
        NGramMap ngm = new NGramMap(wordFile, countFile);

        String hyponymFile = "./data/wordnet/hyponyms.txt";
        String synsetFile = "./data/wordnet/synsets.txt";
        Ngraph ng = new Ngraph(synsetFile,hyponymFile);

        hns.startUp();
        hns.register("history", new HistoryHandler(ngm));
        hns.register("historytext", new HistoryTextHandler(ngm));
        hns.register("hyponyms",new HyponymsHandler(ng,ngm));
    }
}
