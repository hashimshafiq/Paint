// Assignment #: 7
//         Name: Fahad Alluhaydan
//    StudentID: 1205016497
//      Lecture: T Th 1030am
//  Description: WholePanel Class Provides a GUI for our Paint Application.
//               It also contains Handler Classes for ButtonListener, MouseActionListener
//               and MouseMotionListener.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class WholePanel extends JPanel{
    private Color currentColor;
    private CanvasPanel canvas;
    private JPanel leftPanel;
    private JButton undo;
    private JButton erase;
    private ArrayList<Line> lineList;
    private ArrayList<Line> temp;
    private JRadioButton r_black;
    private JRadioButton r_red;
    private JRadioButton r_blue;
    private JRadioButton r_green;
    private JRadioButton r_orange;
    private Point point;
    private int x1,y1,x2,y2;
    private boolean mouseIsDragged = false;
    private boolean flag=false;
    // constructor
    public WholePanel(){
        //default color to draw is black
        currentColor = Color.black;
        // Array list for Line List and erased lines
        lineList = new ArrayList<Line>();
        temp = new ArrayList<Line>();
        // Radio buttons
        r_black = new JRadioButton("Black");
        r_black.setSelected(true);
        r_black.addItemListener(new ChoiceListener());
        r_red = new JRadioButton("Red");
        r_red.addItemListener(new ChoiceListener());
        r_blue = new JRadioButton("Blue");
        r_blue.addItemListener(new ChoiceListener());
        r_green = new JRadioButton("Green");
        r_green.addItemListener(new ChoiceListener());
        r_orange = new JRadioButton("Orange");
        r_orange.addItemListener(new ChoiceListener());
        // Group Radio Buttons
        ButtonGroup radiogroup = new ButtonGroup();
        radiogroup.add(r_black);
        radiogroup.add(r_red);
        radiogroup.add(r_blue);
        radiogroup.add(r_green);
        radiogroup.add(r_orange);
        // Buttons
        undo = new JButton ("Undo");
        erase = new JButton("Erase");
        undo.addActionListener(new ButtonListener());
        erase.addActionListener(new ButtonListener());
        // Left Panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(7,1));
        leftPanel.add(r_black);
        leftPanel.add(r_red);
        leftPanel.add(r_blue);
        leftPanel.add(r_green);
        leftPanel.add(r_orange);
        leftPanel.add(undo);
        leftPanel.add(erase);
        canvas = new CanvasPanel();
        // Mous Motion And Point Listener Handler
        canvas.addMouseListener(new PointListener());
        canvas.addMouseMotionListener(new PointListener());
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, canvas);
        setLayout(new BorderLayout());
        add(sp);
    }

    //CanvasPanel is the panel where shapes will be drawn
    private class CanvasPanel extends JPanel{
        //this method draws all shapes specified by a user
        @Override
        public void paintComponent(Graphics page){
            super.paintComponent(page);
            setBackground(Color.WHITE);
            //page.setColor(currentColor);
            if(mouseIsDragged){
                page.setColor(currentColor);
                page.drawLine(x1, y1, x2, y2);
            }
            for(int i=0; i<lineList.size(); i++)
                lineList.get(i).draw(page);
        }
    }
    //ButtonListener defined actions to take in case
    //"Undo", or "Erase" is chosed.
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent event){
            if(event.getActionCommand().equals("Undo")){
                //remove the last rect and repaint
                if (lineList.size() > 0)
                    lineList.remove(lineList.size()-1);	
                canvas.repaint();
                //if "undo" after "erase" - repaint it
		if (flag == true){
                    for (int i=0; i<temp.size(); i++){
			lineList.add(temp.get(i));
                    }
                    canvas.repaint();
                    flag=false;
		}
            }else if (event.getActionCommand().equals("Erase")){
                //set flag to true (will be used in "Undo" above)
                flag = true;
                //add all elements in rectList to temp
                for (int i=0; i<lineList.size(); i++){
                    temp.add(lineList.get(i));
                }
                //clear the rectList and repaint (so nothing is on canvas)
                lineList.clear();
                canvas.repaint();
            }
        }
    } // end of ButtonListener


    // listener class to set the color chosen by a user using
    // the color radio buttons
    private class ChoiceListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource()==r_black){
                currentColor=Color.black;
            }else if(e.getSource()==r_red){
                currentColor=Color.red;
            }else if(e.getSource()==r_blue){
                currentColor=Color.blue;
            }else if(e.getSource()==r_green){
                currentColor=Color.green;
            }else if(e.getSource()==r_orange){
                currentColor=Color.orange;
            }
        }
    } // end of ChoiceListener

    // listener class that listens to the mouse
    public class PointListener implements MouseListener, MouseMotionListener{
	//in case that a user presses using a mouse,
	//record the point where it was pressed.
        @Override
        public void mousePressed (MouseEvent e){
            point = e.getPoint();
            x1 = point.x;
            y1 = point.y;
            mouseIsDragged = true;
        }
        //mouseReleased method takes the point where a mouse is released,
        //using the point and the pressed point to create a line,
        //add it to the ArrayList "lineList", and call paintComponent method.
        @Override
        public void mouseReleased (MouseEvent event){
            Line line = new Line(x1,y1,x2,y2,currentColor);
            lineList.add(line);
            canvas.repaint();
            mouseIsDragged = false;
        }
        @Override
        public void mouseClicked (MouseEvent event) {}
        @Override
        public void mouseEntered (MouseEvent event) {}
        @Override
        public void mouseExited (MouseEvent event) {}
    
        //mouseDragged method takes the point where a mouse is dragged
        //and call paintComponent method
        @Override
        public void mouseDragged(MouseEvent e){
            point = e.getPoint();
            x2 = point.x;
            y2 = point.y;
            canvas.repaint();
        }
        @Override
        public void mouseMoved(MouseEvent event) {}
    } // end of PointListener
} // end of Whole Panel Class
