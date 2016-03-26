//CSc206 - Final Question 2
//Numerical Methods - PI Value
//Bhargi Patel

import java.util.Random;
import java.lang.*;

public class PiValue
{
	//max and min value from pi function
	float max = 1;
	float min = 0;

	int noofIntervals;
	float piTrapezoidal;
	float piSimpson;
	float piMidpoint;
	
	//no. of random points for Monte Carlo Method
	int noofPoints = 100000;
	float piMonteCarlo;
	//max and min for y to computer area
	float minY = 0;
	float maxY = 4; 
	//max and min values of x to compute area
	float maxX = 1;
	float minX = 0;
	
	//pi = Integral from 0-1 {4*sqrt(1-x*x)}
	public float calculateFx(float x)
	{
		return (float)(4*(Math.sqrt(1-x*x)));
	}
	
	//Trapezoidal rule - compute intervals and find value of pi 
	public void calculatePiTrapezoidal()
	{
		int intervalNo = 1;
		float increment;
		float x1;	//xi-1
		float x2;	//xi
		//pi value calculated at each interval
		float piValueInterval;
		//final PI value after which the values are same/lessens
		float piValueFinal = (float) 0.0;
		//can stop calculating once the value becomes same for next interval
		Boolean stop = false;
		float diff = max-min;
		while(!stop)
		{
			//find increment
			increment = diff/intervalNo;
			piValueInterval = (float) 0.0;
			//calculate  pi from the function
			for(int interval = 1; interval <= intervalNo; interval++)
			{
				//trapezoidal rule
				x1 = min + (interval-1)*increment;
				x2 = min + (interval)*increment;
				piValueInterval += (calculateFx(x1) + calculateFx(x2));
			}
			piValueInterval = (piValueInterval*increment)/2;
			if(piValueInterval < piValueFinal)
			{
				piTrapezoidal = piValueFinal;
				noofIntervals = intervalNo -1;
				stop = true;
			}
			piValueFinal = piValueInterval;
			intervalNo++;
		}
	}
	
	//calculate midpoint
	public void calculatePiMidpoint()
	{
		int intervalNo = 1;
		float increment = (max - min)/noofIntervals;
		float mid = (minX + increment)/2;
		int count = 1;
		while(intervalNo <= noofIntervals)
		{
			piMidpoint += calculateFx(mid*count);
			count += 2;
			intervalNo++;
		}
		piMidpoint*=increment;
	}
	
	public void calculatePiSimpson()
	{
		int intervalNo = 1;
		float increment = (max - min)/noofIntervals;
		float x1;
		float x2;
		piSimpson = (float)0.0;
		float tempVal = increment/6;
		while(intervalNo <= noofIntervals)
		{
			x1 = min + (intervalNo-1)*increment;
			x2 = min + (intervalNo)*increment;
			piSimpson += (calculateFx(x1) + 4*calculateFx((x1+x2)/2) + calculateFx(x2));
			intervalNo++;
		}
		piSimpson*=tempVal;
	}
	
	//calculate pi using MonteCarlo Method
	public void calculatePiMonteCarlo()
	{
		Random randomGenerator = new Random();
		int points = 1;
		float x;
		float y;
		int pointsInside = 0;
		float ratio;
		float areaFull;
		float mul = (maxY - minY) + minY;
		while(points <= noofPoints)
		{
			x = randomGenerator.nextFloat();
			y = randomGenerator.nextFloat()*mul;
			if(y <= calculateFx(x))
				pointsInside++;
			points++;
		}
		ratio = (float) pointsInside/noofPoints;
		areaFull = (maxX-minX)*(maxY-minY);
		piMonteCarlo = ratio*areaFull;
	}
	
	public static void main(String[] args) 
	{
		PiValue pi = new PiValue();
		pi.calculatePiTrapezoidal();
		System.out.println("The value of pi using Trapezoidal method is:" + pi.piTrapezoidal);
		System.out.println("The number of intervals used is " + pi.noofIntervals );
		pi.calculatePiSimpson();
		System.out.println("The value of pi using Simpson's method is:" + pi.piSimpson);
		pi.calculatePiMidpoint();
		System.out.println("The value of pi using Midpoint method is:" + pi.piMidpoint);
		pi.calculatePiMonteCarlo();
		System.out.println("The value of pi using Monte Carlo method with " + pi.noofPoints + " random points is : " + pi.piMonteCarlo);
	}
}