public class Montecarlo {
    private float epsilon = 0.0001f; //valor de epsilon para el control de convergencia

    public Montecarlo(float epsilon){
        this.epsilon = epsilon;
    }

    int primerSimbolo(){
        float[] V0acum = new float[] {1f/3, 2f/3, 1f}; //tomamos equiprobabilidad para elegir el primer simbolo
        float r = (float) Math.random();
        for (int i = 0; i < 3; i++)
            if (r < V0acum[i]) {
                return i;
            }
        return -1;
    }

    int sigSimbolo(int ant){
        float[][] M_acum = new float[][] {{1f/2, 1f/3, 0f},
                {1f, 2f/3, 1f},
                {1f, 1f, 1f}}; //matriz de probabilidades acumuladas
        float r = (float) Math.random();
        for (int i = 0; i < 3; i++) //de acuerdo a la probabilidad aleatoria obtenida se determina que simbolo fue emitido
            if (r < M_acum[i][ant])
                return i;
        return -1;
    }

    boolean converge(float a[], float b[]){
        for(int i = 0; i < 3; i++){
            if(Math.abs(a[i] - b[i]) > epsilon) //si la diferencia es lo suficientemente pequeña, converge
                return false;
        }
        return true;
    }

    public float[] CalcularProbabilidadEstacionaria(){ //ej 11a
        int[] emisiones = new int[] {0, 0, 0}; //cantidad de emisiones de cada simbolo
        float[] Vt = new float[] {0, 0, 0}; //vector de probabilidades actual
        float[] Vt_ant = new float[] {-1, 0, 0}; //vector de probabilidades anterior
        int cant_simb = 0; //cantidad de simbolos emitidos
        int s = 0;
        while (!converge(Vt, Vt_ant) || cant_simb < 10000){
            s = sigSimbolo(s);
            cant_simb++;
            Vt_ant = Vt;
            emisiones[s] = emisiones[s]+1;
            for(int i = 0; i < 3; i++){
                Vt[i] = (float) emisiones[i]/cant_simb; //se actualiza el vector de probabilidades
            }
        }
        return Vt; //se devuelve el vector estacionario obtenido
    }

    public float[][] CalcularProbabilidadPrimeraRecurrencia(){ //ej11b
        int[] recurrencias = new int[] {0, 0, 0}; //cantidad de retornos ret
        float[][] fi_i = new float[][] {{0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0}}; //probabilidad de primera recurrencia para cada simbolo
        float[][] fi_i_ant = new float[][] {{-1, -1, -1},{-1, -1, -1},{-1, -1, -1}}; //probabilidad de primera recurrencia anterior para cada simbolo
        int[][] retornos_totales = {{0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0}}; //ret_n
        int[] ultimo_retorno = new int[] {0,0,0}; //el ultimo paso donde se retorno a cada simbolo
        int paso_actual = 0;
        int s = primerSimbolo();
        int pasos = 0;
        while (!converge(fi_i[s], fi_i_ant[s]) || paso_actual < 10000){//?
            s = sigSimbolo(s);
            paso_actual++;
            pasos = paso_actual - ultimo_retorno[s];
            if(ultimo_retorno[s] > 0)
              recurrencias[s]++;
            fi_i_ant = fi_i;
            if (pasos < 6){
              retornos_totales[s][pasos]++;
              for (int i = 0; i < 3; i++) {
                fi_i[i][pasos] = (float) retornos_totales[i][pasos]/recurrencias[i]; 
              }
            }
            ultimo_retorno[s] = paso_actual;
        }
        return fi_i; //se devuelve las probabilidades de primera recurrencia en n pasos obtenida
    }
}