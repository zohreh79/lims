package e.hospital.lims.controller;

import e.hospital.lims.model.LabTestRequestModel;
import e.hospital.lims.model.LabTestResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab")
public class LabTestController {

    @GetMapping("/list")
    public List<LabTestResponseModel> getLabTestList(@RequestParam(value = "topic", required = false) String topic) {
        return null;
    }

    @GetMapping()
    public LabTestResponseModel getLabTestById(@RequestParam(value = "id") Long id) {
        return null;
    }

    @PostMapping("/set-result")
    public ResponseEntity<String> setLabTestResult(@RequestBody LabTestResponseModel labTestResponseModel){
        return null;
    }

    @PostMapping("/set-test")
    public ResponseEntity<String> setLabTest(@RequestBody LabTestRequestModel labTestRequestModel){
        return null;
    }

}
