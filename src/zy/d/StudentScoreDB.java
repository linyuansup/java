package zy.d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class StudentScoreDB {
    public static void main(String[] args) {
        JFrame mainUI = new JFrame("学生信息处理");
        mainUI.setLayout(new GridLayout(4, 1, 5, 5));
        mainUI.setSize(500, 150);
        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InputArea nameArea = new InputArea("姓名");
        InputArea idArea = new InputArea("学号");
        InputArea scoreArea = new InputArea("成绩");
        StudentDao studentDao = new StudentDao("./db.txt");
        BottomBar bottomBar = new BottomBar("保存", "计算", e -> {
            studentDao.add(new Student(nameArea.getText(), idArea.getText(), scoreArea.getText()));
            JOptionPane.showMessageDialog(mainUI, "已保存");
        }, e -> {
            ArrayList<Student> result = studentDao.readAll();
            result.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getScore())));
            StudentDao sortedDao = new StudentDao("./sort.txt");
            int sum = 0;
            for (Student student : result) {
                sortedDao.add(student);
                sum += Integer.parseInt(student.getScore());
            }
            sortedDao.add("最大值：" + result.get(result.size() - 1).getScore());
            sortedDao.add("最小值：" + result.get(0).getScore());
            sortedDao.add("平均值：" + sum / result.size());
        });
        mainUI.add(nameArea);
        mainUI.add(idArea);
        mainUI.add(scoreArea);
        mainUI.add(bottomBar);
        mainUI.setVisible(true);
    }
}

class InputArea extends JPanel {
    private final JTextArea inputBox;

    public InputArea(String label) {
        inputBox = new JTextArea();
        JLabel labelArea = new JLabel(label + "：");
        setLayout(new BorderLayout());
        add(inputBox, BorderLayout.CENTER);
        add(labelArea, BorderLayout.WEST);
    }

    public String getText() {
        return inputBox.getText();
    }
}

class BottomBar extends JPanel {

    public BottomBar(String saveLabel, String openLabel, ActionListener saveClick, ActionListener openClick) {
        JButton saveButton = new JButton(saveLabel);
        JButton openButton = new JButton(openLabel);
        saveButton.addActionListener(saveClick);
        openButton.addActionListener(openClick);
        setLayout(new GridLayout(1, 3));
        add(saveButton);
        add(openButton);
    }
}

class Student {
    private final String name;
    private final String id;
    private final String score;

    public Student(String name, String id, String score) {
        this.name = name;
        this.id = id;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getScore() {
        return score;
    }
}

class StudentDao {
    private final BufferedWriter bw;
    private final BufferedReader br;

    public StudentDao(String path) {
        File file = new File(path);
        try {
            boolean newFile = file.createNewFile();
            if (!newFile) {
                JOptionPane.showMessageDialog(null, "文件已存在");
            }
            br = new BufferedReader(new FileReader(path));
            bw = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Student student) {
        try {
            bw.write(student.getName() + " " + student.getId() + " " + student.getScore());
            bw.flush();
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String data) {
        try {
            bw.write(data);
            bw.flush();
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Student> readAll() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String s = br.readLine();
            while (s != null) {
                String[] arr = s.split(" ");
                students.add(new Student(arr[0], arr[1], arr[2]));
                s = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
