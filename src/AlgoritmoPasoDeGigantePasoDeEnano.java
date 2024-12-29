import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Algoritmo para calcular el logaritmo discreto usando el método
 * paso de gigante y paso de enano.
 */
public class AlgoritmoPasoDeGigantePasoDeEnano {

    public static void modoManual(){
        Scanner lectura = new Scanner(System.in);
        System.out.println("Introduce el orden m (entero):");
        BigInteger m = lectura.nextBigInteger();
        System.out.println("Introduce el generador g (entero):");
        BigInteger g = lectura.nextBigInteger();
        System.out.println("Introduce y (entero):");
        BigInteger y = lectura.nextBigInteger();
        System.out.println("Empieza a ejecutar el programa y contabilizar el tiempo");
        long startTime = System.currentTimeMillis();
        Map<BigInteger, Integer> parejas = pasoGigante(g, m);
        BigInteger resultado = pasoEnano(parejas, g, y, m);
        System.out.printf("El resultado de b es %s%n", resultado);
        long endTime = System.currentTimeMillis() -startTime;
        System.out.println("Duración " + endTime + " milisegundos.");

    }
    public  static void modoTest(){
        BigInteger[] mGrupo = {
                new BigInteger("65537"),
                new BigInteger("1073741827"),
                new BigInteger("4294967311"),
                new BigInteger("109951627791"),
                new BigInteger("35184372088891"),
                new BigInteger("1125899906842679")
        };
        BigInteger[] gGrupo = {
                new BigInteger("3"),
                new BigInteger("2"),
                new BigInteger("3"),
                new BigInteger("3"),
                new BigInteger("3"),
                new BigInteger("11")
        };
        BigInteger[] yValores = {
                new BigInteger("27861"),
                new BigInteger("503539909"),
                new BigInteger("3333860011"),
                new BigInteger("73096380924"),
                new BigInteger("24056225665222"),
                new BigInteger("12761818229206")
        };
        long startTime = System.currentTimeMillis();
        for(int i=0;i<6;i++){
            Map<BigInteger, Integer> parejas = pasoGigante(gGrupo[i], mGrupo[i]);
            BigInteger resultado = pasoEnano(parejas, gGrupo[i], yValores[i], mGrupo[i]);
            System.out.printf("El resultado de b es %s%n para el valor de la pos: %d %n ", resultado,i);
            long endTime = System.currentTimeMillis() -startTime;
            System.out.println("Duración " + endTime + " milisegundos.");
            //comprobamos si es correcto el calculo
            if(resultado!=null){
                BigInteger valueIsCorrect =gGrupo[i].modPow(resultado,mGrupo[i]);
                System.out.printf("El valor generado es %s  y el valor introducido es %s %n",valueIsCorrect,yValores[i]);

            }

        }


    }
    public static void main(String[] args) {

        modoTest();
    }

    public static Map<BigInteger, Integer> pasoGigante(BigInteger g, BigInteger m) {
        Map<BigInteger, Integer> parejas = new HashMap<>();
        double t = Math.ceil(Math.sqrt(m.doubleValue())); // sqrt(m)
        for (int j = 0; j < t; j++) {

            BigInteger temporalT = BigDecimal.valueOf(t).toBigInteger();
            BigInteger temporalJ = BigInteger.valueOf(j);
            BigInteger resultadoTemp= temporalJ.multiply(temporalT).mod(m.subtract(new BigInteger("1")));
            BigInteger valorT= g.modPow(resultadoTemp,m);
            parejas.put(valorT, j);
        }
        return parejas;
    }

    public static BigInteger pasoEnano(Map<BigInteger, Integer>  parejas, BigInteger g, BigInteger y, BigInteger m) {
        double t = Math.ceil(Math.sqrt(m.doubleValue())); // sqrt(m)
        for (int i = 0; i < t; i++) {
            BigInteger valorTemporal=g.modPow(BigInteger.valueOf(i),m);
            BigInteger valor = valorTemporal.multiply(y).mod(m);
            if (parejas.containsKey(valor)) {
                Integer j = parejas.get(valor);
                return BigInteger.valueOf(j).multiply(BigDecimal.valueOf(t).toBigInteger()).subtract(BigInteger.valueOf(i)).mod(m);            }
        }
        System.out.println("devuelve nulo");
        return null;
    }


}
