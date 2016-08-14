package facade;

import entity.Group;
import fetcher.AuthorScraper;
import fetcher.ClassScraper;
import fetcher.GroupScraper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RolfMoikjær
 */
public class GroupFacade {

    public static List<String> urls;
    public static BlockingQueue authorRes;
    public static BlockingQueue classRes;
    public static BlockingQueue groupRes;

    public GroupFacade() {
        this.urls = new ArrayList<>();
        this.authorRes = new ArrayBlockingQueue(10);
        this.classRes = new ArrayBlockingQueue(10);
        this.groupRes = new ArrayBlockingQueue(10);
    }

    private static List<Group> makeGroups(BlockingQueue authorRes, BlockingQueue classRes, BlockingQueue groupRes) {

        List<Group> groups1 = new ArrayList<>();

        List authors = new ArrayList();
        authorRes.drainTo(authors);
        Object author;

        List classes = new ArrayList();
        classRes.drainTo(classes);
        Object class1;

        List groups = new ArrayList();
        groupRes.drainTo(groups);
        Object group;
        for (int i = 0; i < authors.size(); i++) {
            author = authors.get(i);
            class1 = classes.get(i);
            group = groups.get(i);

            groups1.add(new Group(author.toString(), group.toString(), class1.toString()));
        }
        return groups1;
    }

    public List<Group> createGroups() throws InterruptedException {
        //Class A
        urls.add("http://cphbusinessjb.cloudapp.net/CA2/");
        urls.add("http://ca2-ebski.rhcloud.com/CA2New/");
        urls.add("http://ca2-pernille.rhcloud.com/NYCA2/");

        //Class B
        urls.add("https://ca2-ssteinaa.rhcloud.com/CA2/");
        urls.add("https://ca2-ksw.rhcloud.com/DeGuleSider/");
        urls.add("http://ca2-ab207.rhcloud.com/CA2/index.html");
        urls.add("http://ca2-sindt.rhcloud.com/CA2/index.jsp");
        urls.add("http://ca2gruppe8-tocvfan.rhcloud.com/");
        urls.add("https://ca-ichti.rhcloud.com/CA2/");

        //Class COS
        urls.add("https://ca2-9fitteen.rhcloud.com:8443/CA2/");

        ExecutorService authExc = Executors.newFixedThreadPool(4);
        ExecutorService classExc = Executors.newFixedThreadPool(4);
        ExecutorService groupExc = Executors.newFixedThreadPool(4);

        Callable<BlockingQueue> authCall = new AuthorScraper(urls, authorRes);
        Callable<BlockingQueue> classCall = new ClassScraper(urls, classRes);
        Callable<BlockingQueue> groupCall = new GroupScraper(urls, groupRes);

        Future<BlockingQueue> authFut = authExc.submit(authCall);
        Future<BlockingQueue> classFut = classExc.submit(classCall);
        Future<BlockingQueue> groupFut = groupExc.submit(groupCall);

        try {
            authorRes = authFut.get();
            classRes = classFut.get();
            groupRes = groupFut.get();
        } catch (ExecutionException ex) {
            Logger.getLogger(GroupFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Group> finalGroups = makeGroups(authorRes, classRes, groupRes);
        for (int i = 0; i < finalGroups.size(); i++) {
            String authorString = finalGroups.get(i).getAuthor();
            String classString = finalGroups.get(i).getClass1();
            String groupString = finalGroups.get(i).getGroup();

            System.out.println("|GROUP: " + groupString + " |AUTHORS: " + authorString + " |CLASS: " + classString);
            System.out.println("--------------------------------------------------------------------------------");
        }

        authExc.shutdown();
        classExc.shutdown();
        groupExc.shutdown();

        return finalGroups;
    }
}
