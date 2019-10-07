package business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class Moto
{
    private Integer idmotos;
    private String Placa;
    private String Modelo;
    private String color;
    private String motor;
    private String año_modelo;


}
