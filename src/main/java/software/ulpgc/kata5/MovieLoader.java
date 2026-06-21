package software.ulpgc.kata5;

import java.util.List;
import java.util.stream.Stream;

public interface MovieLoader {

    Stream<Movie> loadAll();

}
