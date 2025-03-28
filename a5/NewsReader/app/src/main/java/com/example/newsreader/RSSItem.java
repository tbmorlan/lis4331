package com.example.newsreader;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class RSSItem {
    private String title = null;
    private String description = null;
    private String link = null;
    private String pubDate = null;

    //includes user's location date, time, and Greenwich Mean Time (GMT) timezone offset (Z)
    //(Note: Times west of GMT have negative offset, and times east of GMT have positive offset
    //private SimpleDateFormat dateOutFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.getDefault());
    //private SimpleDateFormat dateInFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.getDefault());

    //includes user's location date, time
    private SimpleDateFormat dateOutFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.getDefault());
    private SimpleDateFormat dateInFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.getDefault());

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getPubDateFormatted() {
        try {
            if (pubDate != null) {    // make sure pubDate exists
                Date date = dateInFormat.parse(pubDate);
                String pubDateFormatted = dateOutFormat.format(date);
                return pubDateFormatted;
            }
            else {
                return "No date in RSS feed";
            }
        }
        catch (ParseException e) {
            return "Error in date of RSS feed";    // don't throw exception
        }
    }
}