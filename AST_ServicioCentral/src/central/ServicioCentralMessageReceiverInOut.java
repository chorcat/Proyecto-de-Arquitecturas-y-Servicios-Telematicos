/**
 * ServicioCentralMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */
package central;


/**
 *  ServicioCentralMessageReceiverInOut message receiver
 */
public class ServicioCentralMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext,
        org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            ServicioCentralSkeleton skel = (ServicioCentralSkeleton) obj;

            //Out Envelop
            org.apache.axiom.soap.SOAPEnvelope envelope = null;

            //Find the axisOperation that has been set by the Dispatch phase.
            org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext()
                                                                      .getAxisOperation();

            if (op == null) {
                throw new org.apache.axis2.AxisFault(
                    "Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }

            java.lang.String methodName;

            if ((op.getName() != null) &&
                    ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(
                            op.getName().getLocalPart())) != null)) {
                if ("login".equals(methodName)) {
                    central.LoginResponse loginResponse24 = null;
                    central.Login wrappedParam = (central.Login) fromOM(msgContext.getEnvelope()
                                                                                  .getBody()
                                                                                  .getFirstElement(),
                            central.Login.class);

                    loginResponse24 = skel.login(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            loginResponse24, false,
                            new javax.xml.namespace.QName("http://central",
                                "LoginResponse"));
                } else if ("listarLibros".equals(methodName)) {
                    central.ListarLibrosResponse listarLibrosResponse26 = null;
                    central.ListarLibros wrappedParam = (central.ListarLibros) fromOM(msgContext.getEnvelope()
                                                                                                .getBody()
                                                                                                .getFirstElement(),
                            central.ListarLibros.class);

                    listarLibrosResponse26 = skel.listarLibros(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            listarLibrosResponse26, false,
                            new javax.xml.namespace.QName("http://central",
                                "listarLibrosResponse"));
                } else if ("obtenerLibro".equals(methodName)) {
                    central.ObtenerLibroResponse obtenerLibroResponse28 = null;
                    central.ObtenerLibro wrappedParam = (central.ObtenerLibro) fromOM(msgContext.getEnvelope()
                                                                                                .getBody()
                                                                                                .getFirstElement(),
                            central.ObtenerLibro.class);

                    obtenerLibroResponse28 = skel.obtenerLibro(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            obtenerLibroResponse28, false,
                            new javax.xml.namespace.QName("http://central",
                                "obtenerLibroResponse"));
                } else if ("consultaTitulo".equals(methodName)) {
                    central.ConsultaTituloResponse consultaTituloResponse30 = null;
                    central.ConsultaTitulo wrappedParam = (central.ConsultaTitulo) fromOM(msgContext.getEnvelope()
                                                                                                    .getBody()
                                                                                                    .getFirstElement(),
                            central.ConsultaTitulo.class);

                    consultaTituloResponse30 = skel.consultaTitulo(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            consultaTituloResponse30, false,
                            new javax.xml.namespace.QName("http://central",
                                "consultaTituloResponse"));
                } else if ("introducirLibros".equals(methodName)) {
                    central.IntroducirLibrosResponse introducirLibrosResponse32 = null;
                    central.IntroducirLibros wrappedParam = (central.IntroducirLibros) fromOM(msgContext.getEnvelope()
                                                                                                        .getBody()
                                                                                                        .getFirstElement(),
                            central.IntroducirLibros.class);

                    introducirLibrosResponse32 = skel.introducirLibros(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            introducirLibrosResponse32, false,
                            new javax.xml.namespace.QName("http://central",
                                "introducirLibrosResponse"));
                } else {
                    throw new java.lang.RuntimeException("method not found");
                }

                newMsgContext.setEnvelope(envelope);
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    //
    private org.apache.axiom.om.OMElement toOM(central.Login param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.Login.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(central.LoginResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.LoginResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(central.ListarLibros param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.ListarLibros.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        central.ListarLibrosResponse param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.ListarLibrosResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(central.ObtenerLibro param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.ObtenerLibro.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        central.ObtenerLibroResponse param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.ObtenerLibroResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(central.ConsultaTitulo param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.ConsultaTitulo.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        central.ConsultaTituloResponse param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.ConsultaTituloResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(central.IntroducirLibros param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.IntroducirLibros.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        central.IntroducirLibrosResponse param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.IntroducirLibrosResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(central.Registro param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(central.Registro.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory, central.LoginResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    central.LoginResponse.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private central.LoginResponse wrapLogin() {
        central.LoginResponse wrappedElement = new central.LoginResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        central.ListarLibrosResponse param, boolean optimizeContent,
        javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    central.ListarLibrosResponse.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private central.ListarLibrosResponse wraplistarLibros() {
        central.ListarLibrosResponse wrappedElement = new central.ListarLibrosResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        central.ObtenerLibroResponse param, boolean optimizeContent,
        javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    central.ObtenerLibroResponse.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private central.ObtenerLibroResponse wrapobtenerLibro() {
        central.ObtenerLibroResponse wrappedElement = new central.ObtenerLibroResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        central.ConsultaTituloResponse param, boolean optimizeContent,
        javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    central.ConsultaTituloResponse.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private central.ConsultaTituloResponse wrapconsultaTitulo() {
        central.ConsultaTituloResponse wrappedElement = new central.ConsultaTituloResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        central.IntroducirLibrosResponse param, boolean optimizeContent,
        javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    central.IntroducirLibrosResponse.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private central.IntroducirLibrosResponse wrapintroducirLibros() {
        central.IntroducirLibrosResponse wrappedElement = new central.IntroducirLibrosResponse();

        return wrappedElement;
    }

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type) throws org.apache.axis2.AxisFault {
        try {
            if (central.ConsultaTitulo.class.equals(type)) {
                return central.ConsultaTitulo.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (central.ConsultaTituloResponse.class.equals(type)) {
                return central.ConsultaTituloResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (central.IntroducirLibros.class.equals(type)) {
                return central.IntroducirLibros.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (central.IntroducirLibrosResponse.class.equals(type)) {
                return central.IntroducirLibrosResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (central.ListarLibros.class.equals(type)) {
                return central.ListarLibros.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (central.ListarLibrosResponse.class.equals(type)) {
                return central.ListarLibrosResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (central.Login.class.equals(type)) {
                return central.Login.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (central.LoginResponse.class.equals(type)) {
                return central.LoginResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (central.ObtenerLibro.class.equals(type)) {
                return central.ObtenerLibro.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (central.ObtenerLibroResponse.class.equals(type)) {
                return central.ObtenerLibroResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (central.Registro.class.equals(type)) {
                return central.Registro.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();

        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }
} //end of class
