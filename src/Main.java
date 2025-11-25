import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Payement{
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<External_Member> members = new ArrayList<>();
    static ArrayList<Movie> movies = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Main m = new Main();
        boolean flag = true;
        Gson gsonStudent = new GsonBuilder().setPrettyPrinting().create();
        String filePath1 = "students.json";
        Gson gsonMember = new GsonBuilder().setPrettyPrinting().create();
        String filePath2 = "members.json";
        Gson gsonMovie = new GsonBuilder().setPrettyPrinting().create();
        String filePath3 = "movies.json";
        while (flag) {
            File file = new File(filePath1);
            if (file.exists() && file.length() != 0) {
                try (FileReader reader = new FileReader(file)) {
                    Type listType = new TypeToken<ArrayList<Student>>() {}.getType();
                    students = gsonStudent.fromJson(reader, listType);
                } catch (Exception e) {
                    System.out.println("Error reading existing JSON: " + e.getMessage());
                }
            }
            File file2 = new File(filePath2);
            if (file2.exists() && file2.length() != 0) {
                try (FileReader reader = new FileReader(file2)) {
                    Type listType = new TypeToken<ArrayList<External_Member>>() {}.getType();
                    members = gsonMember.fromJson(reader, listType);
                } catch (Exception e) {
                    System.out.println("Error reading existing JSON: " + e.getMessage());
                }
            }
            File file3 = new File(filePath3);
            if (file3.exists() && file3.length() != 0) {
                try (FileReader reader = new FileReader(file3)) {
                    Type listType = new TypeToken<ArrayList<Movie>>() {}.getType();
                    movies = gsonMovie.fromJson(reader, listType);
                } catch (Exception e) {
                    System.out.println("Error reading existing JSON: " + e.getMessage());
                }
            }
            for(Student s: students){
                if(s.getId() >= Person.getIdCounter()){
                    Person.setIdCounter(s.getId() + 1);
                }
            }
            for(External_Member member: members){
                if(member.getId() >= Person.getIdCounter()){
                    Person.setIdCounter(member.getId() + 1);
                }
            }
            for(Movie mov: movies){
                if(mov.getId() >= Movie.getIdCounter()){
                    Movie.setIdCounter(mov.getId() + 1);
                }
            }
            System.out.println("1-Add Member 2-Add Movie 3-Show member 4-Show Movies 5-Rent movie 6-Return Movie 7-exit");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    m.addMember();
                    try (FileWriter writer = new FileWriter(filePath1)) {
                        gsonStudent.toJson(students, writer);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    try (FileWriter writer = new FileWriter(filePath2)) {
                        gsonMember.toJson(members, writer);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    m.addMovie();
                    try (FileWriter writer = new FileWriter(filePath3)) {
                        gsonMovie.toJson(movies, writer);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 3:
                    m.showMember();
                    break;
                case 4:
                    m.showMovie();
                    break;
                case 5:
                    m.rentMovie();
                    try (FileWriter writer = new FileWriter(filePath3)) {
                        gsonMovie.toJson(movies, writer);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 6:
                    m.calculate();
                    try (FileWriter writer = new FileWriter(filePath3)) {
                        gsonMovie.toJson(movies, writer);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
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
        if(yn.equalsIgnoreCase("Yes")){
            System.out.println("Enter School Name: ");
            String schoolName = input.next();
            System.out.println("Enter School Grade: ");
            int grade = input.nextInt();
            students.add(new Student(name,age,schoolName,grade));
        }
        if(yn.equalsIgnoreCase("No")){
            System.out.println("Enter job: ");
            String job = input.next();
            System.out.println("Enter organization: ");
            String organization = input.next();
            members.add(new External_Member(name,age,job,organization));
        }
    }
    void addMovie(){
        System.out.println("Enter movie name: ");
        String title = input.nextLine();
        System.out.println("Enter movie genre: ");
        String genre = input.next();
        movies.add(new Movie(title,genre,true));
    }
    void rentMovie(){
        System.out.println("Wich movie do u want to rent? \nEnter id");
        int movieId = input.nextInt();
        try {
            for(int i=0;i<movies.size();i++){
                if(movies.get(i).getId()!=movieId||movies.get(i).isAvailability()==false){
                    throw new CheckMovieException("Movie doesn't exist or not available");
                }
                else if(movies.get(i).getId()==movieId&&movies.get(i).isAvailability()==true){
                    System.out.println("Movie rented");
                    movies.get(i).setAvailability(false);
                }
            }
        }catch (CheckMovieException e){
            System.out.println(e.getMessage());
        }
    }
    public void calculate(){
        System.out.println("Enter your id: ");
        int id = input.nextInt();
        System.out.println("Enter movie id you want to return: ");
        int movieId = input.nextInt();
        for(Movie movie : movies){
            if(movie.getId() == movieId){
                movie.setAvailability(true);
            }
        }
        System.out.println("How many days did u rent this movie? ");
        int days = input.nextInt();
        for(int j=0;j<students.size()&&j<members.size();j++){
            int fee = 0;
            if(students.get(j).getId() == id){
                if(days>7){
                    int feedays=days-7;
                    fee=5+feedays*1;
                    System.out.println("Your fee is: "+fee+"$");
                }
                else{
                    fee=5;
                    System.out.println("Your fee is: "+fee+"$");
                }
            }
            if(members.get(j).getId() == id){
                if(days>7){
                    int feedays=days-7;
                    fee=10+feedays*2;
                    System.out.println("Your fee is: "+fee+"$");
                }
                else{
                    fee=10;
                    System.out.println("Your fee is: "+fee+"$");
                }
            }
            System.out.println("Enter payment amount: ");
            int payment = input.nextInt();
            try {
                if(payment > fee||payment < fee){
                    throw new Exception();
                }
            }catch (Exception e){
                System.out.println("Invalid payment amount");
            }
        }
    }
}