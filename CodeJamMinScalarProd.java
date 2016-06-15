//File:		CodeJamMinScalarProd.java
//Author:	Gary Bezet
//Date:		2016-06-15
//Desc:		Designed for google code jam practice problem "Minimum Scalar Product" from round 1A in 2008
//Problem:	https://code.google.com/codejam/contest/32016/dashboard#s=p0
//Results:	

package net.garyscorner.codejamminscalarprod;

//variables

import java.io.*;



//functions
public class CodeJamMinScalarProd {

    private long starttime; //time in ms program was started

    private String infileopt; //filename from commandline
    private String outfileopt;  //"

    private BufferedReader infile;  //infile reader
    private PrintStream outfile;  //outfile writer

    private int totalcases;
    private DataStructure[] testcases;  //test cases array

	    
    
    //start application here
    public void run(String[] args) {
        this.starttime = System.currentTimeMillis();  //set start time
        
        this.initargs(args);
        
        this.openFiles();  //open the files
        
        System.err.println("Loading data...");
        this.loadData();
        
        System.err.printf("Solving %1$d cases...\n", this.totalcases);
        //solve them all
        for( DataStructure testcase : testcases ) {
            //solve
            testcase.Solve();
            //print solution to err
            
            System.err.printf("X:");
            for(int i=0; i<testcase.vectorlen; i++) {
                System.err.printf(" %1$d", testcase.x[i]);
            }
            System.err.printf("\nY:");
            for(int i=0; i<testcase.vectorlen; i++) {
                System.err.printf(" %1$d", testcase.y[i]);
            }
            
            System.err.printf("\nCase #%1$d, solved in %2$d\tAns=%3$d\n", testcase.casenum, testcase.solvetime, testcase.solution);
        }
        
        System.err.printf("Outputing solution to:  %1$s\n", this.outfileopt);
        for( DataStructure testcase : testcases ) {
            outfile.printf("Case #%1$d: %2$d\n", testcase.casenum, testcase.solution);
        }
        
        
        System.err.printf("%1$d cases processed in %2$dms\n", this.totalcases, System.currentTimeMillis() - this.starttime);
        this.closeFiles();  //close the files
    }

    
    //loads the data into DataStructure
    private void loadData() {
        String line = null;
        
        line = this.readLine();
        
        //first line is totalcases
        this.totalcases = this.parseInt(line);
        
        //initilize case array
        testcases = new DataStructure[this.totalcases];
        
        //get all cases
        for(int i=0; i<this.totalcases; i++) {
            
            //initalize data structure element
            testcases[i] = new DataStructure();
            
            //set casenum
            testcases[i].casenum = i+1;
            
            //get vector length
            line = readLine();
            testcases[i].vectorlen = parseInt(line);
            
            //initilize x and y
            testcases[i].x = new int[testcases[i].vectorlen];
            testcases[i].y = new int[testcases[i].vectorlen];
            
            int[] vectorpointer; //pointer to the current vector
            
            vectorpointer = testcases[i].x;
            
            //run once for x vector and again for y vector
            for(int d=0; d<2; d++) {
                
                //read/split vector data
                String[] vectordata = this.readLine().split(" ");
                
                //Load the vector
                for(int c=0; c<testcases[i].vectorlen; c++) {
                    vectorpointer[c] = this.parseInt(vectordata[c]);
                }
                vectorpointer = testcases[i].y;  //change vector to y and repeat
            }
            
        }
        
        
        
    }
    
    private int parseInt(String line) {
        
        int num = 0;
        
        try{
            num = Integer.parseInt(line);
        } catch(NumberFormatException e) {
            System.err.printf("Unable to parse \"%1$s\" to intger!", line);
            System.exit(4);
        }
        
        return num;
    }
    
    //for reading lines and catching errors
    private String readLine() {
        
        String line = null;
        try {
            line = infile.readLine();
        } catch (IOException ex) {
            System.err.printf("Could not read line from %1$s", infileopt);
            System.exit(4);
        }
        
        return line;
    }
    
    private void initargs(String[] args) {
		
        if(  2 < args.length || args.length == 0 ) {
                System.err.println("Program requires 1 or 2 arguments.  First arg is infile name, 2nd arg is outfile name Stderr by default.");
                System.exit(1);  //exit the system if arguments not correct
        }

        infileopt = args[0];

        if( args.length == 2 ) {  //set the outfile if exists
                outfileopt = args[1];
        }
		
    }
    
    
    //close files
    private void closeFiles() {
        
        this.outfile.close();
        
        try {
        this.infile.close();
        } catch(IOException e) {
                
        }
    }
    
    private void openFiles() {
		
		try {
			infile = new BufferedReader(new FileReader(infileopt));
		} catch (FileNotFoundException e) {
			System.err.printf("Could not open: %1$s\n", infileopt);
			System.exit(2);
		}
		
		if( outfileopt == null ) {  //set outfile to stdout if blank otherwise open file for writing
			outfileopt = "Stdout";
			outfile = System.out;
		} else {
			try {
				outfile = new PrintStream(new File(outfileopt));
			} catch (FileNotFoundException e) {
				System.err.printf("Couldn't open \"%1$s\" for writing!\n", outfileopt);
				System.exit(3);
			}
			
		}
		
    }
	
    
    
    //main app entry point
    public static void main(String[] args) {
        CodeJamMinScalarProd prog = new CodeJamMinScalarProd();
        prog.run(args);
    }
    
}
