import java.util.*;
import java.util.concurrent.*;

public class Main {
    final static int NUMBEROFROUTES = 1000;
    final static int ROUTELENGTH = 100;
    public static void main(String[] args) throws InterruptedException {


        Notifier notifier = new Notifier();
        FrequencyLogger frequencyLogger = new FrequencyLogger(notifier);
        Thread loggerThread = new Thread(frequencyLogger);
        loggerThread.start();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < NUMBEROFROUTES; i++) {
            futures.add(executorService.submit(new RouteGeneratorTask(ROUTELENGTH, notifier)));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        loggerThread.interrupt();
    }
}
