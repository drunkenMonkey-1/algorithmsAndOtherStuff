package fhv.at.mwi.tree_visualizer;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Useful when you need to translate some kind of property to a color.
 * For example the RB tree's got a red/black bit that needs to be translated into a color.
 * Since the graphics interface doesn't know that true is red and false black this property
 * translator will do the job. Simply pass a value V (e.g. a boolean) and assign a color to the
 * value.
 * @param <V> Value type you want to "translate"
 */
public class PropertyTranslator<V> {

    private Map<V, Color> propertyMap = new HashMap<>();

    public void addColorForProperty(V prop, Color c){
        propertyMap.put(prop, c);
    }

    public Color getColorByProperty(V prop){
        return propertyMap.get(prop);
    }
}
