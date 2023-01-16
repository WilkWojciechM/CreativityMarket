package com.wilkwm.pracainz.domain.commission;

import com.wilkwm.pracainz.domain.commission.dto.CommissionDto;
import com.wilkwm.pracainz.domain.field.Field;
import com.wilkwm.pracainz.domain.field.FieldDtoMapper;
import com.wilkwm.pracainz.domain.field.FieldRepository;
import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import com.wilkwm.pracainz.domain.project.ProjectDtoMapper;
import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import com.wilkwm.pracainz.domain.user.User;
import com.wilkwm.pracainz.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommissionService {

    private final CommissionRepository commissionRepository;
    private final FieldRepository fieldRepository;
    private final UserRepository userRepository;

    public CommissionService(CommissionRepository commissionRepository, FieldRepository fieldRepository, UserRepository userRepository) {
        this.commissionRepository = commissionRepository;
        this.fieldRepository = fieldRepository;
        this.userRepository = userRepository;
    }

    public Optional<CommissionDto> findCommissionById(long id){
        return commissionRepository.findById(id).map(CommissionDtoMapper::map);
    }
    public List<CommissionDto> findAllCommissions(){
        return StreamSupport.stream(commissionRepository.findAll().spliterator(), false)
                .map(CommissionDtoMapper::map)
                .toList();
    }
    public List<CommissionDto> findCommissionsByFieldName(String field) {
        return commissionRepository.findAllByField_NameIgnoreCase(field).stream()
                .map(CommissionDtoMapper::map)
                .toList();
    }
    public List<CommissionDto> findCommissionsByCreatorName(String name) {
        return commissionRepository.findAllByUser_Name(name).stream()
                .map(CommissionDtoMapper::map)
                .toList();
    }

    public List<CommissionDto> findAllAvailableCommissionsByEmployer(){
        return commissionRepository.findAllByAvailabilityIsTrueAndJobOfferIsTrue().stream().map(CommissionDtoMapper::map).toList();
    }

    public List<CommissionDto> findAllAvailableCommissionsByCreator(){
        return commissionRepository.findAllByAvailabilityIsTrueAndJobOfferIsFalse().stream().map(CommissionDtoMapper::map).toList();
    }

    public List<CommissionDto> findCommissionsByTimeNeededLessThanEqual(Integer time){
        return commissionRepository.findAllByTimeNeededLessThanEqual(time).stream()
                .map(CommissionDtoMapper::map)
                .toList();
    }

    public List<CommissionDto> findCommissionsByPriceToLessThanEqual(BigDecimal price){
        return commissionRepository.findAllByPricingToLessThanEqual(price).stream().map(CommissionDtoMapper::map).toList();
    }

    public List<CommissionDto> findCommissionsByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword) {
        return commissionRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword)
                .stream()
                .map(CommissionDtoMapper::map)
                .toList();
    }

    public void addCommission(CommissionDto saveCommission) {
        Commission commission = new Commission();
        commissionHandling(saveCommission, commission);

        Field field = fieldRepository.findByNameIgnoreCase(saveCommission.getField()).orElseThrow();
        commission.setField(field);

        User user = userRepository.findByName(saveCommission.getUser()).orElseThrow();
        commission.setUser(user);

        commissionRepository.save(commission);
    }
    public void updateCommission(Long id, CommissionDto commissionDto) {
        Commission commission = commissionRepository.findById(id).orElseThrow();
        commissionHandling(commissionDto, commission);
        commission.setField(fieldRepository.findByNameIgnoreCase(commissionDto.getField()).orElseThrow());
        commissionRepository.save(commission);
    }

    private void commissionHandling(CommissionDto commissionDto, Commission commission) {
        commission.setName(commissionDto.getName());
        commission.setDescription(commissionDto.getDescription());
        commission.setTimeNeeded(commissionDto.getTimeNeeded());
        commission.setPreferredCooperation(commissionDto.getPreferredCooperation());
        commission.setPricingFrom(commissionDto.getPricingFrom());
        commission.setPricingTo(commissionDto.getPricingTo());
        commission.setAvailability(commissionDto.isAvailability());
        commission.setJobOffer(commissionDto.isJobOffer());
    }

    public void deleteCommission(Long id){
        commissionRepository.deleteById(id);
    }
}
