package fatec.poo.model;

public class Pessoa {
    
    private String cpf;
    private String nome;
    private String endereco;
    private String cidade;
    private String uf;
    private String cep;
    private String ddd;
    private String telefone;

    public Pessoa(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    public String getDdd() {
        return ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public static boolean validarCpf(String cpf) {
        // Elimina sequencias diferente de 11 digitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Elimina os números com mesma sequencia
        int aux = cpf.charAt(0);
        boolean isEqual = true;
        
        for (int i = 1; i < cpf.length(); i++) {
            if (aux != cpf.charAt(i)) {
                isEqual = false;
                break;
            }
        }
        
        if (isEqual) {
            return false;
        }
        
        // https://www.devmedia.com.br/validar-cpf-com-javascript/23916
        // Cálculo do 1º Dígito Verificador
        int peso = 10;
        int soma = 0;
        
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (peso);
            peso--;
        }
        
        soma = (soma * 10) % 11;
        int dig1 = (soma >= 10) ? 0 : soma;
        
        if (dig1 != cpf.charAt(9) - '0') {
            return false;
        }
        
        // Cálculo do 2º Dígito Verificador
        peso = 11;
        soma = 0;
        
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * peso;
            peso--;
        }

        soma = (soma * 10) % 11;
        int dig2 = (soma >= 10) ? 0 : soma;

        if (dig2 != cpf.charAt(10) - '0') {
            return false;
        }
        
        return true;
    }
}
