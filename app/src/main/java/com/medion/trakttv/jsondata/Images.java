package com.medion.trakttv.jsondata;

/**
 * Created by bhan on 7/13/16.
 */
public class Images {
    private Logo logo;

    private Clearart clearart;

    private Poster poster;

    private Thumb thumb;

    private Banner banner;

    private Fanart fanart;

    public Logo getLogo ()
    {
        return logo;
    }

    public void setLogo (Logo logo)
    {
        this.logo = logo;
    }

    public Clearart getClearart ()
    {
        return clearart;
    }

    public void setClearart (Clearart clearart)
    {
        this.clearart = clearart;
    }

    public Poster getPoster ()
    {
        return poster;
    }

    public void setPoster (Poster poster)
    {
        this.poster = poster;
    }

    public Thumb getThumb ()
    {
        return thumb;
    }

    public void setThumb (Thumb thumb)
    {
        this.thumb = thumb;
    }

    public Banner getBanner ()
    {
        return banner;
    }

    public void setBanner (Banner banner)
    {
        this.banner = banner;
    }

    public Fanart getFanart ()
    {
        return fanart;
    }

    public void setFanart (Fanart fanart)
    {
        this.fanart = fanart;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [logo = "+logo+", clearart = "+clearart+", poster = "+poster+", thumb = "+thumb+", banner = "+banner+", fanart = "+fanart+"]";
    }
}
