package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import ngordnet.nhyponyms.Ngraph;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {

    private Ngraph wordGraph;
    private NGramMap Nmap;

    public HyponymsHandler(Ngraph ng,NGramMap ngm){
        wordGraph = ng;
        Nmap = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k  = q.k();
        if (k == 0){

        }

        ArrayList<String> hypoList =  getMerge(words);
        TreeMap<String,Integer> historyNum = getHistoryMost(hypoList,startYear,endYear);
        List<String> topK = getKMax(historyNum,k);
        topK.sort(String::compareTo);
        return topK.toString();
    }

    private List<String> getKMax(TreeMap<String,Integer> historyNum,int k){
        PriorityQueue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<String, Integer> entry : historyNum.entrySet()) {
            maxHeap.offer(entry);
        }

        List<String> topKeys = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            topKeys.add(maxHeap.poll().getKey());
        }
        return topKeys;
    }

    private TreeMap<String,Integer> getHistoryMost(List<String> hypoList,int startYear,int endYear){

        TreeMap<String,Integer> wordNum = new TreeMap<>();

        for (String word:hypoList){
            int totalNum = 0;
            if (!Nmap.countainsWord(word)){
                wordNum.put(word,0);
                continue;
            }
            TimeSeries thisWord = Nmap.countHistory(word,startYear,endYear);

            for (double num:thisWord.data()){
                totalNum += (int) num;
            }
            wordNum.put(word,totalNum);
        }
        return wordNum;
    }

    private ArrayList<String> getMerge(List<String> words){
        ArrayList<String> hypoList =  new ArrayList<>();
        for (String word:words){
            if (hypoList.isEmpty()){
                hypoList = wordGraph.getHypony(word);
                continue;
            }
            ArrayList<String> newWordList = wordGraph.getHypony(word);

            hypoList.retainAll(newWordList);
        }
        return hypoList;
    }
}
