package {{path}}.{{entity.name}}.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@ApiModel(value = "{{entity.nameUpper}}Request", description = "Model {{entity.name}} request")

public class {{entity.nameUpper}}Request {
    {{#entityProperties}}
        {{level}} {{type}} {{name}};
    {{/entityProperties}}
}
