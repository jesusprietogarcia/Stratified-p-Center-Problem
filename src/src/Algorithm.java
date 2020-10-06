package src;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Jesús
 */
public class Algorithm {
    
    static final double PERCENTAGESEARCH = 2;
    
    private final DecimalFormat df = new DecimalFormat("#.##");
    private final Instance instance;
    private final Data data;
    
    public Algorithm(Instance instance){
        
        this.instance = instance;
        this.data = new Data(instance);
        
        // 1st STAGE: construction
        construction();
        
        // 2nd STAGE: improve
        ArrayList<Object> improved = improve(data.getSelectedNodes(), data.getResult());
        data.setImprovedResult((double) improved.get(0));
        data.setImprovedNodes((ArrayList<Integer>) improved.get(1));
        data.setImprovedTime((String) improved.get(2));
        data.setFinalResult((double) improved.get(0));
        data.setFinalNodes((ArrayList<Integer>) improved.get(1));
        
        // 3th STAGE
        int iterator = 1;
        long start = System.nanoTime();
        while(iterator <= (PERCENTAGESEARCH * instance.getP())){
            // 3.1 STAGE: shake
            shake(iterator);
            // 3.2 STAGE: improved shaked
            ArrayList<Object> improvedShaked = improve(data.getShakedNodes(), data.getShakedResult());
            data.setImprovedShakedResult((double) improvedShaked.get(0));
            data.setImprovedShakedNodes((ArrayList<Integer>) improvedShaked.get(1));
            data.setImprovedShakedTime((String) improvedShaked.get(2));
            // 3.3 STAGE: neighbor change
            if(data.getImprovedShakedResult() < data.getFinalResult()){
                data.setFinalNodes(data.getImprovedShakedNodes());
                data.setFinalResult(data.getImprovedShakedResult());
                iterator = 1;
            }
            else{
                iterator += 1;
            }
        }
        long end = System.nanoTime();
        String formattedTime = df.format((end - start)/1000000000.0);
        data.setRandomSearchTime(formattedTime);
        
    }
    
    public Data getData(){
        return this.data;
    }
    
    private void construction(){
        long start = System.nanoTime();
        
        ArrayList<Integer> selectedNodes = new ArrayList<>();
        while(selectedNodes.size() < instance.getP()){
            selectedNodes.add(getBestNode(selectedNodes));
        }
        
        double result = resultInstance(selectedNodes);
        
        long end = System.nanoTime();
        String formattedTime = df.format((end - start)/1000000000.0);
        
        data.setSelectedNodes(selectedNodes);
        data.setResult(result);
        data.setTime(formattedTime);
    }
    
    private int getBestNode(ArrayList<Integer> selectedNodes){
        
        double bestResult = Double.MAX_VALUE;
        int bestNode = 0;
        
        for(int i=0; i < instance.getN(); i++){
            ArrayList<Integer> auxNodes = new ArrayList<>(selectedNodes);
            auxNodes.add(i);
            double resultAux = resultInstance(auxNodes);
            if((resultAux < bestResult) && !(isEqual(selectedNodes, i))){
                bestResult = resultAux;
                bestNode = i;
            }
        }
        
        return bestNode;
        
    }
    
    private double resultInstance(ArrayList<Integer> possibleNodes){
        
        List<Integer> maximumDStratum = new ArrayList<>();
        for(int stratum = 0; stratum < instance.getDem()[0].size(); stratum++){
            List<Integer> minimumDistances = new ArrayList<>();
            for(int node = 0; node < instance.getD().length; node++){
                minimumDistances.add(minimumDistanceNodeStratum(possibleNodes, node, stratum));
            }
            int result = 0;
            for(int distAux : minimumDistances){
                if(!(distAux == -1)){
                    if(!(distAux == 0)){
                        if(distAux > result){
                            result = distAux;
                        }
                    }
                }
            }
            maximumDStratum.add(result);
        }
        
        double result = 0;
        for(int aux : maximumDStratum){
            result = result + (aux * 0.1);
        }

        return result;
        
    }
    
    private int minimumDistanceNodeStratum(ArrayList<Integer> possibleNodes, int node, int stratum){
        
        List<Integer> listaSoluciones = new ArrayList<>();
        int result;
        
        if(isEqual(possibleNodes, node)){
            result = -1;
        }
        else{
            if(instance.getDem()[node].get(stratum) == 0){
                result = -1;
            }
            else{
                for(int i = 0; i < instance.getD().length; i++){
                    if(isEqual(possibleNodes, i)){
                        listaSoluciones.add(instance.getD()[node].get(i));
                    }
                }  
                result = Integer.MAX_VALUE;
                for(int j : listaSoluciones){
                    if(!(j == 0)){
                        if(j < result){
                            result = j;
                        }
                    }
                }
            }
        }   
        
        return result;
        
    }
    
    private ArrayList<Object> improve(ArrayList<Integer> selectedNodes, double result){
        ArrayList<Integer> improvedNodes = new ArrayList<>(selectedNodes);
        double improvedResult = result;
        long start = System.nanoTime();
        boolean flag = true;
        mainLoop : while(flag){
            flag = false;
            for(int i = 0; i < improvedNodes.size(); i++){
                ArrayList<Integer> auxSelectedNodes = new ArrayList<>(improvedNodes);
                for(int j = 0; j < instance.getN(); j++){
                    auxSelectedNodes.set(i, j);
                    double auxResult = resultInstance(auxSelectedNodes);
                    if(auxResult < result){
                        improvedNodes.set(i, j);
                        improvedResult = auxResult;
                        flag = true;
                        break mainLoop;
                    }
                }
            }
        }
        long end = System.nanoTime();
        String formattedTime = df.format((end - start)/1000000000.0);
        
        return new ArrayList<>(Arrays.asList(improvedResult, improvedNodes, formattedTime));
    }
    
    private void shake(int nLoops){
        ArrayList<Integer> shakedNodes = new ArrayList<>(data.getImprovedNodes());
        for(int i= 0; i < nLoops; i++){
            Random r = new Random();
            int position = r.nextInt(shakedNodes.size());
            int newNode = r.nextInt(instance.getN());
            shakedNodes.set(position, newNode);
        }
        data.setShakedNodes(shakedNodes);
        data.setShakedResult(resultInstance(shakedNodes));
    }
    
    private boolean isEqual(ArrayList<Integer> possibleNodes, int num){
        boolean resultAux = false;
        for(int nodeAux : possibleNodes){
            if(nodeAux == num){
                resultAux = true;
            }
        }
        return resultAux;
    }
    
}
