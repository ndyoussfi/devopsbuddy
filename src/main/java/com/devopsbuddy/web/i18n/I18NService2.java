package com.devopsbuddy.web.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.stereotype.Service;

import java.util.Locale;

// class is scanned and managed like a bean

//@Service // Spring Service
public class I18NService2 {
    
    /** The applicatin logger*/
    private static final Logger LOG = LoggerFactory.getLogger(I18NService2.class);

//    @Autowired // injecting messagesource bean using Annotation
    private MessageSource messageSource;

    /**
    * Returns a message for the given message id and default locale in the session ocntext
    * @param messageId The key the messages resource file
    **/
    public String getMessage(String messageId){
        LOG.info("Returning i18n text for messageId {}", messageId);
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(messageId, locale);
    }

    /**
     * Returns a message for the given message id and locale
     * @param messageId The key to the messages resource file
     * @param locale The locale
     **/
    public String getMessage(String messageId, Locale locale){
        return messageSource.getMessage(messageId, null, locale);
    }
}
