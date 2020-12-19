package main;
import de.vogella.rss.model.FeedMessage;
import eecs40.observer.RSSFeedObserver;
import eecs40.rssfeed.RSSFeed;
import eecs40.rssfeed.RSSFeedInterface;
import eecs40.rssmanager.AbstractRSSManager;
import eecs40.rssmanager.RSSManager;
import eecs40.rssmanager.RSSManagerInterface;


public class Main implements RSSFeedObserver {
    final static long _1MIN = 60000;
    final static long _5MIN = 300000;
    final static long _10MIN = 600000;

    public static void main(String[] args) {
        Main m = new Main();
        RSSFeedInterface f1 = new RSSFeed("http://rss.cnn.com/rss/edition.rss", "CNN News");
        //System.out.println(f1.getFeed());
        RSSFeedInterface f2 = new RSSFeed("http://feeds.foxnews.com/foxnews/latest", "Fox News");
        RSSFeedInterface f3 = new RSSFeed("http://feeds.bbci.co.uk/news/world/rss.xml", "BBC");
        RSSFeedInterface f4 = new RSSFeed("https://www.nytimes.com/svc/collections/v1/publish/https://www.nytimes.com/section/world/rss.xml","New York Times");
        RSSFeedInterface f5 = new RSSFeed("https://www.buzzfeed.com/world.xml", "Buzzfeed");
        RSSFeedInterface f6 = new RSSFeed("https://www.yahoo.com/news/rss/world", "Yahoo");
        RSSFeedInterface f7 = new RSSFeed("https://timesofindia.indiatimes.com/rssfeeds/296589292.cms", "Times of India");
        // Create RSSManager

        RSSManagerInterface rm = new RSSManager();
        // Add current class's object as a listener
        rm.addObserver(m);
        // set fetch interval
        rm.setInterval(10000); // 10000 ms, 10 sec
        //Add feed
        rm.addFeed(f1);
        rm.addFeed(f2);
        rm.addFeed(f3);
        rm.addFeed(f4);
        rm.addFeed(f5);
        rm.addFeed(f6);
        rm.addFeed(f7);
        // run RSSManager in background
        new Thread(rm).start();
    }
    @Override
    public void newFeedArrived(RSSFeedInterface f) {
        System.out.println("\n" + f.getFeed().getTitle() + " has update: " + f.getLastNumUpdate());
        int count = 0;
        for( FeedMessage fm : f.getFeed().getMessages()) {
            System.out.println("\t" + fm);
            if (++count == f.getLastNumUpdate())
                break;
        }
    }
}
