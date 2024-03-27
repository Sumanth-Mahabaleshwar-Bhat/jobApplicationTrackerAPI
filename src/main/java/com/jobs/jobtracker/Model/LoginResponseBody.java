package com.jobs.jobtracker.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponseBody {
    private boolean success;
    private String message;
    private String username;
    private int statusCode;
}
