package com.e_commerce.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private String message;
    private Date date;
    private String uri;

}
