import java.rmi.RemoteException;
import java.util.Scanner;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingDetail;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.FindQualifiers;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.GetBindingDetail;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.ServiceList;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

public class Cliente {

	private static EndpointReference targetEPR;
	private static OMFactory fac = OMAbstractFactory.getOMFactory();
	private static OMNamespace omNs = fac.createOMNamespace("http://central", "ns1");
	private static String loginUser = "loginUser OK";
	private static String loginAdmin = "loginAdmin OK";
	private static String loginERROR = "login ERROR";
	private static String s_nick;
	private static String s_password;
	private static UDDISecurityPortType security = null;
	private static UDDIInquiryPortType inquiry = null;

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		iniciar();
		find();
		menu_principal(entrada);
	}

	private static void menu_principal(Scanner entrada) {
		int opcion = 0;
		String estado_login = null;
		while (opcion != 3) {
			System.out.println("Bienvenido al servicio de Biblioteca");
			System.out.println("1.- Registro");
			System.out.println("2.- Login");
			System.out.println("3.- Salir");
			System.out.println("Escoja una opcion: ");
			opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				menu_Registro(entrada);
				break;
			case 2:
				estado_login = menu_Login(entrada);
				if (estado_login.equals(loginUser))
					menu_Usuario(entrada);
				else if (estado_login.equals(loginAdmin))
					menu_Admin(entrada);
				else if (estado_login.equals(loginERROR))
					System.out
							.println("\nLos datos que ha introducido son incorrectos, por favor vuelva a intentarlo.");
				else
					System.out.println("\nHa habido un error en el login, por favor vuelva a intentarlo.");
				break;
			case 3:
				break;
			default:
				break;
			}
		}
	}

	private static void menu_Admin(Scanner entrada) {
		int opcion = 0;
		while (opcion != 5) {
			System.out.println("\n\n\n");
			System.out.println("MENÚ ADMINISTRADOR:");
			System.out.println("1.- Mostrar todos los libros disponibles en la biblioteca.");
			System.out.println("2.- Buscar libros disponibles por Titulo.");
			System.out.println("3.- Coger un libro en préstamo.");
			System.out.println("4.- Introducir un nuevo libro en la biblioteca");
			System.out.println("5.- Cerrar sesión.");
			System.out.println("Escoja una opción: ");
			opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				listarLibros();
				break;
			case 2:
				buscarLibros(entrada);
				break;
			case 3:
				if(obtenerLibro(entrada) == false) {
					System.out.println("No se ha podido encontrar el libro.");
				} else {
					System.out.println("Se obtuvo correctamente!");
				}
				break;
			case 4:
				if(introducirLibros(entrada) == false) {
					System.out.println("No se ha podido introducir el libro.");
				} else {
					System.out.println("Se introdujo correctamente.");
				}
				break;
			case 5:
				break;
			default:
				break;
			}
		}
	}

	private static boolean introducirLibros(Scanner entrada) {
		String in_titulo, in_isbn, in_cantidad;
		boolean respuesta = false;
		System.out.println("\n\n\n");
		System.out.println("Introduzca el Titulo: ");
		in_titulo = entrada.next();
		System.out.println("Introduzca el ISBN: ");
		in_isbn = entrada.next();
		System.out.println("Introduzca la cantidad de libros: ");
		in_cantidad = entrada.next();
		ServiceClient sc;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR);
			opts.setAction("urn:introducirLibros");
			sc.setOptions(opts);
			
			OMElement introducirLibros = fac.createOMElement("introducirLibros", omNs);
			OMElement titulo = fac.createOMElement("Titulo", "", "");
			OMElement isbn = fac.createOMElement("ISBN", "", "");
			OMElement cantidad = fac.createOMElement("Cantidad", "", "");
			OMElement nick = fac.createOMElement("nick", "", "");
			OMElement password = fac.createOMElement("password", "", "");
			titulo.setText(in_titulo);
			isbn.setText(in_isbn);
			cantidad.setText(in_cantidad);
			nick.setText(s_nick);
			password.setText(s_password);
			introducirLibros.addChild(titulo);
			introducirLibros.addChild(isbn);
			introducirLibros.addChild(cantidad);
			introducirLibros.addChild(nick);
			introducirLibros.addChild(password);
			
			OMElement response = sc.sendReceive(introducirLibros);
			if(response.getFirstElement().getText().equals("true"))
				respuesta = true;
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;		
	}

	private static void menu_Usuario(Scanner entrada) {
		int opcion = 0;
		while (opcion != 4) {
			System.out.println("\n\n\n");
			System.out.println("MENÚ USUARIO:");
			System.out.println("1.- Mostrar todos los libros disponibles en la biblioteca.");
			System.out.println("2.- Buscar libros disponibles por Titulo.");
			System.out.println("3.- Coger un libreo en préstamo.");
			System.out.println("4.- Cerrar sesión.");
			System.out.println("Escoja una opción: ");
			opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				listarLibros();
				break;
			case 2:
				buscarLibros(entrada);
				break;
			case 3:
				if(obtenerLibro(entrada) == false) {
					System.out.println("No se ha podido encontrar el libro.");
				} else {
					System.out.println("Se obtuvo correctamente!");
				}
				break;
			case 4:
				break;
			default:
				break;
			}
		}
	}

	private static boolean obtenerLibro(Scanner entrada) {
		String in_isbn;
		boolean respuesta = false;
		System.out.println("\n\n\n");
		System.out.println("Introduzca el ISBN: ");
		in_isbn = entrada.next();
		ServiceClient sc;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR);
			opts.setAction("urn:obtenerLibro");
			sc.setOptions(opts);
			
			OMElement obtenerLibro = fac.createOMElement("obtenerLibro", omNs);
			OMElement isbn = fac.createOMElement("ISBN", "", "");
			isbn.setText(in_isbn);
			obtenerLibro.addChild(isbn);
			
			OMElement response = sc.sendReceive(obtenerLibro);
			if(response.getFirstElement().getText().equals("true"))
				respuesta = true;
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;
	}

	private static void buscarLibros(Scanner entrada) {
		String lista_libros, in_titulo;
		System.out.println("Introduzca el titulo: ");
		in_titulo = entrada.next();
		ServiceClient sc;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR);
			opts.setAction("urn:consultaTitulo");
			sc.setOptions(opts);
			
			OMElement consultaTitulo = fac.createOMElement("consultaTitulo", omNs);
			OMElement titulo = fac.createOMElement("Titulo", "", "");
			titulo.setText(in_titulo);
			consultaTitulo.addChild(titulo);
			
			OMElement response = sc.sendReceive(consultaTitulo);
			
			lista_libros = response.getFirstElement().getText();
			
			System.out.println("\n\n\n");
			System.out.println("Lista de libros con el titulo " + in_titulo + ": ");
			System.out.println(lista_libros);
			System.out.println("\n\n\n");
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listarLibros() {
		String lista_libros;
		ServiceClient sc;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR);
			opts.setAction("urn:listarLibros");
			sc.setOptions(opts);
			
			OMElement listarLibros = fac.createOMElement("listarLibros", omNs);
			
			OMElement response = sc.sendReceive(listarLibros);
			
			lista_libros = response.getFirstElement().getText();
			
			System.out.println("\n\n\n");
			System.out.println("Lista de libros: ");
			System.out.println(lista_libros);
			System.out.println("\n\n\n");
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String menu_Login(Scanner entrada) {
		String respuesta = null;
		System.out.println("Introduzca los datos para el login:");
		System.out.println("Nick = ");
		s_nick = entrada.next();
		System.out.println("Password = ");
		s_password = entrada.next();
		ServiceClient sc;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR);
			opts.setAction("urn:Login");
			sc.setOptions(opts);

			OMElement login = fac.createOMElement("Login", omNs);
			OMElement nick = fac.createOMElement("nick", "", "");
			OMElement password = fac.createOMElement("password", "", "");
			nick.setText(s_nick);
			password.setText(s_password);
			login.addChild(nick);
			login.addChild(password);

			OMElement response = sc.sendReceive(login);
			
			//System.out.println(response);

			respuesta = response.getFirstElement().getText();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;
	}

	private static void menu_Registro(Scanner entrada) {
		String in_nick, in_password, in_correo;
		System.out.println("Introduzca los datos para el registro:");
		System.out.println("Nick = ");
		in_nick = entrada.next();
		System.out.println("Password = ");
		in_password = entrada.next();
		System.out.println("Correo = ");
		in_correo = entrada.next();
		ServiceClient sc;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR);
			sc.engageModule("addressing");
			opts.setReplyTo(new EndpointReference(in_correo));
			opts.setAction("urn:Registro");
			sc.setOptions(opts);

			OMElement registro = fac.createOMElement("Registro", omNs);
			OMElement nick = fac.createOMElement("nick", "", "");
			OMElement password = fac.createOMElement("password", "", "");
			OMElement correo = fac.createOMElement("correo", "", "");
			correo.setText(in_correo);
			password.setText(in_password);
			nick.setText(in_nick);
			registro.addChild(nick);
			registro.addChild(password);
			registro.addChild(correo);

			sc.fireAndForget(registro);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void find() {
		// Conexion
		GetAuthToken getAuthTokenMyPub = new GetAuthToken();
		getAuthTokenMyPub.setUserID("bob"); // your username
		getAuthTokenMyPub.setCred("bob"); // your password
		AuthToken myPubAuthToken = null;
		try {
			myPubAuthToken = security.getAuthToken(getAuthTokenMyPub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getAuthTokenMyPub.getUserID() + "'s AUTHTOKEN = " + "******* never log auth tokens!");

		String AuthInfo3 = myPubAuthToken.getAuthInfo();

		FindQualifiers findQualifiers = new FindQualifiers();
		findQualifiers.getFindQualifier().add("sortByNameDesc");
		findQualifiers.getFindQualifier().add("approximateMatch");
		Name s1 = new Name();
		s1.setValue("ServicioCentral");
		FindService findService = new FindService();
		findService.setAuthInfo(AuthInfo3);
		findService.getName().add(s1);
		findService.setFindQualifiers(findQualifiers);
		ServiceList serviceList = null;
		try {
			serviceList = inquiry.findService(findService);
		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ServiceInfo serviceInfo = serviceList.getServiceInfos().getServiceInfo().get(0);
		GetServiceDetail gsd = new GetServiceDetail();
		gsd.getServiceKey().add(serviceInfo.getServiceKey());
		System.out.println(serviceInfo.getServiceKey());
		gsd.setAuthInfo(AuthInfo3);

		ServiceDetail serviceDetail = null;
		try {
			serviceDetail = inquiry.getServiceDetail(gsd);
		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BusinessService bs = serviceDetail.getBusinessService().get(0);
		System.out.println("fetched service name:" + bs.getName().get(0).getValue());
		String bindingkey = bs.getBindingTemplates().getBindingTemplate().get(0).getBindingKey();
		System.out.println(bindingkey);
		GetBindingDetail gbd = new GetBindingDetail();
		gbd.setAuthInfo(AuthInfo3);
		gbd.getBindingKey().add(bindingkey);
		BindingDetail bindingdetail = null;
		try {
			bindingdetail = inquiry.getBindingDetail(gbd);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BindingTemplate bindingtemplate = bindingdetail.getBindingTemplate().get(0);
		String accesspoint = bindingtemplate.getAccessPoint().getValue();
		System.out.println(accesspoint);
		targetEPR = new EndpointReference(accesspoint);
		
	}
	
	private static void iniciar() {
		try {
			// create a client and read the config in the archive;
			// you can use your config file name
			UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
			// a UddiClient can be a client to multiple UDDI nodes, so
			// supply the nodeName (defined in your uddi.xml.
			// The transport can be WS, inVM, RMI etc which is defined in the
			// uddi.xml
			Transport transport = uddiClient.getTransport("default");
			// Now you create a reference to the UDDI API
			security = transport.getUDDISecurityService();
			inquiry = transport.getUDDIInquiryService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
