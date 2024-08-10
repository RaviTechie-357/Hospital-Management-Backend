package com.HMSApp.Hospital.Management.System.docloginController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HMSApp.Hospital.Management.System.doclogin.Repository.MedicineRepository;
import com.HMSApp.Hospital.Management.System.doclogin.entity.Medicine;

@CrossOrigin(origins = "http://localhost:4200" )
@RestController
@RequestMapping("/api/v3")
public class MedicineController {
	MedicineRepository medicineRepository;

	public MedicineController(MedicineRepository mediciniRepository) {
		super();
		this.medicineRepository = mediciniRepository;
	}
	
	@PostMapping("/insert")
	public Medicine createMedicine(@RequestBody Medicine medicine) {
		return medicineRepository.save(medicine);
	}
	
	@GetMapping("/medicines")
	public List<Medicine> getAllMedicine(){
		return medicineRepository.findAll();
	}

	@GetMapping("/medicines/{id}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable long id) throws AttributeNotFoundException{
		
	Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Attribute mot found : "+id)) ;
	
	return ResponseEntity.ok(medicine);
	}
	
	
	@PutMapping("/medicine/{id}")
	public ResponseEntity<Medicine> updateMedicine(@PathVariable long id,@RequestBody Medicine detailsMedicine) throws AttributeNotFoundException{
		
		Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Attribute mot found : "+id)) ;

		medicine.setDrugname(detailsMedicine.getDrugname());
		medicine.setStock(detailsMedicine.getStock());
		medicineRepository.save(medicine);
		return ResponseEntity.ok(medicine);

	}
	
	
	
	@DeleteMapping("/medicine/{id}")
	public ResponseEntity <Map<String, Boolean>> delete(@PathVariable long id) throws AttributeNotFoundException{
		
		Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Attribute mot found : "+id)) ;

		medicineRepository.delete(medicine);
		
		Map<String,Boolean> response = new HashMap<String,Boolean>();
		
		response.put("Delete", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
		
	}
	
	
	
	
	
	


}
