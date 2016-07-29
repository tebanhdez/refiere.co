package co.refiere.services.qrcode;

import java.util.Random;

public class QRCodeService {
    
    public static String generateQRCode() {
        String cadena = getCadenaAlfanumAleatoria(6);
        return cadena;
    }
    
    static String getCadenaAlfanumAleatoria (int longitud){
            String cadenaAleatoria = "";
            long milis = new java.util.GregorianCalendar().getTimeInMillis();
            Random r = new Random(milis);
            int i = 0;
            while ( i < longitud){
                char c = (char)r.nextInt(255);
                if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
                    cadenaAleatoria += c;
                    i ++;
                }
            }
            return cadenaAleatoria;
        }
}
