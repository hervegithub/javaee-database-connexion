package com.myprojet.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myprojet.bean.Utilisateur;
import com.myprojet.database.JdbcConnector;


@WebServlet("/Formservlet")
public class Formservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Formservlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcConnector con= new JdbcConnector();
		con.loadDatabase();
		request.setAttribute("utilisateurs", con.recupererUtilisateurs());
	
		this.getServletContext().getRequestDispatcher("/WEB-INF/form.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getParameter("nom"));
        utilisateur.setPrenom(request.getParameter("prenom"));
        
        JdbcConnector con= new JdbcConnector();
        con.ajouterUtilisateur(utilisateur);
        
        request.setAttribute("utilisateurs", con.recupererUtilisateurs());
        
      
		this.getServletContext().getRequestDispatcher("/WEB-INF/form.jsp").forward(request, response);
	}

}
