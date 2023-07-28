package com.example.BackEndTodo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private String lastName;

    @Column
    @NonNull
    private String email;

    @Column
    @NonNull
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @OneToMany
    @JoinColumn(name="To_Do´s_id")
    private Set<To_Do> ToDoS= new HashSet();


    /*@ManyToMany
    @JoinTable(
            name="user_has_to_do",
            joinColumns = @JoinColumn(name="user_iduser"),
            inverseJoinColumns = @JoinColumn(name = "to_do_idto_do")
    )
    private Set<To_Do> to_dosUser=new HashSet();
*/
}
