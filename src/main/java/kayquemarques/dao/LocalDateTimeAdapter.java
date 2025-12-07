package kayquemarques.dao;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    // Define o formato que será usado na string JSON
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    // CONVERTE de LocalDateTime (objeto Java) PARA JSON (string)
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.format(formatter)); // Escreve como uma string formatada
        }
    }

    @Override
    // CONVERTE de JSON (string) PARA LocalDateTime (objeto Java)
    public LocalDateTime read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        } else {
            String dateString = in.nextString(); // Lê a string
            return LocalDateTime.parse(dateString, formatter); // Converte de volta
        }
    }
}
