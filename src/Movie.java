public class Movie {
    String title;
    String genre;
    int id;
    static int idCounter1 = 1;
    boolean availability;

    public Movie(String title,String genre,boolean availability){
        this.id = idCounter1++;
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

    public int getId() {
        return id;
    }

    public static void setIdCounter(int idCounter){
        idCounter1 = idCounter;
    }

    public static int getIdCounter() {
        return idCounter1;
    }

    public String toString() {
        return "id="+id+
                ", title= " + title +
                ", genre= " + genre +
                ", availability= " + availability;
    }
}
