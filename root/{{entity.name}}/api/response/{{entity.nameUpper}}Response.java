package {{path}}.{{entity.name}}.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@ApiModel(value = "{{entity.nameUpper}}Response", description = "Model {{entity.name}}Response")

public class {{entity.nameUpper}}Response {
    {{#entityProperties}}
        protected {{type}} {{name}};
    {{/entityProperties}}
}
