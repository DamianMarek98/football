package deny.football.data.config;

import org.bson.types.Decimal128;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.math.BigDecimal;

//@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    public String getDatabaseName() {
        return "transfermarkt-players";
    }

    @Override
    protected void configureConverters(MongoCustomConversions.MongoConverterConfigurationAdapter adapter) {
        adapter.registerConverter((Converter<Decimal128, BigDecimal>) Decimal128::bigDecimalValue);
        adapter.registerConverter((Converter<BigDecimal, Decimal128>) Decimal128::new);
    }
}
