public class Movie {
    String title;
    String genre;
    String id;
    boolean availability;

    public Movie(String id,String title,String genre,boolean availability){
        this.id = id;
        this.title=title;
        this.genre=genre;
        this.availability=availability;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailability() {
        return availability;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "id="+id+
                ", title= " + title +
                ", genre= " + genre +
                ", availability= " + availability;
    }
}
