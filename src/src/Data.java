package src;

import java.util.ArrayList;

/**
 *
 * @author Jesús
 */

public class Data {
    
    private final Instance instance;
    private ArrayList<Integer> selectedNodes;
    private String time;
    private double result;
    private ArrayList<Integer> improvedNodes;
    private String improvedTime;
    private double improvedResult;
    private ArrayList<Integer> shakedNodes;
    private double shakedResult;
    private ArrayList<Integer> improvedShakedNodes;
    private double improvedShakedResult;
    private String improvedShakedTime;
    private ArrayList<Integer> finalNodes;
    private double finalResult;
    private String randomSearchTime;
    
    public Data(Instance instance){
        this.instance = instance;
    }

    public Instance getInstance() {
        return instance;
    }

    public ArrayList<Integer> getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(ArrayList<Integer> selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public ArrayList<Integer> getImprovedNodes() {
        return improvedNodes;
    }

    public void setImprovedNodes(ArrayList<Integer> improvedNodes) {
        this.improvedNodes = improvedNodes;
    }

    public String getImprovedTime() {
        return improvedTime;
    }

    public void setImprovedTime(String improvedTime) {
        this.improvedTime = improvedTime;
    }

    public double getImprovedResult() {
        return improvedResult;
    }

    public void setImprovedResult(double improvedResult) {
        this.improvedResult = improvedResult;
    }

    public ArrayList<Integer> getShakedNodes() {
        return shakedNodes;
    }

    public void setShakedNodes(ArrayList<Integer> shakedNodes) {
        this.shakedNodes = shakedNodes;
    }

    public double getShakedResult() {
        return shakedResult;
    }

    public void setShakedResult(double shakedResult) {
        this.shakedResult = shakedResult;
    }

    public ArrayList<Integer> getImprovedShakedNodes() {
        return improvedShakedNodes;
    }

    public void setImprovedShakedNodes(ArrayList<Integer> improvedShakedNodes) {
        this.improvedShakedNodes = improvedShakedNodes;
    }

    public double getImprovedShakedResult() {
        return improvedShakedResult;
    }

    public void setImprovedShakedResult(double improvedShakedResult) {
        this.improvedShakedResult = improvedShakedResult;
    }

    public String getImprovedShakedTime() {
        return improvedShakedTime;
    }

    public void setImprovedShakedTime(String improvedShakedTime) {
        this.improvedShakedTime = improvedShakedTime;
    }

    public ArrayList<Integer> getFinalNodes() {
        return finalNodes;
    }

    public void setFinalNodes(ArrayList<Integer> finalNodes) {
        this.finalNodes = finalNodes;
    }

    public double getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(double finalResult) {
        this.finalResult = finalResult;
    }

    public String getRandomSearchTime() {
        return randomSearchTime;
    }

    public void setRandomSearchTime(String randomSearchTime) {
        this.randomSearchTime = randomSearchTime;
    }
    
}
