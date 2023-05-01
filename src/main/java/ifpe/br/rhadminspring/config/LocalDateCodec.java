package ifpe.br.rhadminspring.config;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateCodec implements Codec<LocalDate> {

    @Override
    public void encode(BsonWriter writer, LocalDate value, EncoderContext encoderContext) {
        writer.writeString(value.toString());
    }

    @Override
    public LocalDate decode(BsonReader reader, DecoderContext decoderContext) {
        String dateStr = reader.readString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateStr, formatter);
    }

    @Override
    public Class<LocalDate> getEncoderClass() {
        return LocalDate.class;
    }
}

