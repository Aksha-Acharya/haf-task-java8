package com.microservice.EmpPersonalDetails.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
	private int status;
    private String message;
    private long timeStamp;
 
  
}
