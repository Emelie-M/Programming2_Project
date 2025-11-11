abstract public class Person {
    String name;
    int age;
    String id;

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

    public String getId() {
        return id;
    }

    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
