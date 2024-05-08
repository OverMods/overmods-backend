package org.overmods.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupDto {
    @NotBlank
    public String username;

    @NotBlank
    @Email(regexp = "\\^\\(\\(\\[\\^\\<\\>\\(\\)\\[\\\\\\]\\\\\\\\\\.,;\\:\\\\s@\"\\]\\+\\(\\\\\\.\\[\\^\\<\\>\\(\\)\\[\\\\\\]\\\\\\\\\\.,;\\:\\\\s@\"\\]\\+\\)\\*\\)\\|\\.\\(\"\\.\\+\"\\)\\)@\\(\\(\\\\\\[\\[0\\-9\\]\\{1,3\\}\\\\\\.\\[0\\-9\\]\\{1,3\\}\\\\\\.\\[0\\-9\\]\\{1,3\\}\\\\\\.\\[0\\-9\\]\\{1,3\\}\\\\\\]\\)\\|\\(\\(\\[a\\-zA\\-Z\\\\\\-0\\-9\\]\\+\\\\\\.\\)\\+\\[a\\-zA\\-Z\\]\\{2,\\}\\)\\)\\$ ")
    public String email;

    @NotBlank
    public String password;

}
