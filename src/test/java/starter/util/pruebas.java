package starter.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class pruebas {
    public static void main(String[] args) {

        Random random = new Random();

        int ram = 6;
        //se almacena los numeros ids perteneciente a los item (en el ejemplo solo hay dos productos a seleccionar)
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < 2) {
            uniqueNumbers.add(random.nextInt(ram-1) + 1);
        }

        System.out.println(uniqueNumbers);
    }
}
