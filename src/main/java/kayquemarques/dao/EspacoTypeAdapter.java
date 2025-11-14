package kayquemarques.dao;

import com.google.gson.*;
import kayquemarques.model.*;

import java.lang.reflect.Type;

public class EspacoTypeAdapter implements JsonSerializer<Espaco>, JsonDeserializer<Espaco> {

    @Override
    public JsonElement serialize(Espaco espaco, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        obj.addProperty("id", espaco.getId());
        obj.addProperty("nome", espaco.getNome());
        obj.addProperty("capacidade", espaco.getCapacidade());
        obj.addProperty("disponivel", espaco.isDisponivel());
        obj.addProperty("precoPorHora", espaco.getPrecoPorHora());
        obj.addProperty("tipo", espaco.getClass().getSimpleName());

        if (espaco instanceof SalaDeReuniao s) {
            obj.addProperty("usaProjetor", s.isUsaProjetor());
        } else if (espaco instanceof Auditorio a) {
            obj.addProperty("temPalco", a.isTemPalco());
            obj.addProperty("capacidadeExtra", a.getCapacidadeExtra());
        }

        return obj;
    }

    @Override
    public Espaco deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();
        String tipo = obj.get("tipo").getAsString();

        switch (tipo) {
            case "SalaDeReuniao":
                return context.deserialize(json, SalaDeReuniao.class);
            case "CabineIndividual":
                return context.deserialize(json, CabineIndividual.class);
            case "Auditorio":
                return context.deserialize(json, Auditorio.class);
            default:
                throw new JsonParseException("Tipo desconhecido: " + tipo);
        }
    }
}