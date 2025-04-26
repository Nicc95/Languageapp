package com.example.languageapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        Picasso.get().load("https://example.com/image.jpg").into(imageView);

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
                tts.speak("Hello", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
