package com.bezkoder.springjwt.services.Impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.entity.PaymentEntity;
import com.bezkoder.springjwt.mapper.PaymentMapper;
import com.bezkoder.springjwt.models.PaymentDTO;
import com.bezkoder.springjwt.repository.PaymentRepository;
import com.bezkoder.springjwt.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
	PaymentRepository paymentRepository;

	@Autowired
	PaymentMapper paymentMapper;
    
    
    @Override
    public PaymentDTO newPayment(PaymentDTO payment) {
        PaymentEntity pe = paymentMapper.dtoToEntity(payment);
		pe = paymentRepository.save(pe);
		return paymentMapper.entityToDto(pe);
    }

    @Override
    public PaymentDTO update(PaymentDTO payment) {
        PaymentEntity oe = paymentMapper.dtoToEntity(payment); // utente con alcuni campi null
        PaymentEntity entity = paymentMapper.dtoToEntity(getPaymentById(oe.getId())); // utente con tutti i campi non nulli
        List<String> stringFields = Arrays.asList("description", "creditCard");
        List<String> intFields = Arrays.asList("total");
        List<String> booleanFields = Arrays.asList("isPaid,ongoing");
        //TODO date type and external key ?
        for (Field field : PaymentEntity.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (stringFields.contains(field.getName())) {
                    String value = (String) field.get(oe);
                    if (value != null && value.length() > 0) {
                        field.set(entity, value);
                    }
                } else if (intFields.contains(field.getName())) {
                    int value = (int) field.get(oe);
                    if (value > 0) {
                        field.set(entity, value);
                    }
                }else if (booleanFields.contains(field.getName())) {
                    boolean value = (boolean) field.get(oe);
                    field.set(entity, value);}
                    
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        entity = paymentRepository.save(entity);
        return paymentMapper.entityToDto(entity);
    }

    @Override
    public boolean deletePaymentById(String id) {
        if(paymentRepository.existsById(id)) {
			paymentRepository.deleteById(id);
			return true;
		}
		
		return false;
	}

    @Override
    public PaymentDTO getPaymentById(String id) {
        Optional<PaymentEntity> pe = paymentRepository.findById(id);
		if(pe.isPresent()) {
			return paymentMapper.entityToDto(pe.get());
		} else {
            PaymentDTO payment = new PaymentDTO();
			return payment;
		}
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
				.stream().map(paymentMapper::entityToDto)
				.toList();
    }

     @Override
    public List<PaymentDTO> getAllPaymentsByHouseId(String houseId) {
        return paymentRepository.findByHouseId(houseId)
        .stream().map(paymentMapper::entityToDto)
        .toList();
    }

     @Override
    public List<PaymentDTO> getAllPaymentsByUserId(String userId) {
        return paymentRepository.findByHouseId(userId)
        .stream().map(paymentMapper::entityToDto)
        .toList();
    }
    
  @Override
    public boolean checkValue(PaymentDTO payment) {
        //Date today = new Date();
        if (payment.getHouse().getId().length()>0 &&
            payment.getCreditCard().length()>15 &&
            payment.getUser().getId().length()>0)
           {
                return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean checkId(String id) {
        if(id.length()>0 && paymentRepository.existsById(id))
        {return true;}
        else
        {return false;}
    
    }

}
