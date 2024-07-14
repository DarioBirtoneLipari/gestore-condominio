package com.bezkoder.springjwt.services.Impl;

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
        PaymentEntity pe = paymentMapper.dtoToEntity(payment);
		pe = paymentRepository.save(pe);
		return paymentMapper.entityToDto(pe);
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
