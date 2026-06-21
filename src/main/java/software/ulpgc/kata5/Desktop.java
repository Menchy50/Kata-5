package software.ulpgc.kata5;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

public class Desktop extends JFrame {

    private final Store store;

    public static Desktop with(Store store) {
        return new Desktop(store);
    }

    private Desktop(Store store) {
        this.store = store;
        this.setTitle("Histogram Display");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Desktop display() {
        display(histogramOf(movies()));
        return this;
    }

    private void display(Histogram histogram) {
        this.getContentPane().add(displayOf(histogram));
        this.revalidate();
    }

    private Component displayOf(Histogram histogram) {
        return new ChartPanel(decorate(chartOf(histogram)));
    }

    private JFreeChart decorate(JFreeChart chart) {
        return chart;
    }

    private JFreeChart chartOf(Histogram histogram) {
        return ChartFactory.createHistogram(histogram.title(), histogram.x(), "count", datasetOf(histogram));
    }

    private XYSeriesCollection datasetOf(Histogram histogram) {
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(seriesOf(histogram));
        return collection;
    }

    private XYSeries seriesOf(Histogram histogram) {
        XYSeries series = new XYSeries(histogram.legend());
        for (int bin : histogram) {
            series.add(bin, histogram.count(bin));
        }
        return series;
    }

    private Stream<Movie> movies() {
        return store.movies().filter(m -> m.year() >= 1900).filter(m -> m.year() <= 2025);
    }

    private Histogram histogramOf(Stream<Movie> movies) {
        return HistogramBuilder.with(movies).title("Peliculas por año").x("Año").legend("Nº peliculas").build(Movie::year);
    }

}
