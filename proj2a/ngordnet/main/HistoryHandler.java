package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {

    private NGramMap Nmap;

    public HistoryHandler(NGramMap map){
        Nmap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        XYChart chart = null;
        for (String word:words){
            TimeSeries currentWord = Nmap.countHistory(word,startYear,endYear);
            labels.add(word);
            lts.add(currentWord);
            chart = Plotter.generateTimeSeriesChart(labels, lts);
        }


        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
