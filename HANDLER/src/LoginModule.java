import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisDescription;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.modules.Module;
import org.apache.neethi.Assertion;
import org.apache.neethi.Policy;

public class LoginModule implements Module {

	public LoginModule() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyPolicy(Policy arg0, AxisDescription arg1) throws AxisFault {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canSupportAssertion(Assertion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void engageNotify(AxisDescription arg0) throws AxisFault {
		if(arg0 instanceof AxisService) {
			AxisService servicio = (AxisService) arg0;
			System.out.println("[LoginModule] => Se ha enlazado al servicio: " + servicio.getName());
		} else if(arg0 instanceof AxisOperation) {
			AxisOperation operacion = (AxisOperation) arg0;
			System.out.println("[LoginModule] => Se ha enlazado a la operación: " + operacion.getName().getLocalPart());
		}
	}

	@Override
	public void init(ConfigurationContext arg0, AxisModule arg1) throws AxisFault {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown(ConfigurationContext arg0) throws AxisFault {
		// TODO Auto-generated method stub

	}


}
