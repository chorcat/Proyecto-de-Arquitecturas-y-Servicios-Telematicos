/**
 * GestorBiblioMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.0  Built on : Jan 18, 2016 (09:41:27 GMT)
 */
package gestor;


/**
 *  GestorBiblioMessageReceiverInOut message receiver
 */
public class GestorBiblioMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext,
        org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            GestorBiblioSkeleton skel = (GestorBiblioSkeleton) obj;

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
                if ("consultaTitulo".equals(methodName)) {
                    gestor.ConsultaTituloResponse consultaTituloResponse16 = null;
                    gestor.ConsultaTitulo wrappedParam = (gestor.ConsultaTitulo) fromOM(msgContext.getEnvelope()
                                                                                                  .getBody()
                                                                                                  .getFirstElement(),
                            gestor.ConsultaTitulo.class);

                    consultaTituloResponse16 = skel.consultaTitulo(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            consultaTituloResponse16, false,
                            new javax.xml.namespace.QName("http://gestor",
                                "consultaTituloResponse"));
                } else
                 if ("obtenerLibro".equals(methodName)) {
                    gestor.ObtenerLibroResponse obtenerLibroResponse18 = null;
                    gestor.ObtenerLibro wrappedParam = (gestor.ObtenerLibro) fromOM(msgContext.getEnvelope()
                                                                                              .getBody()
                                                                                              .getFirstElement(),
                            gestor.ObtenerLibro.class);

                    obtenerLibroResponse18 = skel.obtenerLibro(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            obtenerLibroResponse18, false,
                            new javax.xml.namespace.QName("http://gestor",
                                "obtenerLibroResponse"));
                } else
                 if ("listarLibros".equals(methodName)) {
                    gestor.ListarLibrosResponse listarLibrosResponse20 = null;
                    gestor.ListarLibros wrappedParam = (gestor.ListarLibros) fromOM(msgContext.getEnvelope()
                                                                                              .getBody()
                                                                                              .getFirstElement(),
                            gestor.ListarLibros.class);

                    listarLibrosResponse20 = skel.listarLibros(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            listarLibrosResponse20, false,
                            new javax.xml.namespace.QName("http://gestor",
                                "listarLibrosResponse"));
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
    private org.apache.axiom.om.OMElement toOM(gestor.ConsultaTitulo param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(gestor.ConsultaTitulo.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        gestor.ConsultaTituloResponse param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(gestor.ConsultaTituloResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(gestor.IntroducirLibros param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(gestor.IntroducirLibros.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(gestor.ObtenerLibro param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(gestor.ObtenerLibro.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        gestor.ObtenerLibroResponse param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(gestor.ObtenerLibroResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(gestor.ListarLibros param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(gestor.ListarLibros.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        gestor.ListarLibrosResponse param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(gestor.ListarLibrosResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        gestor.ConsultaTituloResponse param, boolean optimizeContent,
        javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    gestor.ConsultaTituloResponse.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private gestor.ConsultaTituloResponse wrapconsultaTitulo() {
        gestor.ConsultaTituloResponse wrappedElement = new gestor.ConsultaTituloResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        gestor.ObtenerLibroResponse param, boolean optimizeContent,
        javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    gestor.ObtenerLibroResponse.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private gestor.ObtenerLibroResponse wrapobtenerLibro() {
        gestor.ObtenerLibroResponse wrappedElement = new gestor.ObtenerLibroResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        gestor.ListarLibrosResponse param, boolean optimizeContent,
        javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    gestor.ListarLibrosResponse.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private gestor.ListarLibrosResponse wraplistarLibros() {
        gestor.ListarLibrosResponse wrappedElement = new gestor.ListarLibrosResponse();

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
            if (gestor.ConsultaTitulo.class.equals(type)) {
                return gestor.ConsultaTitulo.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (gestor.ConsultaTituloResponse.class.equals(type)) {
                return gestor.ConsultaTituloResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (gestor.IntroducirLibros.class.equals(type)) {
                return gestor.IntroducirLibros.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (gestor.ListarLibros.class.equals(type)) {
                return gestor.ListarLibros.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (gestor.ListarLibrosResponse.class.equals(type)) {
                return gestor.ListarLibrosResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (gestor.ObtenerLibro.class.equals(type)) {
                return gestor.ObtenerLibro.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (gestor.ObtenerLibroResponse.class.equals(type)) {
                return gestor.ObtenerLibroResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
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
