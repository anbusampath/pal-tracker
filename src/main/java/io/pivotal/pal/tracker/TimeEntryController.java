package io.pivotal.pal.tracker;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeEntryController {

	private final TimeEntryRepository timeEntryRepository;

	public TimeEntryController(TimeEntryRepository timeEntryRepository) {
		this.timeEntryRepository = timeEntryRepository;
	}

	@PostMapping("/time-entries")
	public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
		
		TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntry);

		return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
	}

	@GetMapping("/time-entries/{id}")
	public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
		
		TimeEntry timeEntry = timeEntryRepository.find(id);
		if (timeEntry != null) {
			return new ResponseEntity<>(timeEntry, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/time-entries")
	public ResponseEntity<List<TimeEntry>> list() {
		
		return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
	}

	@PutMapping("/time-entries/{id}")
	public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
		
		TimeEntry updatedTimeEntry = timeEntryRepository.update(id, timeEntry);
		if (updatedTimeEntry != null) {
			return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/time-entries/{id}")
	public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
		
		timeEntryRepository.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}