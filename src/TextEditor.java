import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {
    // declaring properties of TextEditor
    JFrame frame;
    JMenuBar menuBar;

    JMenu file,edit;

    // file menu item
    JMenuItem newFile,openFile,saveFile;
// edit menu item
JMenuItem cut,copy,paste,selectAll,close;

JTextArea  textArea;
    TextEditor(){

        frame= new JFrame();

        // initialize menubar
        menuBar= new JMenuBar();

        // initialise text area

        textArea= new JTextArea();

        // initialize menu

        file=new JMenu("File");
        edit=new JMenu("Edit");

        // initialise file menu items first
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        // add Action Listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // add  menu items to the file menu

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // initialize edit menu items;

        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        // add action listner to editmenu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this); cut.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        // add  menu items to the edit menu

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        /// add menu to menubar
        menuBar.add(file);
        menuBar.add(edit);

        // set menubar to frame

        frame.setJMenuBar(menuBar);

        // create content pane

        JPanel panel= new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // add text area to panel
        panel.add(textArea,BorderLayout.CENTER);

        // create scroll pane

        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
// Add scroll pane to panel
        panel.add(scrollPane);
        // add panel to framwe

        frame.add(panel);


        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);



}
 @Override
 public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            // performe cut operation
            textArea.cut();

        }
     if(actionEvent.getSource()==copy){
         // performe copy operation
         textArea.copy();

     }
     if(actionEvent.getSource()==paste){
         // performe paste operation
         textArea.paste();

     }

     if(actionEvent.getSource()==selectAll){
         // performe selectAll operation
         textArea.selectAll();
     }
     if(actionEvent.getSource()==close){
         // performe close operation
         System.exit(0);
     }

     if(actionEvent.getSource()==openFile){
        // open a file choser
        JFileChooser fileChooser= new JFileChooser("C:");
        int chooseOption= fileChooser.showOpenDialog(null);

        // if  we click on open button

        if(chooseOption==JFileChooser.APPROVE_OPTION){
            //GETTING SELECTED FILE

            File file= fileChooser.getSelectedFile();
            // get the path of selected file
            String filePath=file.getPath();

            try {
                FileReader fileReader = new FileReader(filePath);
                // initialize buffered reader
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String intermediate = "", output = "";
                // read contents of file line by line

                while ((intermediate =bufferedReader.readLine()) != null) {
                    output += intermediate + "\n";
                }
                // SET THE output string to textarea
textArea.setText(output);
            }
            catch (IOException ioException){
                    ioException.printStackTrace();
                }
        }
    }
     if(actionEvent.getSource()==saveFile) {
     // initialize file picker
         JFileChooser fileChooser= new JFileChooser("C:");

         //get choose option from file chooser
         int chooseOption= fileChooser.showOpenDialog(null);
         // check if we clicked on save button
         if(chooseOption== JFileChooser.APPROVE_OPTION){
             // create a new file with chosen directory path and file menu
             File file= new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

             try {
                 // initiLIze file writer
                 FileWriter fileWriter = new FileWriter(file);
                 // initialize buffer writer

                 BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                 // write contents of text area to file

                 textArea.write(bufferedWriter);
                 bufferedWriter.close();
             }
             catch(IOException ioException){
                 ioException.printStackTrace();
             }
         }

     }
     if(actionEvent.getSource()==newFile){
         TextEditor newTextEditor= new TextEditor();
     }
     }


    public static void main(String[] args) {

       TextEditor  textEditor = new TextEditor();

    }
}