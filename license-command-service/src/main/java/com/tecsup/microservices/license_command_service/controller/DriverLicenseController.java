package com.tecsup.microservices.license_command_service.controller;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTORequest;
import com.tecsup.microservices.common_models.dto.DriverLicenseDTOResponse;
import com.tecsup.microservices.common_models.dto.DriverLicenseDTOUpdateRequest;
import com.tecsup.microservices.license_command_service.service.ILicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class DriverLicenseController {
    private  final ILicenseService iLicenseService;
    @Operation(
            summary = "Create a new driver license",
            description = "This endpoint allows an admin to create a new driver license.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Example Request",
                                    value = """
                                               {
                                                                           "licenseCategory": "A_IIIa",
                                                                           "fullName": "John",
                                                                           "documentNumber": 62345678,
                                                                           "userId": 16
                                                                         }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Driver license successfully created",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Example Response",
                                            value = """
                            {
                                \"id\": \"12345\",
                                \"category\": \"A\",
                                \"isValid\": true,
                                \"ownerName\": \"John Doe\",
                                \"issueDate\": \"2024-12-31\",
                                \"expiryDate\": \"2029-12-31\"
                            }
                        """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request data",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access denied",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<DriverLicenseDTOResponse> createLicense(@RequestBody DriverLicenseDTORequest driverLicenseDTORequest){
        DriverLicenseDTOResponse response = iLicenseService.createLicense(driverLicenseDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Update a driver's license",
            description = "Allows updating an existing driver's license.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Driver License update details",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DriverLicenseDTOUpdateRequest.class),
                            examples = @ExampleObject(
                                    name = "Update Driver License Example",
                                    summary = "Example of updating a driver's license",
                                    value = """
                                               {
                                                                       "licenseId":"Q86623400"
                                                                         }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "License updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DriverLicenseDTOResponse.class),
                                    examples = @ExampleObject(
                                            name = "Successful Update Example",
                                            value = "{\n" +
                                                    "  \"idLicense\": \"DL12345\",\n" +
                                                    "  \"licenseCategory\": \"A\",\n" +
                                                    "  \"isValidate\": true,\n" +
                                                    "  \"expiryDate\": \"2025-12-31\"\n" +
                                                    "}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "License not found",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<DriverLicenseDTOResponse> updateLicense(@RequestBody DriverLicenseDTOUpdateRequest driverLicenseDTOUpdateRequest){
        DriverLicenseDTOResponse response = iLicenseService.updateLicense(driverLicenseDTOUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLicense(){
        String response = iLicenseService.deleteLicense();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
