package src;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesús
 * 
 */

public class Instance {

    private String name;
    private int n;
    private int p;
    private List<Integer>[] d;
    private List<Integer>[] dem;
    
    public Instance(){
        this.name = "";
        n = 0;
        p = 0;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public List<Integer>[] getD() {
        return d;
    }

    public void setD(List<Integer>[] d) {
        this.d = d;
    }

    public List<Integer>[] getDem() {
        return dem;
    }

    public void setDem(List<Integer>[] dem) {
        this.dem = dem;
    }
        
    @Override
    public String toString(){
        
        try{   
            String result = "Instancia: " + name + "\n";
            result += "n: " + n + "\n" + "p: " + p + "\n";
            result += "d: \n";
            for(List<Integer> lista : d){
                for(int elemento : lista){
                    result += elemento + " ";
                }
                result += "\n";
            }
            result += "dem: \n";
            for(List<Integer> lista2 : dem){
                for(int elemento2 : lista2){
                    result += elemento2 + " ";
                }
                result += "\n";
            }
            return result;
        }
        
        catch(Exception e){
            System.err.println(e.toString());
            return "Error";   
        }
        
    }
    
    public void fill(String ruta, Instance instancia) throws FileNotFoundException, IOException {
        
        try{
      
            InputStream fis = new FileInputStream(ruta);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);

            String line;
            while((line = br.readLine()) != null){

                if(line.matches("n:.*")){
                    int n;
                    n = Integer.parseInt(line.substring(2));
                    instancia.setN(n);
                }
                else if(line.matches("p:.*")){
                    int p;
                    p = Integer.parseInt(line.substring(2));
                    instancia.setP(p);
                }
                else if(line.matches("d:.*")){
                    List<Integer>[] d = new ArrayList[instancia.getN()];
                    int contador = 0;
                        while(!((line = br.readLine()).equals("]"))){
                            String[] auxiliar = line.split(" ");
                            List<Integer> listAux = new ArrayList<>();
                            for(int i = 0; i < auxiliar.length; i++){
                                listAux.add(Integer.parseInt(auxiliar[i]));
                            }
                            d[contador] = listAux;
                            contador++;
                        }
                    instancia.setD(d);
                }
                else if(line.matches("dem:.*")){
                    List<Integer>[] dem = new ArrayList[instancia.getN()];
                    int contador2 = 0;
                        while(!((line = br.readLine()).equals("]"))){
                            String[] auxiliar2 = line.split(" ");
                            List<Integer> listAux2 = new ArrayList<>();
                            for(int i = 0; i < auxiliar2.length; i++){
                                listAux2.add(Integer.parseInt(auxiliar2[i]));
                            }
                            dem[contador2] = listAux2;
                            contador2++;
                        }
                        instancia.setDem(dem);
                    }
                }
        }
        catch(IOException e){
            System.err.println();
        }
        
    }
    
}
