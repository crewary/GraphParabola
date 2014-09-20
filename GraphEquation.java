// GraphEquation.java
// By Thomas Nguyen

import java.util.Scanner;
import java.awt.*;

public class GraphEquation
{
   //class variable(s)... Window Settings of graph and Table
   private static double densityX = .001;          //As densityX approaches 0, the plot becomes much smoother
   private static double leftX = -5;               //left bound
   private static double rightX = 5;               //right bound
   private static double upY = 5;                  //upper bound
   private static double downY = -5;               //down bound
   private static double xDifference = 1;          //X axis labelling interval
   private static double yDifference = 1;          //Y axis labelling interval
   private static double tableXStart = -5;         //Starting value of table of values
   private static double tableXEnd = 5;            //End value of table of values
   private static double tableDifference = .5;      //Interval for table of value return
   private static int wholeScale = 72; //<<<<<<<<<~~~~~~~~~Change to change size of window, eg 36,72,91~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   
   
   public static void main(String[] args)
   {
      
      System.out.println("This program will ask you for the coefficients of a quadratic.\nThe quadratic will then be graphed from \nx: ("+leftX+","+rightX+") \ny: ("+downY+","+upY+")\nWith X density of: "+densityX);
      System.out.println("The form of the quadratic will be: y=Ax^2+Bx+C");
      
      Scanner scan = new Scanner(System.in);
      System.out.print("Please enter coefficient A: ");
      double A = scan.nextDouble();
      System.out.print("Please enter coefficient B: ");
      double B = scan.nextDouble();
      System.out.print("Please enter C: ");
      double C = scan.nextDouble();
      

      
      //start drawing panel
      int drawPanelYSize = (int)(20+wholeScale*(Math.abs(upY)+Math.abs(downY)));
      if(drawPanelYSize<(85+20*(Math.abs(tableXStart)+Math.abs(tableXEnd))*1.0/tableDifference))
      {
         drawPanelYSize = (int)(85+20*(Math.abs(tableXStart)+Math.abs(tableXEnd))*1.0/tableDifference);
      }
      
      DrawingPanel panel = new DrawingPanel((int)(200+wholeScale*(Math.abs(leftX)+Math.abs(rightX))), drawPanelYSize);
      Graphics g = panel.getGraphics();
      panel.setBackground(Color.LIGHT_GRAY);
      
      //graph parts.. comment out unwanted components
      plotGraph(g, A, B, C);
      drawAxis(g);
      labelAxis(g);
      drawTableOfPoints(g, A, B, C);
   	g.drawString("y="+A+"x^2+"+B+"x+"+C,0,10);   //write Eq on graph  
      
   }//end main method
   
   //return y values
   public static double findYValue(double inNumber, double A, double B, double C)
   {
      return ((wholeScale*(A*Math.pow(inNumber,2)+B*inNumber+C)));
   }
   
   public static void drawTableOfPoints(Graphics g, double A, double B, double C)
   {
      //Lines
      g.drawLine((int)(10+wholeScale*(Math.abs(leftX)+Math.abs(rightX))),50,(int)(190+wholeScale*(Math.abs(leftX)+Math.abs(rightX))),50);
      g.drawLine((int)(100+wholeScale*(Math.abs(leftX)+Math.abs(rightX))),25,(int)(100+wholeScale*(Math.abs(leftX)+Math.abs(rightX))),(int)(-5+wholeScale*(Math.abs(upY)+Math.abs(downY))));
      g.drawString("X Values",(int)(30+wholeScale*(Math.abs(leftX)+Math.abs(rightX))),40);
      g.drawString("Y Values",(int)(120+wholeScale*(Math.abs(leftX)+Math.abs(rightX))),40); 
      
      
      //find number of values to be listed
      double numberOfPoints = (Math.abs(tableXStart)+Math.abs(tableXEnd))*1.0/tableDifference;
      double p = tableXStart;
      int l = 0;
      
      //list points
      while (p<= tableXEnd)
      {
         int yVals = (int)(65+20*(l));
         g.drawString(Double.toString(p),(int)(40+wholeScale*(Math.abs(leftX)+Math.abs(rightX))), yVals);
         g.drawString(Double.toString(findYValue(p,A,B,C)/wholeScale),(int)(130+wholeScale*(Math.abs(leftX)+Math.abs(rightX))),yVals);
         p += tableDifference;
         l++;
      }
   
   }//end drawTableOfPoints
   
   public static void drawAxis(Graphics g)
   {
      g.drawLine(10,(int)(10+wholeScale*(Math.abs(upY))),(int)(10+wholeScale*(Math.abs(leftX)+Math.abs(rightX))),(int)(10+wholeScale*(Math.abs(upY))));
      g.drawLine(10+(int)(wholeScale*Math.abs(leftX)),10,10+(int)(wholeScale*Math.abs(leftX)),(int)(10+wholeScale*(Math.abs(upY)+Math.abs(downY))));
   }
   
   public static void labelAxis(Graphics g)
   {
      //label and tick x axis
      for(double k=leftX; k<=rightX; k+=xDifference)
         {
            g.drawString(Double.toString(k),(int)(10+wholeScale*Math.abs(leftX)+k*wholeScale) ,(int)(20+wholeScale*(Math.abs(upY))));
            g.drawLine((int)(10+wholeScale*Math.abs(leftX)+k*wholeScale),(int)(5+wholeScale*(Math.abs(upY))),(int)(10+wholeScale*Math.abs(leftX)+k*wholeScale),(int)(10+wholeScale*(Math.abs(upY))));
         }
      //label and tick y axis
      for(double j=downY; j<=upY; j+=yDifference)
         {
            g.drawString(Double.toString(j),(int)(10+wholeScale*Math.abs(leftX)),(int)(20+wholeScale*Math.abs(upY)-j*wholeScale));
            g.drawLine((int)(5+wholeScale*Math.abs(leftX)),(int)(10+wholeScale*Math.abs(upY)-j*wholeScale),(int)(10+wholeScale*Math.abs(leftX)),(int)(10+wholeScale*Math.abs(upY)-j*wholeScale));
         }
      g.drawString("X",(int)(20+wholeScale*(Math.abs(leftX)+Math.abs(rightX))),(int)(10+wholeScale*(Math.abs(upY))));
   }
   
   public static void plotGraph(Graphics g, double A, double B, double C)
   {
      for(double i=leftX+densityX; i<=rightX; i+=densityX)
      {
         if(findYValue(i-densityX,A,B,C)<=upY*wholeScale && findYValue(i-densityX,A,B,C)>=downY*wholeScale)
         {
            g.drawLine((int)(10+wholeScale*Math.abs(leftX)+(i-densityX)*wholeScale),(int)(10+wholeScale*Math.abs(upY)-findYValue(i-densityX,A,B,C)),(int)(10+wholeScale*Math.abs(leftX)+(i)*wholeScale),(int)(10+wholeScale*Math.abs(upY)-findYValue(i,A,B,C)));
         }
      }
      g.drawString("Y",10+(int)(wholeScale*Math.abs(leftX)),10);
   }//end plotGraph
      
}//end class

/*
stuff to do:
Use array to make it graph any equation
Allow you to pick window size- I scale it for you

*/

