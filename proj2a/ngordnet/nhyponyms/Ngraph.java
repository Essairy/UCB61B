package ngordnet.nhyponyms;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import ngordnet.ngrams.TimeSeries;

import java.util.*;

public class Ngraph {

    private HashMap<String,Integer> wordMap;

    private HashMap<Integer,String> valueWordMap;
    private HashMap<Integer,graphNode> wordGraph;

    public Ngraph(String synsetName, String hyponymsName){
        In synFile = new In(synsetName);
        wordMap = new HashMap<>();
        valueWordMap = new HashMap<>();
        wordGraph = new HashMap<>();

        while(synFile.hasNextLine()){
            String newLine = synFile.readLine();
            String[] keyNote = newLine.split(",");
            int keyNum = Integer.parseInt(keyNote[0]);
            String key = keyNote[1];
            wordMap.put(key,keyNum);
            valueWordMap.put(keyNum,key);
            graphNode newNode = new graphNode();
            wordGraph.put(keyNum,newNode);
        }

        In hyponyFile = new In(hyponymsName);


        while(hyponyFile.hasNextLine()){
            String newLine = hyponyFile.readLine();
            String[] keyNote = newLine.split(",");
            int key = Integer.parseInt(keyNote[0]);
            ArrayList<Integer> targetList = new ArrayList<>();
            for (int i = 1;i<keyNote.length;i+=1){
                targetList.add(Integer.parseInt(keyNote[i]));
            }
            graphNode currentNode = new graphNode();
            currentNode.addTargetList(targetList);
            wordGraph.put(key,currentNode);
        }


    }

    private ArrayList<Integer> getsub(ArrayList<Integer> subList){
        Boolean t = false;
        ArrayList<Integer> newSub = new ArrayList<>();
        for (int i:subList){
            newSub.addAll(wordGraph.get(i).getTargetList());
        }
        if (newSub.isEmpty()){
            return newSub;
        } else {
            newSub.addAll(getsub(newSub));
            return newSub;
        }
    }

    public ArrayList<String> getHypony(String word){

        ArrayList<String> keyWordSet = new ArrayList<>();
        for(String keyword:wordMap.keySet()){
            String[] keyList = keyword.split(" ");
            for (String key:keyList){
                if (key.equals(word)){
                    keyWordSet.add(keyword);
                }
            }
        }

        ArrayList<Integer> valueList = new ArrayList<>();
        for (String keyWord:keyWordSet){
            int newValue = wordMap.get(keyWord);
            ArrayList<Integer> values = wordGraph.get(newValue).getTargetList();
            valueList.add(newValue);
            valueList.addAll(values);
            valueList.addAll(getsub(values));
        }

        ArrayList<String> totalWordList = new ArrayList<>();
        for (int i:valueList){
            totalWordList.addAll(Arrays.asList(valueWordMap.get(i).split(" ")));
        }

        totalWordList.sort(String::compareTo);

        String oldword = totalWordList.get(0);
        ArrayList<String> resultList = new ArrayList<>();
        resultList.add(oldword);
        for (String keyword:totalWordList){
            if (keyword.equals(oldword)){
                continue;
            }
            resultList.add(keyword);
            oldword = keyword;
        }

        return resultList;
    }
}
