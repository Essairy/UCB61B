package ngordnet.nhyponyms;

import java.util.ArrayList;
import java.util.List;

public class graphNode {

    private ArrayList<Integer> targetList;

    public graphNode(){
        targetList = new ArrayList<>();
    }

    public void addTarget(int targetValue){
        targetList.add(targetValue);
    }

    public void addTargetList(List<Integer> targetValueList){
        targetList.addAll(targetValueList);
    }

    public ArrayList<Integer> getTargetList(){
        return targetList;
    }


}
