package eecs40.rssfeed;

import de.vogella.rss.model.Feed;
import de.vogella.rss.model.FeedMessage;
import de.vogella.rss.read.RSSFeedParser;

import java.util.ArrayList;
import java.util.Iterator;

public class RSSFeed implements RSSFeedInterface{
    Feed myFeed = null;
    Feed compFeed = null;
    ArrayList<String> ListGUID = null;
    RSSFeedParser mParser = null;
    String url = "";
    String source = "";
    int updateCount = 0;


    public RSSFeed(String url, String source){
        this.url = url;
        this.source = source;
        mParser =  new RSSFeedParser(url);
        ListGUID = new ArrayList<String>();


    }

    @Override
    public Feed getFeed() {
        return myFeed;
    }

    @Override
    synchronized public int read() {
        compFeed = mParser.readFeed();
        updateCount = compFeed.getMessages().size();
        Iterator<FeedMessage> fcmIt = iterator();
        if(myFeed != null) {
            while (fcmIt.hasNext()) {
                FeedMessage fcm = fcmIt.next();
                for (FeedMessage fmm : myFeed.getMessages()) {
                    if (fcm.getGuid().equals(fmm.getGuid())) {
                        updateCount--;
                        fcmIt.remove();
                    }
                }
            }
            for (FeedMessage fm : compFeed.getMessages()) {
                myFeed.getMessages().add(0, fm);
                ListGUID.add(fm.getGuid());
            }
        }
        else {
            myFeed = compFeed;
        }
        return updateCount;
    }

    @Override
    public int size() {
        if (myFeed != null)
            return myFeed.getMessages().size();
        else
            return 0;
    }

    @Override
    public int getLastNumUpdate() {
        return updateCount;
    }

    @Override
    public Iterator<FeedMessage> iterator() {
        return compFeed.getMessages().iterator();
    }
}
