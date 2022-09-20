package zy.b;

import java.util.Random;
import java.util.Vector;

public class Stu {
    public static void main(String[] args) {
        Vector<Object> vector = new Vector<>();
        for (int i = 0; i < 5; i++) {
            switch (new Random().nextInt(3)) {
                case 0 -> {
                    Student stu = new Student();
                    stu.id = 114514;
                    stu.name = "tshe";
                    vector.add(stu);
                }
                case 1 -> {
                    Faculty fac = new Faculty();
                    fac.id = 1919;
                    fac.name = "lts";
                    vector.add(fac);
                }
                case 2 -> {
                    Staff stf = new Staff();
                    stf.id = 810;
                    stf.name = "dc";
                    vector.add(stf);
                }
            }
        }
        for (Object object : vector) {
            System.out.println(object);
        }
    }
}

class Person {
    public int id; // 编号
    public String name; // 姓名
    public String email; // 电子邮件

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + "]";
    }
}

class Student extends Person {
    public int grade; // 班级状态

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + "]";
    }
}

class Employee extends Person {
}

class Faculty extends Employee {
    public String teach; // 主讲课程
    public String major; // 专业信息

    @Override
    public String toString() {
        return "Faculty [id=" + id + ", name=" + name + "]";
    }
}

class Staff extends Employee {
    public String work; // 职务

    @Override
    public String toString() {
        return "Staff [id=" + id + ", name=" + name + "]";
    }
}
