import java.util.Map;

public class FrequencyLogger implements Runnable {
    private final Notifier notifier;

    public FrequencyLogger(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (notifier) {
                try {
                    notifier.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            printCurrentLeader();
        }
    }

    private void printCurrentLeader() {
        Map<Integer, Integer> sizeToFreq = SizeToFrequencyMap.getMap();
        int maxFrequency = sizeToFreq.values().stream().max(Integer::compareTo).orElse(0);
        int mostFrequentRCount = sizeToFreq.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(1);

        System.out.println("Самое частое количество повторений " + mostFrequentRCount + " (встретилось " + maxFrequency + " раз)");
    }
}
