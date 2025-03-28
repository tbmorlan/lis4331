package com.example.newsreader;

import android.content.Context;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

// Javadoc comments
/**
 *
 * Author:
 */
public class FileIO {
    // https://www.daveramsey.com/blog.rss
    private final String URL_STRING = "https://www.pcmag.com/feeds/rss/latest"; // specifies URL for RSS feed
    private final String FILENAME = "news_feed.xml"; // file stores RSS feed

    /*
     * Context: abstract class allows access to application-specific resources and classes,
     * as well as calls for application-level operations such as launching activities,
     * broadcasting and receiving intents (implicit or explicit), etc.
     */

    // stores context for app. Here, can be used for both downloadFile and readFile methods
    private Context context = null;

    // public member methods
    // default constructor (initializes data member)
    public FileIO(Context context) {
        this.context = context;
    }

    public void downloadFile() {
        try {
            // get URL
            URL url = new URL(URL_STRING);

            // get input stream
            InputStream in = url.openStream();

            // get output stream (MODE_PRIVATE will only allow news_feed.xml to be run--no other files!)
            FileOutputStream out = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            // read input and write output
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            while (bytesRead != -1) {
                out.write(buffer, 0, bytesRead);
                bytesRead = in.read(buffer);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            Log.e("News reader", e.toString());
        }
    }

    public RSSFeed readFile() {
        try {
            // get XML reader (parses XML into Java objects)
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            // set content handler
            RSSFeedHandler theRssHandler = new RSSFeedHandler();
            xmlreader.setContentHandler(theRssHandler);

            // read file from internal storage
            FileInputStream in = context.openFileInput(FILENAME);

            // parse data (to get title, date, description)
            InputSource is = new InputSource(in);
            xmlreader.parse(is);

            // set feed in activity
            RSSFeed feed = theRssHandler.getFeed();
            return feed;
        } catch (Exception e) {
            Log.e("News reader", e.toString());
            return null;
        }
    }
}
