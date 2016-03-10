/*
	Alternate Solution to Codility's Peaks Task
	Run Time O(N*log(log(N)))
*/

import java.lang.Math;

class Solution
{
    int solution(int [] A)
	{
		int N = A.length;
        
        if (N<3) return 0; //Must Be At Least 3 Elements for Peak
        else if (N==3) return (A[1]<=A[0] || A[1]<=A[2]) ? 0 : 1; //If Exactly 3, Check if Middle is Greatest
        
        int cp = countPeaks(A, 0, N); //Get Count of Peaks
        
        if (cp<2) return cp; //Need At Least Two Peaks to Split At Least Once
        if (isPrime(N)) return 1; //Prime Number of Elements, Can't Evenly Split Into Blocks
        
        for (int i=cp; i>1; i--) //Begin Loop From Peaks Count (Can't Be More Blocks Than Peaks)
        {
            if (N%i!=0) continue; //Skip if Non-Divisor
			
            int range = N/i; //Block Size
			boolean split = true; //Determine If Valid Block Split
			
            for (int j=0; j<N; j+=range) //Check Array Blocks
			{
				if (countPeaks(A, j, range+j)==0) //Block Doesn't Contain Peak
				{
					split = false; //Invalid Block Split
					break; //Leave Loop
				}
			}
			if (split) return i; //If All Blocks Have Peak, Max Divisions Found
        }
        
        return 1; //No Possible Proper Divisons
	}
	
    boolean isPrime(int N) //Check If Number is Prime
    {
        if ((N&1)==0) return false; //Not Prime if Even (N=2 Doesn't Reach Here)
        
        for (int i=3; i<=Math.sqrt(N); i+=2) //Loop From 3 to Square Root of Number
            if (N%i==0) return false; //If Divides Evenly, Then Not Prime
        
        return true; //No Clean Divisors (Besides 1 and Self), Prime Number
    }
    
    int countPeaks(int [] A, int startInd, int endInd) //Count Peaks of Range
    {
        int peaks = 0; //Count of Peaks
        
        for (int i=startInd+1; i<endInd-1; i++) //Loop Through Array Range
            if (A[i]>Math.max(A[i-1], A[i+1])) peaks++; //If Number is Greater Than Adjacent Numbers, Peak
		if (startInd>0 && A[startInd]>Math.max(A[startInd-1], A[startInd+1])) peaks++; //If Not First Element, and Number is Greater Than Adjacent Numbers, Peak
        if (endInd<A.length && A[endInd-1]>Math.max(A[endInd-2], A[endInd])) peaks++; //If Not Last Element, and Number is Greater Than Adjacent Numbers, Peak
        
        return peaks; //Return Count
    }
}