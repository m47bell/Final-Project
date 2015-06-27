package com.example.c4q_ac35.justmygoogle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dexafree.materialList.cards.BasicButtonsCard;
import com.dexafree.materialList.cards.BasicImageButtonsCard;
import com.dexafree.materialList.cards.BasicListCard;
import com.dexafree.materialList.cards.BigImageButtonsCard;
import com.dexafree.materialList.cards.BigImageCard;
import com.dexafree.materialList.cards.OnButtonPressListener;
import com.dexafree.materialList.cards.SimpleCard;
import com.dexafree.materialList.cards.SmallImageCard;
import com.dexafree.materialList.cards.WelcomeCard;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.controller.RecyclerItemClickListener;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.model.CardItemView;
import com.dexafree.materialList.view.MaterialListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private Context mContext;
    private MaterialListView mListView;
    private String sportsAPI = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sportsAPI = "http://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU";
        new DownloadSportsTask().execute(sportsAPI);

        // Save a reference to the context
        mContext = this;

        // Bind the MaterialListView to a variable
        mListView = (MaterialListView) findViewById(R.id.material_listview);

        fillArray();
        // Set the dismiss listener
        mListView.setOnDismissCallback(new OnDismissCallback() {
            @Override
            public void onDismiss(Card card, int position) {

                // Recover the tag linked to the Card
                String tag = card.getTag().toString();

                // Show a toast
                Toast.makeText(mContext, "You have dismissed a " + tag, Toast.LENGTH_SHORT).show();
            }
        });

        // Add the ItemTouchListener
        mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(CardItemView view, int position) {
                Log.d("CARD_TYPE", view.getTag().toString());
            }

            @Override
            public void onItemLongClick(CardItemView view, int position) {
                Log.d("LONG_CLICK", view.getTag().toString());
            }
        });

    }

    private class DownloadSportsTask extends AsyncTask<String, Void, ArrayList<Feed>> {

        @Override
        protected ArrayList<Feed> doInBackground(String... urls) {
            SportsXmlUtils xmlBundle = null;
            try {
                String xmlData = downloadXML(urls[0]);
                xmlBundle = new SportsXmlUtils(xmlData);
            } catch (IOException e) {
            }
            return xmlBundle.getSportsFeeds();
        }

        @Override
        protected void onPostExecute(ArrayList<Feed> sportsFeedsArrayList) {
            //use cardadapter
            //sportsFeedListView.setAdapter(adapter);
        }
    }

    private String downloadXML(String theUrl) throws IOException {
        int BUFFER_SIZE = 2000;
        InputStream inputStream = null;
        String xmlContents = "";
        try {
            URL url = new URL(theUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            int charRead;
            char[] inputBuffer = new char[BUFFER_SIZE];
            try {
                while ((charRead = streamReader.read(inputBuffer)) > 0) {
                    String readString = String.copyValueOf(inputBuffer, 0, charRead);
                    // TODO REFACTOR INTO STRING BUILDER
                    xmlContents += readString;
                    inputBuffer = new char[BUFFER_SIZE];
                }
                return xmlContents;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } finally {
            if (inputStream != null)
                inputStream.close();
        }
    }


    private void fillArray() {
        for (int i = 0; i < 35; i++) {
            Card card = getRandomCard(i);
            mListView.add(card);
        }
    }

    private Card getRandomCard(final int position) {
        String title = "Card number " + (position + 1);
        String description = "Lorem ipsum dolor sit amet";

        int type = position % 6;

        SimpleCard card;
        Drawable icon;

        switch (type) {

            case 0:
                card = new SmallImageCard(this);
                card.setDescription(description);
                card.setTitle(title);
                card.setDrawable(R.drawable.sample_android);
                card.setDismissible(true);
                card.setTag("SMALL_IMAGE_CARD");
                card.setBackgroundColor(Color.BLUE);
                return card;

            case 1:
                card = new BigImageCard(this);
                card.setDescription(description);
                card.setTitle(title);
                //card.setDrawable(R.drawable.photo);
                card.setDrawable("https://assets-cdn.github.com/images/modules/logos_page/GitHub-Mark.png");
                card.setTag("BIG_IMAGE_CARD");
                return card;

            case 2:
                card = new BasicImageButtonsCard(this);
                card.setDescription(description);
                card.setTitle(title);
                card.setDrawable(R.drawable.snow);
                card.setTag("BASIC_IMAGE_BUTTON_CARD");
                card.setBackgroundColor(Color.RED);
                ((BasicImageButtonsCard) card).setLeftButtonText("LEFT");
                ((BasicImageButtonsCard) card).setRightButtonText("RIGHT");

                if (position % 2 == 0)
                    ((BasicImageButtonsCard) card).setDividerVisible(true);

                ((BasicImageButtonsCard) card).setOnLeftButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "You have pressed the left button", Toast.LENGTH_SHORT).show();
                        ((SimpleCard) card).setTitle("CHANGED ON RUNTIME");
                    }
                });

                ((BasicImageButtonsCard) card).setOnRightButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "You have pressed the right button on card " + ((SimpleCard) card).getTitle(), Toast.LENGTH_SHORT).show();
                        mListView.remove(card);
                    }
                });
                card.setDismissible(true);

                return card;

            case 3:
                card = new BasicButtonsCard(this);
                card.setDescription(description);
                card.setTitle(title);
                card.setTag("BASIC_BUTTONS_CARD");
                ((BasicButtonsCard) card).setLeftButtonText("LEFT");
                ((BasicButtonsCard) card).setRightButtonText("RIGHT");
                ((BasicButtonsCard) card).setRightButtonTextColorRes(R.color.accent_material_dark);

                if (position % 2 == 0)
                    ((BasicButtonsCard) card).setDividerVisible(true);

                ((BasicButtonsCard) card).setOnLeftButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "You have pressed the left button", Toast.LENGTH_SHORT).show();
                    }
                });

                ((BasicButtonsCard) card).setOnRightButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                    }
                });
                card.setDismissible(true);


                return card;

            case 4:
                card = new WelcomeCard(this);
                card.setTitle("Welcome Card");
                card.setDescription("I am the description");
                card.setTag("WELCOME_CARD");
                ((WelcomeCard) card).setSubtitle("My subtitle!");
                ((WelcomeCard) card).setButtonText("Okay!");
                ((WelcomeCard) card).setOnButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "Welcome!", Toast.LENGTH_SHORT).show();
                    }
                });

                if (position % 2 == 0)
                    ((WelcomeCard) card).setBackgroundColorRes(R.color.background_material_dark);
                card.setDismissible(true);

                return card;

            case 5:
                card = new BasicListCard(this);
                card.setTitle("List Card");
                card.setDescription("Take a list");
                BasicListAdapter adapter = new BasicListAdapter(this);
                adapter.add("Text1");
                adapter.add("Text2");
                adapter.add("Text3");
                card.setTag("LIST_CARD");
                ((BasicListCard) card).setAdapter(adapter);
                /*
                ((BasicListCard) card).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    	// Do what ever you want...
                    }
                });
                */
                card.setDismissible(true);

                return card;

            default:
                card = new BigImageButtonsCard(this);
                card.setDescription(description);
                card.setTitle(title);
                card.setDrawable(R.drawable.clear_day);
                card.setTag("BIG_IMAGE_BUTTONS_CARD");
                card.setBackgroundColor(Color.GREEN);
                ((BigImageButtonsCard) card).setLeftButtonText("ADD CARD");
                ((BigImageButtonsCard) card).setRightButtonText("RIGHT BUTTON");

                if (position % 2 == 0) {
                    ((BigImageButtonsCard) card).setDividerVisible(true);
                }

                ((BigImageButtonsCard) card).setOnLeftButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Log.d("ADDING", "CARD");

                        mListView.add(generateNewCard());
                        Toast.makeText(mContext, "Added new card", Toast.LENGTH_SHORT).show();
                    }
                });

                ((BigImageButtonsCard) card).setOnRightButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                    }
                });
                card.setDismissible(true);


                return card;

        }

    }

    private Card generateNewCard() {
        SimpleCard card = new BasicImageButtonsCard(this);
        card.setDrawable(R.drawable.fog);
        card.setBackgroundColor(Color.GRAY);

        card.setTitle("I'm new");
        card.setDescription("I've been generated on runtime!");
        card.setTag("BASIC_IMAGE_BUTTONS_CARD");

        return card;
    }

    private void addMockCardAtStart(){
        BasicImageButtonsCard card = new BasicImageButtonsCard(this);
        card.setDrawable(R.drawable.rain);
        card.setTitle("Hi there");
        card.setDescription("I've been added on top!");
        card.setLeftButtonText("LEFT");
        card.setRightButtonText("RIGHT");
        card.setTag("BASIC_IMAGE_BUTTONS_CARD");

        card.setDismissible(true);

        mListView.addAtStart(card);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
