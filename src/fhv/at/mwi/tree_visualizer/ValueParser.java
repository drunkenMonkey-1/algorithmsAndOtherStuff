package fhv.at.mwi.tree_visualizer;

/**
 * This interface is used for anonymous callbacks so the class you pass it to, knows which values to parse
 * and how to parse them
 * @param <V> Value to be parsed or to be parsed to
 */
public interface ValueParser<V extends Comparable> {
    public V parse(String text) throws java.lang.NumberFormatException;
    public String parseVtoString(V value);
}
