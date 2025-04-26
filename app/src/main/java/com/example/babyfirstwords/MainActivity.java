package com.example.babyfirstwords;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Views
    private ImageView wordImageView;
    private TextView wordTextView;
    private ImageButton prevButton;
    private ImageButton nextButton;
    private ImageButton englishButton;
    private ImageButton norwegianButton;

    // List of words
    private List<Word> wordList;

    // Current word index
    private int currentIndex = 0;

    // Current language
    private String currentLanguage;

    // Language codes
    private static final String LANG_ENGLISH = "en";
    private static final String LANG_NORWEGIAN = "no";

    // Preferences
    private static final String PREF_NAME = "BabyFirstWordsPrefs";
    private static final String PREF_LANGUAGE = "language";
    private static final String PREF_LAST_WORD = "lastWordIndex";

    // Media player for playing sounds
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        wordImageView = findViewById(R.id.wordImageView);
        wordTextView = findViewById(R.id.wordTextView);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        englishButton = findViewById(R.id.englishButton);
        norwegianButton = findViewById(R.id.norwegianButton);

        // Initialize word list
        initializeWordList();

        // Load saved preferences
        loadPreferences();

        // Set click listeners
        wordImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playWordSound();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousWord();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextWord();
            }
        });

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage(LANG_ENGLISH);
            }
        });

        norwegianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage(LANG_NORWEGIAN);
            }
        });

        // Display the current word
        updateWordDisplay();
    }

    /**
     * Load saved preferences
     */
    private void loadPreferences() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        currentLanguage = prefs.getString(PREF_LANGUAGE, LANG_ENGLISH);
        currentIndex = prefs.getInt(PREF_LAST_WORD, 0);

        // Make sure index is valid
        if (currentIndex >= wordList.size()) {
            currentIndex = 0;
        }
    }

    /**
     * Save preferences
     */
    private void savePreferences() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_LANGUAGE, currentLanguage);
        editor.putInt(PREF_LAST_WORD, currentIndex);
        editor.apply();
    }

    /**
     * Initialize the list of words with multiple languages
     * You will need to add actual resources to your project for these to work
     */
    private void initializeWordList() {
        wordList = new ArrayList<>();

        // Add words to the list with both English and Norwegian translations
        // Format: new Word(image).addLanguage(language code, text, audio)

        // Ball - Ball
        wordList.add(new Word(R.drawable.ball)
                .addLanguage(LANG_ENGLISH, "Ball", R.raw.en_ball)
                .addLanguage(LANG_NORWEGIAN, "Ball", R.raw.no_ball));

        // Cat - Katt
        wordList.add(new Word(R.drawable.cat)
                .addLanguage(LANG_ENGLISH, "Cat", R.raw.en_cat)
                .addLanguage(LANG_NORWEGIAN, "Katt", R.raw.no_cat));

        // Dog - Hund
        wordList.add(new Word(R.drawable.dog)
                .addLanguage(LANG_ENGLISH, "Dog", R.raw.en_dog)
                .addLanguage(LANG_NORWEGIAN, "Hund", R.raw.no_dog));

        // Apple - Eple
        wordList.add(new Word(R.drawable.apple)
                .addLanguage(LANG_ENGLISH, "Apple", R.raw.en_apple)
                .addLanguage(LANG_NORWEGIAN, "Eple", R.raw.no_apple));

        // Banana - Banan
        wordList.add(new Word(R.drawable.banana)
                .addLanguage(LANG_ENGLISH, "Banana", R.raw.en_banana)
                .addLanguage(LANG_NORWEGIAN, "Banan", R.raw.no_banana));

        // Milk - Melk
        wordList.add(new Word(R.drawable.milk)
                .addLanguage(LANG_ENGLISH, "Milk", R.raw.en_milk)
                .addLanguage(LANG_NORWEGIAN, "Melk", R.raw.no_milk));

        // Mom - Mamma
        wordList.add(new Word(R.drawable.mom)
                .addLanguage(LANG_ENGLISH, "Mom", R.raw.en_mom)
                .addLanguage(LANG_NORWEGIAN, "Mamma", R.raw.no_mom));

        // Dad - Pappa
        wordList.add(new Word(R.drawable.dad)
                .addLanguage(LANG_ENGLISH, "Dad", R.raw.en_dad)
                .addLanguage(LANG_NORWEGIAN, "Pappa", R.raw.no_dad));

        // Add more words as needed
    }

    /**
     * Change the current language
     *
     * @param languageCode The new language code
     */
    private void changeLanguage(String languageCode) {
        // Check if the current word is available in the new language
        if (!wordList.get(currentIndex).hasLanguage(languageCode)) {
            // Find a word that is available in this language
            for (int i = 0; i < wordList.size(); i++) {
                if (wordList.get(i).hasLanguage(languageCode)) {
                    currentIndex = i;
                    break;
                }
            }
        }

        currentLanguage = languageCode;
        updateWordDisplay();
        savePreferences();

        // Show a toast indicating language change
        Toast.makeText(
                this,
                languageCode.equals(LANG_ENGLISH) ? "English" : "Norsk",
                Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Update the display with the current word
     */
    private void updateWordDisplay() {
        Word currentWord = wordList.get(currentIndex);

        // Set the image
        wordImageView.setImageResource(currentWord.getImageResourceId());

        // Set the text in the current language
        wordTextView.setText(currentWord.getText(currentLanguage));

        // Play the sound automatically when a new word is displayed
        playWordSound();
    }

    /**
     * Play the sound for the current word
     */
    private void playWordSound() {
        // Release any existing MediaPlayer
        releaseMediaPlayer();

        Word currentWord = wordList.get(currentIndex);

        // Get audio resource ID for current language
        int audioResourceId = currentWord.getAudioResourceId(currentLanguage);