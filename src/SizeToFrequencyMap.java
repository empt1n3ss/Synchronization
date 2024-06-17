import java.util.HashMap;
import java.util.Map;

public class SizeToFrequencyMap {
    private static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static Map<Integer, Integer> getMap() {
        return sizeToFreq;
    }

    public static void updateFrequencyMap(int rCount) {
        synchronized (sizeToFreq) {
            sizeToFreq.merge(rCount, 1, Integer::sum);
        }
    }
}
