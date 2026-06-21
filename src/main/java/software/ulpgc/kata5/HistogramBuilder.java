package software.ulpgc.kata5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class HistogramBuilder {

    private final Stream<Movie> movies;
    private final Map<String, String> axis;

    public HistogramBuilder(Stream<Movie> movies) {
        this.axis = new HashMap<>();
        this.movies = movies;
    }

    public static HistogramBuilder with(Stream<Movie> movies) {
        return new HistogramBuilder(movies);
    }

    public HistogramBuilder title(String title) {
        axis.put("title", title);
        return this;
    }

    public HistogramBuilder x(String x) {
        this.axis.put("x", x);
        return this;
    }

    public HistogramBuilder legend(String legend) {
        this.axis.put("legend", legend);
        return this;
    }

    public Histogram build(Function<Movie, Integer> binarize) {
        Histogram histogram = new Histogram(axis);
        movies.map(binarize).forEach(histogram::add);
        return histogram;
    }

}
