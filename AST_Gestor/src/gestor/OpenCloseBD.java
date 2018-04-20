package gestor;

import java.rmi.RemoteException;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;

import org.uddi.api_v3.*;
import org.apache.juddi.api_v3.*;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.v3_service.UDDISecurityPortType;
import org.uddi.v3_service.UDDIPublicationPortType;

public class OpenCloseBD implements ServiceLifeCycle {

	private static UDDISecurityPortType security = null;
	private static UDDIPublicationPortType publish = null;
	private static String myBusKey;
	private static String AuthInfo;
	private static String myServKey;

	public OpenCloseBD() {

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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void shutDown(ConfigurationContext arg0, AxisService arg1) {
		// TODO Auto-generated method stub
		System.out.println("Se cierrra el servicio -> " + arg1.getName());
		ObjectContainer db = (ObjectContainer) arg0.getProperty("db_conexion_biblio");
		db.close();
		delete();
	}

	@Override
	public void startUp(ConfigurationContext arg0, AxisService arg1) {
		System.out.println("Se ha iniciado el servicio -> " + arg1.getName());
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Biblioteca.db4o");
		arg0.setProperty("db_conexion_biblio", db);

		OpenCloseBD sp = new OpenCloseBD();
		sp.publish();
	}

	/**
	 * This function shows you how to publish to UDDI using a fairly generic
	 * mechanism that should be portable (meaning use any UDDI v3 library with
	 * this code)
	 */
	public void publish() {
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
			AuthInfo = myPubAuthToken.getAuthInfo();
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
			myServName.setValue("GestorBiblio");
			myService.getName().add(myServName);

			// Add binding templates, etc...
			BindingTemplate myBindingTemplate = new BindingTemplate();
			AccessPoint accessPoint = new AccessPoint();
			accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
			accessPoint.setValue("http://localhost:8181/axis2/services/GestorBiblio?wsdl");
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
	
	public void delete() {
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
