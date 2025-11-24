import com.google.gson.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<External_Member> members = new ArrayList<>();
    ArrayList<Movie> movies = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Main m = new Main();
        boolean flag = true;
        while (flag) {
            System.out.println("1-Add Member 2-Add Movie 3-Show member 4-Show Movies 5-Rent movie 6-Return Movie 7-exit");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    m.addMember();
                    break;
                case 2:
                    m.addMovie();
                    break;
                case 3:
                    m.showMember();
                    break;
                case 4:
                    m.showMovie();
                    break;
                case 5:
                    m.rentMovie();
                    break;
                case 6:
                    break;
                case 7:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
    void showMember(){
        for (External_Member member : members) {
            System.out.println(member);
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }
    void showMovie(){
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
    void addMember(){
        System.out.println("Enter Name: ");
        String name = input.next();
        System.out.println("Enter age: ");
        int age = input.nextInt();
        System.out.println("Are you a student? Yes/No");
        String yn = input.next();
        if(yn.equals("Yes")){
            System.out.println("Enter School Name: ");
            String schoolName = input.next();
            System.out.println("Enter School Grade: ");
            float grade = input.nextFloat();
            students.add(new Student(name,age,schoolName,grade));
        }
        if(yn.equals("No")){
            System.out.println("Enter job: ");
            String job = input.next();
            System.out.println("Enter organization: ");
            String organization = input.next();
            members.add(new External_Member(name,age,job,organization));
        }
    }
    void addMovie(){
        System.out.println("Enter movie name: ");
        String title = input.next();
        System.out.println("Enter movie genre: ");
        String genre = input.next();
        movies.add(new Movie(title,genre,true));
    }
    void rentMovie(){
        System.out.println("Wich movie do u want to rent? \nEnter title");
        String title = input.next();
        try {
            for(int i=0;i<movies.size();i++){
                if(!movies.get(i).getTitle().equals(title)||movies.get(i).availability==false){
                    throw new CheckMovieException("Movie doesn't exist or not available");
                }
                else if(movies.get(i).getTitle().equals(title)){
                    movies.get(i).setAvailability(false);
                }
            }
        }catch (CheckMovieException e){
            System.out.println(e.getMessage());
        }

    }
    void payment(){}
}