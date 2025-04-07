package com.code_red.phc_attendance_system.dto;

import java.util.List;
import lombok.Data;
import com.code_red.phc_attendance_system.dto.LocationDTO;

@Data
public class FacilityDTO {
 private String name;
 private String block;
 private String district;
 private String facilityType;
 private List<LocationDTO> region;
}
