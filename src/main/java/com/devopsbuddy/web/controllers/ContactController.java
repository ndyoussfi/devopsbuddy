package com.devopsbuddy.web.controllers;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {

    /** The applicatin logger*/
    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    /** The key which identifies the feedback payload in the Model. */
    public static final String FEEDBACK_MODEL_KEY = "feedback";

    /** The Contact Us view name. */                // folder / view name
    private static final String CONTACT_US_VIEW_NAME = "contact/contact";


    /** Email service to inject into ContactController*/
    @Autowired
    private EmailService emailService;

    // defining method for get request (by default) is not necessary, however this requires more than 1 request type
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactGet(ModelMap model){
        FeedbackPojo feedbackPojo = new FeedbackPojo();
        model.addAttribute(ContactController.FEEDBACK_MODEL_KEY, feedbackPojo);
        return ContactController.CONTACT_US_VIEW_NAME;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactPost(@ModelAttribute(FEEDBACK_MODEL_KEY) FeedbackPojo feedback){
        LOG.debug("Feedback POJO content: {}", feedback);
        emailService.sendFeedbackEmail(feedback);
        return ContactController.CONTACT_US_VIEW_NAME;
    }
}
