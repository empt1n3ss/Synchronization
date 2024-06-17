import java.util.Random;

public class RouteGeneratorTask implements Runnable {
    private final int ROUTELENGTH;
    private final Notifier notifier;

    public RouteGeneratorTask(int routeLength, Notifier notifier) {
        this.ROUTELENGTH = routeLength;
        this.notifier = notifier;
    }

    @Override
    public void run() {
        String route = generateRoute(ROUTELENGTH);
        int rCount = countChar(route, 'R');
        SizeToFrequencyMap.updateFrequencyMap(rCount);
        notifier.notifyLogger();
    }

    private String generateRoute(int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append("RLRFR".charAt(random.nextInt("RLRFR".length())));
        }
        return route.toString();
    }

    private int countChar(String str, char ch) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == ch) {
                count++;
            }
        }
        return count;
    }
}
