package com.dwp.onlineticketsbookingapplication.service;

import com.dwp.onlineticketsbookingapplication.entity.TicketsType;
import com.dwp.onlineticketsbookingapplication.model.TicketsTypeModel;
import com.dwp.onlineticketsbookingapplication.repository.TicketsTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketsTypeService {
    @Autowired
    TicketsTypeRepository ticketsTypeRepository;

    public void saveAllTicketsType(final List<TicketsTypeModel> ticketsTypeModels){
        final List<TicketsType> ticketsTypeEntities = ticketsTypeModels.stream().map(value -> {
            TicketsType ticketsType = new TicketsType();
            BeanUtils.copyProperties(value, ticketsType);
            return ticketsType;
        }).collect(Collectors.toList());
        ticketsTypeRepository.saveAll(ticketsTypeEntities);
    }

    public List<TicketsTypeModel> getAllTicketsType(){
        final List<TicketsType> ticketsTypeEntities = ticketsTypeRepository.findAll();
        final List<TicketsTypeModel> ticketsTypeModels = ticketsTypeEntities.stream().map(ticketsTypeEntity -> {
            TicketsTypeModel ticketsTypeModel = new TicketsTypeModel();
            BeanUtils.copyProperties(ticketsTypeEntity, ticketsTypeModel);
            return ticketsTypeModel;
        }).collect(Collectors.toList());
        return ticketsTypeModels;
    }
}
