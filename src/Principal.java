import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {


        int opcion;
        do{
            String blockText = """
                    Challenge CieloScopio:
                    -----------------------------------------------------------
                    Eligie una ciudad par obtener los datos meteorologicos:
                    1. Ciudad de Mexico
                    2. Buenos Aires
                    3. Bogota
                    4. Lima
                    5. Santiago
                    6. Deseo consultar otra ciudad
                    7. salir
                    ----------------------------------------------------------
                    Elige una opcion basada en el numero:
                    """;

            System.out.printf(blockText);
            Scanner input = new Scanner(System.in);
            opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Prueba Ciudad De Mexico");
                    break;
                case 2:
                    System.out.println("Prueba Buenos Aires");
                    break;
                case 3:
                    System.out.println("Prueba Bogota");
                    break;
                case 4:
                    System.out.println("Prueba Lima");
                    break;
                case 5:
                    System.out.println("Prueba Santiago");
                    break;
                case 6:
                    System.out.println("Deseo consultar otra ciudad");
                    break;
                default:
                    System.out.println("Consultar de nuevo");
            }
        } while (opcion != 7);



    }

}
