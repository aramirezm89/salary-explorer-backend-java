package com.salaryexplorer.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class SalaryRequest implements Serializable {

    @NotBlank
    @JsonProperty("jobPosition")
    String jobPosition;
    @JsonProperty("country")
    @NotBlank
    String country;
}
