/**
 * AutentificadorSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */
package autentificador;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import org.apache.axis2.context.MessageContext;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * AutentificadorSkeleton java skeleton for the axisService
 */
public class AutentificadorSkeleton {
    private static String pw_admin = "admin";
    private static String from = "astbiblioteca1516@gmail.com";
    private static String pw = "borjabiblioteca";

    /**
     * Auto generated method signature
     *
     * @param login
     * @return loginResponse
     */
    public autentificador.LoginResponse login(autentificador.Login login) {
        String Nick = login.getNick();
        String password = login.getPassword();
        LoginResponse response = new LoginResponse();
        boolean existeLogin;
        MessageContext msg = MessageContext.getCurrentMessageContext();
        existeLogin = ExisteLogin(Nick, password);

        if (existeLogin == true) {
            if (password.equals(pw_admin)) {
                msg.getServiceContext().setProperty("login", "loginAdmin OK");
            } else {
                msg.getServiceContext().setProperty("login", "loginUser OK");
            }
        }
        else {
            msg.getServiceContext().setProperty("login", "login ERROR");
        }

        response.set_return(true);

        return response;
    }

    /**
     * Auto generated method signature
     *
     * @param registro
     * @return
     */
    public void registro(autentificador.Registro registro) {
        String Nick = registro.getNick();
        String password = registro.getPassword();
        String mail = registro.getCorreo();
        boolean comprobarExiste;

        comprobarExiste = comprobarExiste(Nick);

        if (comprobarExiste == true) {
            enviar_mail_usuario_existente(Nick);

            return;
        }

        insertarUsuario(Nick, password, mail);
        enviar_mail(Nick, password);

        return;
    }

    private void enviar_mail_usuario_existente(String nick) {
        MessageContext msg = MessageContext.getCurrentMessageContext();
        String email_to = msg.getReplyTo().getAddress();
        String mensaje = "No se ha podido completar el registro. El nick " +
            nick + " ya está siendo usado por otro usuario.";
        mensaje += "\nPor favor, vuelva a intentar registrarse con otro nick.";

        Properties props = new Properties();

        // Nombre del host de correo, es smtp.gmail.com
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // TLS si está disponible
        props.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        props.setProperty("mail.smtp.port", "587");

        // Nombre del usuario
        props.setProperty("mail.smtp.user", from);

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        // Session session = Session.getDefaultInstance(props);
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, pw);
                    }
                });

        // session.setDebug(true);
        MimeMessage message = new MimeMessage(session);

        try {
            // Quien envia el correo
            message.setFrom(new InternetAddress(from));

            // A quien se envia el correo
            message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(email_to));

            // Construimos el mensaje
            message.setSubject("Registro fallido en el servicio AST_Biblioteca");
            message.setText(mensaje);

            // Enviamos el mensaje
            Transport t = session.getTransport("smtp");
            t.connect(from, pw);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void enviar_mail(String nick, String password) {
        MessageContext msg = MessageContext.getCurrentMessageContext();
        String email_to = msg.getReplyTo().getAddress();
        String mensaje = "Te has registrado satisfactoriamente. Los datos de acceso de su cuenta al servicio son: \n";
        mensaje += ("Nick de acceso = " + nick + "\n");
        mensaje += ("Password de la cuenta = " + password + "\n");
        mensaje += "\nYa puede usar todos las funciones que les proporciona nuestro servicio de biblioteca";

        Properties props = new Properties();

        // Nombre del host de correo, es smtp.gmail.com
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // TLS si está disponible
        props.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        props.setProperty("mail.smtp.port", "587");

        // Nombre del usuario
        props.setProperty("mail.smtp.user", from);

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        // Session session = Session.getDefaultInstance(props);
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, pw);
                    }
                });

        // session.setDebug(true);
        MimeMessage message = new MimeMessage(session);

        try {
            // Quien envia el correo
            message.setFrom(new InternetAddress(from));

            // A quien se envia el correo
            message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(email_to));

            // Construimos el mensaje
            message.setSubject(
                "Registro completado en el servicio AST_Biblioteca");
            message.setText(mensaje);

            // Enviamos el mensaje
            Transport t = session.getTransport("smtp");
            t.connect(from, pw);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void insertarUsuario(String Nick, String password, String mail) {
        ObjectContainer db = (ObjectContainer) MessageContext.getCurrentMessageContext()
                                                             .getProperty("db_conexion");
        Usuarios usuario = new Usuarios(Nick, password, mail);
        db.store(usuario);
    }

    public boolean comprobarExiste(String Nick) {
        ObjectContainer db = (ObjectContainer) MessageContext.getCurrentMessageContext()
                                                             .getProperty("db_conexion");
        Usuarios usuario = new Usuarios(Nick, null, null);
        ObjectSet<Object> resultado = db.queryByExample(usuario);

        if (resultado.size() == 1) {
            return true;
        }

        return false;
    }

    public boolean ExisteLogin(String Nick, String password) {
        ObjectContainer db = (ObjectContainer) MessageContext.getCurrentMessageContext()
                                                             .getProperty("db_conexion");
        Usuarios usuario = new Usuarios(Nick, password, null);
        ObjectSet<Object> resultado = db.queryByExample(usuario);

        if (resultado.size() == 1) {
            return true;
        }

        return false;
    }
}
