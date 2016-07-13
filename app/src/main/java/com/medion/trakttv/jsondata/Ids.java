package com.medion.trakttv.jsondata;

/**
 * Created by bhan on 7/13/16.
 */
public class Ids
{
    private String tmdb;

    private String trakt;

    private String slug;

    private String imdb;

    public String getTmdb ()
    {
        return tmdb;
    }

    public void setTmdb (String tmdb)
    {
        this.tmdb = tmdb;
    }

    public String getTrakt ()
    {
        return trakt;
    }

    public void setTrakt (String trakt)
    {
        this.trakt = trakt;
    }

    public String getSlug ()
    {
        return slug;
    }

    public void setSlug (String slug)
    {
        this.slug = slug;
    }

    public String getImdb ()
    {
        return imdb;
    }

    public void setImdb (String imdb)
    {
        this.imdb = imdb;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tmdb = "+tmdb+", trakt = "+trakt+", slug = "+slug+", imdb = "+imdb+"]";
    }
}