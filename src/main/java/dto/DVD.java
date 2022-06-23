package dto;

import java.util.SplittableRandom;

public class DVD {
    private String title;
    private String date;
    private String rating;
    private String directorsName;
    private String studio;
    private String userRating;


    public DVD(String title) { // added void here
        this.title = title;
    }

    public void setDate(String date){this.date = date;}
    public String getDate(){return date;}

    public void setTitle(String title){this.title = title;}
    public String getTitle(){ return title;}

    public void setRating(String rating){ this.rating = rating;}
    public String getRating(){return rating;}

    public void setDirectorsName(String directorsName){this.directorsName = directorsName;}
    public String getDirectorsName(){return directorsName;}

    public void setStudio(String studio){this.studio = studio;}
    public String getStudio(){return studio;}

    public void setUserRating(String userRating){this.userRating = userRating;}
    public String getUserRating(){return userRating;}


}
