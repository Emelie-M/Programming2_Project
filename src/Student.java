public class Student extends Person{
    String schoolName;
    float grade;

    public Student(String name, int age, String schoolName,float grade){
        super(name,age);
        idGenerator();
        this.schoolName= schoolName;
        this.grade=grade;
    }

    public void idGenerator(){
       this.id=idCounter++;
    }

    public String toString() {
        return super.toString();
    }
}
