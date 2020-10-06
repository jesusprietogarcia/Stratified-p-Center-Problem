package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static src.Algorithm.PERCENTAGESEARCH;


/**
 *
 * @author Jesús
 */

public class Main {
    
    static DecimalFormat df = new DecimalFormat("#.##");
    static ArrayList<Instance> instances;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        instances = new ArrayList<>();
        fill();
        generateExcel(generateResults());
        
    }
    
    public static void fill(){
        
        //INPUT ITERATING OVER 40 DIFFERENT INSTANCES
        
        for(int i = 1; i < 41; i++){
            String ruta = "./files/pmed" + i + "_esce.txt";
            try{
                Instance instAux = new Instance();
                instAux.fill(ruta, instAux);
                instAux.setName("Instance " + i);
                instances.add(instAux);
            }
            catch(IOException e){
                System.err.println(e.toString());
            }
            
        }
    }
    
    public static Map<String, Object[]> generateResults(){
        Map<String, Object[]> collection = new TreeMap<>();
        collection.put("1", new Object[] {"INSTANCE", "N", "P", "RESULT", "TIME", "IMPROVED RESULT", "IMPROVING TIME", 
            "% SEARCH", "FINAL RESULT", "RANDOM SEARCH TIME"});
        int counter = 1;
        for(Instance instance : instances){
            counter++;
            Algorithm algorithm = new Algorithm(instance);
            Data data = algorithm.getData();
            collection.put(Integer.toString(counter),new Object[] {instance.getName(), instance.getN(), instance.getP(), 
                df.format(data.getResult()), data.getTime(),
                df.format(data.getImprovedResult()), data.getImprovedTime(),
                df.format(PERCENTAGESEARCH), df.format(data.getFinalResult()), data.getRandomSearchTime()});
        }
        return collection;
    }
    
    public static void generateExcel(Map<String, Object[]> result){
        XSSFWorkbook wb = new XSSFWorkbook(); 
        XSSFSheet sh = wb.createSheet("SpC Problem - Results");
        Set<String> keyset = result.keySet();
        int rowcounter = 0;
        for (String key : keyset){
            Row row = sh.createRow(rowcounter++);
            Object[] array = result.get(key);
            int cellnum = 0;
            for (Object object : array)
            {
               Cell cell = row.createCell(cellnum++);
               if(object instanceof String)
                    cell.setCellValue((String)object);
                else if(object instanceof Integer)
                    cell.setCellValue((Integer)object);
            }
        }
        try{
            FileOutputStream output = new FileOutputStream(new File("output.xlsx"));
            wb.write(output);
            output.close();
            System.out.println("EXCEL FILE CREATED, OUTPUT RESULTS FINISHED!");
        } 
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
}
