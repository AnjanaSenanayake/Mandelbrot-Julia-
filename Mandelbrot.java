/***************************************************************************************
*    Title: Mandelbrot
*    Author: Anjana Senanayake
*    Date: 15/07/2017  
*    Code version: v1.0
*    Availability: https://github.com/AnjanaSenanayake/Mandelbrot-Julia-
*
***************************************************************************************/

public class Mandelbrot
{
	protected  static boolean bound;
	private double x_start;
	private double x_end;
	private double y_start;
	private double y_end;
	private static double x;
	private static double y;
	private int niter;
	
	//Constructor Mandelbrot
	public Mandelbrot(double x_s,double x_e,double y_s,double y_e)
	{
		this.x_start=x_s;
		this.x_end=x_e;
		this.y_start=y_s;
		this.y_end=y_e;
	}
	
	//Maps the x,y coordinates into complex number within the region of interest 
	public void complexValue(int i,int j)
	{
		x=(((double)i*(x_end-x_start))/800)-Math.abs(x_start);
		y=(((double)j*(y_end-y_start))/800)-Math.abs(y_start);
	}
	
	//Checks the complex coordinates are in the set of Mandelbrot
	public boolean boundCheck(double x,double y,int iterations)		
	{
		niter=0;
		double znow_x=0;
		double znow_y=0;
		while(iterations-->0)
		{
			double znext_x=(znow_x*znow_x)-(znow_y*znow_y)+x;
			double znext_y=(2*znow_x*znow_y)+y;
			znow_x=znext_x;
			znow_y=znext_y;
			niter++;
			if((Math.pow(znow_x,2)+Math.pow(znow_y,2))>4)
			{	
				return bound=false;
			}	
		}	
		return bound=true;
	}
	
	//returns x,y coordinates
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	//returns the number of iterations took on the boundCheck
	public int getIter()
	{
		return niter;
	}
}
