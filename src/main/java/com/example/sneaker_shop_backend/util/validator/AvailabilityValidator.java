package com.example.sneaker_shop_backend.util.validator;

import com.example.sneaker_shop_backend.domain.entity.item.Availability;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AvailabilityValidator {
    public boolean validate(Integer size, Integer amount, List<Availability> availabilities){

        boolean hasSizeAvailable = availabilities.stream().anyMatch(availability
                -> Objects.equals(availability.getSize(), size));

        if(!hasSizeAvailable){
            return false;
        }

        boolean hasEnoughAmount = availabilities.stream().anyMatch(availability
                -> Objects.equals(availability.getSize(), size) &&
                availability.getAmount() - amount >= 0);

        return hasEnoughAmount;
    }
}
