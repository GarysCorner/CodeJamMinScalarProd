//File:		DataStructure.java
//Author:	Gary Bezet
//Date:		2016-06-15
//Desc:		Designed for google code jam practice problem "Minimum Scalar Product" from round 1A in 2008
//Problem:	https://code.google.com/codejam/contest/32016/dashboard#s=p0
//Results:	Small:751ms     Large:77ms

//package net.garyscorner.codejamminscalarprod;


import java.math.BigInteger;
import static java.util.Arrays.sort;


class DataStructure {

    //variables
    public int casenum;
    
    public int vectorlen; //number of inputs
    public BigInteger[] x;  //x vector
    public BigInteger[] y;  //y vector
    
    
    public BigInteger solution;  //the answer
    
    public long solvetime;  //time taken to solve this problem
    //functions
    
    //solve the problem here and put the solution in int solution
    public void Solve() {
        //for calculating solvetime
        long starttime = System.currentTimeMillis();
        
        
        
        //sort the arrays
        sort(x);sort(y);
        
       
        //Reverse Y       
        for(int i=0; i<(this.vectorlen/2); i++ ) {
            
            BigInteger tmp = this.y[i];
            this.y[i] = this.y[this.vectorlen-(1+i)];
            this.y[this.vectorlen-(1+i)] = tmp;
            
            
        }
        
        //initialize this.solution
        this.solution = BigInteger.valueOf(0);
        
        //find scalar product
        for(int i=0; i<(this.vectorlen); i++ ) {
            
            this.solution = this.solution.add(this.x[i].multiply( this.y[i] ));
        }
        
        //calculate solve time
        solvetime = System.currentTimeMillis() - starttime;
    }
}
