package files.modelo;

 //Cambiar por el paquete y clase del proyecto
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class CSVReader {
	
    private ConexionBase con ;  //Cambiat por la clase del proyecto
    private File csvFile ;  //Cambiar por la ruta del archivo
    private BufferedReader br = null;
    private String line = "";
    private String csvSplitBy = ",";
    
    public CSVReader(ConexionBase con) {
    	this.con = con;
    }

    public void CSVReaderMedida (File urlFile) throws IOException, SQLException {
    	this.csvFile = urlFile;
    	br = new BufferedReader(new FileReader(csvFile));
    	while((line=br.readLine())!=null){
    		String[] medida = line.split(csvSplitBy);
    		con.guardar("INSERT INTO medida (idcliente, idusuario,fechamedida, medida, idActivo) VALUES ("+
    		medida[0]+", "+medida[1]+", "+medida[2]+", "+medida[3]+", "+medida[4]+");");
    		}
    	if (br!=null){
    		try{
    			br.close();
    			}catch (IOException e){
    				e.printStackTrace();
    				}
    		}
    	}
        
    public void CSVReaderPago (File urlFile) throws IOException, SQLException  {
        
        	this.csvFile = urlFile;
            br = new BufferedReader(new FileReader(csvFile));
            while((line=br.readLine())!=null){
                String[] medida = line.split(csvSplitBy);
                con.guardar("INSERT INTO RegistroPago (idusuario,idFactura, pago, fechaPago) VALUES ("+
                medida[0]+", "+medida[1]+", "+medida[2]+", "+medida[3]+");");
                }
       
            if (br!=null){
                try{
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

