
package javaapplication015_serialize;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

class Tovar implements  Serializable
{
    private String  name;
    private double  price;
    private int     weight;
    
    public  Tovar ()
    {
    }
   
    public  Tovar (String nm, double pr, int w)
    {
        this.name   = nm;
        this.price  = pr;
        this.weight = w;
    }
    
    @Override
    public  String  toString()
    {
        return "Name : " + this.name + "\t" + "Price : " + this.price + "\t" + 
               "Weight : " + this.weight;
    }
}

class   Point   implements  Serializable
{
    private int x;
    private int y;
    
    public  Point (int x, int y)
    {
        this.x= x;
        this.y= y;
    }
    
    public  void    showPoint()
    {
        System.out.println("X = " + this.x + "\tY = " + this.y);
    }
}

public class JavaApplication015_Serialize {

    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
       
        //<editor-fold defaultstate="collapsed" desc="сериализация и десериализация объекта">
//        Tovar   T   = new Tovar ("Snickers",    12.5,   45);
///*
//* сериализация
//*/
//        FileOutputStream    FOS = new FileOutputStream ("tovars2.dat");
//        ObjectOutputStream  OOS = new ObjectOutputStream (FOS);
//        OOS.writeObject(T); // сериализируем товар
//        OOS.close();
//        
///*
//* десериализация
//*/
//       FileInputStream      FIS = new FileInputStream("tovars2.dat");
//       ObjectInputStream    OIS = new ObjectInputStream (FIS);
//       Tovar    Z   = (Tovar) OIS.readObject();
//       OIS.close(); //FIS тоже будет закрыт
//       
//       System.out.println(Z);
        //</editor-fold>
       
        //<editor-fold defaultstate="collapsed" desc="cep. и десериализация списка">
//        ArrayList<Tovar>    list    = new ArrayList<>();
//        
//        list.add(new Tovar ("Sniscers", 12.5,   45));
//        list.add(new Tovar ("Bounty",   13.15,  50));
//        list.add(new Tovar ("Mars",     14.5,   35));
//        list.add(new Tovar ("Twix",     13.5,   34));
//        list.add(new Tovar ("KitKat",   9.5,    22));
//        list.add(new Tovar ("Milka",    20,     80));
//        
//        try
//        {
//            FileOutputStream    FOS = new FileOutputStream ("tovars3.dat");
//            ObjectOutputStream  OOS = new ObjectOutputStream (FOS);
//            for (Tovar t : list)
//            {
//                OOS.writeObject(t);
//            }
//            OOS.close();
//        }
//        catch (Exception e)
//        {
//            System.out.println("Error : " + e.getMessage());
//        }
//        
//        ArrayList<Tovar>    list2    = new ArrayList<>();
//        try
//        {
//            FileInputStream    FIS = new FileInputStream ("tovars3.dat");
//            ObjectInputStream  OIS = new ObjectInputStream (FIS);
//            while (FIS.available() > 0)
//            {
//                Tovar   T   = (Tovar) OIS.readObject();
//                list2.add(T);
//            }
//            OIS.close();
//        }
//        catch (Exception e)
//        {
//            System.out.println("Error : " + e.getMessage());
//        }
//        
//        for (Tovar z : list2)
//        {
//            System.out.println(z);
//        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="определение типа объекта">
        
//        Object  obj = new Tovar ("Snickers",    17.2,   90);
//        System.out.println(obj.getClass().getTypeParameters());
//        
//        Field[] F   = obj.getClass().getDeclaredFields();
//        for (Field f : F)
//        {
//            System.out.println("Name of field : " + f.getName());
//            System.out.println("Type of field : " + f.getClass().getTypeParameters());
//        }
        //</editor-fold>
       
        //<editor-fold defaultstate="collapsed" desc="сериализация и десериализация объектов разного типа">
        FileOutputStream    FOS = new FileOutputStream ("object.dat");
        try (ObjectOutputStream OOS = new ObjectOutputStream (FOS))
        {
            int cnt = (int) (Math.random() * 10 + 10);
            for (int i = 0; i < cnt; i++)
            {
                int what    = (int) (Math.random() * 2);
                if (what == 0)
                {
                    Tovar   T   = new Tovar ("Tovar" + i, Math.random() * 10, i * 10);
                    OOS.writeObject(T);
                }
                else
                {
                    Point   P   = new Point ((int) (Math.random()*100), (int) (Math.random() * 100));
                    OOS.writeObject(P);
                }
            }
        }
        
        ArrayList<Tovar>    listT   = new   ArrayList<>();
        ArrayList<Point>    listP   = new   ArrayList<>();
        
        FileInputStream     FIS = new FileInputStream ("object.dat");
        ObjectInputStream   OIS = new ObjectInputStream (FIS);
        
        while (FIS.available() > 0)
        {
            Object  obj = OIS.readObject();
            if (obj instanceof Tovar)
            {
                listT.add((Tovar) obj);
            }
            else
            if (obj instanceof Point)
            {
                listP.add((Point) obj);
            }
            else
            {
                System.out.println("Unnown object : " + obj.getClass().getName());
            }
        }
        OIS.close();
        
        for (Point p : listP)
        {
            p.showPoint();
        }
        
        for (Tovar t : listT)
        {
            System.out.println(t);
        }
        //</editor-fold>
        
        
    }
}
