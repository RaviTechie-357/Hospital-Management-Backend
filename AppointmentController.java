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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HMSApp.Hospital.Management.System.doclogin.Repository.AppointmentRepository;
import com.HMSApp.Hospital.Management.System.doclogin.entity.Appointment;

@CrossOrigin(origins = "http://localhost:4200" )
@RestController
@RequestMapping("/api/v2")
public class AppointmentController {
	AppointmentRepository appointmentReopsitory;

	public AppointmentController(AppointmentRepository appointmentReopsitory) {
		super();
		this.appointmentReopsitory = appointmentReopsitory;
	}
	
	@PostMapping("/insert")
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		
		return appointmentReopsitory.save(appointment);
		
	}
	
	@GetMapping("/appointments")
	public List<Appointment> getAllAppointments(){
		return appointmentReopsitory.findAll();
	}
	
	@DeleteMapping("/appointments/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteAppointment(@PathVariable long id)throws AttributeNotFoundException{
		Appointment appointment =  appointmentReopsitory.findById(id).orElseThrow(()-> new AttributeNotFoundException("Aappintment not found with id"+id));
		appointmentReopsitory.delete(appointment);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);		
	}
}
