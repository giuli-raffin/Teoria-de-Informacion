import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Nodo {
    float data;
    int c;
    Nodo izq;
    Nodo der;
}

class MyComparator implements Comparator<Nodo> {
    public int compare(Nodo x, Nodo y) {
        return Float.compare(x.data, y.data);
    }
}

public class Huffman {
    public Signal signal;
    public static HashMap<Integer, Integer> longitud = new HashMap<>();

    public static void imprimirArbol(Nodo raiz, String s, HashMap<Integer, Integer> longitud){ //s es el codigo de 1s y 0s
        //caso base
        if(raiz.c!=-1) {
            longitud.put(raiz.c, s.length());
            if (raiz.izq == null && raiz.der == null) {
                System.out.println(raiz.c + ":" + s + " longitud: " + s.length());
                return;
            }
        }
        //izq 0, der 1 para el código
        imprimirArbol(raiz.izq, s + "0",longitud);
        imprimirArbol(raiz.der, s + "1",longitud);
    }
        public boolean obtenerProbabilidades(float[] arr, int tam, float[][] d) {
            for (int i = 0; i < tam; i++) {
                arr[i] = d[1][i];
            }
            return true;
        }

        //ejercicio b

        public int longitudTotalEnBits(Signal signal){
            int l = 0;
            for(int i = 0; i < signal.simbolos.length; i++){
                l += Integer.toString(signal.simbolos[i]).length()*8;
            }
            System.out.println("La longitud total en bits de la senial "+signal.nombre+" es de "+l);
            return l;
        }

        public int longitudHuffmanEnBits(Signal signal, HashMap<Integer, Integer> longitud) {
            int lon = 0;
            int val;
            for (int i = 0; i < 1000; i++) {
                val = signal.simbolos[i];
                    if (longitud.get(val) != null) {
                        lon += longitud.get(val);
                    }
                }
            System.out.println("La longitud total en bits de la senial "+ signal.nombre +" codificada con huffman es de " + lon);
            return lon;
        }

        //ejercicio a 2da parte
        public Huffman(Signal signal, float[][] distribucion){ //pasarlo a otro metodo
            this.signal = signal;
            obtenerProbabilidades(distribucion[1],signal.cant_simbolos,distribucion);
            PriorityQueue<Nodo> q = new PriorityQueue<>(new MyComparator());
            for (int i = 0; i < signal.cant_simbolos; i++) {
                Nodo hn = new Nodo();
                hn.c = (int)distribucion[0][i];
                hn.data = distribucion[1][i];
                hn.izq = null;
                hn.der = null;
                q.add(hn);
            }
            Nodo raiz = null;
            while (q.size() > 1){
                Nodo x = q.peek();
                q.poll();
                Nodo y = q.peek();
                q.poll();
                Nodo f = new Nodo();
                assert y != null;
                f.data = x.data + y.data;
                f.c = -1;
                f.izq = x;
                f.der = y;
                raiz = f;
                if (!q.contains(f))
                    q.add(f);
            }
            assert raiz != null;
            imprimirArbol(raiz, "", longitud);
            longitudHuffmanEnBits(signal,longitud);
            longitudTotalEnBits(signal);
            rendimiento(distribucion,longitud);
        }

    //ejercicio c

     private float log2 (float prob){
         return - (float)(Math.log(prob) / Math.log(2));
    }

    private float entropia (float d[][]){
        float e = 0, log = 0;
        for(int i = 0; i < longitud.size(); i++){
            log = log2(d[1][i]);
            if(log != log/0) { //porque el logaritmo en base 2 de cero da infinity y no hay que considerarlo, se toma a la division por 0 como infinity
                e += d[1][i] * log;
            }
        }
        return e;
    }

    private float longitudMedia(float[][] d, HashMap<Integer, Integer> longitud){
        float med = 0;
        int val;
        for (int i = 0; i < 1000; i++) {
            val = (int)d[0][i];
            if (longitud.get(val) != null) {
                med += d[1][i] * longitud.get(val);
            }
        }
        return med;
    }

    public float rendimiento(float[][] d, HashMap<Integer, Integer> longitud){ //revisar
        float rend = entropia(d)/longitudMedia(d,longitud);
        System.out.println("El rendimiento de la fuente "+ signal.nombre +" es del " + rend*100+"%");
        return rend;
    }
}
