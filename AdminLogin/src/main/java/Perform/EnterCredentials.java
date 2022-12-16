package Perform;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnterCredentials
 */
@WebServlet("/EnterCredentials")
public class EnterCredentials extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterCredentials() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out=response.getWriter();
			String s=request.getParameter("info").toString();
			//out.println(s);
			if(stringCompare(s,"admin")==0) {
				//out.println("Correct credentials\n");
				RequestDispatcher rs = request.getRequestDispatcher("UpdateYear.jsp");
				rs.forward(request, response);
			}else {
				
				RequestDispatcher rs2 = request.getRequestDispatcher("index.jsp");
				rs2.forward(request, response);
				out.println("Incorrect credentials\n");
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public static int stringCompare(String str1, String str2)
    {
  
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);
  
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);
  
            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
  
        if (l1 != l2) {
            return l1 - l2;
        }
  
        else {
            return 0;
        }
    }

}
