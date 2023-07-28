package com.example.BackEndTodo.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "To_do")
public class To_Do {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String title;

    @Column
    @NonNull
    private String description;

    @Column
    @NonNull
    private Boolean active;
}
