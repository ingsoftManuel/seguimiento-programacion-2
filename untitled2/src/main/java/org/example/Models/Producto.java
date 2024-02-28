package org.example.Models;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {
    private Long id;
    private String nombre;
    private double precio;
    private LocalDateTime fecha_registro;


}
