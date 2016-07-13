package com.medion.trakttv.jsondata;

/**
 * Created by bhan on 7/13/16.
 */
public class Poster {
    private String full;

    private String thumb;

    private String medium;

    public String getFull ()
    {
        return full;
    }

    public void setFull (String full)
    {
        this.full = full;
    }

    public String getThumb ()
    {
        return thumb;
    }

    public void setThumb (String thumb)
    {
        this.thumb = thumb;
    }

    public String getMedium ()
    {
        return medium;
    }

    public void setMedium (String medium)
    {
        this.medium = medium;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [full = "+full+", thumb = "+thumb+", medium = "+medium+"]";
    }
}
