/***************************************************************************************
*    Title: Julia
*    Author: Anjana Senanayake
*    Date: 15/07/2017  
*    Code version: v1.0
*    Availability: https://github.com/AnjanaSenanayake/Mandelbrot-Julia-
*
***************************************************************************************/

public class Julia
{
	protected boolean bound;
	private double x_input;
	private double y_input;
	private double x;
	private double y;
	private double znow_x;
	private double znow_y;
	private int niter;
	
	//Constructor Julia
	public Julia(double x,double y)
	{
		x_input=x;
		y_input=y;
	}
	
	//Maps the x,y coordinates into complex number within the region of interest 
	public void complexValue(double i,double j)
	{
		znow_x=(((double)i*2)/800)-1;
		znow_y=(((double)j*2)/800)-1;
	}
	
	//Checks the complex coordinates are in the set of Julia
	public boolean boundCheck(double x,double y,int iterations)		
	{
		niter=0;
		x=x_input;
		y=y_input;
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
