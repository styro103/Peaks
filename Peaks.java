/*
	Solution to Codility's Peaks Task
	Run Time O(N*log(log(N)))
*/

import java.lang.Math;
import java.util.ArrayList;

class Solution
{
    public int solution(int [] A)
    {
        int N = A.length; //Length of Array
        
        if (N<3) return 0; //Needs To Be At Least Three Elements for Peak to Exist
        else if (N==3) return (A[1]<=A[0] || A[1]<=A[2]) ? 0 : 1; //If Three Elements, Return Whether or Not Middle is Peak
        
        ArrayList <Integer> peaksLoc = getPeaksLoc(A); //ArrayList of Peak Locations
        int cp = peaksLoc.size(); //Number of Peaks
        
        if (cp<2) return cp; //Less Than Two Peaks, Return Number of Peaks
        if (isPrime(N)) return 1; //Prime Number Can't Be Evenly Divided, Only Option is Whole Block
        
        int cb = 1; //Blocks Count, Start At 1
        
        for (int i=2; i<=cp; i++) //Loop From Two to Peaks Count (Can't Be More Blocks Than Peaks)
        {
            if (N%i!=0) continue; //If Doesn't Evenly Divide, Skip Number
			int find = 0; //Peak(s) in Block
            int range = N/i; //Block Size
			boolean ok = true; //Boolean to Continue Check Block Divisions
			for (int loc : peaksLoc) //Check Every Peak Location
			{
				int tmp = loc/i; //Divide Block At Location
				if (tmp>find) //If Peaks Still Within Blocks
				{
					ok = false; //Continally Check Divisions
					cb = i; //Set Max Block Count
					break; //Break Out of Loop
				}
				if (tmp==find) find++; // Increment No Peak in Block, Increment Find
			}
			if (find!=range) ok = false; //If Haven't, Continually Check Divisions
			if (ok) return range; //If ok, Return Block Size
        }
        
        return cb; //Return Blocks Count
    }
	
	boolean isPrime(int N) //Check If Number of Elements is Prime
    {
        if ((N&1)==0) return false; //If  Even, Not Prime (N=2 Doesn't Reach Here in This Program)
        
        for (int i=3; i<=Math.sqrt(N); i+=2) //Loop Till Square Root of Number
            if (N%i==0) return false; //If Number Divides Evenly, Not Prime
        
        return true; //Prime Number
    }
    
	ArrayList <Integer> getPeaksLoc(int [] A) //Function to Find Peak Locations
	{
		ArrayList <Integer> pl = new ArrayList<Integer>(); //List of Peak Locations
		
		for (int i=1; i<A.length-1; i++) //Loop Through Array
            if (A[i]>A[i-1] && A[i]>A[i+1]) pl.add(i); //If Peak, Add Location to List
		
		return pl; //Return List of Peak Locations
	}
}