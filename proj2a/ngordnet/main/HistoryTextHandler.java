package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {

    private NGramMap Nmap;

    public HistoryTextHandler(NGramMap map){
        Nmap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        StringBuilder response = new StringBuilder();

        for (String word:words){
            TimeSeries thisWord = Nmap.weightHistory(word,startYear,endYear);
            response.append(word).append(": ");
            response.append(thisWord.toString()).append("\n");
        }

        return response.toString();
    }
}
