import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExamTimer {
    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public static void startTimer(int durationInMinutes, Runnable onTimeout) {
        executor.schedule(() -> {
            System.out.println("Time is up! Auto-submitting your answers...");
            onTimeout.run();
            executor.shutdown();
        }, durationInMinutes, TimeUnit.MINUTES);
    }

    public static void stopTimer() {
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }
}

