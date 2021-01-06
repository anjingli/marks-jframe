/*
 * Name: Anjing Li
 * Date: August 9, 2019.
 * Title: U3A6_MarksALi
 * Purpose: ArrayList used to review marks from students
 */
package u3a6_marksali;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class U3A6_MarksALi extends JFrame implements ActionListener {

    // State components necessary
    JLabel lblTitle;
    JLabel lblStudentMark;
    JTextField txtStudentMark = new JTextField();  // Initializing avoids NullPointerException at ActionListener
    JButton btnAdd = new JButton("Add");
    JButton btnSort = new JButton("Sort");
    JButton btnAnalyze = new JButton("Analyze");
    JTextArea txtareaInput = new JTextArea();
    JTextArea txtareaOutput = new JTextArea();

    // Constructor
    public U3A6_MarksALi() {

        super("Marks");
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);      // Makes background light gray

        // Intitialize all components
        JLabel lblTitle = new JLabel("Marks Program");         // Title
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setForeground(Color.BLUE);

        JLabel lblStudentMark = new JLabel("Student Mark");     // Prompt
        lblStudentMark.setFont(new Font("Arial", Font.PLAIN, 15));

        txtStudentMark.setText("");                    // Input student mark

        btnAdd.setFont(new Font("Arial", Font.BOLD, 10));    // Add Button
        btnAdd.setActionCommand("Add");
        btnAdd.addActionListener(this);
        
        btnSort.setFont(new Font("Arial", Font.BOLD, 10));   // Display Button
        btnSort.setActionCommand("Sort");
        btnSort.addActionListener(this);
        btnSort.setEnabled(false);

        btnAnalyze.setFont(new Font("Arial", Font.BOLD, 10));  // Initialize Button
        btnAnalyze.setActionCommand("Analyze");
        btnAnalyze.addActionListener(this);
        btnAnalyze.setEnabled(false);
        
        txtareaInput.setText(""); // Input

        txtareaOutput.setText(""); // Output

        // Set size and locations
        lblTitle.setSize(400, 50);
        lblTitle.setLocation(140, 20);

        lblStudentMark.setSize(500, 50);
        lblStudentMark.setLocation(90, 70);

        txtStudentMark.setSize(90, 30);
        txtStudentMark.setLocation(200, 80);

        btnAdd.setSize(60, 20);
        btnAdd.setLocation(330, 85);

        btnSort.setSize(60, 20);
        btnSort.setLocation(80, 500);

        btnAnalyze.setSize(60, 20);
        btnAnalyze.setLocation(290, 500);

        txtareaInput.setSize(120, 340);
        txtareaInput.setLocation(50, 135);
        
        txtareaOutput.setSize(200, 340);
        txtareaOutput.setLocation(220, 135);        

        // Add to frame
        add(lblTitle);
        add(lblStudentMark);
        add(txtStudentMark);
        add(btnAdd);
        add(btnSort);
        add(btnAnalyze);
        add(txtareaInput);
        add(txtareaOutput);

        // Configure window
        pack();
        setSize (480, 600);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // Closes when x is pressed
        setLocationRelativeTo (null);
        setResizable(false);   // Not resizable window
        }

    // Array lists
    ArrayList <Integer> Marks = new ArrayList();

    // Creates a method for mark display
    private void displayMarks() {
        txtareaInput.setText("Marks");
        for (int i = 0; i < Marks.size(); i++) {
            this.txtareaInput.setText(this.txtareaInput.getText() + "\n" + Marks.get(i));
        }
    }   
    
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Add")) {
            // Declare variables, retrieves text from text field, and converts string to integer
            String strInput = txtStudentMark.getText();
            int intMark = Integer.parseInt(strInput);
    
            //checks if input field contains non numbers
            for (int j = 0; j < txtStudentMark.getText().length(); j++){ //checks the input
                if (Character.codePointAt(strInput, j) >= 48 && Character.codePointAt(strInput, j) <= 57){
                    if (intMark >= 0 && intMark <= 100) { //checks to see if the number is between 0 and 100
                        //adds the integer to the array
                        Marks.add(intMark);

                        //uses method to display marks
                        displayMarks();
                        //clears the text field
                        txtStudentMark.setText("");
                    }
                    btnSort.setEnabled(true);
                }
            }
        }
        
        else if(e.getActionCommand().equals("Sort")) {
            // The text area starts off blank 
            txtareaInput.setText("");
            // Sorts inputted marks
            Collections.sort(Marks);
            // Displays sorted marks
            displayMarks();

            // Enables the Analyze button
            btnAnalyze.setEnabled(true);
        }
        
        else if(e.getActionCommand().equals("Analyze")) {
            // Display text in second text area
            txtareaOutput.setText("Class Average: " + classAverage() + "\n");
            txtareaOutput.setText(txtareaOutput.getText() + "Maximum Mark: " + highestMark() + "\n");
            txtareaOutput.setText(txtareaOutput.getText() + "Minimum Mark: " + lowestMark() + "\n");
            txtareaOutput.setText(txtareaOutput.getText() + "Range of Marks: " + (highestMark() - lowestMark()) + "\n");
            txtareaOutput.setText(txtareaOutput.getText() + "Number at level 4: " + level(4) + "\n");
            txtareaOutput.setText(txtareaOutput.getText() + "Number at level 3: " + level(3) + "\n");       
            txtareaOutput.setText(txtareaOutput.getText() + "Number at level 2: " + level(2) + "\n");
            txtareaOutput.setText(txtareaOutput.getText() + "Number at level 1: " + level(1) + "\n");
            txtareaOutput.setText(txtareaOutput.getText() + "Number at level R: " + level(0) + "\n");
        }
    }
    
    // Helper methods to organize marks
    // To calculate class average
    private String classAverage(){
        // Decimal format for 2 decimal places and percentage
        DecimalFormat ave = new DecimalFormat("##.00%");
        double dblSum = 0;
        for (int k = 0; k < Marks.size(); k++){
            dblSum = dblSum + Marks.get(k);
        }
        // The average is the sum of all averages divided by the number of marks inputted
        double dblAverage = dblSum/Marks.size();
        return ave.format(dblAverage/100);
    }
    
    // Maximum mark
    private int highestMark(){
        int intHighestMark = Marks.get(Marks.size()-1);
        return intHighestMark;
    }
    
    // Minimum mark
    private int lowestMark(){
        int intLowestMark = Marks.get(0);
        return intLowestMark;
    }
    
    // Number of students per level
    private int level(int intLevel){
        int [] intArray = new int[Marks.size()];
        
        // Declare variables
        int intLvlR = 0, intLvlOne = 0, intLvlTwo = 0, intLvlThree = 0, intLvlFour = 0;
        
        // M represents marks
        for (int m = 0; m < intArray.length; m++){
            // Put the values of in the array into intArray
            intArray[m] = Marks.get(m);
            
            // Marks are level R if under 50
            if (intArray[m] < 50){
                intLvlR += 1;//counter; counting the number of students
                
            // Marks are level 1 if between 50 - 59                   
            }else if(intArray[m]>= 50 && intArray[m] <= 59){
                intLvlOne += 1;
                
            // Marks are level 2 if between 60 - 69 
            }else if (intArray[m] >= 60 && intArray[m] <= 69){
                intLvlTwo +=1;
                
            // Marks are level 3 if between 70 - 79    
            }else if (intArray[m] >= 70 && intArray[m] <= 79){
                intLvlThree +=1;
                
            // Marks are level 4 if betwen 80 - 100   
            }else if (intArray[m] >= 80 && intArray[m] <= 100){
                intLvlFour += 1;
            }    
        }
        
        int []intArrayNumPerLvl = {intLvlR, intLvlOne, intLvlTwo, intLvlThree, intLvlFour};
        return intArrayNumPerLvl[intLevel];
    }

    // Main method (tester)
    public static void main( String[] args ) {
        U3A6_MarksALi myFrame = new U3A6_MarksALi();
        myFrame.setVisible(true);
    }
}
