
package Loja;

import java.util.ArrayList;
import java.util.List;

public class Autenticador {
    private static List<Usuario> usuarios = new ArrayList<>();

    // Bloco estático para adicionar usuários ao iniciar
    static {
        usuarios.add(new Usuario("admin", "1234"));
        usuarios.add(new Usuario("joao", "abcd"));
        usuarios.add(new Usuario("maria", "senha123"));
    }

    public static boolean autenticar(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}