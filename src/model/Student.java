package model;

public class Student {
    private Integer id;
    private String names;
    private String gender;
    private String className;

    public Student(Integer id, String names, String gender, String className) {
        this.id = id;
        this.names = names;
        this.gender = gender;
        this.className = className;
    }

    public Student(String names, String gender, String className) {
        this.names = names;
        this.gender = gender;
        this.className = className;
    }

    public Student(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + names + '\'' +
                ", gender='" + gender + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
