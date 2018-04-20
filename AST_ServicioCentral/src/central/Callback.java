package central;


import org.apache.axiom.om.OMElement;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.MessageContext;

public class Callback implements AxisCallback {
	
    private boolean complete = false;
    private MessageContext msg;

	public Callback() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onComplete() {
		System.out.println( "Message transmission complete") ;
        complete = true;
	}
	
	public boolean isComplete() {
        return complete;
    }

	@Override
	public void onError(Exception arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFault(MessageContext arg0) {
		System.out.println( "Call Back got Fault: " + arg0.getEnvelope() ) ;
	}

	@Override
	public void onMessage(MessageContext arg0) {
		System.out.println( "Call Back got Result: " + arg0.getEnvelope() ) ;
	    msg = arg0;
	}
	
	public OMElement getResponse() {
    	OMElement response = msg.getEnvelope().getBody().getFirstElement();
    	return response;
    }
}
