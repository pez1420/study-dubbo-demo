package com.study.io.nio.file;

/**
 * WatchDog
 *
 * @author luyb@servyou.com.cn
 * @version 2016-08-17 22:40
 */
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

/**
 *
 * @author GinKo.Wang
 * @mail <a href="mailto:yingosen@gmail.com">yingosen@gmail.com</a>
 * @date 2016年3月28日 上午10:58:29
 *
 */
public class WatchDog {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        guard("F:/aaaa");
    }

    private static void guard(String watchPath) throws IOException {
        Path dir = new File(watchPath).toPath();
        WatchService watcher = FileSystems.getDefault().newWatchService();
        dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        System.err.println("start WatchDog ...");
        while (true) {
            WatchKey watchKey = null;
            try {
                watchKey = watcher.take();
                System.err.println("something maybe happend ...");
                dealEvents(watchKey);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                break;
            }
        }
    }

    private static void dealEvents(WatchKey watchKey) {
        if (watchKey == null) {
            System.err.println("nothing happend ...");
        }
        List<WatchEvent<?>> pollEvents = watchKey.pollEvents();
        for (WatchEvent<?> watchEvent : pollEvents) {
            WatchEvent.Kind<?> kind = watchEvent.kind();
            if (kind == StandardWatchEventKinds.OVERFLOW) {
                continue;
            }
            Path path = (Path) watchKey.watchable();
            Path filename = (Path) watchEvent.context();
            System.err.format(kind.name() + " # file : %s\\%s%n", path.toString(), filename.toString());
            if (!watchKey.reset()) {
                break;
            }
        }
    }
}