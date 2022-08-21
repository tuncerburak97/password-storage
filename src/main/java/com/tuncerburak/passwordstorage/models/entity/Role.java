package com.tuncerburak.passwordstorage.models.entity;

import com.tuncerburak.passwordstorage.models.common.ERole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Data
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

}