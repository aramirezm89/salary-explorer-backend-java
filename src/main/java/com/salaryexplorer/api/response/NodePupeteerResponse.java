package com.salaryexplorer.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NodePupeteerResponse {
    private List<SalaryData> data;
}
