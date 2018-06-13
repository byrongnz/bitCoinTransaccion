/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobitcoin;

/**
 *
 * @author byron
 */
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.HashMap;

public class Wallet {
	
    public PrivateKey privateKey;
    public PublicKey publicKey;
    public float saldo = 10;
    
    public Wallet() {
        generaLlaves();
    }

    public void generaLlaves() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom rndm = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            kpg.initialize(ecSpec, rndm); 
            KeyPair kp = kpg.generateKeyPair();
            privateKey = kp.getPrivate();
            publicKey = kp.getPublic();
            System.out.println("Llave privada: "+privateKey);
            System.out.println("Llave publica: "+publicKey);
        }catch(Exception e) {
                throw new RuntimeException(e);
        }
    }
    
    public Transaccion enviaMonto(PublicKey pkVendedor,float monto ) {
        Transaccion nuevaT = new Transaccion(getPublicKey(), pkVendedor , monto);
        nuevaT.generaFirma(getPrivateKey());

        return nuevaT;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    
    
    
}