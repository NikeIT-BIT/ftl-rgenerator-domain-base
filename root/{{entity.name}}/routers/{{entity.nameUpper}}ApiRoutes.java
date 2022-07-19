package {{path}}.{{entity.name}}.routers;

import {{path}}.base.routers.BaseApiRouters;

public class {{entity.nameUpper}}ApiRoutes {
    public static final String ROOT = BaseApiRouters.V1 + "/{{entity.restApiUrl}}";
    public static final String BY_ID = ROOT + "/{id}";

}
