package com.nojac.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by nickolas on 5/30/17.
 */
@Controller
public class CalendarController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("jsoncalendar");
    }

}