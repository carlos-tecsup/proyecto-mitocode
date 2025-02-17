package com.tecsup.microservices.licence_query_service.controller;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTORequest;
import com.tecsup.microservices.common_models.dto.DriverLicenseDTOResponse;
import com.tecsup.microservices.common_models.enums.LicenseCategory;
import com.tecsup.microservices.licence_query_service.service.DriverLicenseService;
import com.tecsup.microservices.licence_query_service.wrapper.LicenseResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class DriverLicenseController {
    private final DriverLicenseService driverLicenseService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of filtered licenses",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Example Response",
                                                    value = """
                                                                {
                                                                                                     "licenses": [
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John Doe",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "G73549613",
                                                                                                             "dueDate": "2024-11-25",
                                                                                                             "emitDate": "2027-12-26"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John Doe",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "D15213846",
                                                                                                             "dueDate": "2024-12-26",
                                                                                                             "emitDate": "2027-12-26"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "N30580288",
                                                                                                             "dueDate": "2024-12-26",
                                                                                                             "emitDate": "2027-12-26"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "P58347865",
                                                                                                             "dueDate": "2024-11-26",
                                                                                                             "emitDate": "2025-01-26"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John Doe",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "Q86623400",
                                                                                                             "dueDate": "2030-12-22",
                                                                                                             "emitDate": "2027-12-21"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "R99241881",
                                                                                                             "dueDate": "2024-12-28",
                                                                                                             "emitDate": "2027-12-28"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "N41133415",
                                                                                                             "dueDate": "2024-12-28",
                                                                                                             "emitDate": "2027-12-28"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "W99282044",
                                                                                                             "dueDate": "2024-12-28",
                                                                                                             "emitDate": "2027-12-28"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "Q30987040",
                                                                                                             "dueDate": "2024-12-28",
                                                                                                             "emitDate": "2027-12-28"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "V52964507",
                                                                                                             "dueDate": "2024-12-28",
                                                                                                             "emitDate": "2027-12-28"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "T12986066",
                                                                                                             "dueDate": "2026-12-28",
                                                                                                             "emitDate": "2027-12-28"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "Z45276441",
                                                                                                             "dueDate": "2024-12-28",
                                                                                                             "emitDate": "2027-12-28"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "U59375456",
                                                                                                             "dueDate": "2024-12-28",
                                                                                                             "emitDate": "2027-12-28"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "K75087265",
                                                                                                             "dueDate": "2024-12-28",
                                                                                                             "emitDate": "2027-12-28"
                                                                                                         },
                                                                                                         {
                                                                                                             "licenseCategory": "A_IIIa",
                                                                                                             "fullName": "John",
                                                                                                             "documentNumber": 62345678,
                                                                                                             "id": "J44866642",
                                                                                                             "dueDate": "2029-12-28",
                                                                                                             "emitDate": "2024-12-28"
                                                                                                         }
                                                                                                     ]
                                                                                                 }
                                                            """
                                            )
                                    }
                            )
                    )
            }
    )
    @GetMapping("/filter")
    public ResponseEntity<LicenseResponseWrapper> filterLicense(
            @RequestParam(required = false) Boolean isValidate,
            @RequestParam(required = false) LicenseCategory licenseCategory,
            @RequestParam(required = false) String idLicense) {

        List<DriverLicenseDTOResponse> licenses = driverLicenseService.filterLicense(isValidate, licenseCategory, idLicense);
        return ResponseEntity.ok(new LicenseResponseWrapper(licenses));

    }
}
