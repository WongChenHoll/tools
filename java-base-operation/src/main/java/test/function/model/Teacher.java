package test.function.model;

/**
 * 教师
 *
 * @author WangChenHol
 * @date 2021-12-5 17:51
 **/
public class Teacher {
    private String teacherName;
    private int age;
    private String subjects; // 教学科目

    public Teacher(String teacherName, int age, String subjects) {
        this.teacherName = teacherName;
        this.age = age;
        this.subjects = subjects;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherName='" + teacherName + '\'' +
                ", age=" + age +
                ", subjects='" + subjects + '\'' +
                '}';
    }
}
