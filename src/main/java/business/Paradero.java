package business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Paradero {

    private Integer idparadero;
    private Integer idchofer;
    private Integer idmoto;
    private String nombre;
    private String direccion;
}
