package com.example.babyfirstwords;

/**
 * Class representing a word with its associated image and audio resources
 */
public class Word {
    private String text;         // The word text
    private int imageResourceId; // Resource ID for the image
    private int audioResourceId; // Resource ID for the audio file

    /**
     * Constructor for Word object
     *
     * @param text The word text
     * @param imageResourceId Resource ID for the image
     * @param audioResourceId Resource ID for the audio file
     */
    public Word(String text, int imageResourceId, int audioResourceId) {
        this.text = text;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
    }

    /**
     * @return The word text
     */
    public String getText() {
        return text;
    }

    /**
     * @return Resource ID for the image
     */
    public int getImageResourceId() {
        return imageResourceId;
    }

    /**
     * @return Resource ID for the audio file
     */
    public int getAudioResourceId() {
        return audioResourceId;
    }
}