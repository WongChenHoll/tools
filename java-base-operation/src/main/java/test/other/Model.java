package test.other;

/**
 * 测试用的实体类
 *
 * @author ChenHol.Wong
 * @create 2020/10/5 16:05
 */
public class Model implements Comparable {
    private String name;
    private Integer age;
    private String address;

    public Model() {
    }

    public Model(String name, int age, String address) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) return -1;
        if (this == null) return 1;
        if (o instanceof Model) {
            Model c = (Model) o;
            Integer age = c.getAge();
            return age.compareTo(this.age);
        }
        return 0;
    }
}
