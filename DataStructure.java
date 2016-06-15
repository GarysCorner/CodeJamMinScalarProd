//File:		DataStructure.java
//Author:	Gary Bezet
//Date:		2016-06-15
//Desc:		Designed for google code jam practice problem "Minimum Scalar Product" from round 1A in 2008
//Problem:	https://code.google.com/codejam/contest/32016/dashboard#s=p0
//Results:	

package net.garyscorner.codejamminscalarprod;


import static java.util.Arrays.sort;


class DataStructure {

    //variables
    public int casenum;
    
    public int vectorlen; //number of inputs
    public int[] x;  //x vector
    public int[] y;  //y vector
    private int[] ysolved;//y ordered backwards
    
    public int solution;  //the answer
    
    public long solvetime;  //time taken to solve this problem
    //functions
    
    //solve the problem here and put the solution in int solution
    public void Solve() {
        //for calculating solvetime
        long starttime = System.currentTimeMillis();
        
        this.ysolved = new int[this.vectorlen];
        
        //sort the arrays
        sort(x);sort(y);
        
        this.solution = 0;
        //go through all of X to find best answers
        for(int i=0; i<(this.vectorlen)/2; i++ ) {
            
            int tmp = this.y[i];
            this.y[i] = this.y[this.vectorlen-(1+i)];
            this.y[this.vectorlen-(1+i)] = tmp;
            
            
        }
        for(int i=0; i<(this.vectorlen); i++ ) {
            this.solution += this.x[i] * this.y[i];
        }
        
        //calculate solve time
        solvetime = System.currentTimeMillis() - starttime;
    }
}
