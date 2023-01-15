package com.wilkwm.pracainz.domain.commission;

import com.wilkwm.pracainz.domain.commission.Commission;
import com.wilkwm.pracainz.domain.commission.dto.CommissionDto;

public class CommissionDtoMapper {
    public static CommissionDto map(Commission commission) {
        return new CommissionDto(
                commission.getId(),
                commission.getName(),
                commission.getDescription(),
                commission.getTimeNeeded(),
                commission.getPreferredCooperation(),
                commission.getPricingFrom(),
                commission.getPricingTo(),
                commission.isAvailability(),
                commission.isJobOffer(),
                commission.getUser().getName(),
                commission.getField().getName()
        );
    }
}