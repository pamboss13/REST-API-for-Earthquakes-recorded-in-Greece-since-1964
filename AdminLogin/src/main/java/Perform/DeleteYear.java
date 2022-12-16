package Perform;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteYear
 */
@WebServlet("/DeleteYear")
public class DeleteYear extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String jdbcUrl = "jdbc:mysql://localhost:3306/eqdb";
	String username = "root";
	String password = "admin";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteYear() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int batchSize = 1000;	//HUGE TABLE SO WE BATCH THE INSERTS (1K ROWS AT A TIME)
		Connection connection = null;
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String s = request.getParameter("info").toString();
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,username,password);
			connection.setAutoCommit(false);
			Statement st=connection.createStatement();
			st.executeUpdate("DELETE FROM earthquake WHERE Date LIKE '%"+s+"%'");
			System.out.println("Deleted Records Successfully");
			connection.commit();
			//connection.close();
			RequestDispatcher rs = request.getRequestDispatcher("index2.jsp");
			rs.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	

}
