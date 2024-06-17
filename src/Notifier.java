public class Notifier {
    public synchronized void notifyLogger() {
        notify();
    }
}
