package kayquemarques.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import kayquemarques.model.Espaco;
import kayquemarques.model.Auditorio;
import kayquemarques.model.CabineIndividual;
import kayquemarques.model.SalaDeReuniao;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class EspacoDAOJSON {

    private static final String ARQUIVO = "espacos.json";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Espaco.class, new EspacoTypeAdapter())
            .create();

    public void salvar(List<Espaco> espacos) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(espacos, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Espaco> carregar() {
        try (Reader reader = new FileReader(ARQUIVO)) {
            return gson.fromJson(reader, new TypeToken<List<Espaco>>() {
            }.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void adicionar(Espaco espaco) {
        List<Espaco> espacos = carregar();
        espacos.add(espaco);
        salvar(espacos);
    }

    public void removerPorId(int id) {
        List<Espaco> espacos = carregar();
        espacos.removeIf(e -> e.getId() == id);
        salvar(espacos);
    }

    public Espaco buscarPorId(int id) {
        for (Espaco e : carregar()) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public List<Espaco> listarTodos() {
        return carregar();
    }

}















