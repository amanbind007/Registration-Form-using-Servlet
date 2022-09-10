package Registration.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stdName = request.getParameter("student_name");
		String username = request.getParameter("user_name");
		String password = request.getParameter("pass_word");
		long phoneNo = Long.parseLong(request.getParameter("phone"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String enrollmentNo = request.getParameter("enrollNo");
		String institute = request.getParameter("institute");
		String address = request.getParameter("address");

		String sql = "insert into registrationform values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "dragonarts@007");
			st = con.prepareStatement(sql);
			st.setString(1, stdName);
			st.setString(2, username);
			st.setString(3, password);
			st.setLong(4, phoneNo);
			st.setString(5, gender);
			st.setString(6, email);
			st.setString(7, enrollmentNo);
			st.setString(8, institute);
			st.setString(9, address);

			int i = st.executeUpdate();

			if (i > 0) {
				PrintWriter out = response.getWriter();
				out.println("Hello " + stdName + ", You are Successfully Registered!!!");
			} else {
				System.out.println("Test");
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
