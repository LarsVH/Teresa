package com.jari.teresa.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends Activity {
    private static final int SPEECH_RECOGNITION_CODE = 1;
    private boolean isTeresaSaid = false;
    private Streak streak = new Streak();
    private DBHelper database;
    public TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new DBHelper(getApplicationContext());
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

        inputProvider();
    }

    private void inputProvider() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK && data != null) {
                    Log.d("Speech", "onActivityResult: " + data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS));
                    actionManager(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS));
                } else {
                    Log.d("Speech", "data is null");
                }
                break;
            default:
                System.exit(255);
        }
        inputProvider();
    }

    public void actionManager(ArrayList<String> text) {
        for (String t : text) {
            text.set(text.indexOf(t), t.toUpperCase());
        }
        if (isTeresaSaid) {
            try {
                Method action = database.getMethod(text);
                String output = String.valueOf(action.invoke(null, text, this));
                Log.d("output", "actionManager: output = " + output);
                textToSpeech.speak(output, TextToSpeech.QUEUE_ADD, null, null);
            } catch (NoSuchMethodException e) {
                textToSpeech.speak("error 0 0 4 no such method exception in action manager in main activity", TextToSpeech.QUEUE_ADD, null, null);
                Log.d("error", "actionManager: error 004");
            } catch (ClassNotFoundException e) {
                textToSpeech.speak("error 0 0 3 class not found in action manager in main activity", TextToSpeech.QUEUE_ADD, null, null);
                Log.d("error", "actionManager: error 004");
            } catch (InvocationTargetException e) {
                textToSpeech.speak("error 0 0 2 invocation exception in action manager in main activity", TextToSpeech.QUEUE_ADD, null, null);
                Log.d("error", "actionManager: error 004");
            } catch (IllegalAccessException e) {
                textToSpeech.speak("error 0 0 1 illegal access exception in action manager in main activity", TextToSpeech.QUEUE_ADD, null, null);
                Log.d("error", "actionManager: error 004");
            }
        } else {
            if (text.contains("TERESA")) {
                isTeresaSaid = true;
            }
        }
    }

    public void say(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, null);
    }

    public void createTextToSpeech() {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
    }
}
