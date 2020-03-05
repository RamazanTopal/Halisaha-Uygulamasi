package com.example.halisahaprojesi;

public class FriendlyMessage {

    private String text;
    private String name;
    private String photoUrl;


    // Default constructor required for calls to
// DataSnapshot.getValue(FriendlyMessage.class)
    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

}