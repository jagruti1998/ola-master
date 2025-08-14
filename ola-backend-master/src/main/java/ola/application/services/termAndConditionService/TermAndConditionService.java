package ola.application.services.termAndConditionService;

import ola.application.configuration.PDFGenerator;
import ola.application.dto.termAndCondition.TermAndConditionDto;
import ola.application.entity.OlaTermAndCondition;
import ola.application.exception.BadRequestException;
import ola.application.repository.TermAndConditionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TermAndConditionService{

    @Autowired
    private TermAndConditionRepo termAndConditionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PDFGenerator pdfGenerator;

    // Method to retrieve all terms and conditions
    public List<TermAndConditionDto> getAllTermsAndConditions() {
        List<OlaTermAndCondition> termsAndConditions = termAndConditionRepository.findAll();
        return termsAndConditions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Method to retrieve a specific term and condition by ID
    public TermAndConditionDto getTermAndConditionById(int id) throws BadRequestException {
        OlaTermAndCondition termAndCondition = termAndConditionRepository.findById(id).orElseThrow(()->new BadRequestException(401, "term and condition not found") );
        return convertToDTO(termAndCondition);
    }

    // Method to update a term and condition
    public TermAndConditionDto updateTermAndCondition(int id, TermAndConditionDto termAndConditionDTO) throws BadRequestException {
        OlaTermAndCondition existingTermAndCondition = termAndConditionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(401, "Term and condition not found with id: " + id));

        // Update fields
        existingTermAndCondition.setGeneralTerms(termAndConditionDTO.getGeneralTerms());
        existingTermAndCondition.setBookingAndReservations(termAndConditionDTO.getBookingAndReservations());
        existingTermAndCondition.setPassengerResponsibilities(termAndConditionDTO.getPassengerResponsibilities());
        existingTermAndCondition.setDriverResponsibilities(termAndConditionDTO.getDriverResponsibilities());
        existingTermAndCondition.setFaresAndPayments(termAndConditionDTO.getFaresAndPayments());
        existingTermAndCondition.setCancellationsAndRefunds(termAndConditionDTO.getCancellationsAndRefunds());
        existingTermAndCondition.setLiability(termAndConditionDTO.getLiability());
        existingTermAndCondition.setComplaintsAndDisputes(termAndConditionDTO.getComplaintsAndDisputes());
        existingTermAndCondition.setAmendments(termAndConditionDTO.getAmendments());
        existingTermAndCondition.setPrivacyPolicy(termAndConditionDTO.getPrivacyPolicy());
        existingTermAndCondition.setContactInformation(termAndConditionDTO.getContactInformation());
        existingTermAndCondition.setEffectiveDate(termAndConditionDTO.getEffectiveDate());
        existingTermAndCondition.setVersion(termAndConditionDTO.getVersion());
        existingTermAndCondition.setLastUpdated(LocalDateTime.now());  // Updating last updated timestamp

        OlaTermAndCondition updatedTermAndCondition = termAndConditionRepository.save(existingTermAndCondition);
        return convertToDTO(updatedTermAndCondition);
    }


    // Method to generate PDF for all terms and conditions
    public byte[] generateTermsAndConditionsPDF() {
        List<OlaTermAndCondition> termsAndConditions = termAndConditionRepository.findAll();
        return pdfGenerator.generatePDF(termsAndConditions);
    }

    // Helper method to convert Entity to DTO
    private TermAndConditionDto convertToDTO(OlaTermAndCondition termAndCondition) {
        return modelMapper.map(termAndCondition, TermAndConditionDto.class);
    }

    // Helper method to convert DTO to Entity
    private OlaTermAndCondition convertToEntity(TermAndConditionDto termAndConditionDTO) {
        return modelMapper.map(termAndConditionDTO, OlaTermAndCondition.class);
    }
}
