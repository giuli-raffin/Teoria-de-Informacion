public class Signal {
    public String nombre;
    public int[] simbolos = new int[] {};
    public int cant_simbolos;

    public Signal(int[] s, String nombre){
        this.simbolos = s;
        this.nombre = nombre;
    }
    //ej a 1era parte

    private int nuevosimbolo(float d[][],int s){
        int i = 0;
        while(i < simbolos.length && d[0][i] != s)
            i++;
        if(i < simbolos.length)
            return i;
        else return -1;
    }

    private void calcularProb(float d[][]){
        float cant = 0;
        for (int i = 0; i < simbolos.length ; i++){
            cant = d[1][i];
            d[1][i] = cant/simbolos.length;
        }
    }

    public float[][] distribucionProbabilidades(){
        float distr[][] = new float[2][simbolos.length]; //en fila 0 esta el simbolo y en fila 1 la probabilidad
        int indice_d = 0;
        for(int i = 0; i < simbolos.length; i++){
            int simb = nuevosimbolo(distr,simbolos[i]);
            if(simb < 0){
                distr[0][indice_d] = simbolos[i];
                distr[1][indice_d] = 1f;
                indice_d++;
            }
            else{
                distr[1][simb]++;
            }
        }
        calcularProb(distr);
        imprimirDistribucion(distr);
        return distr;
    }

    public void imprimirDistribucion(float d[][]) {
        int t = 0;
        System.out.println("\n"+"La distribucion de probabilidades de la fuente " + nombre + " es:");
        for (int i = 0; i < simbolos.length; i++) {
            if(d[1][i] > 0) {
                t++;
                System.out.println("simbolo: " + (int) d[0][i] + " - probabilidad: " + d[1][i]);
            }
        }
        cant_simbolos = t;
    }
}