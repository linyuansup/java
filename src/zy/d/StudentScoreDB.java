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
        mainUI.setLayout(new GridLayout(4, 1, 5, 5)); // 4 行 1 列，间距为 5
        mainUI.setSize(500, 150); // 窗体大小
        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭事件
        // 新建文本填写区域
        InputArea nameArea = new InputArea("姓名");
        InputArea idArea = new InputArea("学号");
        InputArea scoreArea = new InputArea("成绩");
        StudentDao studentDao = new StudentDao("./db.txt"); // 建立数据库操作对象
        BottomBar bottomBar = new BottomBar("保存", "计算", e -> {
            studentDao.add(new Student(nameArea.getText(), idArea.getText(), scoreArea.getText())); // 添加学生数据
            // 清除文本框内容便于下次填写
            nameArea.clear();
            idArea.clear();
            scoreArea.clear();
            JOptionPane.showMessageDialog(mainUI, "已保存"); // 弹窗提示
        }, e -> {
            ArrayList<Student> result = studentDao.readAll(); // 获取所有学生内容
            result.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getScore()))); // 根据 getScore 排序
            StudentDao sortedDao = new StudentDao("./sort.txt"); // 建立数据库操作对象
            int sum = 0; // 总分
            for (Student student : result) {
                sortedDao.add(student); // 向排序内容添加数据
                sum += Integer.parseInt(student.getScore()); // 把当前学生分数加到总分中
            }
            sortedDao.add("最大值：" + result.get(result.size() - 1).getScore()); // 排序后最后一个为最大值
            sortedDao.add("最小值：" + result.get(0).getScore()); // 排序后第一个为最小值
            sortedDao.add("平均值：" + sum / result.size()); // 总分除以人数为平均值
            JOptionPane.showMessageDialog(mainUI, "计算完成");
        });
        // 添加 UI
        mainUI.add(nameArea);
        mainUI.add(idArea);
        mainUI.add(scoreArea);
        mainUI.add(bottomBar);
        mainUI.setVisible(true); // 显示 UI
    }
}

class InputArea extends JPanel {
    private final JTextArea inputBox;

    /**
     * 新建一个带标签的输入区域
     *
     * @param label 标签名
     */
    public InputArea(String label) {
        inputBox = new JTextArea();
        JLabel labelArea = new JLabel(label + "：");
        setLayout(new BorderLayout());
        add(inputBox, BorderLayout.CENTER);
        add(labelArea, BorderLayout.WEST);
    }

    /**
     * 获取输入文本框内的内容
     *
     * @return 文本框内容
     */
    public String getText() {
        return inputBox.getText();
    }

    /**
     * 清除文本框所有内容
     */
    public void clear() {
        inputBox.setText("");
    }
}

class BottomBar extends JPanel {

    /**
     * 添加一个底栏
     *
     * @param saveLabel 保存按钮文本
     * @param openLabel 打开按钮文本
     * @param saveClick 保存按钮点击事件
     * @param openClick 打开按钮点击事件
     */
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
    private final BufferedWriter bw; // 文件写入流
    private final BufferedReader br; // 文件读取流

    /**
     * 建立一个学生数据库连接对象
     *
     * @param path 路径
     */
    public StudentDao(String path) {
        File file = new File(path); // 新建一个文件对象
        try {
            boolean newFile = file.createNewFile(); // 创建新文件
            if (!newFile) {
                JOptionPane.showMessageDialog(null, "文件已存在");
            }
            // 初始化读写流
            br = new BufferedReader(new FileReader(path));
            bw = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加一个学生信息
     *
     * @param student 学生信息
     */
    public void add(Student student) {
        add(student.getName() + " " + student.getId() + " " + student.getScore());
    }

    /**
     * 添加一段文本
     *
     * @param data 文本
     */
    public void add(String data) {
        try {
            bw.write(data); // 写入信息
            bw.flush(); // 刷新缓存
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取所有学生信息
     *
     * @return 带有所有学生信息的 List
     */
    public ArrayList<Student> readAll() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            // 从文件中读取内容
            for (String s = br.readLine(); s != null; s = br.readLine()) {
                // 当读取到的内容不为空时
                String[] arr = s.split(" "); // 用空格分隔一行的内容
                students.add(new Student(arr[0], arr[1], arr[2])); // 添加到 List
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
