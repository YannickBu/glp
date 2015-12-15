package ipint.glp.domain.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connexion extends HttpServlet {
	private String paramLogin;
    private String paramPassword;
     
    public void init() throws ServletException {
        this.paramLogin = "zero@zero";
        this.paramPassword = "zero";
    }
    private static final long serialVersionUID = 1L;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
         
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String login = request.getParameter("email");
        String pwd = request.getParameter("motdepasse");
         
        RequestDispatcher dispatcher = null;
         
         
        if(this.paramLogin.equalsIgnoreCase(login) && this.paramPassword.equalsIgnoreCase(pwd)){
            dispatcher = request.getRequestDispatcher("/");
        }else{
        	//.jsp?
            dispatcher = request.getRequestDispatcher("/connexion");
        }
        dispatcher.forward(request, response);
        }
         
    }