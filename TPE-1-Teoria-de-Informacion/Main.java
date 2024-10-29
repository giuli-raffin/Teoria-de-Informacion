public class Main {

    public static void main(String[] args) {
      Montecarlo m = new Montecarlo(0.0001f); //se le envia epsilon como parametro
      int tests = 4;
      System.out.println("Ejercicio 11 a" + "\n"); //probabilidades estacionarias
      for(int i = 0; i < tests; i++) {
        float prob[] = m.CalcularProbabilidadEstacionaria();
        System.out.println("test "+ (i+1));
        for(int j = 0; j < 3; j++){
            System.out.println(" - probabilidad simbolo "+ (j+1) +": " + prob[j]);
        }
      }
      
      
  System.out.println("\n"+"Ejercicio 11 b" + "\n"); //probabilidades de primera recurrencia en n pasos
        float rec[][] = m.CalcularProbabilidadPrimeraRecurrencia();
          for(int j = 0; j < 3; j++){
            System.out.println(" - probabilidad recurrencia simbolo "+ (j+1));
            for(int h = 1; h < 6; h++){
              System.out.println("  en "+h+" pasos: " + rec[j][h]);
            }
            System.out.println("\n");
          }
        }
    

}