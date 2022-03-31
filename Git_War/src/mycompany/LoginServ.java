package mycompany;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

public class LoginServ extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        Ran_cookie ran_cook=new Ran_cookie();
        Cookie[] cookies = request.getCookies();
        String name = "mycompany_Cookie";
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String otp=request.getParameter("otp");
		String HighSecPass=request.getParameter("highsecpass");
	try {
		System.out.println("Username : "+username+" Password : "+password+" otp: "+otp+" HighSecPass : "+HighSecPass);
        if (cookies != null) {
        	System.out.println("Cookie is not null");
            for (Cookie cookie : cookies) {
            	System.out.println("For loop of cookie "+cookie);
                if (cookie.getName().equals(name)) {
                	System.out.println("Cookie name matached to mycompany");
                	if(ran_cook.login_check(cookie.getValue().toString()) == 1) {
               		 System.out.println("login_check method called returns 1. session already there");
                		 request.getRequestDispatcher("profile.html").include(request, response);
                		 break;
                	}else {
                		System.out.println("Else block of cookie check");
                        request.getRequestDispatcher("logout.html").include(request, response);
                        break;
                	}
                }else {
                if(username.equals("admin") && password.equals("12345") && otp.equals("12345") && HighSecPass.equals("12345")) 
        		{
        			ran_cook.login();
        			Cookie ck=new Cookie("mycompany_Cookie",ran_cook.cookie_value);
        			response.addCookie(ck);
            		System.out.println("Admin login successfull. cookie created");
        			request.getRequestDispatcher("profile.html").include(request, response);
        			break;
        		}
        		else 
        		{
            		System.out.println("Admin login not successfull. username password mismatch");
                    request.getRequestDispatcher("logout.html").include(request, response);
                    break;
        		}
               }
            }
        }else {
            if(username.equals("admin") && password.equals("12345") && otp.equals("12345") && HighSecPass.equals("12345")) 
     		{
     			ran_cook.login();
     			Cookie ck=new Cookie("mycompanys_Cookie",ran_cook.cookie_value);
     			response.addCookie(ck);
         		System.out.println("Admin login successfull. cookie created");
     			request.getRequestDispatcher("profile.html").include(request, response);
     		}
     		else 
     		{
         		System.out.println("Admin login not successfull. username password mismatch");
                 request.getRequestDispatcher("logout.html").include(request, response);
     		}
        }
		
	}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
