import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberOfRoutes = 1000;
        int routeLength = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfRoutes; i++) {
            futures.add(executorService.submit(new RouteGeneratorTask(routeLength)));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

        int maxFrequency = Collections.max(SizeToFrequencyMap.getMap().values());
        int mostFrequentRCount = -1;

        for (Map.Entry<Integer, Integer> entry : SizeToFrequencyMap.getMap().entrySet()) {
            if (entry.getValue() == maxFrequency) {
                mostFrequentRCount = entry.getKey();
                break;
            }
        }

        System.out.println("Самое частое количество повторений " + mostFrequentRCount + " (встретилось " + maxFrequency + " раз)");
        System.out.println("Другие размеры:");

        for (Map.Entry<Integer, Integer> entry : SizeToFrequencyMap.getMap().entrySet()) {
            if (entry.getKey() != mostFrequentRCount) {
                System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " раз)");
            }
        }
    }
}
