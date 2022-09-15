package zy.b;

import java.util.Vector;

public class Stu {
    public static void main(String[] args) {
        // 定义一个 Object 的容器存储数据
        Vector<Object> vector = new Vector<>();
        // 定义第一个学生
        Student s1 = new Student();
        s1.id = 114514;
        s1.name = "tshe";
        // 定义第二个学生
        Student s2 = new Student();
        s2.id = 1919810;
        s2.name = "ysxb";
        // 定义第一个教师
        Faculty f1 = new Faculty();
        f1.id = 12345;
        f1.name = "t1";
        // 定义第二个教师
        Faculty f2 = new Faculty();
        f2.id = 123456;
        f2.name = "t2";
        // 定义一个 stf
        Staff stf1 = new Staff();
        stf1.id = 54321;
        stf1.name = "hahaha";
        vector.add(s1);
        vector.add(s2);
        vector.add(f1);
        vector.add(f2);
        vector.add(stf1);
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
