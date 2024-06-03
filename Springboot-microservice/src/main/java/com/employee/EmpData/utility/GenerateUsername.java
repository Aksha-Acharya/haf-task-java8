package com.employee.EmpData.utility;

import org.springframework.stereotype.Component;

@Component
public class GenerateUsername {
	public String generateUsername(String employeeName) {
        
        String[] parts = employeeName.toLowerCase().split("\\s+");
       
        StringBuilder usernameBuilder = new StringBuilder();
        for (String part : parts) {
            if (usernameBuilder.length() < 6 && part.length() > 0) {
                usernameBuilder.append(part.charAt(0));
            }
        }
        while (usernameBuilder.length() < 6) {
            usernameBuilder.append((char) ('a' + Math.random() * 26));
        }
       return usernameBuilder.toString();
    }

}
