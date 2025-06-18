import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoListApp extends JFrame {

    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JButton addButton, deleteButton, markDoneButton, editButton;

    public ToDoListApp() {
        setTitle("To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel for Input
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        taskInput = new JTextField();
        addButton = new JButton("Add Task");
        topPanel.add(taskInput, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // Center Panel for Task List
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Bottom Panel for Buttons
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        editButton = new JButton("Edit Task");
        markDoneButton = new JButton("Mark Done");
        deleteButton = new JButton("Delete Task");

        bottomPanel.add(editButton);
        bottomPanel.add(markDoneButton);
        bottomPanel.add(deleteButton);

        // Add all panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        markDoneButton.addActionListener(e -> markTaskDone());
        editButton.addActionListener(e -> editTask());

        setVisible(true);
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskInput.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a task.");
        }
    }

    private void deleteTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            taskListModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete.");
        }
    }

    private void markTaskDone() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            String task = taskListModel.get(index);
            if (!task.startsWith("✔ ")) {
                taskListModel.set(index, "✔ " + task);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to mark as done.");
        }
    }

    private void editTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            String oldTask = taskListModel.get(index);
            String newTask = JOptionPane.showInputDialog(this, "Edit task:", oldTask);
            if (newTask != null && !newTask.trim().isEmpty()) {
                taskListModel.set(index, newTask.trim());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to edit.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListApp::new);
    }
}
