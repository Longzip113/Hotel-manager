package com.hotelManager.Users;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(
        name = "UserDTOMapping",
        classes = @ConstructorResult(
                targetClass = User.class,
                columns = {
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "pass_word", type = String.class),
                        @ColumnResult(name = "code_role", type = String.class),
                        @ColumnResult(name = "is_delete", type = Boolean.class)
                }))
public class User {

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASS_WORD", nullable = false)
    private String passWord;

    @Column(name = "CODE_ROLE", nullable = false)
    private String role;

    @Column(name = "IS_DELETED", nullable = false)
    private Boolean isDeleted;
}
