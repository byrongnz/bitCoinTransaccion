/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobitcoin;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

/**
 *
 * @author byron
 */
public class Transaccion {
    public String transactionId; 
    public PublicKey dirEnvia; 
    public PublicKey dirRecibe; 
    public float monto; 
    public byte[] firma; 
    
    public Transaccion(PublicKey pkEnvia, PublicKey pkRecibe, float monto) {
        this.dirEnvia = pkEnvia;
        this.dirRecibe = pkRecibe;
        this.monto = monto;
    }
    
    public boolean ejecutaTransaccion(Wallet comprador, Wallet vendedor) {
	System.out.println("Ejecuta la transaccion ...");
        if(validaFirma()== false) {
            System.out.println("ERROR: La firma no coincide...");
            return false;
        }else{
            comprador.setSaldo(comprador.getSaldo()-monto);
            vendedor.setSaldo(vendedor.getSaldo()+monto);
            return true;
        }
    }
    
    public void generaFirma(PrivateKey privateKey) {
        String str = getStringKey(dirEnvia) + getStringKey(dirRecibe) + Float.toString(monto)	;
        firma = firmaECDSA(privateKey,str);		
    }
	
    public boolean validaFirma() {
        String str = getStringKey(dirEnvia) + getStringKey(dirRecibe) + Float.toString(monto)	;
        return verificaECDSA(dirEnvia, str, firma);
    }
    
    public static String getStringKey(Key key) {
	return Base64.getEncoder().encodeToString(key.getEncoded());
    }
        
    public static byte[] firmaECDSA(PrivateKey privateKey, String str) {
        System.out.println("Genera firma ECDSA ...");
        Signature firmaEcdsa;
        byte[] output = new byte[0];
        try {
            firmaEcdsa = Signature.getInstance("ECDSA", "BC");
            firmaEcdsa.initSign(privateKey);
            byte[] strByte = str.getBytes();
            firmaEcdsa.update(strByte);
            byte[] realSig = firmaEcdsa.sign();
            output = realSig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }
    
    public static boolean verificaECDSA(PublicKey publicKey, String info, byte[] firma) {
        System.out.println("Verifica firma ECDSA ...");
        try {
            Signature ecda = Signature.getInstance("ECDSA", "BC");
            ecda.initVerify(publicKey);
            ecda.update(info.getBytes());
            return ecda.verify(firma);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
