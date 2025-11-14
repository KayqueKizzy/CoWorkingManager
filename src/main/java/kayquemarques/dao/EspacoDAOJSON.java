package kayquemarques.dao;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import kayquemarques.dao.interfaces.EspacoDAO;
import kayquemarques.model.Espaco;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EspacoDAOJSON implements EspacoDAO {

    private static final String ARQUIVO = "espacos.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Espaco.class, new EspacoTypeAdapter())
            .create();

    @Override
    public void salvar(Espaco espaco) {
        List<Espaco> lista = buscarTodos();
        lista.removeIf(e -> e.getId() == espaco.getId());
        lista.add(espaco);
        salvarTodos(lista);
    }

    @Override
    public void salvarTodos(List<Espaco> lista) {
        try (Writer w = new FileWriter(ARQUIVO)) {
            gson.toJson(lista, w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Espaco buscarPorId(int id) {
        return buscarTodos().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Espaco> buscarTodos() {
        try (Reader r = new FileReader(ARQUIVO)) {
            List<Espaco> lista = gson.fromJson(r, new TypeToken<List<Espaco>>(){}.getType());
            return lista != null ? lista : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void remover(int id) {
        List<Espaco> lista = buscarTodos();
        lista.removeIf(e -> e.getId() == id);
        salvarTodos(lista);
    }
}