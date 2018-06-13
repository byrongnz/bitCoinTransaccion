/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobitcoin;
import java.security.Security;
/**
 *
 * @author byron
 */
public class EjecutaTransaccion {
    public static void main(String[] args) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());  
        System.out.println("Llaves del comprador: \n");
        Wallet comprador = new Wallet();
        System.out.println("*********************");
        System.out.println("Llaves del vendedor: \n");
        Wallet vendedor = new Wallet();
        float monto = 0.5f;
        System.out.println("*********************");
        System.out.println("Saldo del vendedor:"+vendedor.getSaldo());
        System.out.println("Saldo del comprador:"+comprador.getSaldo());
        System.out.println("*********************");
        System.out.println("Monto de la compra:"+monto);
        Transaccion transaccion = comprador.enviaMonto(vendedor.getPublicKey(), monto);
        if(transaccion.ejecutaTransaccion(comprador,vendedor)){
            System.out.println("La transaccion se realizo correctamente...");
        }else{
            System.out.println("NO se pudo realizar la operacion");
        }
        System.out.println("*********************");
        System.out.println("Saldo FINAL del comprador:"+comprador.getSaldo());
        System.out.println("Saldo FINAL del vendedor:"+vendedor.getSaldo());
        
    }
}
