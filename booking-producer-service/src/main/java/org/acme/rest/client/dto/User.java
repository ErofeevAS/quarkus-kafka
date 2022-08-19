package org.acme.rest.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
