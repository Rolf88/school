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
public class GroupScraper implements Callable<BlockingQueue> {

    public static BlockingQueue groupRes;
    public static List<String> urls;

    public GroupScraper(List urls, BlockingQueue groupRes) {
        this.urls = urls;
        this.groupRes = groupRes;
    }

    public synchronized BlockingQueue webScrape() throws IOException {

        for (int i = 0; i < urls.size(); i++) {
            //remember to add jsoup.jar
            org.jsoup.nodes.Document doc = Jsoup.connect(urls.get(i)).get();
            //fetch authors
            Elements group = doc.select("#group");
            String groups = group.text();
            groupRes.add(groups);
            //System.out.println("Group: " + groups);
        }

        return groupRes;
    }

    @Override
    public BlockingQueue call() throws Exception {

        return webScrape();
    }
}
