/***************************************************************************************
*    Title: Fractals-Main class
*    Author: Anjana Senanayake
*    Date: 15/07/2017  
*    Code version: v1.0
*    Availability: https://github.com/AnjanaSenanayake/Mandelbrot-Julia-
*
***************************************************************************************/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.*;

//inherits JPanel 
@SuppressWarnings("serial")
public class Fractal extends JPanel
{ 	
	private boolean bound;
	private int width=800;
	private int height=800;
	private int iterations;
	private double x_start;
	private double x_end;
	private double y_start;
	private double y_end;
	private int complex=5;
	
	Mandelbrot mb=null;
	Julia mb1=null;
	
	//constructor for Mandelbrot
	public Fractal(double x_s,double x_e,double y_s,double y_e,int iter)
	{
		this.x_start=x_s;
		this.x_end=x_e;
		this.y_start=y_s;
		this.y_end=y_e;
		this.iterations=iter;
		complex=0;
		mb = new Mandelbrot(x_start,x_end,y_start,y_end);
		// set the panel size 
		setPreferredSize(new Dimension(width,height));
	}
	
	//constructor for Julia
	public Fractal(double x,double y,int iterations)
	{
		this.x_start=x;
		this.y_start=y;
		this.iterations=iterations;
		complex=1;
		mb1 = new Julia(x,y);
		// set the panel size 
		setPreferredSize(new Dimension(width,height));
	}
	
	//print points in the given coordinates with a given color
	private static void printPoint(Graphics2D frame, Color c,double x,double y) 
	{
	    frame.setColor(c); 
	    frame.draw(new Line2D.Double(x,y,x,y)); 
	}	
	 
	//call paintComponent from parent class 
	public void paintComponent(Graphics g) 
	{ 
		// call paintComponent from parent class 
		super.paintComponent(g); 
		Color color=null;
		for(int i=0;i<=800;i++)
		{	
			for(int j=0;j<=800;j++)
			{	
				if(complex==0)//checks the type of fractal
				{	
					mb.complexValue(i,j);
					this.bound = mb.boundCheck(mb.getX(),mb.getY(),iterations);
					//color iteration for different points
					color = Color.getHSBColor((float)mb.getIter()*20.0f/(float)iterations,1.0f,1.0f);
				}
				else if(complex==1)//checks the type of fractal
				{	
					mb1.complexValue(i,j);
					this.bound = mb1.boundCheck(mb1.getX(),mb1.getY(),iterations);
					//color iteration for different points
					color = Color.getHSBColor((float)mb1.getIter()*10.0f/(float)iterations,1.0f,1.0f);
				}	
				//checks the point exists in or out of the fractal set
				if(bound)
				{	 
					printPoint((Graphics2D)g,Color.BLACK,i,j); 
				}	 
				else
				{
					printPoint((Graphics2D)g,color,i,j);
				}
			}	
		}		
	}	
	
	//Usage Details
	public static void printUsage()
	{
		System.out.println("Usage:");
    	System.out.println("java Fractal Mandelbrot x_start x_end y_start y_end iterations");
    	System.out.println("java Fractal Julia x y iterations");
    	System.exit(0);
	}
	
	public static void main(String[]args)
	{	
	    JFrame frame=null;
	    if(args.length>=1)
	    {	
	    	if(args[0].equals("Mandelbrot"))
	    	{
	    		double x_start=0;
	    		double x_end=0;
	    		double y_start=0;
	    		double y_end=0;
	    		int iterations=0;
	    		if(args.length==1)
	    		{
	    			x_start=-1;
	    			x_end=1;
	    			y_start=-1;
	    			y_end=1;
	    			iterations=1000;
	    		}
	    		else if(args.length==6)
	    		{
	    			x_start = Double.parseDouble(args[1]);
	    			x_end = Double.parseDouble(args[2]);
	    			y_start = Double.parseDouble(args[3]);
	    			y_end = Double.parseDouble(args[4]);
	    			iterations = Integer.parseInt(args[5]);
	    		}
	    		else
	    		{
	    			System.out.println("\nOops Error Encountered");
	    			printUsage();
	    		}
	    		frame = new JFrame("Mandelbrot Set");     
	    		// set the content of the frame
	    		frame.setContentPane(new Fractal(x_start,x_end,y_start,y_end,iterations));
	    	}	
	    	else if(args[0].equals("Julia"))
	    	{
	    		
	    		double x_start=0;
	    		double y_start=0;
	    		int iterations=0;
	    		if(args.length==1)
	    		{
	    			x_start=-0.4;
	    			y_start=0.6;
	    			iterations=1000;
	    		}
	    		else if(args.length==4)
	    		{	
	    			x_start = Double.parseDouble(args[1]);
	    			y_start = Double.parseDouble(args[2]);
	    			iterations = Integer.parseInt(args[3]);
	    		}
	    		else
	    		{
	    			System.out.println("\nOops Error Encountered");
	    			printUsage();
	    		}
	    		frame = new JFrame("Julia Set"); 
	    		// set the content of the frame as one of this panel
	    		frame.setContentPane(new Fractal(x_start,y_start,iterations));
	    	}
	    	else
	    	{
	    		System.out.println("\nThe Passed Argument for the Fractal Type is not Identified");
		    	printUsage();
	    	}
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack(); 
			frame.setLocationRelativeTo(null); 
			frame.setVisible(true); 	
	    }	
	    else
	    {
	    	System.out.println("\nNot enough arguments");
	    	printUsage();
	    }
	}
}
