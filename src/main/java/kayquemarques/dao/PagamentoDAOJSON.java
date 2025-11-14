package kayquemarques.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import kayquemarques.dao.interfaces.PagamentoDAO;
import kayquemarques.model.Pagamento;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAOJSON implements PagamentoDAO {

    private static final String ARQUIVO = "pagamentos.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public void salvar(Pagamento pagamento) {
        List<Pagamento> lista = buscarTodos();
        lista.removeIf(p -> p.getId() == pagamento.getId());
        lista.add(pagamento);
        salvarTodos(lista);
    }

    @Override
    public void salvarTodos(List<Pagamento> lista) {
        try (Writer w = new FileWriter(ARQUIVO)) {
            gson.toJson(lista, w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pagamento buscarPorId(int id) {
        return buscarTodos().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Pagamento> buscarTodos() {
        try (Reader r = new FileReader(ARQUIVO)) {
            List<Pagamento> lista = gson.fromJson(r, new TypeToken<List<Pagamento>>(){}.getType());
            return lista != null ? lista : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void remover(int id) {
        List<Pagamento> lista = buscarTodos();
        lista.removeIf(p -> p.getId() == id);
        salvarTodos(lista);
    }
}