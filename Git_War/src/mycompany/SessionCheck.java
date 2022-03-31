package mycompany;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class SessionCheck extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		JSONObject obj=new JSONObject();
		PrintWriter pw=response.getWriter();
		System.out.println("Request came to servlet doPost");
		Ran_cookie ran_cook=new Ran_cookie();
        Cookie[] cookies = request.getCookies();
        System.out.println("cookie is not null "+cookies);
        String name = "mycompany_Cookie";
        if (cookies != null) {
        	System.out.println("Cookie is not null in mycompany Session check  Servlet");
            for (Cookie cookie : cookies) {
            	System.out.println("mycompany Session check Servlet for loop + cookie name is "+cookie.getName());
                if (cookie.getName().equals(name)) {
                	System.out.println("mycompany Session check Servlet get name");
                	try {
						if(ran_cook.login_check(cookie.getValue().toString()) == 1) {
							obj.put("state", "hold");
						    pw.print(obj);
						    pw.flush();
						    System.out.println("mycompany Session check Servlet Object hold + break exicuted");
						    break;
						}else {
							obj.put("state", "move");
						    pw.print(obj);
						    pw.flush();
						    System.out.println("mycompany Session check Servlet Object move");
						    break;
						}
					} catch (ClassNotFoundException | SQLException | JSONException e ) {
						e.printStackTrace();
					}
                }
            }
        }else {
        	System.out.println("Cookie is null so came to else block in mycompany Session check");
			 try {
				obj.put("state", "move");
			    pw.print(obj);
			    pw.flush();
			    System.out.println("mycompany Session check Servlet Object move finall");
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
	}
}
