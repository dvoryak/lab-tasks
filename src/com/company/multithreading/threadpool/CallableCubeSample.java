package multithreading.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableCubeSample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();

        List<Future<Long>> result = new ArrayList<>();

        for (int i = 0; i < 100_000; i++) {
            final long val = i;
            Callable<Long> task = () -> {
                return val * val * val;
            };

            Future<Long> submit = pool.submit(task);
            result.add(submit);
        }

        for (Future<Long> future : result) {
            //System.out.println(future.get());
            future.get();
        }
        pool.shutdownNow();

       // System.out.println(result.get(9999));

        System.out.println(((ThreadPoolExecutor) pool).getLargestPoolSize());
    }
}
