package eecs40.rssmanager;

import de.vogella.rss.model.Feed;
import eecs40.observer.RSSFeedObserver;
import eecs40.rssfeed.RSSFeed;
import eecs40.rssfeed.RSSFeedInterface;

import java.util.ArrayList;
import java.util.List;

public class RSSManager extends AbstractRSSManager{
    ArrayList<RSSFeedInterface> feeds;
    ArrayList<RSSFeedObserver> observers;
    long interval = 0;

    public RSSManager(){
        feeds = new ArrayList<RSSFeedInterface>();
        observers = new ArrayList<RSSFeedObserver>();
    }

    @Override
    public void addFeed(RSSFeedInterface f) {
        if(!feeds.contains(f)) {
            feeds.add(f);
            //notifyObservers(f);
        }
        else {
            System.out.println("You are already subscribed to this feed.");
        }
    }

    @Override
    public void removeFeed(RSSFeedInterface f) {
        if (feeds.contains(f)) {
            feeds.remove(f);
        }
        else {
            System.out.println("You are not subscribed to this feed.");
        }
    }

    @Override
    public List<RSSFeedInterface> getFeedList() {
        return feeds;
    }

    @Override
    public void addObserver(RSSFeedObserver ob) {
        if (!observers.contains(ob)) {
            observers.add(ob);
        }
        else {
            System.out.println("This observer already exists!");
        }
    }

    @Override
    public void removeObserver(RSSFeedObserver ob) {
        if(observers.contains(ob)) {
            observers.remove(ob);
        }
        else {
            System.out.println("This observer doesn't exist!");
        }
    }

    @Override
    public List<RSSFeedObserver> getObserverList() {
        return observers;
    }

    @Override
    public void notifyObservers(RSSFeedInterface fi) {
        super.notifyObservers(fi);
    }

    @Override
    public void readAll() {
        super.readAll();
    }

    @Override
    public void run() {
        readAll();
        super.run();
    }

    @Override
    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public long getInterval() {
        return interval;
    }
}
