package software.ulpgc.kata5;

import java.util.HashMap;
import java.util.*;

public class Histogram implements Iterable<Integer> {

    private final Map<String, String> labels;
    private final Map<Integer, Integer> map;

    public Histogram(Map<String, String> labels) {
        this.labels = labels;
        this.map = new HashMap<>();
    }

    public void add(int key) {
        map.put(key, count(key) + 1);
    }

    public int count(int key) {
        return map.getOrDefault(key, 0);
    }

    @Override
    public Iterator<Integer> iterator() {
        return map.keySet().iterator();
    }

    public int size() {
        return map.size();
    }

    public String x() {
        return labels.getOrDefault("x", "");
    }

    public String title() {
        return labels.getOrDefault("title", "");
    }

    public String legend() {
        return labels.getOrDefault("legend", "");
    }

}
