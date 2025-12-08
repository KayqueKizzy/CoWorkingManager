package kayquemarques.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import kayquemarques.dao.interfaces.ReservaDAO;
import kayquemarques.model.Reserva;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOJSON implements ReservaDAO {

    private static final String ARQUIVO = "reservas.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();


    @Override
    public void salvar(Reserva reserva) {
        List<Reserva> lista = buscarTodos();
        lista.removeIf(r -> r.getId() == reserva.getId());
        lista.add(reserva);
        salvarTodos(lista);
    }

    @Override
    public void salvarTodos(List<Reserva> lista) {
        try (Writer w = new FileWriter(ARQUIVO)) {
            gson.toJson(lista, w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reserva buscarPorId(int id) {
        return buscarTodos().stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Reserva> buscarTodos() {
        try (Reader r = new FileReader(ARQUIVO)) {
            List<Reserva> lista = gson.fromJson(r, new TypeToken<List<Reserva>>(){}.getType());
            return lista != null ? lista : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void remover(int id) {
        List<Reserva> lista = buscarTodos();
        lista.removeIf(r -> r.getId() == id);
        salvarTodos(lista);
    }
}