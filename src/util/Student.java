package util;

public class Student extends Person{
    public Double score;
    public String className;

    public Student() {}

    public Student(Integer id, String name, String gender, Integer age, String className , Double score) {
        super(id, name, gender, age);
        this.className = className;
        this.score = score;
    }
}
