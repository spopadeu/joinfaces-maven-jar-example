package org.joinfaces.example.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MessageUtil {

    public static void addInfoMessage(String str) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle =
                context.getApplication().getResourceBundle(context, "msg");
        String message = bundle.getString(str);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }

    public static void addInfoMessage(String msgKey, Object[] params) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle =
                context.getApplication().getResourceBundle(context, "msg");
        String message = bundle.getString(msgKey);
        String result = MessageFormat.format(
                message, params);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, result, ""));
    }
}
