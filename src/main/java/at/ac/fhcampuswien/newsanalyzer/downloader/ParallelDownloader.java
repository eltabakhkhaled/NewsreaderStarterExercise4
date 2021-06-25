package at.ac.fhcampuswien.newsanalyzer.downloader;

import org.h2.command.dml.Call;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelDownloader extends Downloader {
    @Override
    public int process(List<String> urls) {
        int count = 0;

        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService threads = Executors.newFixedThreadPool(processors);

        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++){
            int index = i;
            Callable<String> todo = () -> saveUrl2File(urls.get(index));
            callables.add(todo);
        }
        threads.shutdown();
        return count;
    }
}
