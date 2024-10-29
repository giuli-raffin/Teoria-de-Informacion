public class Main {

    public static void main(String[] args){
        float[] prob_entrada = new float[] {1/3f, 2/3f}; //p(0)=1/3 y p(1)=2/3
        float Betai = 0.05f;
        TransmisionRepeticion ti = new TransmisionRepeticion(Betai,prob_entrada);
        float probCruceMayoriaBetai = ti.probabilidadRepeticion3VecesMayoria();
        float probCruceCoincidenciaBetai = ti.probabilidadRepeticion3VecesCoincidencia();

        float Betaii = 0.5f;
        TransmisionRepeticion tii = new TransmisionRepeticion(Betaii, prob_entrada);
        float probCruceMayoriaBetaii = tii.probabilidadRepeticion3VecesMayoria();
        float probCruceCoincidenciaBetaii = tii.probabilidadRepeticion3VecesCoincidencia();

        System.out.println("La probabilidad de equivocación en la transmisión si, luego de transmitir 3 veces cada símbolo de entrada se toma como salida el símbolo que más se repite en la secuencia recibida son:\n"+
                "para beta = 0.05 : "+probCruceMayoriaBetai+"\n y para beta = 0.5: "+probCruceMayoriaBetaii);
        System.out.println("La probabilidad de equivocación en la transmisión si, luego de transmitir 3 veces cada símbolo de entrada se toma como salida el mismo símbolo de entrada si las 3 transmisiones coinciden con el son:\n"+
                "para beta = 0.05 : "+probCruceCoincidenciaBetai+"\n y para beta = 0.5: "+probCruceCoincidenciaBetaii);

    }
}