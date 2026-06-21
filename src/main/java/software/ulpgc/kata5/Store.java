package software.ulpgc.kata5;

import java.util.stream.Stream;

public interface Store {
    Stream<Movie> movies();
}
