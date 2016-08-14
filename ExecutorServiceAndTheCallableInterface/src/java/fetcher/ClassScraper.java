/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetcher;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 *
 * @author RolfMoikjær
 */
public class ClassScraper implements Callable<BlockingQueue> {

    public static BlockingQueue classRes;
    public static List<String> urls;

    public ClassScraper(List urls, BlockingQueue classRes) {
        this.urls = urls;
        this.classRes = classRes;
    }

    public synchronized BlockingQueue webScrape() throws IOException {
        for (int i = 0; i < urls.size(); i++) {

            //remember to add jsoup.jar
            org.jsoup.nodes.Document doc = Jsoup.connect(urls.get(i)).get();
            //fetch authors
            Elements class1 = doc.select("#class");
            String classes = class1.text();
            classRes.add(classes);
            //System.out.println("Class: " + classes);
        }
        return classRes;
    }

    @Override
    public BlockingQueue call() throws Exception {

        return webScrape();
    }
}
