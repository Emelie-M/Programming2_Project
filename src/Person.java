abstract public class Person {
    String name;
    int age;
    int id;
    static int idCounter1 = 1;

    public Person(String name, int age){
        this.name=name;
        this.age=age;
    }

    abstract void idGenerator();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
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
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
