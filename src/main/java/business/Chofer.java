package business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chofer {

    private Integer idChofer;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private Date fechanacimiento;
}
