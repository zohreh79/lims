package e.hospital.lims.controller;

import e.hospital.lims.model.LabTestModel;
import e.hospital.lims.model.UpdateStatusModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab")
public class LabTestController {

    // todo : need to add its services
    @GetMapping("/list")
    public List<LabTestModel> getLabTestList() {
        return null;
    }

    @GetMapping()
    public LabTestModel getLabTestById(@RequestParam(value = "id") Long id) {
        return null;
    }


    @PostMapping("/set-result")
    public ResponseEntity<String> setLabTestResult(@RequestBody LabTestModel labTestModel) {
        return null;
    }

    @PostMapping("/set-test")
    public ResponseEntity<String> setLabTest(@RequestBody LabTestModel labTestRequestModel) {
        return null;
    }

    @PutMapping("/update-status")
    public ResponseEntity<String> updateStatus(@RequestBody UpdateStatusModel model) {
        return null;
    }

}
