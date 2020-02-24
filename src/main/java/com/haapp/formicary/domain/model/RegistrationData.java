package com.haapp.formicary.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationData {

   // @Size(min = 6, max = 50)
    private String username;
 //   @Size(min = 8)
    private String password;
  //  @Email
    private String email;
}
