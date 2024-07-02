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

        } while (opcion != 7);




    }

}
