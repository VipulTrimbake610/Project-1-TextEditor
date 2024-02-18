import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor(){
        frame = new JFrame();
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");

        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        copy = new JMenuItem("Copy");
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        menuBar.add(file);
        menuBar.add(edit);


        frame.setJMenuBar(menuBar);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea,BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setBounds(400,200,500,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){

        if(actionEvent.getSource() == copy){
            textArea.copy();
        }
        if(actionEvent.getSource() == cut){
            textArea.cut();
        }

        if(actionEvent.getSource() == paste){
            textArea.paste();
        }

        if(actionEvent.getSource() == selectAll){
            textArea.selectAll();
        }

        if(actionEvent.getSource() == close){
            System.exit(0);
        }
       if(actionEvent.getSource() == openFile){
           JFileChooser fileChooser = new JFileChooser("C");
           int chooseOption = fileChooser.showOpenDialog(null);
           if(chooseOption == JFileChooser.APPROVE_OPTION){
               File file = fileChooser.getSelectedFile();
               String filepath = file.getPath();
               try{
                   FileReader fileReader = new FileReader(filepath);
                   BufferedReader bufferedReader = new BufferedReader(fileReader);
                   String intermediate="",output="";
                   while((intermediate=bufferedReader.readLine()) != null){
                        output+=intermediate+"\n";
                   }
                   textArea.setText(output);
               } catch (IOException ioException){
                   ioException.printStackTrace();
               }
           }
    }
       if(actionEvent.getSource() == saveFile){
           JFileChooser fileChooser = new JFileChooser("C");
           int chooseOption = fileChooser.showSaveDialog(null);
           if(chooseOption == JFileChooser.APPROVE_OPTION){
               File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
               try{
//                   System.out.println(file.getName());
                   FileWriter fileWriter = new FileWriter(file);
                   BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                   textArea.write(bufferedWriter);
               }
               catch(IOException ioException){
                    ioException.printStackTrace();
               }

           }
       }
       if(actionEvent.getSource() == newFile){
           TextEditor textEditor = new TextEditor();
       }
    }


    public static void main(String[] args) {
        TextEditor t = new TextEditor();
    }
}