package fetcher;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RolfMoikjær
 */
public class AuthorScraper implements Callable<BlockingQueue> {

    public static BlockingQueue authorRes;
    public static List<String> urls;

    public AuthorScraper(List urls, BlockingQueue authorRes) {
        this.urls = urls;
        this.authorRes = authorRes;
    }

    public synchronized BlockingQueue webScrape() throws IOException {
        
        for (int i = 0; i < urls.size(); i++) {
            //remember to add jsoup.jar
            org.jsoup.nodes.Document doc = Jsoup.connect(urls.get(i)).get();
            //fetch authors
            Elements author = doc.select("#authors");
            String authors = author.text();
            authorRes.add(authors);
            //System.out.println("author: " + authors);
        }
            
        
        return authorRes;
    }

    @Override
    public BlockingQueue call() throws Exception {
        
        return webScrape();
    }
}
