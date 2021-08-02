package mx.edu.utez.controller;

import mx.edu.utez.model.pelicula.BeanPelicula;
import mx.edu.utez.model.pelicula.DaoPelicula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SerPelicula", urlPatterns = {"/readPelicula", "/createPelicula", "/getPeliculaById", "/updatePelicula", "/deletePelicula"})
public class ServletPelicula extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ServletPelicula.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listaPelicula", new DaoPelicula().findAll());
        request.getRequestDispatcher("/views/pelicula/pelicula.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch(action){
            case "create":
                String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                String description = request.getParameter("description");
                String Estreno = request.getParameter("Estreno");
                double Recaudacion = Integer.parseInt(request.getParameter("Recaudacion"));

                BeanPelicula beanPelicula = new BeanPelicula(1,name, description, Estreno, Recaudacion, 1);

                if(new DaoPelicula().create(beanPelicula)){
                    request.setAttribute("message", "Pelicula registrada correctamente");
                } else {
                    request.setAttribute("message", "Pelicula no registrada");
                }
                doGet(request, response);
                break;

            case "getPeliculaById":
                // do something
                long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("pelicula", new DaoPelicula().findById(id));
                request.getRequestDispatcher("/views/pelicula/update.jsp").forward(request, response);
                break;

            case "update":
                // do something
                long idPeli = Long.parseLong(request.getParameter("id"));
                String NamePeli = request.getParameter("name") != null ? request.getParameter("name") : "";
                String DescriptionPeli = request.getParameter("description");
                String EstrenoPeli = request.getParameter("Estreno");
                double RecaudacionPeli = Integer.parseInt(request.getParameter("Recaudacion"));

                BeanPelicula beanPeli = new BeanPelicula(idPeli, NamePeli, DescriptionPeli, EstrenoPeli, RecaudacionPeli, 1);

                if(new DaoPelicula().update(beanPeli)){
                    request.setAttribute("message", "La Película ha sido modificada");
                } else {
                    request.setAttribute("message", "La Pelicula no ha sido modificada");
                }
                doGet(request, response);
                break;

            case "delete":
                long id2 = Long.parseLong(request.getParameter("id"));
                if(new DaoPelicula().delete(id2)){
                    request.setAttribute("message", "Usuario eliminado");
                } else {
                    request.setAttribute("message", "Usuario no eliminado");
                }
                doGet(request, response);
                break;
            default:
                // no supported
                break;
        }
    }
}
