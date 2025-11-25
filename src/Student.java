public class Student extends Person{
    String schoolName;
    int grade;

    public Student(String name, int age, String schoolName,int grade){
        super(name,age);
        idGenerator();
        this.schoolName= schoolName;
        this.grade=grade;
    }

    public void idGenerator(){
       this.id=idCounter1++;
    }

    public String toString() {
        return super.toString()+", schoolName: "+schoolName+", grade: "+grade;
    }
}
