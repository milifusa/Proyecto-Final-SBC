/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proeyctordf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import jxl.*;
import jxl.read.biff.BiffException;


/**
 *
 * @author LizzBet
 */
public class ProeyctoRdf {

    
    public static void main(String[] args) throws IOException, BiffException {
        Workbook workbook = Workbook.getWorkbook(new File("DatosCapturados.xls"));
        Sheet sheet = workbook.getSheet(0);
        //Clases
        String Measure="http://def.seegrid.csiro.au/isotc211/iso19156/2011/observation#";
        String City = "http://schema.org/City";
        String TimeInterval = "http://www.w3.org/2006/time#";
        String Standar = "http://utpl.edu.ec/sbc2015/proyectoFinal/standar#";

        //Propiedades
        String degrees="http://utpl.edu.ec/sbc2015/proyectoFinal/standar#degrees";
        String scheduleService="http://vocab.org/transit/terms/scheduleService";
        String capacity="http://utpl.edu.ec/sbc2015/proyectoFinal/standar#establishedCapacity";
        String identifier="http://purl.org/essglobal/vocab/identifier";
        String year="http://www.w3.org/2006/time#years";
        String month="http://www.w3.org/2006/time#month";
        String day="http://www.w3.org/2006/time#day";
        String hour="http://www.w3.org/2006/time#hours";
        String minutes="http://www.w3.org/2006/time#minutes";
        String area= "http://www.w3.org/2002/07/owl#area";
        String StablishedCapacity= "http://utpl.edu.ec/sbc2015/proyectoFinal/standar#StablishedCapacity";
        String stablishedTemperature="http://utpl.edu.ec/sbc2015/proyectoFinal/standar#stablishedTemperature";
        String latitude="http://schema.org/geo#lat";
        String longi="http://schema.org/geo#longi";

        
        Model model = ModelFactory.createDefaultModel();
        Property degreesProperty=ResourceFactory.createProperty(degrees);
        Property scheduleServiceProperty=ResourceFactory.createProperty(scheduleService);
        Property capacityProperty = ResourceFactory.createProperty(capacity);
        Property identifierProperty = ResourceFactory.createProperty(identifier);
        Property yearProperty = ResourceFactory.createProperty(year);
        Property monthProperty = ResourceFactory.createProperty(month);
        Property dayProperty = ResourceFactory.createProperty(day);
        Property hourProperty=ResourceFactory.createProperty(hour);
        Property minutesProperty=ResourceFactory.createProperty(minutes);
        Property areaProperty = ResourceFactory.createProperty(area);
        Property StablishedCapacityProperty = ResourceFactory.createProperty(StablishedCapacity);
        Property stablishedTemperatureProperty = ResourceFactory.createProperty(stablishedTemperature);
        Property latitudeProperty=ResourceFactory.createProperty(latitude);
        Property longiProperty=ResourceFactory.createProperty(longi);
        
        String temp="",schs = "", capa = "", identi = "", año = "", mes = "", 
                dia = "", hora = "", minu="",areas="",stacap="",statemp="",
                lat="",longit="";
        for (int fila = 1; fila < sheet.getRows(); fila++) {
          

            temp = sheet.getCell(0, fila).getContents();// temperaturaa
            schs = sheet.getCell(1, fila).getContents(); //ubicacion
            capa = sheet.getCell(2, fila).getContents(); //id sala
            identi = sheet.getCell(3, fila).getContents(); //fecha de recoleccion
            año = sheet.getCell(4, fila).getContents(); //hora recoleccion
            mes = sheet.getCell(5, fila).getContents(); //horario
            dia = sheet.getCell(6, fila).getContents(); //capacidad
            hora = sheet.getCell(7, fila).getContents(); //id sala
            minu = sheet.getCell(8, fila).getContents(); //fecha de recoleccion
            areas= sheet.getCell(9, fila).getContents(); //hora recoleccion
            stacap = sheet.getCell(10, fila).getContents(); //horario
            statemp = sheet.getCell(11, fila).getContents(); //capacidad
            lat = sheet.getCell(12, fila).getContents();
            longit = sheet.getCell(13, fila).getContents();

         
            Resource measure1 = model.createResource(Measure)   
                    .addProperty(degreesProperty, temp)
                    .addProperty(scheduleServiceProperty, schs)
                    .addProperty(capacityProperty, capa)
                    .addProperty(identifierProperty, identi)
                    .addProperty(RDFS.comment, "Esta clase detalla los datos principales de los datos capturados");
            
            Resource Citys=model.createResource(City)
                    .addProperty(longiProperty, longit)
                    .addProperty(latitudeProperty, lat)
                    .addProperty(RDFS.comment, "Clase que informa de la ubicacion del lugar donde se obtuvo los datos");

            Resource intervaltime=model.createResource(TimeInterval)
                    .addProperty(yearProperty, año)
                    .addProperty(monthProperty, mes)
                    .addProperty(dayProperty, dia)
                    .addProperty(hourProperty, hora)
                    .addProperty(minutesProperty, minu)
                    .addProperty(RDFS.comment, "Clase que informa acerca del dia y la hroa  en la cual se recogio los datos");
            
            Resource standar1 = model.createResource(Standar)
                    .addProperty(areaProperty, areas )
                    .addProperty(StablishedCapacityProperty, stacap)
                    .addProperty(stablishedTemperatureProperty, statemp)
                    .addProperty(RDFS.comment, "Clase que indica  cuales son los valores establecidos para el correcto uso de una sala, ara, temperatura");
                  //model.write(System.out);
            model.write(System.out, "RDF/XML");
         String aux= model.write(System.out)+"";
         PrintWriter writer = new PrintWriter("Temperatura.rdf", "UTF-8");
         writer.println(aux);
         writer.close();
           
        }
        
         
        

    }
    
}
