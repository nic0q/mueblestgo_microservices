package com.tingeso.extra_hours.controller;

import com.tingeso.extra_hours.entity.ExtraHours;
import com.tingeso.extra_hours.service.ExtraHoursService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/extra-hours")
public class ExtraHoursController {

  @Autowired
  ExtraHoursService extraHoursService;

  @GetMapping
  public ResponseEntity<List<ExtraHours>> getAll() {
    List<ExtraHours> Justificatives = extraHoursService.getAll();
    if (Justificatives.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(Justificatives);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExtraHours> getById(@PathVariable("id") Integer id) {
    ExtraHours ExtraHours = extraHoursService.getJustificativeById(id);
    if (ExtraHours == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(ExtraHours);
  }

  @GetMapping("/byemployee/{employeeId}")
  public ResponseEntity<List<ExtraHours>> getByStudentId(
    @PathVariable("employeeId") String employeeId
  ) {
    List<ExtraHours> extraHours = extraHoursService.getExtraHoursByRut(employeeId);
    return ResponseEntity.ok(extraHours);
  }

  @PostMapping
  public ResponseEntity<ExtraHours> save(@RequestBody ExtraHours ExtraHours) {
    ExtraHours JustificativeNew = extraHoursService.save(ExtraHours);
    return ResponseEntity.ok(JustificativeNew);
  }
}