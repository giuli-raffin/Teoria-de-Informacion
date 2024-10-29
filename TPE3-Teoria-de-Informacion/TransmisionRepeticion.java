public class TransmisionRepeticion {
    private final float beta;
    private final float[] prob_entrada;

    public TransmisionRepeticion(float beta, float[] prob_entrada){
        this.beta = beta;
        this.prob_entrada = prob_entrada;
    }

    public float probabilidadRepeticion3VecesMayoria(){ //Ejercicio b)i.
        String transmisiones = "000";
        float prob = 0; //probabilidad de cruce
        while(!transmisiones.equals("1000")) { //todos los casos posibles
            if(cantidadUnos(transmisiones) == 2)
                prob += (1-beta)*beta*beta;
            else if(cantidadUnos(transmisiones) == 3)
                prob += beta*beta*beta;
            transmisiones = sumaBinaria(transmisiones);
        }
        return prob;
    }

    public float probabilidadRepeticion3VecesCoincidencia() { //Ejercicio b)ii.
        String transmisiones = "000";
        float prob = 0; //probabilidad de cruce
        while (!transmisiones.equals("1000")) { //todos los casos posibles
            if(cantidadUnos(transmisiones) == 1)
                prob += (1-beta)*(1-beta)*beta;
            else if (cantidadUnos(transmisiones) == 2)
                prob += (1 - beta) * beta * beta;
            else if (cantidadUnos(transmisiones) == 3)
                prob += beta * beta * beta;
            transmisiones = sumaBinaria(transmisiones);
        }
        return prob;
    }

    private String sumaBinaria(String s){
        int i = Integer.parseInt(s, 2);
        i++;
        s = Integer.toBinaryString(i);
        if(i == 1) {
            s = "00" + s; //sino queda unicamente 1 y no 001
        }
        else if(i == 3 || i == 2){ //sino queda 10 o 11 y no 010 o 011
            s = "0" + s;
        }
        return s;
    }

    private int cantidadUnos(String s){
        int c = 0;
        for(int i = 0; i < 3; i++){
            if(s.charAt(i) == '1')
                c++;
        }
        return c;
    }
}