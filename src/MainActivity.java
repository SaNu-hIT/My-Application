package com.example.yohanson.myapplication;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.txusballesteros.bubbles.BubbleLayout;
import com.txusballesteros.bubbles.BubblesManager;
import com.txusballesteros.bubbles.OnInitializedCallback;
/**
 * Created by yohanson on 20/09/17.
 */

public class MainActivity extends ActionBarActivity {

    private BubblesManager bubblesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initializeBubblesManager();

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    public void run() { addNewBubble();
                    }
                });
            }
        });
    }

    private void addNewBubble() {
        BubbleLayout bubbleView = (BubbleLayout)LayoutInflater.from(MainActivity.this).inflate(R.layout.bubble_layout, null);
        bubbleView.setOnBubbleRemoveListener(new BubbleLayout.OnBubbleRemoveListener() {
            @Override
            public void onBubbleRemoved(BubbleLayout bubble) { }
        });
        bubbleView.setOnBubbleClickListener(new BubbleLayout.OnBubbleClickListener() {

            @Override
            public void onBubbleClick(BubbleLayout bubble) {



                //Toast.makeText(getApplicationContext(), "Clicked !",
                      //  Toast.LENGTH_SHORT).show();
            }
        });
        bubbleView.setShouldStickToWall(true);
        bubblesManager.addBubble(bubbleView, 60, 20);
    }

    private void initializeBubblesManager() {
        bubblesManager = new BubblesManager.Builder(this)
                .setTrashLayout(R.layout.bubble_trash_layout)
                .setInitializationCallback(new OnInitializedCallback() {
                    @Override
                    public void onInitialized() {
                        addNewBubble();
                    }
                })
                .build();
        bubblesManager.initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bubblesManager.recycle();
    }
}
