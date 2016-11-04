package example;

import DAO.ContatoDAO;
import beans.Contato;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by handyc on 02/11/16.
 */


@WebServlet(urlPatterns = {"/adicionaContato"})

public class AdicionaContatoServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        PrintWriter out = response.getWriter();

        //pegando os parâmetros do request
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String endereco = request.getParameter("endereco");
        String dtEmText= request.getParameter("dataNascimento");

        Calendar dataNascimento = null;

        try{
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(dtEmText));
            dataNascimento = Calendar.getInstance();
            dataNascimento.setTime(date);
        }catch (ParseException pe){
            out.println("Erro na conversao da data: "+pe.getMessage());
            return;
        }

        //mont um objeto contato
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEndereco(endereco);
        contato.setEmail(email);
        contato.setDtNascimento(dataNascimento);

        //salva contato
        ContatoDAO dao = new ContatoDAO();
        dao.adicionar(contato);

        //imprime o nome do contato que foi adicionado
        out.println("<html>");
        out.println("<body>");
        out.println("Contato: "+contato.getNome()+"\nContato adicionado");
        out.println("</body>");
        out.println("</html>");
    }

    /*
    public static void main(String []argv) {
        Object implementor = new HelloWorld();
        String address = "http://localhost:8080/adiciona-contato";
        Endpoint.publish(address, implementor);

    }
    */
}
