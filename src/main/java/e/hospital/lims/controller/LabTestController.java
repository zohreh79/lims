package e.hospital.lims.controller;

import e.hospital.lims.model.LabTestModel;
import e.hospital.lims.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab")
public class LabTestController {
    @Autowired
    private LabTestService labTestService;

    @GetMapping("/list")
    public List<LabTestModel> getLabTestList() {
        return labTestService.getAllTests();
    }

    @GetMapping()
    public LabTestModel getLabTestById(@RequestParam(value = "id") Long id) {
        return labTestService.getTestResult(id);
    }

    @PutMapping("/set-result")
    public ResponseEntity<String> updateLabTestResult(@RequestBody LabTestModel labTestRequestModel) {
        labTestService.updateTestResult(labTestRequestModel);
        return ResponseEntity.ok("test updated!");
    }

    @PostMapping("/set-result")
    public ResponseEntity<String> setLabTestResult(@RequestBody LabTestModel labTestModel) {
        labTestService.setTestResult(labTestModel);
        return ResponseEntity.ok("test result sent!");
    }

    @PostMapping("/set-test")
    public ResponseEntity<String> setLabTest(@RequestBody LabTestModel labTestRequestModel) {
        labTestService.setTest(labTestRequestModel);
        return ResponseEntity.ok("test request sent!");
    }

}
