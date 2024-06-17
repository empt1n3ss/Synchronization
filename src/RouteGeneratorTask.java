import java.util.Random;

public class RouteGeneratorTask implements Runnable {
    private final int routeLength;

    public RouteGeneratorTask(int routeLength) {
        this.routeLength = routeLength;
    }

    @Override
    public void run() {
        String route = generateRoute("RLRFR", routeLength);
        int rCount = countChar(route, 'R');
        SizeToFrequencyMap.updateFrequencyMap(rCount);
    }

    private String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
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
