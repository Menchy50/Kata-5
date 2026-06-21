package software.ulpgc.kata5;

import java.util.stream.Stream;

public interface Recorder {
    void record(Stream<Movie> movies);
}
