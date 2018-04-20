/**
 * ServicioCentralSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */
package central;

import java.rmi.RemoteException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.AccessPoint;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingDetail;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BindingTemplates;
import org.uddi.api_v3.BusinessDetail;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.DeleteBusiness;
import org.uddi.api_v3.DeleteService;
import org.uddi.api_v3.DiscardAuthToken;
import org.uddi.api_v3.FindQualifiers;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.GetBindingDetail;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.SaveBusiness;
import org.uddi.api_v3.SaveService;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.ServiceList;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

/**
 * ServicioCentralSkeleton java skeleton for the axisService
 */
public class ServicioCentralSkeleton implements ServiceLifeCycle {

	private static EndpointReference targetEPR_externo = new EndpointReference(
			"http://webservices.daehosting.com/services/isbnservice.wso?WSDL");
	private static EndpointReference targetEPR_gestor;
	private static EndpointReference targetEPR_autentificador;
	private static OMFactory fac = OMAbstractFactory.getOMFactory();
	private static OMNamespace omNs_aut = fac.createOMNamespace("http://autentificador", "ns1");
	private static OMNamespace omNs_ext = fac.createOMNamespace("http://webservices.daehosting.com/ISBN", "ns1");
	private static OMNamespace omNs_gest = fac.createOMNamespace("http://gestor", "ns1");
	private static UDDISecurityPortType security = null;
	private static UDDIPublicationPortType publish = null;
	private static UDDIInquiryPortType inquiry = null;
	private static String myBusKey;
	private static String myServKey;

	/**
	 * Auto generated method signature
	 *
	 * @param login
	 * @return loginResponse
	 */
	public central.LoginResponse login(central.Login login) {
		central.LoginResponse response = new central.LoginResponse();
		response.set_return(call_login(login.getNick(), login.getPassword()));
		return response;
	}

	/**
	 * Auto generated method signature
	 *
	 * @param listarLibros
	 * @return listarLibrosResponse
	 */
	public central.ListarLibrosResponse listarLibros(central.ListarLibros listarLibros) {
		central.ListarLibrosResponse response = new central.ListarLibrosResponse();
		String response_call_listarLibros = call_listarLibros();
		response.set_return(response_call_listarLibros);
		return response;
	}

	/**
	 * Auto generated method signature
	 *
	 * @param obtenerLibro
	 * @return obtenerLibroResponse
	 */
	public central.ObtenerLibroResponse obtenerLibro(central.ObtenerLibro obtenerLibro) {
		central.ObtenerLibroResponse response = new central.ObtenerLibroResponse();

		// Llamada síncrona al servicio externo
		boolean response_comprobarISBN = comprobarISBN(obtenerLibro.getISBN());
		if (response_comprobarISBN == false) {
			response.set_return(false);
			return response;
		}

		// Llamada síncrona al servicio gestorBiblio
		boolean response_call_obtenerLibro = call_obtenerLibro(obtenerLibro.getISBN());
		if (response_call_obtenerLibro == false) {
			response.set_return(false);
			return response;
		}

		// Si se llegó hasta aquí, todas las llamadas se ejecutaron
		// correctamente
		// y se devuelve true al cliente
		response.set_return(true);
		return response;
	}

	/**
	 * Auto generated method signature
	 *
	 * @param consultaTitulo
	 * @return consultaTituloResponse
	 */
	public central.ConsultaTituloResponse consultaTitulo(central.ConsultaTitulo consultaTitulo) {
		central.ConsultaTituloResponse response = new central.ConsultaTituloResponse();
		response.set_return(call_consultaTitulo(consultaTitulo.getTitulo()));
		return response;
	}

	/**
	 * Auto generated method signature
	 *
	 * @param introducirLibros
	 * @return introducirLibrosResponse
	 */
	public central.IntroducirLibrosResponse introducirLibros(central.IntroducirLibros introducirLibros) {
		central.IntroducirLibrosResponse response = new central.IntroducirLibrosResponse();

		// Llamada asíncrona al servicio externo
		Callback callback = new Callback();
		// comprobarISBN_async(introducirLibros.getISBN(), callback);

		ServiceClient sc = null;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR_externo);
			opts.setAction("");
			sc.setOptions(opts);

			OMElement IsValidISBN13 = fac.createOMElement("IsValidISBN13", omNs_ext);
			OMElement sISBN = fac.createOMElement("sISBN", "", "");
			sISBN.setText(introducirLibros.getISBN());
			IsValidISBN13.addChild(sISBN);

			sc.sendReceiveNonBlocking(IsValidISBN13, callback);

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Llamada síncrona al autentificador para comprobar que no sea un
		// administrador hacker
		String response_autentificar_admin = call_login(introducirLibros.getNick(), introducirLibros.getPassword());
		if (!response_autentificar_admin.equals("loginAdmin OK")) {
			response.set_return(false);
			return response;
		}

		// Bucle de espera a que se reciba la respuesta de la llamada asíncrona
		while (!callback.isComplete()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			sc.cleanupTransport();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Obtenemos el mensaje que devuelve el callback
		OMElement response_callback = callback.getResponse();
		if (response_callback.getFirstElement().getText().equals("false")) {
			response.set_return(false);
			return response;
		}

		// Llamada síncrona al gestorBiblio
		call_introducirLibros(introducirLibros.getTitulo(), introducirLibros.getISBN(), introducirLibros.getCantidad());

		// Si se llegó hasta aquí, todas las llamadas se ejecutaron
		// correctamente
		// y se devuelve true al cliente
		response.set_return(true);
		return response;
	}

	/**
	 * Auto generated method signature
	 *
	 * @param registro
	 * @return
	 */
	public void registro(central.Registro registro) {
		String nick = registro.getNick();
		String password = registro.getPassword();
		String correo = registro.getCorreo();
		ServiceClient sc;
		MessageContext msg = MessageContext.getCurrentMessageContext();
		String email_to = msg.getReplyTo().getAddress();
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR_autentificador);
			opts.setReplyTo(new EndpointReference(email_to));
			opts.setAction("urn:Registro");
			sc.setOptions(opts);

			OMElement om_registro = fac.createOMElement("Registro", omNs_aut);
			OMElement om_nick = fac.createOMElement("nick", "", "");
			OMElement om_password = fac.createOMElement("password", "", "");
			OMElement om_correo = fac.createOMElement("correo", "", "");
			om_nick.setText(nick);
			om_password.setText(password);
			om_correo.setText(correo);
			om_registro.addChild(om_nick);
			om_registro.addChild(om_password);
			om_registro.addChild(om_correo);

			sc.fireAndForget(om_registro);
			sc.cleanupTransport();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}

	//
	// FUNCIONES AÑADIDAS
	//

	private String call_login(String in_nick, String in_password) {
		ServiceClient sc;
		String respuesta = null;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR_autentificador);
			opts.setAction("urn:Login");
			sc.setOptions(opts);

			OMElement Login = fac.createOMElement("Login", omNs_aut);
			OMElement nick = fac.createOMElement("nick", "", "");
			OMElement password = fac.createOMElement("password", "", "");
			nick.setText(in_nick);
			password.setText(in_password);
			Login.addChild(nick);
			Login.addChild(password);

			sc.sendReceive(Login);
			respuesta = sc.getLastOperationContext().getMessageContext("In").getEnvelope().getHeader().getFirstElement()
					.getText();
			sc.cleanupTransport();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;
	}

	private boolean comprobarISBN(String isbn) {
		ServiceClient sc;
		boolean respuesta = false;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR_externo);
			opts.setAction("");
			sc.setOptions(opts);

			OMElement IsValidISBN13 = fac.createOMElement("IsValidISBN13", omNs_ext);
			OMElement sISBN = fac.createOMElement("sISBN", "", "");
			sISBN.setText(isbn);
			IsValidISBN13.addChild(sISBN);

			OMElement response = sc.sendReceive(IsValidISBN13);

			if (response.getFirstElement().getText().equals("true"))
				respuesta = true;
			else
				respuesta = false;
			sc.cleanupTransport();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;
	}

	private boolean call_obtenerLibro(String in_isbn) {
		ServiceClient sc;
		boolean respuesta = false;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR_gestor);
			opts.setAction("urn:obtenerLibro");
			sc.setOptions(opts);

			OMElement obtenerLibro = fac.createOMElement("obtenerLibro", omNs_gest);
			OMElement isbn = fac.createOMElement("ISBN", "", "");
			isbn.setText(in_isbn);
			obtenerLibro.addChild(isbn);

			OMElement response = sc.sendReceive(obtenerLibro);
			if (response.getFirstElement().getText().equals("true"))
				respuesta = true;
			else
				respuesta = false;
			sc.cleanupTransport();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;
	}

	/*
	 * private void comprobarISBN_async(String isbn, Callback callback) {
	 * ServiceClient sc; try { sc = new ServiceClient(); Options opts = new
	 * Options(); opts.setTo(targetEPR_externo); opts.setAction("");
	 * sc.setOptions(opts);
	 * 
	 * OMElement IsValidISBN13 = fac.createOMElement("IsValidISBN13", omNs_ext);
	 * OMElement sISBN = fac.createOMElement("sISBN", "", "");
	 * sISBN.setText(isbn); IsValidISBN13.addChild(sISBN);
	 * 
	 * sc.sendReceiveNonBlocking(IsValidISBN13, callback);
	 * sc.cleanupTransport(); } catch (AxisFault e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } }
	 */

	private void call_introducirLibros(String in_titulo, String in_isbn, int in_cantidad) {
		ServiceClient sc;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR_gestor);
			opts.setAction("urn:introducirLibros");
			sc.setOptions(opts);

			OMElement introducirLibros = fac.createOMElement("introducirLibros", omNs_gest);
			OMElement Titulo = fac.createOMElement("Titulo", "", "");
			OMElement ISBN = fac.createOMElement("ISBN", "", "");
			OMElement Cantidad = fac.createOMElement("Cantidad", "", "");
			Titulo.setText(in_titulo);
			ISBN.setText(in_isbn);
			Cantidad.setText(Integer.toString(in_cantidad));
			introducirLibros.addChild(Titulo);
			introducirLibros.addChild(ISBN);
			introducirLibros.addChild(Cantidad);

			sc.sendRobust(introducirLibros);
			sc.cleanupTransport();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	private String call_listarLibros() {
		ServiceClient sc;
		String respuesta = null;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR_gestor);
			opts.setAction("urn:listarLibros");
			sc.setOptions(opts);

			OMElement listarLibros = fac.createOMElement("listarLibros", omNs_gest);

			OMElement response = sc.sendReceive(listarLibros);

			respuesta = response.getFirstElement().getText();
			sc.cleanupTransport();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;
	}

	private String call_consultaTitulo(String in_titulo) {
		String respuesta = null;
		ServiceClient sc;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(targetEPR_gestor);
			opts.setAction("urn:consultaTitulo");
			sc.setOptions(opts);

			OMElement consultaTitulo = fac.createOMElement("consultaTitulo", omNs_gest);
			OMElement Titulo = fac.createOMElement("Titulo", "", "");
			Titulo.setText(in_titulo);
			consultaTitulo.addChild(Titulo);

			OMElement response = sc.sendReceive(consultaTitulo);

			respuesta = response.getFirstElement().getText();
			sc.cleanupTransport();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	public void shutDown(ConfigurationContext arg0, AxisService arg1) {
		// TODO Auto-generated method stub
		delete();
	}

	@Override
	public void startUp(ConfigurationContext arg0, AxisService arg1) {
		// TODO Auto-generated method stub
		iniciar();
		publish();
		find();
		System.out.println(targetEPR_autentificador);
		System.out.println(targetEPR_gestor);
	}

	private void iniciar() {
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
			publish = transport.getUDDIPublishService();
			inquiry = transport.getUDDIInquiryService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void publish() {
		try {

			// Login aka retrieve its authentication token
			GetAuthToken getAuthTokenMyPub = new GetAuthToken();
			getAuthTokenMyPub.setUserID("bob"); // your username
			getAuthTokenMyPub.setCred("bob"); // your password
			AuthToken myPubAuthToken = security.getAuthToken(getAuthTokenMyPub);
			System.out.println(getAuthTokenMyPub.getUserID() + "'s AUTHTOKEN = " + "******* never log auth tokens!");

			// Creating the parent business entity that will contain our
			// service.
			BusinessEntity myBusEntity = new BusinessEntity();
			Name myBusName = new Name();
			myBusName.setValue("My Business");
			myBusEntity.getName().add(myBusName);

			// Adding the business entity to the "save" structure, using our
			// publisher's authentication info and saving away.
			SaveBusiness sb = new SaveBusiness();
			sb.getBusinessEntity().add(myBusEntity);
			String AuthInfo = myPubAuthToken.getAuthInfo();
			sb.setAuthInfo(AuthInfo);
			BusinessDetail bd = publish.saveBusiness(sb);
			myBusKey = bd.getBusinessEntity().get(0).getBusinessKey();
			System.out.println("myBusiness key:  " + myBusKey);

			// Creating a service to save. Only adding the minimum data: the
			// parent business key retrieved from saving the business
			// above and a single name.
			BusinessService myService = new BusinessService();
			myService.setBusinessKey(myBusKey);
			Name myServName = new Name();
			myServName.setValue("ServicioCentral");
			myService.getName().add(myServName);

			// Add binding templates, etc...
			BindingTemplate myBindingTemplate = new BindingTemplate();
			AccessPoint accessPoint = new AccessPoint();
			accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
			accessPoint.setValue("http://localhost:8181/axis2/services/ServicioCentral?wsdl");
			myBindingTemplate.setAccessPoint(accessPoint);
			BindingTemplates myBindingTemplates = new BindingTemplates();
			// optional but recommended step, this annotations our binding with
			// all the standard SOAP tModel instance infos
			myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
			myBindingTemplates.getBindingTemplate().add(myBindingTemplate);

			myService.setBindingTemplates(myBindingTemplates);

			// Adding the service to the "save" structure, using our publisher's
			// authentication info and saving away.
			SaveService ss = new SaveService();
			ss.getBusinessService().add(myService);
			ss.setAuthInfo(myPubAuthToken.getAuthInfo());
			ServiceDetail sd = publish.saveService(ss);
			myServKey = sd.getBusinessService().get(0).getServiceKey();
			System.out.println("myService key:  " + myServKey);

			security.discardAuthToken(new DiscardAuthToken(myPubAuthToken.getAuthInfo()));
			// Now you have published a business and service via
			// the jUDDI API!
			System.out.println("Success!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void find() {
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
		Name s2 = new Name();
		s1.setValue("Autentificador");
		s2.setValue("GestorBiblio");
		FindService findService = new FindService();
		findService.setAuthInfo(AuthInfo3);
		findService.getName().add(s1);
		findService.getName().add(s2);
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

		for (ServiceInfo serviceInfo : serviceList.getServiceInfos().getServiceInfo()) {
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
			if(bs.getName().get(0).getValue().equals("GestorBiblio"))
				targetEPR_gestor = new EndpointReference(accesspoint);
			else if(bs.getName().get(0).getValue().equals("Autentificador"))
				targetEPR_autentificador = new EndpointReference(accesspoint);
		}
	}

	private void delete() {
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

		String AuthInfo2 = myPubAuthToken.getAuthInfo();

		// Eliminar Business
		DeleteBusiness db = new DeleteBusiness();
		db.setAuthInfo(AuthInfo2);
		db.getBusinessKey().add(myBusKey);
		try {
			publish.deleteBusiness(db);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to delete business " + myBusKey + " " + e.getMessage());
		}

		// Eliminar Servicio
		DeleteService ds = new DeleteService();
		ds.setAuthInfo(AuthInfo2);
		ds.getServiceKey().add(myServKey);
		try {
			publish.deleteService(ds);
		} catch (RemoteException e) {
			System.out.println("Failed to delete service " + myServKey + " " + e.getMessage());
		}
	}
}
