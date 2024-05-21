package com.salaryexplorer.api.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;




@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorizationResponse implements Serializable {
    String email;
    String name;
}
