package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ItemsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private RSSFeed feed;
    private FileIO io;

    private TextView titleTextView;
    private ListView itemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        // show app icon in emulator
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // create FileIO object
        io = new FileIO(getApplicationContext());

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        itemsListView = (ListView) findViewById(R.id.itemsListView);

        itemsListView.setOnItemClickListener(this);

        new DownloadFeed().execute();
    }




    // DownloadFeed (inner class): uses background thread to download XML for RSS feed
    class DownloadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            io.downloadFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("News reader", "Feed downloaded");
            new ReadFeed().execute();
        }
    }

    // ReadFeed (inner class): background thread to read XML for RSS feed
    // and parse into RSSFeed object (containing multiple RSSItem objects)
    // Note: AsyncTask intended to enable easy use of UI thread (however, deprecated in API level 30).
    class ReadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            feed = io.readFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("News reader", "Feed read");
            // update the display for the activity
            ItemsActivity.this.updateDisplay();
        }
    }

    // updates user interface (UI)
    public void updateDisplay() {
        if (feed == null) {
            titleTextView.setText("Unable to get RSS feed");
            return;
        }

        // set title for feed
        titleTextView.setText(feed.getTitle());

        // get items for feed
        ArrayList<RSSItem> items = feed.getAllItems();

        // create List of Map<String, ?> objects
        // ArrayList stores items as ordered collection, accessed by an index number (int type).
        // HashMap stores items in "key/value" pairs, accessed by key name (String type).
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        for (RSSItem item : items) {
            HashMap<String, String> map = new HashMap<>();
            map.put("pubDate", item.getPubDateFormatted());
            map.put("title", item.getTitle());
            data.add(map);
        }

        // create resource, from and to variables
        int resource = R.layout.listview_item;
        String[] from = {"pubDate", "title"};
        int[] to = {R.id.pubDateTextView, R.id.titleTextView};

        /*
        An adapter is a bridge between UI component and data source.
        Helps fill data in UI component.
        SimpleAdapter used for customization of list or grid items.
        (Note: RecyclerView now used instead of List or Grid views.)
        Easy adapter to map static data to views defined in XML file (layout).
        */

        // create and set adapter
        SimpleAdapter adapter = new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);

        // displays message in LogCat view
        Log.d("News reader", "Feed displayed");
    }

    // Parameters:
    // 1) Provides AdapterView object for list of items,
    // 2) Provides View object of selected item,
    // 3) Provides position of selected item,
    // 4) Provides ID of selected item.
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // get item at specified position
        RSSItem item = feed.getItem(position);

        // Intents: "intention" to perform an action.
        // Explicit intent: used to pass data between activities.
        // Implicit intent: Android determines best app to perform action (e.g., view URL, make call, etc.)
        // create an intent (to pass all data to Item activity)
        Intent intent = new Intent(this, ItemActivity.class);

        intent.putExtra("pubDate", item.getPubDate());
        intent.putExtra("title", item.getTitle());
        intent.putExtra("description", item.getDescription());
        intent.putExtra("link", item.getLink());

        this.startActivity(intent);
    }
}
