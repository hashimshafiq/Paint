// Assignment #: 7
//         Name: Fahad Alluhaydan
//    StudentID: 1205016497
//      Lecture: T Th 1030am
//  Description: The Assignment 7 creates a WholePanel that is
//               an extension of JPanel, add it to its content, 
//               and set a size for the applet.
import javax.swing.*;
public class Assignment7 extends JApplet{
    @Override
    public void init(){
        // create a WholePanel object and add it to the applet
        WholePanel wholePanel = new WholePanel();
        getContentPane().add(wholePanel);
        //set applet size to 400 X 400
        setSize (400, 400);
    }
}

