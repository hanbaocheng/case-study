package com.medion.trakttv.jsondata;

/**
 * Created by bhan on 7/13/16.
 */
public class MovieData {
    private String trailer;

    private String[] genres;

    private Ids ids;

    private String votes;

    private String runtime;

    private String[] available_translations;

    private String homepage;

    private String released;

    private String certification;

    private String title;

    private String updated_at;

    private String overview;

    private Images images;

    private String year;

    private String language;

    private String rating;

    private String tagline;

    public String getTrailer ()
    {
        return trailer;
    }

    public void setTrailer (String trailer)
    {
        this.trailer = trailer;
    }

    public String[] getGenres ()
    {
        return genres;
    }

    public void setGenres (String[] genres)
    {
        this.genres = genres;
    }

    public Ids getIds ()
    {
        return ids;
    }

    public void setIds (Ids ids)
    {
        this.ids = ids;
    }

    public String getVotes ()
    {
        return votes;
    }

    public void setVotes (String votes)
    {
        this.votes = votes;
    }

    public String getRuntime ()
    {
        return runtime;
    }

    public void setRuntime (String runtime)
    {
        this.runtime = runtime;
    }

    public String[] getAvailable_translations ()
    {
        return available_translations;
    }

    public void setAvailable_translations (String[] available_translations)
    {
        this.available_translations = available_translations;
    }

    public String getHomepage ()
    {
        return homepage;
    }

    public void setHomepage (String homepage)
    {
        this.homepage = homepage;
    }

    public String getReleased ()
    {
        return released;
    }

    public void setReleased (String released)
    {
        this.released = released;
    }

    public String getCertification ()
    {
        return certification;
    }

    public void setCertification (String certification)
    {
        this.certification = certification;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getOverview ()
    {
        return overview;
    }

    public void setOverview (String overview)
    {
        this.overview = overview;
    }

    public Images getImages ()
    {
        return images;
    }

    public void setImages (Images images)
    {
        this.images = images;
    }

    public String getYear ()
    {
        return year;
    }

    public void setYear (String year)
    {
        this.year = year;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getRating ()
    {
        return rating;
    }

    public void setRating (String rating)
    {
        this.rating = rating;
    }

    public String getTagline ()
    {
        return tagline;
    }

    public void setTagline (String tagline)
    {
        this.tagline = tagline;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [trailer = "+trailer+", genres = "+genres+", ids = "+ids+", votes = "+votes+", runtime = "+runtime+", available_translations = "+available_translations+", homepage = "+homepage+", released = "+released+", certification = "+certification+", title = "+title+", updated_at = "+updated_at+", overview = "+overview+", images = "+images+", year = "+year+", language = "+language+", rating = "+rating+", tagline = "+tagline+"]";
    }
}
