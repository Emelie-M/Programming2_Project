public class External_Member extends Person{
    String job;
    String organization;

    public External_Member(String name, int age, String job, String organization){
        super(name,age);
        idGenerator();
        this.job=job;
        this.organization=organization;
    }

    public void idGenerator(){
        this.id=idCounter++;
    }

    public String toString() {
        return super.toString();
    }
}
