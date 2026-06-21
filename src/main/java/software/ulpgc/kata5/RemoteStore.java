package software.ulpgc.kata5;

import java.io.*;
import java.net.MalformedURLException;
import java.util.function.Function;
import java.util.stream.Stream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class RemoteStore implements Store{

    private static final String url = "https://datasets.imdbws.com/title.basics.tsv.gz";
    private final Function<String, Movie> deserealize;

    public RemoteStore(Function<String, Movie> deserealize) {
        this.deserealize = deserealize;
    }

    @Override
    public Stream<Movie> movies() {
        try {
            return moviesIn(new URL(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Movie> moviesIn(URL url) throws IOException {
        return moviesIn(url.openConnection());
    }

    private Stream<Movie> moviesIn(URLConnection urlConnection) throws IOException {
        return moviesIn(unzip(urlConnection.getInputStream()));
    }

    private Stream<Movie> moviesIn(InputStream inputStream) {
        return moviesIn(toReader(inputStream)).onClose(() -> close(inputStream));
    }

    private void close(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    private Stream<Movie> moviesIn(BufferedReader reader) {
        return reader.lines().skip(1).map(deserealize);
    }

    private BufferedReader toReader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private InputStream unzip(InputStream inputStream) throws IOException {
        return new GZIPInputStream(new BufferedInputStream(inputStream));
    }

}
