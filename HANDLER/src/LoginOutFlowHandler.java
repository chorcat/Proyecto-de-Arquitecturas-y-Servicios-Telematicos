import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.handlers.AbstractHandler;

public class LoginOutFlowHandler extends AbstractHandler {
	@Override
	public InvocationResponse invoke(MessageContext arg0) throws AxisFault {
		SOAPEnvelope soapEnvelope = arg0.getEnvelope();
		String login = arg0.getServiceContext().getProperty("login").toString();
		OMNamespace omNs = OMAbstractFactory.getOMFactory().createOMNamespace("http://Login","ns1");
		SOAPHeaderBlock header = soapEnvelope.getHeader().addHeaderBlock("login", omNs);
		header.setText(login);
		return InvocationResponse.CONTINUE;
	}
}
