package Controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Livre;
import config.conn;

@WebServlet("/enregistrer")
public class ajouterLivre extends HttpServlet {
    private conn conn;

    public void init() {
        conn = new conn();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ISBN = request.getParameter("ISBN");
        String nom = request.getParameter("Nom");
        String Editeur = request.getParameter("Editeur");
        String Titre = request.getParameter("Titre");
        String Resume = request.getParameter("Resume");
        LocalDate dateEdition = LocalDate.parse(request.getParameter("Date")); // Parse String to LocalDate

        String[] AuteursL = request.getParameterValues("Auteurs");
        ArrayList<String> Auteurs = new ArrayList<>();
        for (String Auteur : AuteursL) {
            Auteurs.add(Auteur);
        }

        String[] MotsClesL = request.getParameterValues("MotsCles");
        ArrayList<String> MotsCles = new ArrayList<>();
        for (String mot : MotsClesL) {
            MotsCles.add(mot);
        }

        Livre L = new Livre(ISBN, nom, Editeur, dateEdition, Resume, MotsCles, Auteurs);
        conn.insertLivre(L);

        // Redirect to a success page or display a message
       // response.sendRedirect("success.jsp");
    }
}
