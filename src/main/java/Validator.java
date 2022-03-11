import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class Validator {

    public static void isValid(String schemaJson, String jsonObject)throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        // Loads the schema from the address.schema.json file
        JsonNode schemaNode = mapper.readTree(new FileReader(schemaJson));

        // Loads the object from the address.json file
        JsonNode validNode = mapper.readTree(new FileReader(jsonObject));

        // JsonNode validNode = mapper.readTree(new FileReader("address.invalid.json"));
        // Compiling the schema in version 7
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = factory.getSchema(schemaNode);

        // Running the validation check
        Set<ValidationMessage> errors = schema.validate(validNode);

        if(errors.size()!=0){
            System.out.println("Invalid JSON object!");
            errors.forEach(System.out::println);
        }
    }
}
