
// package servlet;
// Import Java Libraries
import java.io.*;
import java.util.*;

//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "final432", 
		urlPatterns = { "/final432" })

// CONSTRUCTOR: no constructor specified (default)
//
// ***************  PUBLIC OPERATIONS  **********************************
// public void doPost ()  --> prints a blank HTML page
// public void doGet ()  --> prints a blank HTML page
// private void PrintHead (PrintWriter out) --> Prints the HTML head section
// private void PrintBody (PrintWriter out) --> Prints the HTML body with
//              the form. Fields are blank.
// private void PrintBody (PrintWriter out, String lhs, String rhs, String rslt)
//              Prints the HTML body with the form.
//              Fields are filled from the parameters.
// private void PrintTail (PrintWriter out) --> Prints the HTML bottom
//***********************************************************************

public class final432 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String Domain = "";
	static String Path = "/";
	static String Servlet = "final432";
	
// Button labels
	static String OperationAdd = "Add";
	static String OperationSub = "Subtract";
	static String OperationMult = "Multiply";

// Other strings.
	static String Style = "https://www.cs.gmu.edu/~offutt/classes/432/432-style.css";
	
	static List<String> list1;
	static List<String> logical;
	static List<String> ands;
	static List<String> ors;
	static List<String> vars;
	
	  static enum Data {NAME, YEAR, JC, FW, RB, SS, VSE};
  static String RESOURCE_FILE = "entries.txt";
  static final String VALUE_SEPARATOR = ";";

	/**
	 * ***************************************************** Overrides HttpServlet's
	 * doPost(). Converts the values in the form, performs the operation indicated
	 * by the submit button, and sends the results back to the client.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter(Data.NAME.name());
     
     String error = "";
     if(name == null){
       error= "<li>Input is required</li>";
       name = "";
     }	

     response.setContentType("text/html");
     PrintWriter out = response.getWriter();


		out.println(name);
		out.println("<br>");
			


Arrays.toString(list1.toArray());
Arrays.toString(logical.toArray());

     int[] x = new int[2];
     out.println("  <tr>");
     printTruthTable(out, 2, 0, x, 0, 0);
     out.println("  </tr>");
     
     if (error.length() == 0){
       PrintWriter entriesPrintWriter = new PrintWriter(new FileWriter(RESOURCE_FILE, true), true);
       entriesPrintWriter.println(name);
       entriesPrintWriter.close();

       PrintHead(out);
       PrintResponseBody(out, RESOURCE_FILE);
       PrintTail(out);
     }else{
       PrintHead(out);
       PrintBody(out, name, error);
       PrintTail(out);
     }
		
	}
	
	/* A recursive algorithm to print a truth table of 1s and 0s.
	 N is the number of clauses, or columns, in the truth table.
	 index should be zero on the first call
	 truthVals starts as an empty array of integers of size N 
	 count is the index in the arraylist. if it's an even then it's a logic symbol
	 logic is the symbol. we will use 0 for &&, 1 for |
	 */
	void printTruthTable(PrintWriter out, int N, int index, int[] truthVals, int count, int logic) {
		
	   if (index == N) {
	      for (int i=0; i<N; i++) {
	    	  out.println("  <th>");
	         out.println(truthVals[i] + " ");
	         out.println("  </th>");
	         if (i == N-1) {
	        	 int val = 0;
	        	 if (truthVals.length >= 2 ) {
	        		 if (logic == 0) {
	        			 out.println(truthVals[0] & truthVals[1]);
	        		 }
	        		 else if (logic == 1) {
	        			 out.println(truthVals[0] | truthVals[1]); 
	        		 }
	        		 else { // xor, logic == 3
	        			 out.println(truthVals[0] ^ truthVals[1]); 
	        		 }
	        	 }
	        	 out.println("yes!");
	         }
	      }
	      out.println("<br>");
	   } 
	   else {
		 /*  if (count % 2 != 0) {
			   String temp = list1.get(count);
			
			   if (ands.contains(temp)) {
				   logic = 0;
			   }
			   else if (ors.contains(temp)) {
				   logic = 1 ;
			   }
		   } */
			
	      for (int j = 0; j < 2; j++) {
	         truthVals[index] = j;
	  	   	count++;
	         printTruthTable(out, N, index + 1, truthVals, count, logic);
	      }
	   }
	}
	
	void parseInput(String input) {
        String[] splitting = input.split(" ", 10); 
        list1 = new ArrayList<String>();
        Collections.addAll(list1, splitting);
        
        
        logical = new ArrayList<String>();
        int i = 0;
        while(i < list1.size()){
        	   if(i % 2 == 1){ // If value is odd
        	      logical.add(list1.get(i));
        	   }
        	   i++;
        	}
	}
	  /** *****************************************************
   *  Prints the <BODY> of the HTML page
  ********************************************************* */
  private void PrintResponseBody (PrintWriter out, String resourcePath){
    out.println("<body onLoad=\"setFocus()\">");
    out.println("<p>");
    out.println("Results Database");
    out.println("</p>");
    out.println("");
	  
	out.println(" <style> able, th, td { border: 1px solid black; } </style> ");
    out.println(" <table>");
	
    try {
	 out.println("  <table>");
        out.println("  <tr>");
        out.println("   <th>Input</th>");
	out.println("   <th>Var 1</th>");
        out.println("   <th>Var 2</th>");
	out.println("   <th>Var 3</th>");
	out.println("   <th>Result</th>");
        out.println("  </tr>");
        File file = new File(resourcePath);
        if(!file.exists()){
          out.println("  <tr>");
          out.println("   <td>No entries persisted yet.</td>");
          out.println("  </tr>");
          return;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          String []  entry= line.split(VALUE_SEPARATOR);
          out.println("  <tr>");
          for(String value: entry){
              out.println("   <td>"+value+"</td>");
          }
          out.println("  </tr>");
        }
        bufferedReader.close();
      } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     out.println(" </table>");
     out.println("");
     out.println("</body>");
  }

	/**
	 * ***************************************************** Overrides HttpServlet's
	 * doGet(). Prints an HTML page with a blank form.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		PrintHead(out);
		PrintBody(out);
		PrintTail(out);
		
	
						
		   HttpSession session = request.getSession();
  //  String name   = request.getParameter("attrib_name");

      String action = request.getParameter("action");

   if (action != null && action.equals("invalidate"))
   {  // Called from the invalidate button, kill the session.
      // Get session object
      session.invalidate();

      response.setContentType("text/html");

      out.println("<html>");
      out.println("<head>");
      out.println(" <title>Session lifecycle</title>");
      out.println("</head>");
      out.println("");
      out.println("<body>");

      out.println("<p>Your session has been invalidated.</P>");
      }
	
		
		   
            String lifeCycleURL = "/final"; // --------------------------------------------
      out.print  ("<br><a href=\"" + lifeCycleURL + "?action=invalidate\">");
      out.println("Invalidate the session</a>");
       out.println("<br>");
		
		
	} // End PrintHead

	/**
	 * ***************************************************** Prints the <BODY> of
	 * the HTML page with the form data values from the parameters.
	 */
	private void PrintBody (PrintWriter out, String name, String error){

	
	    
     out.println("<body onLoad=\"setFocus()\">");
     out.println("<p>");
   		 out.println("<b>SWE 432 FINAL: </b> Megan Ngo");
		out.println("<p>");
		out.println("<p><b>Instructions:</b> Please input a boolean predicate string.</p>");
     out.println("</p>");

     if(error != null && error.length() > 0){
       out.println("<p style=\"color:red;\">Please correct the following and resubmit.</p>");
       out.println(error);
     }
		
		  out.print  ("<form name=\"persist2file\" method=\"post\"");
     out.println(" action=\""+Domain+Path+Servlet+"\">");
     out.println("");
		
		     out.println(" <table>");
     out.println("  <tr>");
     out.println("   <td>Input:</td>");
     out.println("   <td><input type=\"text\" name=\""+Data.NAME.name()+"\" value=\""+name+"\" size=30 required></td>");
     out.println("  </tr>");
     out.println("  <tr>");
		 out.println(" </table>");
			out.println("<br> ");
		   
		parseInput(Data.NAME.name());
		
		ands = new ArrayList<String>();
		ands.add("AND");
		ands.add("and");
		ands.add("&&");
		ors = new ArrayList<String>();
		ors.add("OR");
		ors.add("or");
		ors.add("|");
		vars = new ArrayList<String>();
		vars.add("A");
		vars.add("B");
		vars.add("x");
		vars.add("y");
		vars.add("M");
		vars.add("N");
		vars.add("Q");
		vars.add("today");
		vars.add("tomorrow");
	
		
		out.println("<input type=\"submit\" onclick=\"doPost()\" value=\"Submit\">");

     out.println("");
     out.println("</body>");
		out.println("<br>");
		out.println("<br>");
		out.println("<br>");
		out.println("</form>");
		
	
	} // End doGet

	/**
	 * ***************************************************** Prints the <head> of
	 * the HTML page, no <body>.
	 */
	private void PrintHead(PrintWriter out) {
		
	out.println("<html>");
		
     out.println("");
     out.println("<head>");
     out.println("<title>Final</title>");
     // Put the focus in the name field
     out.println ("<script>");
     out.println ("  function setFocus(){");
     out.println ("    document.persist2file.NAME.focus();");
     out.println ("  }");
     out.println ("</script>");
     out.println("</head>");
     out.println("");
	} // End PrintBody

	/**
	 * ***************************************************** Overloads PrintBody
	 * (out,lhs,rhs,rslt) to print a page with blanks in the form fields.
	 */
	private void PrintBody(PrintWriter out) {
		PrintBody(out, "", null);
	}


	/**
	 * ***************************************************** Prints the bottom of
	 * the HTML page.
	 */
	private void PrintTail(PrintWriter out) {
		out.println("");
		out.println("</html>");
	} // End PrintTail
	


} //
