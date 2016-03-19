// Assignment #: 7
//         Name: Fahad Alluhaydan
//    StudentID: 1205016497
//      Lecture: T Th 1030am
//  Description: Line class reperent a Line. It contains starting 
//               x and y coordinates of a line and set color. Also
//               it contains Paint methord to draw a line.
import java.awt.Color;
import java.awt.Graphics;
public class Line {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;
    // constructor
    public Line(int x1,int y1,int x2,int y2,Color color){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
    // set starting point X1
    public void setX1(int x1){
        this.x1 = x1;
    }
    // set ending point X2
    public void setX2(int x2){
        this.x2 = x2;
    }
    // set starting point Y1
    public void setY1(int y1){
        this.y1 = y1;
    }
    // set ending point Y2
    public void setY2(int y2){
        this.y2 = y2;
    }
    // set color of the line
    public void setColor(Color color){
        this.color = color;
    }
    // get starting point X1
    public int getX1(){
        return this.x1;
    }
    // get ending point X2
    public int getX2(){
        return this.x2;
    }
    // get starting point Y1
    public int getY1(){
        return this.y1;
    }
    // get ending point Y2
    public int getY2(){
        return this.y2;
    }
    // get current color
    public Color getColor(){
        return this.color;
    }
    // draw methord to draw the line and set its color
    public void draw(Graphics g){
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}
