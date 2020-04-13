/** *****************************************************************
    assignment6.java   servlet example
        @author Jeff Offutt
********************************************************************* */
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

@WebServlet(name = "assignment6", // "MyServlet2",
		urlPatterns = { "/assignment6" })

// assignment6 class
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

public class assignment6 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String Domain = "swe432assignment6.herokuapp.com";
	static String Path = "/";
	static String Servlet = "assignment6";
	
// Button labels
	static String OperationAdd = "Add";
	static String OperationSub = "Subtract";
	static String OperationMult = "Multiply";

// Other strings.
	static String Style = "https://www.cs.gmu.edu/~offutt/classes/432/432-style.css";

	/**
	 * ***************************************************** Overrides HttpServlet's
	 * doPost(). Converts the values in the form, performs the operation indicated
	 * by the submit button, and sends the results back to the client.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fenwick = request.getParameter("Fenwick"); // a
		String johnson = request.getParameter("JC"); // b
		String robinson = request.getParameter("RB"); // c
		String southside = request.getParameter("Southside"); // d
		String volgenau = request.getParameter("VSE"); // e
		String dropDown = request.getParameter("School Year");
		
		String rslt = "";
		rslt += "\nSchool Year: " + dropDown + " ||| Fenwick: " + fenwick + " ||| Johnson Center: " + johnson + " ||| Robinson Hall B: " + robinson + " ||| Southside: " + southside  + " ||| Volgenau: " + volgenau;

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		PrintHead(out);
		PrintBody(out, rslt.toString());
		PrintTail(out);
	} // End doPost

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
	} // End doGet

	/**
	 * ***************************************************** Prints the <head> of
	 * the HTML page, no <body>.
	 */
	private void PrintHead(PrintWriter out) {
		out.println("<html>");
		out.println("");

		out.println("<head>");
		out.println("<title>Assignment 6</title>");
		out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"" + Style + "\">");
		out.println("</head>");
		out.println("");
	} // End PrintHead

	/**
	 * ***************************************************** Prints the <BODY> of
	 * the HTML page with the form data values from the parameters.
	 */
	private void PrintBody(PrintWriter out,  String rslt) {
	
		out.println("<body>");
   		 out.println("<b>SWE 432: </b> Assignment 6");
		out.println("<p>");
		out.println("<b>Partners:</b> Megan Ngo and Thomas Rigger");
		out.println("</p>");
		out.println("<p><b>Collaboration Summary:</b> Megan and Thomas worked together to build and debug doGet, doPost, printBody, and printHead and then committed it to Heroku. </p>");
		 out.println("<p><b>Survey Instructions:</b> Please fill out this form to rate GMU buildings.</p>");
		   out.println("<br>");

			// these three lines are from the original servlet
		   out.println("<form method=\"post\"");
		   out.println(" action=\"https://" + Domain + Path + Servlet + "\">");
		   out.println("");
		   
		out.println("What year are you?");         
		out.println("  <select name= \"School Year\">"); 
		out.println("  <option value= \"Freshman\" selected=\"selected\">Freshman</option>"); 
		out.println("  <option value=\"Sophomore\">Sophomore</option>"); 
		out.println("  <option value=\"Junior\">Junior</option>"); 
		out.println(" <option value=\"Senior\">Senior</option>"); 
		out.println("</select>"); 

		out.println("<br> <br> Please rate the following GMU buildings on a scale of 1 (worst) to 5 (best): <br> <br> ");

		out.println("<b>Fenwick Library</b>");    
		out.println("<br>");
		out.println("  <input type=\"radio\" name=\"Fenwick\" id=\"one\" value=\"1\" /> ");
		out.println("  <label for=\"one\">1</label> ");
		out.println("  <input type=\"radio\" name=\"Fenwick\" id=\"two\" value=\"2\" /> ");
		out.println(" <label for=\"two\">2</label> ");
		out.println("  <input type=\"radio\" name=\"Fenwick\" id=\"three\" value=\"3\" /> ");
		out.println(" <label for=\"three\">3</label> ");
		out.println("  <input type=\"radio\" name=\"Fenwick\" id=\"four\" value=\"4\" />");
		out.println(" <label for=\"four\">4</label>");
		out.println("  <input type=\"radio\" name=\"Fenwick\" id=\"five\" value=\"5\" />");
		out.println(" <label for=\"five\">5</label>");

		out.println("<br> ");
		out.println("<b>Johnson Center</b>");
		out.println("<br>");
		out.println("  <input type=\"radio\" name=\"JC\" id=\"one\" value=\"1\" />"); 
		out.println("  <label for=\"one\">1</label>"); 
		out.println("  <input type=\"radio\" name=\"JC\" id=\"two\" value=\"2\" />");
		out.println("  <label for=\"two\">2</label>");
		out.println("  <input type=\"radio\" name=\"JC\" id=\"three\" value=\"3\" />");
		out.println("  <label for=\"three\">3</label>");
		out.println("  <input type=\"radio\" name=\"JC\" id=\"four\" value=\"4\" />");
		out.println("  <label for=\"four\">4</label>");
		out.println("  <input type=\"radio\" name=\"JC\" id=\"five\" value=\"5\" />");
		out.println("  <label for=\"five\">5</label>");

		out.println("<br>");

		out.println("<b>Robinson Hall B</b>");
		out.println("<br>");
		out.println("  <input type=\"radio\" name=\"RB\" id=\"one\" value=\"1\" /> ");
		out.println("  <label for=\"one\">1</label>"); 
		out.println("  <input type=\"radio\" name=\"RB\" id=\"two\" value=\"2\" />");
		out.println("  <label for=\"two\">2</label>");
		out.println("  <input type=\"radio\" name=\"RB\" id=\"three\" value=\"3\" />");
		out.println("  <label for=\"three\">3</label>");
		out.println("  <input type=\"radio\" name=\"RB\" id=\"four\" value=\"4\" />");
		out.println("  <label for=\"four\">4</label>");
		out.println("  <input type=\"radio\" name=\"RB\" id=\"five\" value=\"5\" />");
		out.println("  <label for=\"five\">5</label>");

		out.println("<br>");

		out.println("<b>Southside</b>");
		out.println("<br>");
		out.println("  <input type=\"radio\" name=\"Southside\" id=\"one\" value=\"1\" />"); 
		out.println("  <label for=\"one\">1</label>"); 
		out.println("  <input type=\"radio\" name=\"Southside\" id=\"two\" value=\"2\" />");
		out.println(" <label for=\"two\">2</label>");
		out.println("  <input type=\"radio\" name=\"Southside\" id=\"three\" value=\"3\" /");
		out.println(" <label for=\"three\">3</label>");
		out.println("  <input type=\"radio\" name=\"Southside\" id=\"four\" value=\"4\" />");
		out.println(" <label for=\"four\">4</label>");
		out.println("  <input type=\"radio\" name=\"Southside\" id=\"five\" value=\"5\" />");
		out.println(" <label for=\"five\">5</label>");

		out.println("<br>");

		out.println("<b>Volgenau School of Engineering</b>");
		out.println("<br>");
		out.println("  <input type=\"radio\" name=\"VSE\" id=\"one\" value=\"1\" /> ");
		out.println("  <label for=\"one\">1</label> ");
		out.println("  <input type=\"radio\" name=\"VSE\" id=\"two\" value=\"2\" />");
		out.println("  <label for=\"two\">2</label>");
		out.println("  <input type=\"radio\" name=\"VSE\" id=\"three\" value=\"3\" />");
		out.println("  <label for=\"three\">3</label>");
		out.println("  <input type=\"radio\" name=\"VSE\" id=\"four\" value=\"4\" />");
		out.println("  <label for=\"four\">4</label>");
		out.println("  <input type=\"radio\" name=\"VSE\" id=\"five\" value=\"5\" />");
		out.println("  <label for=\"five\">5</label>");
		out.println("<br>");
		out.println("<p></p>");
		out.println("<input type=\"submit\" onclick=\"doPost()\" value=\"Submit\">");

		out.println("</form>");
		
    		if (!rslt.equals("")) {
			out.println("Result: ");
			out.println(rslt);
        	}
		
		out.println("");
		out.println("</body>");
	} // End PrintBody

	/**
	 * ***************************************************** Overloads PrintBody
	 * (out,lhs,rhs,rslt) to print a page with blanks in the form fields.
	 */
	private void PrintBody(PrintWriter out) {
		PrintBody(out, "");
	}


	/**
	 * ***************************************************** Prints the bottom of
	 * the HTML page.
	 */
	private void PrintTail(PrintWriter out) {
		out.println("");
		out.println("</html>");
	} // End PrintTail

} // End assignment6
