package com.example.c4q_ac35.justmygoogle;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by c4q-marbella on 6/26/15.
 */
public class SportsXmlUtils {
    private String sportsData;
    private ArrayList<Feed> sportsFeedList;

    public SportsXmlUtils(String xmlData){
        sportsData = xmlData;
        sportsFeedList = new ArrayList<Feed>();
        this.sportsProcess();
    }

    public ArrayList<Feed> getSportsFeeds(){
        return sportsFeedList;
    }


    public void sportsProcess(){

        Feed currentRecord = null;

        boolean inItem = false;

        String textValue = "";

        try{

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.sportsData));
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {

                String tagName = xpp.getName();
                // Creating a new item
                if (eventType == XmlPullParser.START_TAG) {

                    if(tagName.equalsIgnoreCase("item")){
                        inItem = true;
                        currentRecord = new Feed();
                    }

                }
                // pulling text
                else if (eventType == XmlPullParser.TEXT) {
                    textValue = xpp.getText();
                }
                // finishing tag
                else if (eventType == XmlPullParser.END_TAG) {
                    if(inItem){

                        // finalize item
                        if(tagName.equalsIgnoreCase("item")){
                            sportsFeedList.add(currentRecord);
                            inItem = false;
                        }
                        // set Title
                        if(tagName.equalsIgnoreCase("title")){
                            currentRecord.setTitle(textValue);
                        }
                        if(tagName.equalsIgnoreCase("pubDate")){
                            currentRecord.setPubDate(textValue);
                        }
                        if(tagName.equalsIgnoreCase("category")){
                            currentRecord.setCategory(textValue);
                        }
                        // set link to article
                        if(tagName.equalsIgnoreCase("link")){
                            currentRecord.setLink(textValue);
                        }
                        // set link to image
                        if(tagName.equalsIgnoreCase("enclosure url")){
                            currentRecord.setUrlImage(textValue);
                        }
                    }
                }

                eventType = xpp.next();
            }
        }catch(Exception e ){

        }
    }
}
