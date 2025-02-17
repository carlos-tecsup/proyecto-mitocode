package com.tecsup.microservices.licence_query_service.wrapper;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenseResponseWrapper {
    private List<DriverLicenseDTOResponse> licenses;
}