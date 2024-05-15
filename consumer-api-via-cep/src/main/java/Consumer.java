import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Consumer {
	public static void main(String[] args) throws IOException {
        // URL da API com CEP específico
        String url = "https://viacep.com.br/ws/01001000/json/";

        // Cria a conexão HTTP
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        // Define o método da requisição (GET)
        connection.setRequestMethod("GET");

        // Envia a requisição e obtém a resposta
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Converte a resposta JSON em objeto Java
        ObjectMapper mapper = new ObjectMapper();
        Endereco endereco = mapper.readValue(response.toString(), Endereco.class);

        // Exibe as informações do endereço
        System.out.println("CEP: " + endereco.getCep());
        System.out.println("Logradouro: " + endereco.getLogradouro());
        System.out.println("Complemento: " + endereco.getComplemento());
        System.out.println("Bairro: " + endereco.getBairro());
        System.out.println("Cidade: " + endereco.getLocalidade());
        System.out.println("UF: " + endereco.getUf());
    }
}
