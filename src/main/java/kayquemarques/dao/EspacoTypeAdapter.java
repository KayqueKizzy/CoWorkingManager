package kayquemarques.dao;

import com.google.gson.*;
import kayquemarques.model.*;

import java.lang.reflect.Type;

public class EspacoTypeAdapter implements JsonSerializer<Espaco>, JsonDeserializer<Espaco> {

    @Override
    public JsonElement serialize(Espaco espaco, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = context.serialize(espaco).getAsJsonObject();
        obj.addProperty("tipo", espaco.getClass().getSimpleName());
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

