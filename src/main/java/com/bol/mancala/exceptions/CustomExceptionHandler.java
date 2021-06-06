package com.bol.mancala.exceptions;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import java.util.Iterator;

/**
 * We can handle all unexpected exceptions with this class.
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {
    private ExceptionHandler wrapped;
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * We can catch the all unexpected exceptions with this method.
     * @throws FacesException
     */
    @Override
    public void handle() throws FacesException {
        for (Iterator<ExceptionQueuedEvent> iter = getUnhandledExceptionQueuedEvents().iterator(); iter.hasNext();) {
            Throwable exception = iter.next().getContext().getException();
            PrimeFaces.current().ajax().update("dialogFrm:unhandledWarnDlg");
            PrimeFaces.current().executeScript("PF('unhandledWarnDlg').show();");
            logger.error("There is an unhandled exception!");
        }
        getWrapped().handle();
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

}