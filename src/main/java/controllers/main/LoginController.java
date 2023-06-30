package controllers.main;
import view.main.LoginView;
import controllers.UsuarioLogadoSingleton;
import types.Caixa;
import types.Colaborador;
import types.Fornecedor;
import types.Gerente;
import controllers.ControllerInterface;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import models.*;
/**
 *
 * @author DanIsa
 */
public class LoginController implements ControllerInterface{
private LoginView loginView;

    public LoginController() {
        this.loginView = new LoginView();
        this.loginView.getEntrarBotao().addActionListener((ActionEvent e) -> {
            this.logarUsuario();
        });
   
    }
    
    public void logarUsuario(){
        ColaboradoresModel modelColaboradores = new ColaboradoresModel();
        String usuario = this.loginView.getUsuarioField().getText();
        String senha = this.loginView.getSenhaField().getText();
        try{
            Colaborador c = modelColaboradores.GetColaboradorByLogin(usuario, senha);
            if(c.isDesativado()){
                JOptionPane.showMessageDialog(this.loginView, "Este colaborador está desativado da plataforma. Entre em contato com algum gerente DanIsa ou com o suporte técnico Se achar que isto é um problema.");
                return;
            }
            UsuarioLogadoSingleton.setInstance(c);
            this.loginView.setVisible(false);
            this.loginView.dispose();
            
            if(c instanceof Gerente){
                GerenteController gerenteController = new GerenteController();
                gerenteController.run();
            }
            else if(c instanceof Caixa){
                CaixaController caixaController = new CaixaController();
                caixaController.run();
            }
            else if(c instanceof Fornecedor){
                FornecedorController fornecedorController = new FornecedorController();
                fornecedorController.run();
            }
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(this.loginView, "Login inválido! Certifique-se de que todas as informações foram passadas corretamente.");
        }
    }
    
    public void run(){
        this.loginView.setVisible(true);
        
    }

    

}
