import business.Chofer;
import business.Moto;
import business.Paradero;
import dao.ChoferDao;
import dao.MotoDao;
import dao.ParaderoDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        // CRUD DE CHOFER
       ChoferDao chofer_crud = new ChoferDao();
        Chofer IdCHOFER = chofer_crud.findById(5);
        Chofer chofer = ChoferDao.create(new Chofer(null, "jair", "pedro", "76652147", "villa maria ", java.sql.Date.valueOf("1990-05-05")));
        System.out.println("se ingreso un nuevo dato: " + chofer);
        chofer_crud.update(IdCHOFER, "aaaa");
        chofer_crud.delete(IdCHOFER);
        List<Chofer> chofer1 = ChoferDao.findAll();
        System.out.println("todos los choferes :" + chofer1);

    // CRUD DE MOTO
      MotoDao moto_crud = new MotoDao();
       Moto moto = MotoDao.create(new Moto(null, "jair", "pedro", "76652147", "villa maria ", "1910"));
        System.out.println("se ingreso un nuevo dato: " + moto);
        Moto idmoto = moto_crud.findById(3);
        System.out.println(idmoto);
       moto_crud.update(idmoto,"123");
       moto_crud.delete(idmoto);
       List<Moto> mtos = MotoDao.findAll();
        System.out.println("todas las MOTOS :" + mtos);

    // CRUD DE PARADERO
        ParaderoDao paradero_crud = new ParaderoDao();
       Paradero paradero = ParaderoDao.create(new Paradero(null,1,1,"Asociacion Ficus sta","av. Huarochiri 4450"));
        System.out.println("se ingreso un nuevo dato: " + paradero);
        Paradero idparadero = paradero_crud.findById(2);
        System.out.println(idparadero);
        paradero_crud.update(idparadero,"flamengos 123");
        paradero_crud.delete(idparadero);
        List<Paradero> paraderoList = ParaderoDao.findAll();
        System.out.println("todos los choferes :" + paraderoList);


    }
}