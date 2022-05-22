/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BancoDTO;
import DTO.DepositarDTO;
import DTO.SacarDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class BancoDAO {
    Connection conn;
    PreparedStatement pstm;
    String sql = null;

    public BancoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void cadastrarCliente(String nome, int cpf){
        sql = "insert into cliente (nome, cpf) values (?,?)";
        conn = new ConexaoDAO().conectarDAO();
        try {
            pstm = conn.prepareCall(sql);
            pstm.setString(1, nome);
            pstm.setInt(2, cpf);
            pstm.execute();
            pstm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Cadastrar Cliente " +  e);
        }
    }
    public void cadastrarGerente(String nome){
        sql = "insert into Gerente (nome) values (?)";
        conn = new ConexaoDAO().conectarDAO();
        try {
            pstm = conn.prepareCall(sql);
            pstm.setString(1, nome);
            pstm.execute();
            pstm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Cadastrar Cliente " +  e);
        }
    }
    public void cadastrarConta(int tp, int idG, int idC, int nr, double saldo, boolean ativo, String dt){
        sql = "insert into ? (idgerente, idcliente, numero, saldo, ativo, dtrendimento) values (?,?,?,?,?,?,?)";
        conn = new ConexaoDAO().conectarDAO();
        try {
            pstm = conn.prepareCall(sql);
            pstm.setInt(1, tp);
            pstm.setInt(2, idG);
            pstm.setInt(3, idC);
            pstm.setInt(4, nr);
            pstm.setDouble(5, saldo);
            pstm.setBoolean(6, ativo);
            pstm.setString(7, dt);
            pstm.execute();
            pstm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Cadastrar Cliente " +  e);
        }
    }
    
    public ArrayList<BancoDTO> listar(){
        String comandoSQL = "Select * from banco";
        conn = new ConexaoDAO().conectarDAO();
        
        
        ArrayList<BancoDTO> lista = new ArrayList();
        try {
            pstm = conn.prepareStatement(comandoSQL);
           
            ResultSet rs;
            
            rs = pstm.executeQuery(comandoSQL);
           
           // JOptionPane.showMessageDialog(null, rs.getArray(0));
            while(rs.next()){
                BancoDTO objBancoDTO = new BancoDTO();
                objBancoDTO.setIdcliente(rs.getInt("id"));
                objBancoDTO.setNumero(rs.getInt("numero"));
                objBancoDTO.setSaldo(rs.getInt("saldo"));
                objBancoDTO.setAtivo(rs.getBoolean("ativo"));
                objBancoDTO.setDtrendimento(rs.getString("Rendimento"));

                lista.add(objBancoDTO);
            }
            
    
        } catch (Exception e) {
        }
        return lista;
    } 
    
    public void SacarDAO(int saldo){
        String comandoSQL = "update poupanca set saldo = saldo-? where saldo = ?;";
        conn = new ConexaoDAO().conectarDAO();
        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setInt(1, saldo);
            pstm.execute();
            pstm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SacarDAO "+ e);
        }   
    }
    
    public void DepositarDAO(int saldo){
        String comandoSQL = "update poupanca set saldo = saldo+? where saldo = ?;";
        conn = new ConexaoDAO().conectarDAO();
        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setInt(1, saldo);
            pstm.execute();
            pstm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DepositarDAO "+ e);
        }   
    }
    
    public BancoDTO carregarDados(int idcliente){
        String comandoSQL = "Select * from poupanca "
                        + "where poupanca.saldo = ?";
        conn = new ConexaoDAO().conectarDAO();
        BancoDTO objBancoDTO = new BancoDTO();
        
        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setInt(1,idcliente);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                objBancoDTO.setSaldo(rs.getInt("saldo"));                         
            }
            else{
                JOptionPane.showMessageDialog(null, "Id não encontrado!");
            }
            pstm.close();
                       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Carregar Saldo " + e);
        }
        return objBancoDTO;
    }
    
    public void SacarDAO(SacarDTO objSacarDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public BancoDAO(BancoDTO objBancoDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void cadastrarBanco(BancoDTO objBancoDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void DepositarDAO(DepositarDTO objDepositarDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
