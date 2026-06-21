package software.ulpgc.kata5;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws SQLException {
        Desktop.with(new RemoteStore(MovieDeserialize::fromTsv)).display().setVisible(true);
    }

}
