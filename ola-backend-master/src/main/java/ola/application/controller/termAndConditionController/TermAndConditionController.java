package ola.application.controller.termAndConditionController;

import ola.application.dto.termAndCondition.TermAndConditionDto;
import ola.application.exception.BadRequestException;
import ola.application.services.termAndConditionService.TermAndConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/u/t&c")
public class TermAndConditionController {

    @Autowired
    private TermAndConditionService termAndConditionService;

    // Endpoint to get all terms and conditions
    @GetMapping("/all")
    public ResponseEntity<List<TermAndConditionDto>> getAllTermsAndConditions() {
        return ResponseEntity.ok(termAndConditionService.getAllTermsAndConditions());
    }

    // Endpoint to get a specific term and condition by ID
    @GetMapping("/{id}")
    public ResponseEntity<TermAndConditionDto> getTermAndConditionById(@PathVariable int id) {
       return ResponseEntity.ok(termAndConditionService.getTermAndConditionById(id));
    }

    // Endpoint to update a term and condition
    @PutMapping("/{id}")
    public ResponseEntity<TermAndConditionDto> updateTermAndCondition(@PathVariable int id, @RequestBody TermAndConditionDto termAndConditionDto) {
       return ResponseEntity.ok(termAndConditionService.updateTermAndCondition(id,termAndConditionDto));
    }

    // Endpoint to generate PDF for all terms and conditions
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generateTermsAndConditionsPDF() {
        byte[] pdfContent = termAndConditionService.generateTermsAndConditionsPDF();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdfContent);
    }
}
