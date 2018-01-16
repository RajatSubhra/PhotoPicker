package io.rajat.rsc.photopicker.models;

public class Metadata {
    private String id;
    private String title;
    private String artist;
    private String caption;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        if(title.equalsIgnoreCase(""))
            return "No valid title found";
        else
            return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        if(artist.equalsIgnoreCase(""))
            return "No valid artist found";
        else
            return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCaption() {
        if(caption.equalsIgnoreCase(""))
            return "Nothing to show";
        else
           return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
