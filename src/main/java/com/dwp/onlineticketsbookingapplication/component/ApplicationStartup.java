package com.dwp.onlineticketsbookingapplication.component;

import com.dwp.onlineticketsbookingapplication.enums.TicketTypesEnum;
import com.dwp.onlineticketsbookingapplication.model.TicketsTypeModel;
import com.dwp.onlineticketsbookingapplication.service.TicketsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    
    @Autowired
    TicketsTypeService ticketsTypeService;

    Logger logger = Logger.getLogger(ApplicationStartup.class.getName());

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        logger.info("System Initialization : Add Tickets Type (Infant, Child, Adult) with their price specification .");

        final TicketsTypeModel ticketsTypeModel1 = new TicketsTypeModel(null,TicketTypesEnum.INFANT, 0.00);
        final TicketsTypeModel ticketsTypeModel2 = new TicketsTypeModel(null,TicketTypesEnum.CHILD, 10.00);
        final TicketsTypeModel ticketsTypeModel3 = new TicketsTypeModel(null,TicketTypesEnum.ADULT, 20.00);

        ticketsTypeService.saveAllTicketsType(List.of(ticketsTypeModel1, ticketsTypeModel2, ticketsTypeModel3));

    }
}
