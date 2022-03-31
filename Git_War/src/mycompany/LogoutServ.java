package mycompany;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServ extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Ran_cookie ran_cook=new Ran_cookie();
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        String name = "mycompany_Cookie";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                	try {
						if(ran_cook.logout(cookie.getValue().toString()) == false) {
							 request.getRequestDispatcher("logout.html").include(request, response);
							 System.out.println("return false in main page move to logout.html");
						}else {
							request.getRequestDispatcher("profile.html").include(request, response);
							 System.out.println("return true in main page move to profile.html");
						}
					} catch (ClassNotFoundException | SQLException e ) {
						e.printStackTrace();
					}
                }
            }
        }
        }
	}
